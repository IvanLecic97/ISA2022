import React, { useState, useEffect } from "react";
import { NavItem } from "react-bootstrap";
import "../css/entity.css";
import { getDefaultLocale, DatePicker } from "react-datepicker";
import "local-date/lib/polyfills/reflect";
import "local-date/lib/polyfills/array-from";
import ReservationForm from "../client/reservationForm";
import storage from "../../firebaseConfig";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import Resizer from "react-image-file-resizer";
import { resolvePath } from "react-router-dom";

//import moment from "moment";

function Entity(props) {
  const [list, setList] = useState(props.entity);
  const localDateStart = new Date(list.startDate).toLocaleDateString();
  const localDateEnd = new Date(list.endDate).toLocaleDateString();
  const [image, setImage] = useState();

  const loadImage = async () => {
    const storageRef = ref(storage, `/files/${props.entity.image}`);

    getDownloadURL(storageRef).then((url) => {
      console.log(url);
      setImage(url);
    });
  };

  function onImageClick(image) {
    localStorage.setItem("image", image);
    window.open("/bungalowPage");
  }

  useEffect(() => {
    loadImage();
  }, []);

  const onClickBtnMore = () => {
    localStorage.setItem("attractionId", list.id);
    switch (list.type) {
      case "Bungalow":
        window.open("/reservationForm");
        break;

      case "Ship":
        window.open("/reservationFormShip");
        break;

      case "Fishing instructor":
        window.open("/reservationFormFishing");
        break;
    }
  };

  return (
    <div className="entity">
      <div className="btnMore">
        <button onClick={onClickBtnMore}>View more!</button>
      </div>
      <div className="type">
        <label>Type: {list.type}</label>
      </div>
      <div>
        <label>Name: {list.name}</label>
      </div>
      <div>
        <label>Country: {list.country}</label>
      </div>
      <div>
        <label>Address: {list.address}</label>
      </div>
      <div>
        <label>Price: {list.price}</label>
      </div>
      <div>
        <label>Location: {list.location}</label>
      </div>
      <div>
        <label>Rating : {list.rates}</label>
      </div>
      <div className="dates">
        <label>
          Free from {localDateStart} to {localDateEnd}
        </label>
      </div>
      <div>
        {" "}
        <img
          width="100"
          height="100"
          src={image}
          onClick={() => onImageClick(list.image)}
        />
      </div>
    </div>
  );
}

export default Entity;
