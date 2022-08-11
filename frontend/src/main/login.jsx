import React, { useState } from "react";
import HomepageClient from "./client/homepageClient";
import { FormGroup } from "react-bootstrap";
import { Form } from "react-bootstrap";
import axios from "axios";
import { Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  let navigate = useNavigate();

  let onChangeUsername = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setUsername(newValue);
    console.log(username);
  };

  let onChangePassword = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setPassword(newValue);
    console.log(password);
  };

  let handleSubmit = (event) => {
    event.preventDefault();
    const data = {
      username: username,
      password: password,
    };
    const url = "http://localhost:8081/auth/login";
    axios.post(url, data).then((response) => {
      console.log("User info" + response.data.country);
      localStorage.setItem("token", response.data.token.accessToken);
      localStorage.setItem("username", response.data.username);
      localStorage.setItem("role", response.data.role);
      console.log(response.data.username);
      console.log(localStorage.getItem("role"));
      console.log(localStorage.getItem("token"));

      if (localStorage.getItem("role") === "ROLE_CLIENT") {
        navigate("/homepageClient");
      }
      if (localStorage.getItem("role") === "ROLE_ADMIN") {
        navigate("/homepageAdmin");
      }
    });
  };

  return (
    <div>
      <Form style={{ width: "550px" }} onSubmit={handleSubmit}>
        <FormGroup>
          <input
            onChange={onChangeUsername}
            type="text"
            name="email"
            className="form-control"
            placeholder="E-mail address"
          />
        </FormGroup>
        <FormGroup>
          <input
            onChange={onChangePassword}
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
};

export default Login;
