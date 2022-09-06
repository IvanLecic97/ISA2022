import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";

function ReviewsList() {
  const token = localStorage.getItem("token");
  const [list, setList] = useState([]);

  const loadData = async () => {
    const url = "http://localhost:8081/api/review/getUnseenByAdmin";

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
        <th>Owner rating</th>
        <th>Owner review</th>
        <th>Attraction rating</th>
        <th>Attraction review</th>
        <th>Approve review</th>
        <th>Reject review</th>
      </thead>
      <tbody>
        {list.map((value) => (
          <tr key={value.id}>
            <th>#</th>
            <th>{value.ownerRating}</th>
            <th>{value.ownerReview}</th>
            <th>{value.attractionRating}</th>
            <th>{value.attractionReview}</th>
            <th>
              <button onClick={(event) => onApproveClick(event, value.id)}>
                Approve
              </button>
            </th>
            <th>
              <button onClick={(event) => onRejectClick(event, value.id)}>
                Reject
              </button>
            </th>
          </tr>
        ))}
      </tbody>
    </Table>
  );

  function onApproveClick(event, id) {
    event.preventDefault();

    const url = "http://localhost:8081/api/review/setApproved";
    const data = {
      id: id,
      approved: true,
    };

    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => console.log(res.data));
  }

  function onRejectClick(event, id) {
    event.preventDefault();
    const url = "http://localhost:8081/api/review/setApproved";
    const data = {
      id: id,
      approved: false,
    };
    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => console.log(res.data));
  }

  useEffect(() => {
    loadData();
  }, []);

  return <div style={{ backgroundColor: "aquamarine" }}>{table}</div>;
}

export default ReviewsList;
