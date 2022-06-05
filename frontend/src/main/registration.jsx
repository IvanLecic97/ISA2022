import React, { Component } from "react";
import { Form, FormGroup } from "react-bootstrap";
import axios from "axios";
import "./css/registration.css";

class Registration extends React.Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: "",
      name: "",
      surname: "",
      address: "",
      city: "",
      country: "",
      phone: "",
      repPassword: "",
      //  role: "ROLE_CLIENT",
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
    console.log(this.state.username);
  }

  handleSubmit(event) {
    event.preventDefault();
    if (
      this.state.username != null &&
      this.state.password != null &&
      this.state.password === this.state.repPassword
    ) {
      const url = "http://localhost:8081/api/user/registerClient";
      const data = this.state;
      axios.post(url, data);
      localStorage.setItem("emailToBeConfirmed", this.state.username);
      console.log(localStorage.getItem("emailToBeConfirmed"));
      console.log(this.state.username);
    }
  }

  render() {
    // let repPassword = "";

    return (
      <div
        className="startDiv"
        style={{
          alignItems: "center",
          justifyContent: "center",
          display: "flex",
        }}
      >
        <Form style={{ width: "550px" }} onSubmit={this.handleSubmit}>
          <FormGroup>
            <div className="input-group mb-3">
              <span className="input-group-text" id="username">
                @
              </span>
              <input
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
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
                onChange={this.handleChange}
                name="phone"
                type="text"
                className="form-control"
                placeholder="Phone number"
              />
            </div>
          </FormGroup>
          <button color="info">Register</button>
        </Form>
      </div>
    );
  }
}

export default Registration;
