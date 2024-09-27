package com.pedro.accountsservice.dto;

public class AccountRequestDTO {

    private UserDTO user;
    private AdressDTO adress;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AdressDTO getAdress() {
        return adress;
    }

    public void setAdress(AdressDTO adress) {
        this.adress = adress;
    }
}
