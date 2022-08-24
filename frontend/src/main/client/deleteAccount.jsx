import React, { useState } from "react";
import axios from "axios";

function DeleteAccount() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [reason, setReason] = useState("");

  const onChangeReason = (event) => {
    setReason(event.target.value);
  };

  const onClickSend = (event) => {
    event.preventDefault();
    const url = "http://localhost:8081/api/user/makeDeleteRequest";
    const data = {
      reason: reason,
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
    <div className="bngalowForm">
      <textarea
        placeholder="Deletion reason"
        cols="50"
        rows="12"
        onChange={onChangeReason}
      />
      <br />
      <br />
      <button onClick={onClickSend}>Send</button>
    </div>
  );
}

export default DeleteAccount;
