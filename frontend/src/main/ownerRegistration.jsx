import React, { useState } from "react";
import { Form, FormGroup } from "react-bootstrap";
import "./css/registration.css";
import axios from "axios";

function OwnerRegistration() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repPassword, setRepPassword] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [city, setCity] = useState("");
  const [address, setAddress] = useState("");
  const [country, setCountry] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [bungalow, setBungalow] = useState(true);
  const [ship, setShip] = useState(true);
  const [instructor, setInstructor] = useState(true);
  const [description, setDescription] = useState("");
  const [apiUrl, setApiUrl] = useState("");

  const onUsernameChange = (event) => {
    setUsername(event.target.value);
  };

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

  const onBungalowChange = () => {
    setBungalow(!bungalow);
    if (bungalow === true) {
      setApiUrl("http://localhost:8081/api/user/registerBungalowOwner");
    } else setApiUrl("");
    console.log(bungalow);
  };

  const onShipChange = () => {
    setShip(!ship);
    if (ship === true) {
      setApiUrl("http://localhost:8081/api/user/registerShipOwner");
    } else setApiUrl("");
    console.log(ship);
  };

  const onInstructorChange = () => {
    setInstructor(!instructor);
    if (instructor === true) {
      setApiUrl(
        "http://localhost:8081/api/user/registerFishingInstructorOwner"
      );
    } else setApiUrl("");
    console.log(instructor);
  };

  const onChangeDescription = (event) => {
    setDescription(event.target.value);
  };

  const onSubmit = (event) => {
    event.preventDefault();
    /*if (bungalow == true) {
      setApiUrl("http://localhost:8081/api/user/registerBungalowOwner");
    } else if (ship == true) {
      setApiUrl("http://localhost:8081/api/user/registerShipOwner");
    } else if (instructor == true) {
      setApiUrl(
        "http://localhost:8081/api/user/registerFishingInstructorOwner"
      );
    } */
    console.log(apiUrl);
    const data = {
      username: email,
      password: password,
      name: name,
      surname: surname,
      address: address,
      city: city,
      country: country,
      phoneNumber: phoneNumber,
      description: description,
    };
    if (password === repPassword) {
      axios.post(apiUrl, data).then((res) => {
        console.log(res.data);
      });
    } else {
      window.alert("Passwords must match!!");
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
      <Form style={{ width: "550px" }} onSubmit={onSubmit}>
        <FormGroup>
          <div className="input-group mb-3">
            <span className="input-group-text" id="username">
              @
            </span>
            <input
              onChange={onEmailChange}
              type="text"
              name="username"
              className="form-control"
              placeholder="E-mail address"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onPasswordChange}
              name="password"
              type="text"
              className="form-control"
              placeholder="Password"
              required
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onRepPasswordCHange}
              name="repPassword"
              type="text"
              className="form-control"
              placeholder="Re-enter password"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onNameChange}
              name="name"
              type="text"
              className="form-control"
              placeholder="Name"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onSurnameChange}
              name="surname"
              type="text"
              className="form-control"
              placeholder="Surname"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onAddressChange}
              name="address"
              type="text"
              className="form-control"
              placeholder="Address"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onCityChange}
              name="city"
              type="text"
              className="form-control"
              placeholder="City"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onCountryChange}
              name="country"
              type="text"
              className="form-control"
              placeholder="Country"
            />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <input
              onChange={onPhoneNumberChange}
              name="phone"
              type="text"
              className="form-control"
              placeholder="Phone number"
            />
          </div>
        </FormGroup>
        <Form.Group>
          <Form.Control
            onChange={onChangeDescription}
            as="textarea"
            placeholder="Registration reason"
          />
        </Form.Group>
        <Form.Group className="formGroup">
          <label>Bungalow :</label>
          <br />
          <input type="checkbox" onChange={onBungalowChange} />
        </Form.Group>
        <Form.Group className="formGroup">
          <label>Ship :</label>
          <br />
          <input type="checkbox" onChange={onShipChange} />
        </Form.Group>
        <Form.Group className="formGroup">
          <label>Fishing instructor :</label>
          <br />
          <input type="checkbox" onChange={onInstructorChange} />
        </Form.Group>
        <button color="info">Register</button>
      </Form>
    </div>
  );
}

export default OwnerRegistration;
