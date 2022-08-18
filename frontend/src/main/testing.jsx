import React, { useEffect } from "react";
import DatePicker from "react-datepicker";

function Testing() {
  const dates = [];
  const data = ["2022-08-16", "2022-08-17", "2022-08-18", "2022-08-19"];

  const createDates = () => {
    const retVal = [];
    data.map((value) => {
      dates.push(new Date(value));
    });
  };

  useEffect(() => {
    createDates();
  }, []);

  return (
    <div>
      <DatePicker excludeDates={dates} />
    </div>
  );
}

export default Testing;
