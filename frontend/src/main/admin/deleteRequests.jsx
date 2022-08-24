import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";

function DeleteRequests() {
  const [loadedData, setLoadedData] = useState([]);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [reason, setReason] = useState("");
  const [reasonDisapprove, setReasonDisapprove] = useState("");

  const table = (
    <Table>
      <thead>
        <th>#</th>
        <th>Username</th>
        <th>Reason</th>
        <th>Approve</th>
        <th>Reject</th>
      </thead>
      <tbody>
        {loadedData.map((value) => (
          <tr key={value.id}>
            <th>#</th>
            <th>{value.userUsername}</th>
            <th>{value.reason}</th>
            <th>
              <input
                onChange={(event) => onReasonChange(event)}
                placeholder="Approval reason"
              />
              <br />
              <button
                onClick={(event) =>
                  onApproveClick(event, value.userUsername, value.id)
                }
              >
                Approve
              </button>
            </th>
            <th>
              <input
                onChange={(event) => onReasonDisapproveChange(event)}
                placeholder="Disapproval reason"
              />
              <button
                onClick={(event) =>
                  onDisApproveClick(event, value.userUsername, value.id)
                }
              >
                Disapprove
              </button>
            </th>
          </tr>
        ))}
      </tbody>
    </Table>
  );

  const onReasonChange = (event) => {
    setReason(event.target.value);
    console.log(reason);
  };

  const onReasonDisapproveChange = (event) => {
    setReasonDisapprove(event.target.value);
  };

  const loadData = async () => {
    const url = "http://localhost:8081/api/user/getDeleteRequests";

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

  function onApproveClick(event, username, requestId) {
    event.preventDefault();
    const url = "http://localhost:8081/api/user/deleteUser";
    const data = {
      username: username,
      deleteRequestId: requestId,
      reason: reason,
      allowed: true,
    };

    axios
      .post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => console.log(res.data));
  }

  function onDisApproveClick(event, username, requestId) {
    event.preventDefault();
    const url = "http://localhost:8081/api/user/deleteUser";
    const data = {
      username: username,
      deleteRequestId: requestId,
      reason: reason,
      allowed: false,
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

  return <div className="deleteAccForm">{loadedData && table}</div>;
}

export default DeleteRequests;
