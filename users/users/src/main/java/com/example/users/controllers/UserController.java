package com.example.users.controllers;

import java.util.List;

import com.example.users.dto.ExtraRoleDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.dto.LoginDTO;
import com.example.users.dto.RegisterDTO;
import com.example.users.dto.RegisterResponseDTO;
import com.example.users.service.UserService;




@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
     UserService service;
 
   @PostMapping("/register")
    public RegisterResponseDTO register(@Valid@RequestBody RegisterDTO registerDTO){
        return service.register(registerDTO);
    }

  @GetMapping("/register")
  public List<RegisterDTO>getListOfUsers(){

       return service.getListOfUsers();
  }

@DeleteMapping("/register/{id}")
public String deleteUser(@PathVariable Long id){
  return service.deleteUser(id);
}

@PostMapping("/register/addrole")
public  void addRoleToUser(@RequestBody ExtraRoleDTO dto){
       service.addRoleToUser(dto);

}

@PostMapping("/login")
public String login(@RequestBody LoginDTO DTO){
  return service.login(DTO);


}
}