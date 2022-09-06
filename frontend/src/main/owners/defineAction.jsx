import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import AddShip from "./addShip";
import ReactDOM from "react-dom";
import SaveAction from "./saveAction";

function DefineAction() {
  const token = localStorage.getItem("token");
  const [loadedData, setLoadedData] = useState([]);

  const loadData = async () => {
    const url = "http://localhost:8081/api/attraction/getUsersAttractions";
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

  const table = (
    <div style={{ backgroundColor: "aquamarine" }}>
      {loadedData && (
        <Table>
          <thead>
            <th>#</th>
            <th>Name</th>
            <th>Address</th>
            <th>City</th>
            <th>Country</th>
            <th>Create discount</th>
            <th>Delete</th>
          </thead>
          <tbody>
            {loadedData.map((value) => (
              <tr key={value.id}>
                <th>#</th>
                <th>{value.name}</th>
                <th>{value.address}</th>
                <th>{value.city}</th>
                <th>{value.country}</th>
                <th>
                  <button onClick={() => onCreateClick(value.id)}>
                    Create
                  </button>
                </th>
                <th>
                  <button>Delete</button>
                </th>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </div>
  );

  const element = (
    <div>
      <AddShip />
    </div>
  );

  function onCreateClick(id) {
    ReactDOM.render(
      <SaveAction attractionId={id} />,
      document.getElementById("root")
    );
  }

  useEffect(() => {
    loadData();
  }, []);

  return <div>{table}</div>;
}

export default DefineAction;
