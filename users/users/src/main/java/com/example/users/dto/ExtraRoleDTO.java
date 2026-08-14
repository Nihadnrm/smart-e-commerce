package com.example.users.dto;

public class ExtraRoleDTO {
    private String email;
    private  String roleName;

    public ExtraRoleDTO() {
    }

    public ExtraRoleDTO(String roleName, String email) {
        this.roleName = roleName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
