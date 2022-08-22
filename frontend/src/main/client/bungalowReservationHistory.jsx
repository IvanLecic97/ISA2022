import React, { useState, useEffect } from "react";
import Table from "react-bootstrap/Table";

function BungalowReservationHistory() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [loadedData, setLoadedData] = useState([]);

  const loadData = async () => {
    const url =
      "http://localhost:8081/api/reservation/getClientsReservedBungalows";
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

  function addReview(event, id, username, resId) {
    event.preventDefault();
    localStorage.setItem("attractionId", id);
    localStorage.setItem("owner_username", username);
    localStorage.setItem("reservation_id", resId);
    window.open("/addReview");
  }

  useEffect(() => {
    loadData();
  }, []);

  return (
    <div style={{ backgroundColor: "antiquewhite" }}>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
            <th>Price</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Give feedback</th>
            <th>Make a complaint</th>
          </tr>
        </thead>
        <tbody>
          {loadedData &&
            loadedData.map((value) => (
              <tr key={value.startDate}>
                <th>{}</th>
                <th>{value.name}</th>
                <th>{value.city}</th>
                <th>{value.country}</th>
                <th>{value.price}</th>
                <th>{value.startDate}</th>
                <th>{value.endDate}</th>
                <th>
                  <button
                    onClick={(event) =>
                      addReview(
                        event,
                        value.attractionId,
                        value.ownerUsername,
                        value.id
                      )
                    }
                  >
                    Feedback
                  </button>
                </th>
                <th>
                  <button>Complaint</button>
                </th>
              </tr>
            ))}
        </tbody>
      </Table>
    </div>
  );
}

export default BungalowReservationHistory;
