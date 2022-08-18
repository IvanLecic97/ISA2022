import React, { useState, useEffect } from "react";

function GetOwnAttractions() {
  const [loadedData, setLoadedData] = useState([]);
  const [token, setToken] = useState(localStorage.getItem("token"));

  const getAttractions = async () => {
    const url = "http://localhost:8081/api/attraction/getUsersAttractions";
    try {
      const response = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const json = await response.json();
      console.log(json);
      setLoadedData(json);
    } catch (error) {
      console.log(error, "error");
    }
  };

  function onButtonClick(id, event) {
    event.preventDefault();
    localStorage.setItem("attractionId", id);
    window.open("/setDiscount");
  }

  useEffect(() => {
    getAttractions();
  }, []);

  return (
    <div>
      <div>
        {loadedData &&
          loadedData.map((value) => (
            <ul className="discountedEntity" key={value.id}>
              <button
                className="btnMore"
                onClick={(event) => onButtonClick(value.id, event)}
              >
                Set on discount
              </button>{" "}
              <br />
              <div></div>
              <li style={{ color: "blue" }}>Name : {value.name}</li>
              <li style={{ color: "blue" }}>Address : {value.address}</li>
              <li style={{ color: "blue" }}>City : {value.city}</li>
              <li style={{ color: "blue" }}>Country : {value.country}</li>
            </ul>
          ))}
      </div>
    </div>
  );
}

export default GetOwnAttractions;
