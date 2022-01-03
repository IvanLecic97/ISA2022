import { Component } from "react";
import React from "react";
import { FormGroup } from "react-bootstrap";
import { Form } from "react-bootstrap";
import axios from "axios";

class Login extends Component {
  constructor() {
    super();
    this.state = {
      email: "",
      password: "",
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    const data = {
      username: this.state.email,
      password: this.state.password,
    };
    const url = "http://localhost:8081/auth/login";
    axios.post(url, data).then((response) => {
      console.log("USER INFO" + response);
      this.setState({ email: "", password: "" });
      //this.props.onLogin(response.data.role);
      localStorage.setItem("token", response.data.token.accessToken);
      localStorage.setItem("username", response.data.username);
      localStorage.setItem("role", response.data.role);
      //console.log(localStorage.getItem("token"));
      console.log(response.data.username);
      console.log(localStorage.getItem("username"));
    });
  }

  handleChange(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }
  render() {
    return (
      <div>
        <Form style={{ width: "220px" }} onSubmit={this.handleSubmit}>
          <FormGroup>
            <input
              onChange={this.handleChange}
              type="text"
              name="email"
              className="form-control"
              placeholder="E-mail address"
            />
          </FormGroup>
          <FormGroup>
            <input
              onChange={this.handleChange}
              type="text"
              name="password"
              className="form-control"
              placeholder="Password"
            />
          </FormGroup>
          <FormGroup>
            <button>Login</button>
          </FormGroup>
        </Form>
      </div>
    );
  }
}

export default Login;
