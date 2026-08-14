package com.example.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.order.DTO.*;
import com.example.order.enums.Type;
import com.example.order.webclient.NotificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.entity.Orders;
import com.example.order.entity.OrderItems;
import com.example.order.enums.Status;
import com.example.order.repository.OrderItemsRepository;
import com.example.order.repository.OrderRepository;
import com.example.order.security.JwtService;
import com.example.order.webclient.ProductClient;

@Service
public class OrderService {

    @Autowired
    OrderRepository repo;
    @Autowired
    OrderItemsRepository itemRepo;
    @Autowired
    JwtService jwtService;
    @Autowired
    ProductClient productClient;
    @Autowired
    NotificationClient notificationClient;


    public void checkRoleUser(String token) {
        List<String> role = jwtService.extractRole(token.substring(7));
        if (!role.contains("customer")) {
            throw new RuntimeException("only customer can place order");
        }
    }

    public void checkRoleAdmin(String token) {
        List<String> role = jwtService.extractRole(token.substring(7));
        if (!role.contains("admin")) {
            throw new RuntimeException("only customer can place order");
        }
    }


    public ResponseDTO placeOrder(OrderRequestDTO dto, String token) {

        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        checkRoleUser(token);

        Orders orders = new Orders();
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItems> orderItemEntity = new ArrayList<>();

        List<OrderItemRequestDTO> orderItemsList = dto.getOrderItemsList();

        for (OrderItemRequestDTO i : orderItemsList) {
            ProductResponseDTO product = productClient.getProduct(i.getProductId());

            BigDecimal itemPrice = product.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()));
            totalAmount = totalAmount.add(itemPrice);

            OrderItems orderItems = new OrderItems();
            orderItems.setOrder(orders);
            orderItems.setPrice(product.getPrice());
            orderItems.setProductId(i.getProductId());
            orderItems.setQuantity(i.getQuantity());

            orderItemEntity.add(orderItems);

        }

        orders.setOrderItems(orderItemEntity);
        orders.setOrderStatus(Status.SHIPPED);
        orders.setTotalAmount(totalAmount);
        orders.setUserId(referenceId);

        Orders save = repo.save(orders);

        NotificationInternalrequestDTO notification = new NotificationInternalrequestDTO();
        notification.setUserId(referenceId);
        notification.setType(Type.EMAIL);
        notification.setMessage("order placed successfully");
        notification.setEmail(email);
        notification.setOrderId(save.getId());
        notification.setTotalAmount(save.getTotalAmount());
        notificationClient.addNotification(notification);

        return new ResponseDTO(save.getId(), save.getOrderStatus(), save.getTotalAmount(), save.getCReatedAt());

    }


    // all order
    public List<ResponseDTO> getAllOrders(String token) {
        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));

        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        List<Orders> list = repo.findByUserId(referenceId);

        List<ResponseDTO> listDTO = new ArrayList<>();

        for (Orders i : list) {
            listDTO.add(new ResponseDTO(i.getId(), i.getOrderStatus(), i.getTotalAmount(), i.getCReatedAt()));
        }
        return listDTO;
    }


    //detail order(by sending order id)

    public DetailOrderResponse getOrderDetails(String token, Long id) {

        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        Orders orders = repo.findById(id).orElseThrow(() -> new RuntimeException());

        if (!orders.getUserId().equals(referenceId)) {
            throw new RuntimeException("Unauthorized");
        }

        List<OrderItemResponseDTO> orderItems = new ArrayList<>();

        List<OrderItems> itemList = orders.getOrderItems();

        for (OrderItems i : itemList) {
            ProductResponseDTO product = productClient.getProduct(i.getProductId());

            OrderItemResponseDTO itemDTO = new OrderItemResponseDTO(product.getId(), product.getProductName(), product.getPrice(), i.getQuantity());
            orderItems.add(itemDTO);
        }

        return new DetailOrderResponse(orders.getId(), orders.getOrderStatus(), orders.getTotalAmount(), orders.getCReatedAt(), orderItems);


    }


    public ResponseDTO updateOrder(String token, OrderRequestDTO dto, Long id) {

        checkRoleUser(token);

        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        Orders orders = repo.findById(id).orElseThrow(() -> new RuntimeException());

        if (!orders.getUserId().equals(referenceId)) {
            throw new RuntimeException("Unauthorized");
        }

        orders.getOrderItems().clear();

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItemRequestDTO> orderItemList = dto.getOrderItemsList();

        for (OrderItemRequestDTO i : orderItemList) {
            ProductResponseDTO product = productClient.getProduct(i.getProductId());
            BigDecimal itemPrice = product.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()));
            totalAmount = totalAmount.add(itemPrice);

            OrderItems orderItems = new OrderItems();

            orderItems.setOrder(orders);
            orderItems.setPrice(product.getPrice());
            orderItems.setProductId(i.getProductId());
            orderItems.setQuantity(i.getQuantity());
            orders.getOrderItems().add(orderItems);
        }

        orders.setTotalAmount(totalAmount);
        Orders updated = repo.save(orders);
        return new ResponseDTO(updated.getId(), updated.getOrderStatus(), updated.getTotalAmount(), updated.getCReatedAt());
    }


    public ResponseDTO cancelOrder(String token, Long id) {

        checkRoleUser(token);
        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));


        Orders orders = repo.findById(id).orElseThrow(() -> new RuntimeException("order not found"));

        if (!orders.getUserId().equals(referenceId)) {
            throw new RuntimeException("Unauthorized");
        }

        orders.setOrderStatus(Status.CANCELLED);
        Orders save = repo.save(orders);
        return new ResponseDTO(save.getId(), save.getOrderStatus(), save.getTotalAmount(), save.getCReatedAt());
    }

    public List<ResponseDTO> filterByAmount(String token, BigDecimal amount) {
        checkRoleUser(token);
        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        List<Orders> list = repo.findOrderByAmount(amount, referenceId);
        List<ResponseDTO> listDto = new ArrayList<>();

        for (Orders i : list) {
            listDto.add(new ResponseDTO(i.getId(), i.getOrderStatus(), i.getTotalAmount(), i.getCReatedAt()));

        }
        return listDto;
    }

    public List<TopSoldProductResponseDTO> getTopSoldProduct(String token) {
        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        List<Object[]> result = itemRepo.topSoldProduct();

        List<TopSoldProductResponseDTO> topSold = new ArrayList<>();

        for (Object[] i : result) {
            Long productId = ((Number) i[0]).longValue();
            Long totalSold = ((Number) i[1]).longValue();
            ProductResponseDTO product = productClient.getProduct(productId);
           topSold.add(new TopSoldProductResponseDTO(productId,totalSold,product.getProductName(),product.getPrice()));
        }
        return topSold;
    }

    public Map<String,Long>groupByCategory(String token){
        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));

        List<Orders>list=repo.findByUserId(referenceId);
        Stream<OrderItems> listItems=list.stream().flatMap(i->i.getOrderItems().stream());

        Map<String,Long>  result=listItems.collect(Collectors.groupingBy(item->productClient.getProduct(item.getProductId()).getCategoryName(),Collectors.summingLong(OrderItems::getQuantity)));

      return  result;
    }

    public  BigDecimal totalRevenue(String token){
        checkRoleAdmin(token);
        String email = jwtService.extractEmail(token.substring(7));
        List<String> roles = jwtService.extractRole(token.substring(7));
        Long referenceId = jwtService.extractReferenceId(token.substring(7));
       List<Orders> orders=repo.findAll();

       return  orders.stream().map(i->i.getTotalAmount()).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

}


