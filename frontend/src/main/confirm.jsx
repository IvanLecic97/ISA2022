import axios from "axios";
import React, { Component } from "react";

class Confirm extends React.Component {
  constructor() {
    super();
    this.state = {
      email: "",
    };
    this.email = localStorage.getItem("emailToBeConfirmed");
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    const email = this.state.email;
    const url = "http://localhost:8081/api/user/confirmEmail/" + this.email;

    axios.post(url);
    localStorage.removeItem("emailToBeConfirmed");
    console.log(url);
  }

  render() {
    return (
      <div>
        <label>Click on the button to confirm registration!</label>
        <form onSubmit={this.handleSubmit}>
          <button>Submit</button>
        </form>
      </div>
    );
  }
}

export default Confirm;
