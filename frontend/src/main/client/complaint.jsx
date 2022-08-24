import axios from "axios";
import React, { useState } from "react";

function Complaint() {
  const [attractionId, setAttractionId] = useState(
    localStorage.getItem("attractionId")
  );
  const [ownerUsername, setOwnerUsername] = useState(
    localStorage.getItem("owner_username")
  );
  const [reservationId, setReservationId] = useState(
    localStorage.getItem("reservation_id")
  );
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [text, setText] = useState("");

  const onChangeText = (event) => {
    setText(event.target.value);
  };

  const onSend = (event) => {
    event.preventDefault();

    const url = "http://localhost:8081/api/complaint";
    const data = {
      ownerUsername: ownerUsername,
      attractionId: attractionId,
      text: text,
      reservationId: reservationId,
    };

    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => console.log(res.data));
  };

  return (
    <div className="bungalowForm">
      <textarea
        onChange={onChangeText}
        placeholder="Write your complaint"
        cols="50"
        rows="10"
      />
      <br />
      <br />
      <button onClick={onSend}>Send</button>
    </div>
  );
}

export default Complaint;
