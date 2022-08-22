import React from "react";
import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";

import axios from "axios";
import moment from "moment";

function ReservationFormShip() {
  console.log(localStorage.getItem("attractionId"));
  const [attractionUrl, setAttractionUrl] = useState(
    "http://localhost:8081/api/attraction/getAttractionById/" +
      localStorage.getItem("attractionId")
  );

  const [attraction, setAttraction] = useState();

  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [ogStartDate, setOgStartDate] = useState(new Date());
  const [ogEndDate, setOgEndDate] = useState(new Date());
  const [miniBar, setMiniBar] = useState("Yes");
  const [pool, setPool] = useState("Yes");
  const [restaurant, setRestaurant] = useState("Yes");
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [freeDates, setFreeDates] = useState([]);
  const [reservations, setReservations] = useState([]);

  const fetchAttraction = async () => {
    try {
      const var1 = await fetch(attractionUrl, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const json = await var1.json();
      console.log(json);

      setAttraction(json);
      setStartDate(new Date(json.startDate));
      setEndDate(new Date(json.endDate));
      setOgStartDate(new Date(json.startDate));
      setOgEndDate(new Date(json.endDate));
      if (attraction.miniBar == false) {
        setMiniBar("No");
      }
      if (attraction.pool === false) {
        setPool("No");
      }
      if (attraction.restaurant === false) {
        setRestaurant("No");
      }
      console.log(attraction.startDate.getTime());
    } catch (error) {
      console.log("error", error);
    }
  };

  const loadReservations = async () => {
    const attractionId = localStorage.getItem("attractionId");
    const url =
      "http://localhost:8081/api/reservation/getAllByAttractionId/" +
      attractionId;
    try {
      const data = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data1 = await data.json();
      //const list = [];
      let list = [];
      data1.forEach((value) => {
        let smth = {
          start: new Date(value.startDate),
          end: new Date(value.endDate),
        };
        list.push(smth);
      });
      console.log(list);
      console.log(data1);
      setReservations(data1);
      setFreeDates(list);
    } catch (error) {
      console.log("error", error);
    }
  };

  function onChangeStartDate(date) {
    if (
      date.getTime() > ogEndDate.getTime() ||
      date.getTime() < ogStartDate.getTime()
    ) {
      console.log("DATE ERROR!!!!");
      window.alert("Select a date in provided range!!");
    } else {
      setStartDate(date);
    }
  }

  function onChangeEndDate(date) {
    if (
      date.getTime() > ogEndDate.getTime() ||
      date.getTime() < ogStartDate.getTime()
    ) {
      console.log("DATE ERROR!!!!");
      window.alert("Select a date in provided range!!");
    } else {
      setEndDate(date);
    }
  }

  const onReserveClick = () => {
    const date1Month =
      startDate.getMonth() + 1 < 10
        ? "0" + (startDate.getMonth() + 1)
        : startDate.getMonth() + 1;

    const date2Month =
      endDate.getMonth() + 1 < 10
        ? "0" + (endDate.getMonth() + 1)
        : endDate.getMonth() + 1;

    const date1Day =
      startDate.getDate() + 1 < 10
        ? "0" + (startDate.getDate() + 1)
        : startDate.getDate() + 1;

    const date2Day =
      endDate.getDate() + 1 < 10
        ? "0" + (endDate.getDate() + 1)
        : endDate.getDate() + 1;

    const date1 = startDate.getFullYear() + "-" + date1Month + "-" + date1Day;

    const date2 = endDate.getFullYear() + "-" + date2Month + "-" + date2Day;

    const data = {
      attractionId: attraction.id,
      startDate: date1,

      endDate: date2,
      username: localStorage.getItem("username"),
    };
    const url = "http://localhost:8081/api/reservation/makeReservation";
    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        window.alert(response.data);
      });
  };

  const onChangeRange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
    console.log(startDate);
    console.log(endDate);
  };

  useEffect(() => {
    console.log(attractionUrl);
    loadReservations();
    fetchAttraction();

    console.log(startDate.getTime() + 1);
  }, []);

  return (
    <div className="bungalowForm">
      {attraction && (
        <ul>
          <li>
            <label>Name: {attraction.name}</label>
          </li>
          <li>
            <label>Address: {attraction.address}</label>
          </li>
          <li>
            <label>Country: {attraction.country}</label>
          </li>
          <li>
            <label>Price: {attraction.price}</label>
          </li>
          <li>
            <label>Rating: {attraction.rates}</label>
          </li>
          <li>
            <label>Max guests allowed: {attraction.maxGuests}</label>
          </li>
          <li>
            <label>Free dates</label>
            <DatePicker
              selectsRange
              selected={null}
              onChange={onChangeRange}
              startDate={startDate}
              endDate={endDate}
              minDate={ogStartDate}
              maxDate={ogEndDate}
              isClearable={true}
              excludeDateIntervals={freeDates}
              inline
            />
          </li>
          <li>
            <label>Mini bar : {miniBar}</label>
          </li>
          <li>
            <label>Pool : {pool}</label>
          </li>
          <li>
            <label>Restaurant : {restaurant}</label>
          </li>
          <li>
            <label>Make a reservation : </label>
            <button onClick={onReserveClick}>Make!</button>
          </li>
        </ul>
      )}
      <a href="/discountedPage">Discounted entities</a>
    </div>
  );
}

export default ReservationFormShip;
