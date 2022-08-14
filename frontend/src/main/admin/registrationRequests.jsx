import React, { useState, useEffect } from "react";

function RegistrationRequests() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [loadedData, setLoadedData] = useState([]);

  const loadData = async () => {
    const url = "http://localhost:8081/api/user/getRegistrationRequests";
    try {
      const data = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data1 = await data.json();
      console.log(data1);
      setLoadedData(data1);
    } catch (error) {
      console.log("error", error);
    }
  };

  function onConfirm(username, event) {
    event.preventDefault();
    localStorage.setItem("userUsername", username);
    window.open("/registrationEntity");
  }

  useEffect(() => {
    loadData();
  }, []);

  return (
    <div className="bungalowForm">
      {loadedData &&
        loadedData.map((value) => (
          <ul key={value.id}>
            <li>Username : {value.userUsername}</li>
            <button onClick={(event) => onConfirm(value.userUsername, event)}>
              View more
            </button>
          </ul>
        ))}
    </div>
  );
}

export default RegistrationRequests;
