import React, { useState, useEffect } from "react";
import axios from "axios";
import DatePicker from "react-datepicker";

function SaveAction(props) {
  const token = localStorage.getItem("token");
  const [attraction, setAttraction] = useState(null);
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [ogStartDate, setOgStartDate] = useState(new Date());
  const [ogEndDate, setOgEndDate] = useState(new Date());
  const [freeDates, setFreeDates] = useState([]);

  const loadAttraction = async () => {
    const url = `http://localhost:8081/api/attraction/getAttractionById/${props.attractionId}`;
    try {
      const data = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data1 = await data.json();
      console.log(data1);
      setAttraction(data1);
      setStartDate(new Date(data1.startDate));
      setEndDate(new Date(data1.endDate));
      setOgStartDate(new Date(data1.startDate));
      setOgEndDate(new Date(data1.endDate));
      /*let list = [];
      data1.forEach((value) => {
        let smth = {
          start: new Date(value.startDate),
          end: new Date(value.endDate),
        };
        list.push(smth);
      });
      setFreeDates(list); */
    } catch (error) {
      console.log("error", error);
    }
  };

  const onChangeRange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
    console.log(startDate);
    console.log(endDate);
  };

  const loadReservations = async () => {
    const url = `http://localhost:8081/api/reservation/getAllByAttractionId/${props.attractionId}`;
    try {
      const data = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data1 = await data.json();
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
      setFreeDates(list);
    } catch (error) {
      console.log("error", error);
    }
  };

  useEffect(() => {
    console.log(props.attractionId);
    loadAttraction();
  }, []);

  return (
    <div>
      {attraction && (
        <div className="bungalowForm">
          <ul>
            <li>
              <label>Define discount day(s)</label>
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
          </ul>
        </div>
      )}
    </div>
  );
}

export default SaveAction;
