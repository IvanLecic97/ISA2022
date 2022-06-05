import React, { useState } from "react";
import { NavItem } from "react-bootstrap";
import "../css/entity.css";
import { getDefaultLocale, DatePicker } from "react-datepicker";
import "local-date/lib/polyfills/reflect";
import "local-date/lib/polyfills/array-from";
//import moment from "moment";

function Entity(props) {
  const [list, setList] = useState(props.entity);
  const localDateStart = new Date(
    list.attraction.startDate
  ).toLocaleDateString();
  const localDateEnd = new Date(list.attraction.endDate).toLocaleDateString();

  return (
    <div className="entity">
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
      <div className="dates">
        <label>
          Free from {localDateStart} to {localDateEnd}
        </label>
      </div>
    </div>
  );
}

export default Entity;
