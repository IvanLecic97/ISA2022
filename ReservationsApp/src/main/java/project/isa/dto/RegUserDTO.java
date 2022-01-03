package project.isa.dto;

import project.isa.model.users.Authorities;
import project.isa.model.users.RegUser;
import project.isa.model.users.UserTokenState;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class RegUserDTO {

    private Long id;

    private String username;


    private String password;


    private String name;


    private String surname;


    private String address;


    private String city;


    private String country;


    private String phone;


    private boolean activated;


    private String role;

    private UserTokenState token;
    private List<String> authorities;


    public RegUserDTO(Long id, String username, String password, String name, String surname, String address, String city, String country, String phone, boolean activated, String role, UserTokenState token, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.activated = activated;
        this.role = role;
        this.token = token;
        this.authorities = authorities;
    }
    public RegUserDTO(RegUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phone = user.getPhone();
        this.activated = user.getActivated();
        this.role = user.getRole();
        token = null;
        this.authorities = user.getAuthorities()
                .stream().map(
                        authority ->
                                ((Authorities)authority).getName()).
                collect(Collectors.toList());
    }

    public RegUserDTO()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserTokenState getToken() {
        return token;
    }

    public void setToken(UserTokenState token) {
        this.token = token;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
