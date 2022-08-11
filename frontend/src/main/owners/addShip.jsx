import React from "react";
import { useState } from "react";
import { FormGroup, InputGroup } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import DatePicker from "react-datepicker";
import storage from "../../firebaseConfig";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import axios from "axios";

function AddShip() {
  const [address, setAddress] = useState("");
  const [description, setDescription] = useState("");
  const [endDate, setEndDate] = useState(new Date());
  const [name, setName] = useState("");
  const [startDate, setStartDate] = useState(new Date());
  const [image, setImage] = useState(null);
  const [miniBar, setMiniBar] = useState(false);
  const [pool, setPool] = useState(false);
  const [restaurant, setRestaurant] = useState(false);
  const [percent, setPercent] = useState(0);
  const [maxGuests, setMaxGuests] = useState(0);
  const [price, setPrice] = useState(0);
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token"));

  const onChangeStartDate = (event) => {
    setStartDate(event);
  };

  const onChangeEndDate = (event) => {
    setEndDate(event);
  };

  const onChangeAddress = (event) => {
    setAddress(event.target.value);
  };

  const onChangeDescription = (event) => {
    setDescription(event.target.value);
  };

  const onChangeName = (event) => {
    setName(event.target.value);
  };

  const onChangeMiniBar = (event) => {
    setMiniBar(!miniBar);
  };

  const onChangePool = (event) => {
    setPool(!pool);
  };

  const onChangeRestaurant = (event) => {
    setRestaurant(!restaurant);
  };

  const onChangeImage = (event) => {
    setImage(event.target.files[0]);
  };

  const onChangeGuests = (event) => {
    setMaxGuests(event.target.value);
  };

  const onChangePrice = (event) => {
    setPrice(event.target.value);
  };

  const onChangeCity = (event) => {
    setCity(event.target.value);
  };

  const onChangeCountry = (event) => {
    setCountry(event.target.value);
  };

  const uploadPhoto = () => {
    //event.preventDefault();

    const storageRef = ref(storage, `/files/${image.name}`);
    const uploadTask = uploadBytesResumable(storageRef, image);
    uploadTask.on(
      "state_changed",
      (snapshot) => {
        const percent = Math.round(
          (snapshot.bytesTransferred / snapshot.totalBytes) * 100
        );

        // update progress
        setPercent(percent);
      },
      (err) => console.log(err),
      () => {
        // download url
        getDownloadURL(uploadTask.snapshot.ref).then((url) => {
          console.log(url);
        });
      }
    );
  };

  const onConfirm = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/attraction/addShip";
    const data = {
      name: name,
      address: address,
      country: country,
      city: city,
      description: description,
      rates: 0,
      price: price,
      startDate: startDate,
      endDate: endDate,
      image: image.name,
      ownerUsername: localStorage.getItem("username"),
      maxGuests: maxGuests,
      miniBar: miniBar,
      pool: pool,
      restaurant: restaurant,
      type: "Ship",
    };
    uploadPhoto();
    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        window.alert(res.data);
      });
  };

  return (
    <div className="bungalowFormDiv">
      <Form className="bungalowForm" onSubmit={onConfirm}>
        <Form.Group>
          <input
            onChange={onChangeAddress}
            type="text"
            placeholder="Bungalow address"
          />
        </Form.Group>
        <Form.Group>
          <input onChange={onChangeCity} type="text" placeholder="City" />
        </Form.Group>
        <Form.Group>
          <input onChange={onChangeCountry} type="text" placeholder="Country" />
        </Form.Group>
        <Form.Group>
          <Form.Control
            onChange={onChangeDescription}
            as="textarea"
            placeholder="Bungalow description"
          />
        </Form.Group>
        <Form.Group>
          <input
            type="text"
            onChange={onChangeName}
            placeholder="Bungalow name"
          />
        </Form.Group>
        <Form.Group>
          <input
            type="number"
            placeholder="Max guests number"
            onChange={onChangeGuests}
          />
        </Form.Group>
        <Form.Group>
          <input
            type="number"
            placeholder="Price in €"
            onChange={onChangePrice}
          />
        </Form.Group>
        <FormGroup>
          <label>Start date:</label>
          <DatePicker
            dateFormat="yyyy/MM/dd"
            isClearable
            selected={startDate}
            onChange={onChangeStartDate}
          />
        </FormGroup>
        <FormGroup>
          <label>End date:</label>
          <DatePicker
            dateFormat="yyyy/MM/dd"
            isClearable
            selected={endDate}
            onChange={onChangeEndDate}
          />
        </FormGroup>
        <Form.Group className="formGroup">
          <label>Mini bar:</label>
          <input type="checkbox" onChange={onChangeMiniBar} />
        </Form.Group>
        <Form.Group className="formGroup">
          <label>Pool :</label>
          <input type="checkbox" onChange={onChangePool} />
        </Form.Group>
        <Form.Group className="formGroup">
          <label>Restaurant :</label>
          <br />
          <input type="checkbox" onChange={onChangeRestaurant} />
        </Form.Group>
        <Form.Group>
          <input type="file" onChange={onChangeImage} />
          <p>{percent}% uploading</p>
        </Form.Group>
        <Form.Group>
          <button>Confirm</button>
        </Form.Group>
      </Form>
    </div>
  );
}

export default AddShip;
