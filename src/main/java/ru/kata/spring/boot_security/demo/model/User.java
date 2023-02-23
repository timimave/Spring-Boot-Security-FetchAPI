package ru.kata.spring.boot_security.demo.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "isPersonStudyingJava")
    private Boolean PersonWhoStudiesJava;
    @NonNull
    @Column(name = "password")
    private String passWord;

    @Column(unique = true,name = "userName")
    @NonNull
    private String userName;

    @Transient
    private String confirmPass;

    @Column
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usersRoles",
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String name, String lastName, Boolean personWhoStudiesJava,
        @NonNull String passWord, @NonNull String userName,
        @NonNull Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        PersonWhoStudiesJava = personWhoStudiesJava;
        this.passWord = passWord;
        this.userName = userName;
        this.roles = roles;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getPersonWhoStudiesJava() {
        return PersonWhoStudiesJava;
    }

    public void setPersonWhoStudiesJava(Boolean personWhoStudiesJava) {
        PersonWhoStudiesJava = personWhoStudiesJava;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    public void setPassWord(@NonNull String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    @NonNull
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(
        @NonNull Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User {" +
            "id = " + id +
            ", name = '" + name + '\'' +
            ", lastName = '" + lastName + '\'' +
            ", PersonWhoStudiesJava = " + PersonWhoStudiesJava +
            ", passWord = '" + passWord + '\'' +
            ", userName = '" + userName + '\'' +
            ", roles = " + roles +
            '}';
    }

    // equals & hashcode ...


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getId(), user.getId())
            && Objects.equals(getName(), user.getName())
            && Objects.equals(getLastName(), user.getLastName())
            && Objects.equals(getPersonWhoStudiesJava(),
            user.getPersonWhoStudiesJava());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(),
            getPersonWhoStudiesJava());
    }
}