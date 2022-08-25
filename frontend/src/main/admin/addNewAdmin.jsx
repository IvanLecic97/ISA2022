import React, { useState } from "react";
import { Form, FormGroup } from "react-bootstrap";
import axios from "axios";

function AddNewAdmin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repPassword, setRepPassword] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [city, setCity] = useState("");
  const [address, setAddress] = useState("");
  const [country, setCountry] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token"));

  const onEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const onPasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const onRepPasswordCHange = (event) => {
    setRepPassword(event.target.value);
  };

  const onNameChange = (event) => {
    setName(event.target.value);
  };

  const onSurnameChange = (event) => {
    setSurname(event.target.value);
  };

  const onAddressChange = (event) => {
    setAddress(event.target.value);
  };

  const onCityChange = (event) => {
    setCity(event.target.value);
  };

  const onCountryChange = (event) => {
    setCountry(event.target.value);
  };

  const onPhoneNumberChange = (event) => {
    setPhoneNumber(event.target.value);
  };

  const onClick = (event) => {
    event.preventDefault();
    const data = {
      username: email,
      password: password,
      name: name,
      surname: surname,
      address: address,
      city: city,
      country: country,
      phone: phoneNumber,
    };
    const url = "http://localhost:8081/api/user/addNewAdmin";

    if (password === repPassword) {
      axios
        .post(url, data, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((res) => console.log(res.data));
    } else {
      window.alert("Passwords must match!!!!");
    }
  };

  return (
    <div
      className="startDiv"
      style={{
        alignItems: "center",
        justifyContent: "center",
        display: "flex",
      }}
    >
      <Form style={{ width: "550px" }} onSubmit={onClick}>
        <FormGroup>
          <div className="input-group mb-3">
            <span className="input-group-text" id="username">
              @
            </span>
            <input
              type="text"
              name="username"
              className="form-control"
              placeholder="E-mail address"
              onChange={onEmailChange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="password"
              type="text"
              className="form-control"
              placeholder="Password"
              onChange={onPasswordChange}
              required
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="repPassword"
              type="text"
              className="form-control"
              placeholder="Re-enter password"
              onChange={onRepPasswordCHange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="name"
              type="text"
              className="form-control"
              placeholder="Name"
              onChange={onNameChange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="surname"
              type="text"
              className="form-control"
              placeholder="Surname"
              onChange={onSurnameChange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="address"
              type="text"
              className="form-control"
              placeholder="Address"
              onChange={onAddressChange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="city"
              type="text"
              className="form-control"
              placeholder="City"
              onChange={onCityChange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="country"
              type="text"
              className="form-control"
              placeholder="Country"
              onChange={onCountryChange}
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              name="phone"
              type="text"
              className="form-control"
              placeholder="Phone number"
              onChange={onPhoneNumberChange}
            />
          </div>
        </FormGroup>
        <button color="info">Register</button>
      </Form>
    </div>
  );
}

export default AddNewAdmin;
