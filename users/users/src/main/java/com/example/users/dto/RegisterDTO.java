package com.example.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import com.example.users.validation.Gmail;
import com.example.users.validation.Phone;

public class RegisterDTO {
    
 @Size(min = 3, message = "First name must be at least 3 characters long")   
 private String firstName;

 private String lastName;  

 @Email(message = "Email should be valid")
 @Gmail
 private String email;

@Size(min = 6, message = "Password must be at least 6 characters long")
 private String password;

 @Phone
 private String mobile;

 public RegisterDTO() {
 }

 public RegisterDTO(@Size(min = 3, message = "First name must be at least 3 characters long") String firstName,
        String lastName, @Email(message = "Email should be valid") String email,
        @Size(min = 6, message = "Password must be at least 6 characters long") String password,
        @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters long") String mobile) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.mobile = mobile;
 }

 public String getFirstName() {
    return firstName;
 }

 public void setFirstName(String firstName) {
    this.firstName = firstName;
 }

 public String getLastName() {
    return lastName;
 }

 public void setLastName(String lastName) {
    this.lastName = lastName;
 }

 public String getEmail() {
    return email;
 }

 public void setEmail(String email) {
    this.email = email;
 }

 public String getPassword() {
    return password;
 }

 public void setPassword(String password) {
    this.password = password;
 }

 public String getMobile() {
    return mobile;
 }

 public void setMobile(String mobile) {
    this.mobile = mobile;
 }


 
}
