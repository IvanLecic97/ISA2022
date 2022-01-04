import React, { Component } from "react";
import { Form, FormGroup, ListGroupItem } from "react-bootstrap";
import { ListGroup } from "react-bootstrap";
class UpdateClient extends Component {
  constructor() {
    super();
    this.state = {
      oldPassword: "",
      oldName: "",
      oldSurname: "",
      oldCity: "",
      oldAddress: "",
      oldCountry: "",
      oldPhone: "",
      newPassword: "",
      newPasswordRep: "",
      newName: "",
      newSurname: "",
      newCity: "",
      newAddress: "",
      newCountry: "",
      newPhone: "",
    };
  }
  render() {
    return (
      <div style={{ alignSelf: "center", width: "200px" }}>
        <Form style={{ width: "220px" }}>
          <FormGroup>
            <div className="input-group mb-3">
              <label>Password</label>
              <input value="Password" />
            </div>
          </FormGroup>
          <FormGroup>
            <div className="input-group mb-3">
              <label>Name</label>
              <input value="Name" />
            </div>
          </FormGroup>
          <FormGroup>
            <div className="input-group mb-3">
              <label>Surname</label>
              <input value="Password" />
            </div>
          </FormGroup>
          <FormGroup>
            <div className="input-group mb-3">
              <label>Address</label>
              <input value="Password" />
            </div>
          </FormGroup>

          <FormGroup>
            <div className="input-group mb-3">
              <label>Country</label>
              <input value="Password" />
            </div>
          </FormGroup>
          <FormGroup>
            <div className="input-group mb-3">
              <label>Phone number</label>
              <input value="Password" />
            </div>
          </FormGroup>
          <FormGroup>
            <div className="input-group mb-3">
              <label>City name</label>
              <input value="Password" />
            </div>
          </FormGroup>
          <FormGroup>
            <button color="info">Confirm</button>
          </FormGroup>
        </Form>
      </div>
    );
  }
}

export default UpdateClient;
