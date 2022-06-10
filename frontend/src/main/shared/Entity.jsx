import React, { useState } from "react";
import { NavItem } from "react-bootstrap";
import "../css/entity.css";
import { getDefaultLocale, DatePicker } from "react-datepicker";
import "local-date/lib/polyfills/reflect";
import "local-date/lib/polyfills/array-from";
import ReservationForm from "../client/reservationForm";
//import moment from "moment";

function Entity(props) {
  const [list, setList] = useState(props.entity);
  const localDateStart = new Date(
    list.attraction.startDate
  ).toLocaleDateString();
  const localDateEnd = new Date(list.attraction.endDate).toLocaleDateString();

  const onClickBtnMore = () => {
    localStorage.setItem("attractionId", list.attraction.id);
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
        <label>Name: {list.attraction.name}</label>
      </div>
      <div>
        <label>Country: {list.attraction.country}</label>
      </div>
      <div>
        <label>Address: {list.attraction.address}</label>
      </div>
      <div>
        <label>Price: {list.attraction.price}</label>
      </div>
      <div>
        <label>Location: {list.attraction.location}</label>
      </div>
      <div>
        <label>Rating : {list.attraction.rates}</label>
      </div>
      <div className="dates">
        <label>
          Free from {localDateStart} to {localDateEnd}
        </label>
      </div>
    </div>
  );
}

export default Entity;
