import React from "react";
import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import axios from "axios";

function ReservationFormFishing() {
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
  const [fishingEquipment, setFishingEquipment] = useState("Yes");

  const fetchAttraction = async () => {
    try {
      const var1 = await fetch(attractionUrl);
      const json = await var1.json();
      console.log(json);

      setAttraction(json);
      setStartDate(new Date(json.startDate));
      setEndDate(new Date(json.endDate));
      setOgStartDate(new Date(json.startDate));
      setOgEndDate(new Date(json.endDate));

      if (json.fishingEquipment === null) {
        setFishingEquipment("No");
      }

      console.log(attraction.startDate.getTime());
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
    };
    const url = "http://localhost:8081/api/reservation/makeReservation";
    axios.post(url, data).then((response) => {
      window.alert(response.data);
    });
  };

  useEffect(() => {
    console.log(attractionUrl);
    fetchAttraction();

    console.log(startDate.getTime() + 1);
  }, []);

  return (
    <div>
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
              <label>Starting day : </label>
              <DatePicker
                dateFormat={"yyyy/MM/dd"}
                selected={startDate}
                onChange={(date) => onChangeStartDate(date)}
              />
            </li>
            <li>
              <label>Final day : </label>
              <DatePicker
                dateFormat={"yyyy/MM/dd"}
                selected={endDate}
                onChange={(date) => onChangeEndDate(date)}
              />
            </li>
            <li>
              <label>Fishing equipment : {fishingEquipment}</label>
            </li>
            <li>
              <label>Instructor info : {attraction.instructorInfo}</label>
            </li>
            <li>
              <label>Make a reservation : </label>{" "}
              <button onClick={onReserveClick}>Make!</button>
            </li>
          </ul>
        )}
      </div>
    </div>
  );
}

export default ReservationFormFishing;
