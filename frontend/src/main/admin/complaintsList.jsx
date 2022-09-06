import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";

function ComplaintsList() {
  const token = localStorage.getItem("token");
  const [list, setList] = useState([]);
  const [ownerAnswer, setOwnerAnswer] = useState("");
  const [clientAnswer, setClientAnswer] = useState("");

  const loadData = async () => {
    const url = "http://localhost:8081/api/complaint/getNotAnsweredComplaints";

    try {
      const data = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data1 = await data.json();
      console.log(data1);
      setList(data1);
    } catch (error) {
      console.log("error", error);
    }
  };

  const table = list && (
    <Table>
      <thead>
        <th>#</th>
        <th>Client username</th>
        <th>Owner username</th>
        <th>Complaint text</th>
        <th>Answer owner</th>
        <th>Answer client</th>
        <th>Send</th>
      </thead>
      <tbody>
        {list.map((value) => (
          <tr key={value.id}>
            <th>#</th>
            <th>{value.clientUsername}</th>
            <th>{value.ownerUsername}</th>
            <th>{value.text}</th>
            <th>
              <input type="text" onChange={onOwnerChange} />
            </th>
            <th>
              <input type="text" onChange={onClientAnswer} />
            </th>
            <th>
              <button onClick={(event) => onSendClick(event, value.id)}>
                Send
              </button>
            </th>
          </tr>
        ))}
      </tbody>
    </Table>
  );

  function onSendClick(event, id) {
    event.preventDefault();
    const url = "http://localhost:8081/api/complaint/answerComplaint";
    const data = {
      id: id,
      ownerAnswer: ownerAnswer,
      clientAnswer: clientAnswer,
    };
    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => console.log(res.data));
  }

  const onOwnerChange = (event) => {
    setOwnerAnswer(event.target.value);
  };

  const onClientAnswer = (event) => {
    setClientAnswer(event.target.value);
  };

  useEffect(() => {
    loadData();
  }, []);

  return <div style={{ backgroundColor: "aquamarine" }}>{table}</div>;
}

export default ComplaintsList;
