package ru.kata.spring.boot_security.demo.dto;

public class RoleDTO {
    private Long id;
    private String role;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
