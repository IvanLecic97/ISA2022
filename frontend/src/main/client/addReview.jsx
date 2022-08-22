import axios from "axios";
import React, { useState } from "react";

function AddReview() {
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
  const [attractionReview, setAttractionReview] = useState("");
  const [attractionRating, setAttractionRating] = useState(0);
  const [ownerRating, setOwnerRating] = useState(0);
  const [ownerReview, setOwnerReview] = useState("");

  const onChangeAttractionReview = (event) => {
    setAttractionReview(event.target.value);
  };

  const onChangeAttractionRating = (event) => {
    if (event.target.value < 5 || event.target.value > 10) {
      window.alert("Rating needs to be between 5 and 10");
    } else {
      setAttractionRating(event.target.value);
    }
  };

  const onChangeOwnerReview = (event) => {
    setOwnerReview(event.target.value);
  };

  const onChangeOwnerRating = (event) => {
    if (event.target.value < 5 || event.target.value > 10) {
      window.alert("Rating needs to be between 5 and 10");
    } else {
      setOwnerRating(event.target.value);
    }
  };

  const onSend = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/review";
    const data = {
      attractionRating: attractionRating,
      attractionReview: attractionReview,
      ownerRating: ownerRating,
      ownerReview: ownerReview,
      attractionId: attractionId,
      ownerUsername: ownerUsername,
      reservationId: reservationId,
    };
    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log(reservationId);
        console.log(res.data);
      });
  };

  return (
    <div className="bungalowForm">
      <textarea
        placeholder="Attraction review"
        cols="50"
        onChange={onChangeAttractionReview}
      />
      <br />
      <input
        placeholder="Attraction rating(5-10)"
        min="5"
        max="10"
        onChange={onChangeAttractionRating}
      />
      <br />
      <br />
      <br />
      <textarea
        placeholder="Owner review"
        cols="50"
        onChange={onChangeOwnerReview}
      />
      <br />
      <input
        placeholder="Owner rating(5-10)"
        min="5"
        max="10"
        onChange={onChangeOwnerRating}
      />
      <button onClick={onSend}>Send</button>
    </div>
  );
}

export default AddReview;
