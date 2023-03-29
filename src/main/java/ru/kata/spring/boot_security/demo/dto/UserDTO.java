package ru.kata.spring.boot_security.demo.dto;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String lastname;
    private String PersonWhoStudiesJava;
   // private String password;
    private String username;
//    private Set<Role> roles;
    private Set<RoleDTO> roles;



    public UserDTO(Long id, String name, String lastname, String personWhoStudiesJava, String username,
        Set<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        PersonWhoStudiesJava = personWhoStudiesJava;
        this.username = username;
        this.roles = roles;
    }

    public UserDTO(Long id, String name, String lastname, String username) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPersonWhoStudiesJava() {
        return PersonWhoStudiesJava;
    }

    public void setPersonWhoStudiesJava(String personWhoStudiesJava) {
        PersonWhoStudiesJava = personWhoStudiesJava;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }


    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", PersonWhoStudiesJava='" + PersonWhoStudiesJava + '\'' +
            ", username='" + username + '\'' +
            ", roles=" + roles +
            '}';
    }
}
