import axios from "axios";
import React, { useState, useEffect } from "react";

function RegistrationEntity() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [username, setUsername] = useState(
    localStorage.getItem("userUsername")
  );
  const [user, setUser] = useState();
  const [reason, setReason] = useState("");

  const loadUser = async () => {
    const url = "http://localhost:8081/api/user/getUserByUsername/" + username;
    try {
      const data = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data1 = await data.json();
      console.log(data1);
      setUser(data1);
    } catch (error) {
      console.log("error", error);
    }
  };

  const onChangeReason = (event) => {
    setReason(event.target.value);
  };

  const onApprove = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/user/approveRegistration";
    const data = {
      username: user.username,
    };
    axios.post(url, data, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  };

  const onDisApprove = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/user/disApproveRegistration";
    const data = {
      username: user.username,
      message: reason,
    };
    axios.post(url, data, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  };

  useEffect(() => {
    loadUser();
  }, []);

  return (
    <div className="bungalowForm">
      {user && (
        <div>
          <ul>
            <li>Username : {user.username}</li>
            <li>Name : {user.name}</li>
            <li>Surname : {user.surname}</li>
            <li>Address : {user.address}</li>
            <li>City : {user.city}</li>
            <li>Country : {user.country}</li>
            <li>Registration reason : {user.description}</li>
            <br />
            <br />
          </ul>
          <textarea
            placeholder="Rejection reason"
            rows="6"
            cols="40"
            onChange={onChangeReason}
          />{" "}
          <br />
          <button onClick={onApprove}>Approve registration</button>
          <br />
          <br />
          <br />
          <button onClick={onDisApprove}>Reject registration</button>
        </div>
      )}
    </div>
  );
}

export default RegistrationEntity;
