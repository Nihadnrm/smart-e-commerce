package com.example.users.service;

import java.util.ArrayList;
import java.util.List;

import com.example.users.dto.ExtraRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.users.dto.LoginDTO;
import com.example.users.dto.RegisterDTO;
import com.example.users.dto.RegisterResponseDTO;
import com.example.users.entity.Role;
import com.example.users.entity.User;
import com.example.users.enums.Status;
import com.example.users.exception.DuplicateUser;
import com.example.users.exception.UserNotFound;
import com.example.users.repository.RoleRepository;
import com.example.users.repository.UserRepository;
import com.example.users.security.JwtService;

@Service
public class UserService {
    @Autowired
    UserRepository repo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;




    public RegisterResponseDTO register(RegisterDTO DTO){

      if(repo.existsByEmail(DTO.getEmail())){
         throw new DuplicateUser("user already exist");
      }

      Role role=roleRepo.findByRoleName("customer").orElseThrow(()->new UserNotFound("Role not found"));

        

        User user=new User();
        user.setFirstName(DTO.getFirstName());
        user.setLastName(DTO.getLastName());
        user.setEmail(DTO.getEmail());
        user.setPassword(passwordEncoder.encode(DTO.getPassword()));
        user.setMobile(DTO.getMobile());
        user.setStatus(Status.ACTIVE);
        user.setRoles(List.of(role));
        User save= repo.save(user);
        return new RegisterResponseDTO("User Registered Successfully",save.getId());

    }
    public List<RegisterDTO>getListOfUsers(){
       List<User>list= repo.findAll();
       List<RegisterDTO>listDTO=new ArrayList<>();
        for(User user:list){
          listDTO.add(new RegisterDTO(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getMobile()));
    }
    return listDTO;
    }

    public String deleteUser(Long id){

      User user= repo.findById(id).orElseThrow(()->new UserNotFound("User not found"));
      repo.delete(user);
      return "User deleted successfully";

    }

    public void addRoleToUser(ExtraRoleDTO dto){
        User user=repo.findByEmail(dto.getEmail()).orElseThrow(()->new UserNotFound("user not found"));
        Role role=roleRepo.findByRoleName(dto.getRoleName()).orElseThrow(()->new RuntimeException("role not found"));
        boolean exist= user.getRoles().stream().anyMatch(i->i.getRoleName().equalsIgnoreCase(dto.getRoleName()));
        if(exist){
            throw  new RuntimeException("role already exist");
        }
        user.getRoles().add(role);
        User save=repo.save(user);
    }



    public String login(LoginDTO dto){
      User user= repo.findByEmail(dto.getEmail()).orElseThrow(()->new UserNotFound("mail not matching"));
      if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
          throw new UserNotFound("Invalid credentials");
      }

       List<String>roles=user.getRoles().stream().map(i->i.getRoleName()).toList(); // this means : role list ayyittan ullath. ivide role object mothathil send cheyyathe list of rolename mathram sens aakunnu
      
        return jwtService.generateToken(user.getEmail(),roles,user.getId());
    }

}
