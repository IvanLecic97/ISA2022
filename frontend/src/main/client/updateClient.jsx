import React, { useEffect, useState } from "react";
import {
  Container,
  Form,
  FormGroup,
  ListGroupItem,
  Row,
} from "react-bootstrap";
import "../css/clientProfile.css";
import { ListGroup } from "react-bootstrap";
import axios from "axios";
const UpdateClient = () => {
  const [oldPassword, setOldPassword] = useState("");
  const [oldName, setOldName] = useState("");
  const [oldSurname, setOldSurname] = useState("");
  const [oldCity, setOldCity] = useState("");
  const [oldAddress, setOldAddress] = useState("");
  const [oldCountry, setOldCountry] = useState("");
  const [oldPhone, setOldPhone] = useState("");
  const [currentUser, setCurrentUser] = useState(
    localStorage.getItem("username")
  );

  /*
  useEffect(() => {
    setCurrentUser(localStorage.getItem("username"));
    console.log(currentUser);
    const url = "http://localhost:8081/api/user/getUser/" + currentUser;
    axios.get(url).then((res) => {
      console.log(res.data);
      setOldPassword(res.data.password);
      setOldName(res.data.name);
      setOldSurname(res.data.surname);
      setOldCity(res.data.city);
      setOldCountry(res.data.country);
      setOldAddress(res.data.address);
      setOldPhone(res.data.phone);
    });
    console.log(oldPassword);
    console.log(oldSurname);
  });
  */

  let onLoad = (event) => {
    event.preventDefault();
    //setCurrentUser(localStorage.getItem("username"));
    console.log(currentUser);
    const url = "http://localhost:8081/api/user/getUser/" + currentUser;
    axios
      .get(url, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setOldPassword(res.data.password);
        setOldName(res.data.name);
        setOldSurname(res.data.surname);
        setOldCity(res.data.city);
        setOldCountry(res.data.country);
        setOldAddress(res.data.address);
        setOldPhone(res.data.phone);
      });
    console.log(oldPassword);
    console.log(oldSurname);
  };

  let onSubmit = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/user/updateUser";
    const data = {
      name: oldName,
      surname: oldSurname,
      city: oldCity,
      address: oldAddress,
      country: oldCountry,
      phone: oldPhone,
      username: currentUser,
    };
    axios
      .post(url, data, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
      });
  };

  let onChangePassword = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldPassword(newValue);
    console.log(oldPassword);
  };

  let onChangeName = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldName(newValue);
    console.log(oldName);
  };

  let onChangeSurname = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldSurname(newValue);
  };

  let onChangeCountry = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldCountry(newValue);
  };

  let onChangeCity = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldCity(newValue);
  };

  let onChangeAddress = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldAddress(newValue);
  };

  let onChangePhone = (event) => {
    event.preventDefault();
    const newValue = event.target.value;
    setOldPhone(newValue);
  };

  return (
    <div className="clientDiv">
      <Form className="formField" onSubmit={onSubmit}>
        <FormGroup>
          <div>
            <label>Name</label>
            <input value={oldName} onChange={onChangeName} />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <label>Surname</label>
            <input value={oldSurname} onChange={onChangeSurname} />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <label>Address</label>
            <input value={oldAddress} onChange={onChangeAddress} />
          </div>
        </FormGroup>

        <FormGroup>
          <div className="input-group mb-3">
            <label>Country</label>
            <input value={oldCountry} onChange={onChangeCountry} />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <label>Phone number</label>
            <input value={oldPhone} onChange={onChangePhone} />
          </div>
        </FormGroup>
        <FormGroup>
          <div className="input-group mb-3">
            <label>City name</label>
            <input value={oldCity} onChange={onChangeCity} />
          </div>
        </FormGroup>
        <FormGroup>
          <button color="info">Confirm</button>
        </FormGroup>
      </Form>
      <button onClick={onLoad}>Load info</button>
    </div>
  );
};

export default UpdateClient;
