package com.pedro.accountsservice.model;

import com.pedro.accountsservice.dto.AdressDTO;
import com.pedro.accountsservice.dto.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String username;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDate birthday;

    @ManyToOne
    private Adress adress;

    public User(long id, String name, String username, String cpf, String email, String password, LocalDateTime createdAt, Adress adress, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.adress = adress;
        this.birthday = birthday;
    }

    public User(UserDTO userDto) {
        this.name = userDto.getName();
        this.username = userDto.getUsername();
        this.cpf = userDto.getCpf();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.birthday = userDto.getBirthday();
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
