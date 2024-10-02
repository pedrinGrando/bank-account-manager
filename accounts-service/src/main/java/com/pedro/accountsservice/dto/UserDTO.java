package com.pedro.accountsservice.dto;

import com.pedro.accountsservice.model.User;

import java.time.LocalDate;

public class UserDTO {

    private String name;

    private String username;

    private String cpf;

    private String email;

    private String password;

    private LocalDate birthday;

    public UserDTO(String name, String username, String cpf, String email, String password, LocalDate birthday) {
        this.name = name;
        this.username = username;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.birthday = user.getBirthday();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}
