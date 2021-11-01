package project.isa.model.Users;



import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue
    private Long userId;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "Country")
    private String country;

    @Column(name = "Email")
    private String email;

    @Column(name = "Is_valid")
    private int isValid;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone_number")
    private String phoneNumber;

    @Column(name = "Role")
    private String role;

    @Column(name = "Surname")
    private String surname;

    public User() {
    }

    public User(String address, String city, String country, String email, int isValid,
                String name, String password, String phoneNumber, String role, String surname) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.isValid = isValid;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.surname = surname;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserSurname() {
        return surname;
    }

    public void setUserSurname(String userSurname) {
        this.surname = userSurname;
    }
}

