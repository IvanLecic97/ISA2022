import React from "react";
import Entity from "../shared/Entity";
import { useState, useEffect, useRef, useReducer } from "react";
import axios from "axios";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "../css/entity.css";
import { Form } from "react-bootstrap";
import { format } from "date-fns";

const myReducer = (state, action) => {
  let array = [];
  switch (action.type) {
    case "LOAD":
      array = [...state];
      array.push(action.data);
      return {
        state: action.data,
      };
    case "FILTER":
      return {
        state: action.data,
      };
  }
};

function SearchEntities() {
  const variable = [
    {
      name: "Ivan",
      rating: 10,
      address: "Dr Ribara 7",
      price: 15,
    },
    {
      name: "Aleksa",
      rating: 9,
      address: "Dr Ribara 7",
      price: 20,
    },
    {
      name: "Milos",
      rating: 1,
      address: "Dr Ribara 7",
      price: 82317,
    },
    {
      name: "Damjan",
      rating: 99,
      address: "Dom Bajic",
      price: 1,
    },
  ];

  const listItems = variable.map((myList) => <li>{myList}</li>);
  const [importedList, setImportedList] = useState([]);
  const [number, setNumber] = useState(0);
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [country, setCountry] = useState("");
  const [price, setPrice] = useState(0);
  const [rating, setRating] = useState(0);
  const [countriesList, setCountriesList] = useState(null);
  const [notEmpty, setNotEmpty] = useState(0);
  const [isFiltering, setIsFiltering] = useState(0);
  const [filteredList, setFilteredList] = useState(null);
  const [state, dispatch] = useReducer(myReducer, []);
  const [startDateChanged, setStartDateChanged] = useState(false);
  const [endDateChanged, setEndDateChanged] = useState(false);

  const url = "http://localhost:8081/api/attraction/getAttractions";
  const getAllCountries =
    "http://localhost:8081/api/attraction/getAllCountries";
  const filterUrl =
    "http://localhost:8081/api/attraction/getFilteredAttraction";

  const fetchData = async () => {
    try {
      const response = await fetch(url);
      const json = await response.json();
      console.log(json);
      setImportedList(json);
      dispatch({ type: "LOAD", data: json });
      //retVal = [...json];
    } catch (error) {
      console.log("error", error);
    }

    console.log(state);
  };

  const fetchCountries = async () => {
    try {
      const response = await fetch(getAllCountries);
      const json = await response.json();
      console.log(json);
      setCountriesList(json);
    } catch (error) {
      console.log("error", error);
    }
  };

  const reset = (event) => {
    //event.preventDefault();
    // window.location.reload();
    /*setStartDate(new Date("1900/01/01"));
    setEndDate(new Date("1900/01/01"));
    setCountry("");
    setRating(0);
    setPrice(0);
    fetchData();*/
    console.log(state.state);
  };

  const filterData = () => {
    //event.preventDefault();
    const copiedArray = [...importedList];

    copiedArray.forEach((element) => {
      const d1 = new Date(element.attraction.startDate);
      const d2 = new Date(element.attraction.endDate);
      element.attraction.startDate = d1;
      element.attraction.endDate = d2;
      console.log(new Date(element.attraction.startDate));
      console.log(new Date(element.attraction.endDate));
    });

    let firstIteration = [];

    let startDateFilter = [];
    startDateFilter = copiedArray.filter(
      (element) => element.attraction.startDate.getTime() > startDate.getTime()
    );
    if (startDateChanged !== false && startDateFilter.length !== 0) {
      firstIteration = [...startDateFilter];
    } else {
      firstIteration = [...copiedArray];
    }

    let endDateFilter = [];
    let secondIteration = [];
    endDateFilter = firstIteration.filter(
      (element) => element.attraction.endDate.getTime() < endDate.getTime()
    );
    if (endDateChanged !== false && endDateFilter.length !== 0) {
      secondIteration = [...endDateFilter];
    } else {
      secondIteration = [...firstIteration];
    }

    let countryFilter = [];
    let thirdIteration = [];

    if (country !== "") {
      countryFilter = secondIteration.filter(
        (element) => element.attraction.country === country
      );
      thirdIteration = [...countryFilter];
    } else {
      thirdIteration = [...secondIteration];
    }

    let priceFilter = [];
    let fourthIteration = [];
    if (price !== 0) {
      priceFilter = thirdIteration.filter(
        (element) => element.attraction.price < price
      );
      fourthIteration = [...priceFilter];
    } else {
      fourthIteration = [...thirdIteration];
    }

    let ratingFilter = [];
    let fifthIteration = [];
    if (rating !== 0) {
      ratingFilter = fourthIteration.filter(
        (element) => element.attraction.rates > rating
      );
      fifthIteration = [...ratingFilter];
    } else {
      fifthIteration = [...fourthIteration];
    }

    dispatch({ type: "FILTER", data: fifthIteration });

    //console.log(startDateFilter);
    console.log(copiedArray);
    console.log(state.state);
    console.log(startDateChanged);
  };

  useEffect(() => {
    fetchCountries();
    console.log(country);
    // loadItems();
    //console.log(state);
    if (isFiltering == 0) {
      fetchData();
    }
  }, []);

  const onChageStartDate = (event) => {
    //event.preventDefault();
    setStartDate(event);
    console.log(startDate);
    setStartDateChanged(true);
    console.log(startDateChanged);
  };

  const onChageEndDate = (event) => {
    //event.preventDefault();
    setEndDate(event);
    console.log(startDate);
    setEndDateChanged(true);
    console.log(startDateChanged);
  };

  return (
    <div>
      <div className="filterBox">
        <div className="searchLabel">
          <label>Search entitites:</label> <br />
          <label>Select starting date</label>
          <DatePicker
            dateFormat="yyyy/MM/dd"
            isClearable
            selected={startDate}
            onChange={onChageStartDate}
          />
          <label>Select ending date</label>
          <DatePicker
            dateFormat="yyyy/MM/dd"
            isClearable
            selected={endDate}
            onChange={onChageEndDate}
          />
          <label>Select country</label>
          <select
            selected={country}
            onChange={(event) =>
              setCountry(event.target.value) && setNotEmpty(1)
            }
          >
            <option>{""}</option>
            {countriesList &&
              countriesList.map((something) => (
                <option value={something}>{something}</option>
              ))}
          </select>
          <br />
          <label>Select max price</label>
          <input
            type="number"
            onChange={(event) => setPrice(event.target.value)}
          ></input>{" "}
          <br />
          <label>Select minimum rating </label>
          <input
            type="number"
            onChange={(event) => setRating(event.target.value)}
          ></input>{" "}
          <br />
          <button type="button" onClick={filterData}>
            Filter
          </button>
          <button type="button" onClick={reset}>
            Reset
          </button>
        </div>
      </div>
      <div className="shownEntities">
        {state.state &&
          state.state.map((value) => (
            <li key={value.attraction.id}>
              <Entity entity={value} />)
            </li>
          ))}
      </div>
    </div>
  );
}

export default SearchEntities;
