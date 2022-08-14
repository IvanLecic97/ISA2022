import axios from "axios";
import React, { useState, useEffect } from "react";

function SetDiscount() {
  const [id, setId] = useState(localStorage.getItem("attractionId"));
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [newPrice, setNewPrice] = useState(0);
  const [attraction, setAttraction] = useState(null);

  const loadAttraction = async () => {
    const url = "http://localhost:8081/api/attraction/getAttractionById/" + id;
    try {
      const var1 = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const json = await var1.json();
      console.log(json);
      setAttraction(json);
    } catch (error) {
      console.log(error, "error");
    }
  };

  const onPriceChange = (event) => {
    setNewPrice(event.target.value);
  };

  const onConfirm = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/discount/setDiscountedEntity";
    const data = {
      oldPrice: attraction.price,
      newPrice: newPrice,
      attractionId: attraction.id,
    };
    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log(res.data);
      });
  };

  useEffect(() => {
    loadAttraction();
  }, []);

  return (
    <div className="bungalowForm">
      {attraction && (
        <ul>
          <li>Name : {attraction.name}</li>
          <li>Address : {attraction.address}</li>
          <li>City : {attraction.city}</li>
          <li>Country : {attraction.country}</li>
          <li>Regular price : {attraction.price}</li>
          <input
            type="number"
            placeholder="Discounted price"
            onChange={onPriceChange}
          />
          <button onClick={onConfirm}>Set discount</button>
        </ul>
      )}
    </div>
  );
}

export default SetDiscount;
