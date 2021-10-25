package model.Users;

import javax.persistence.*;

    @Entity
    @Table(name = "users")
    public class User {
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "Id")
        @SequenceGenerator(name = "seq", sequenceName = "seq1", initialValue = 1, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
        private Long id;

        @Column(name = "Email")
        private String email;

        @Column(name = "Password")
        private String password;

        @Column(name = "Name")
        private String name;

        @Column(name = "Surname")
        private String surname;

        @Column(name = "Address")
        private String address;

        @Column(name = "City")
        private String city;

        @Column(name = "Country")
        private String country;

        @Column(name = "Phone_Number")
        private String phoneNumber;

        @Column(name = "Role")
        private String role;

        @Column(name = "Is_Valid")
        private boolean isValid;





        public User() {}

        public long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public boolean isValid() {
            return isValid;
        }

        public void setValid(boolean valid) {
            isValid = valid;
        }


    }

