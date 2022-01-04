import React, { Component } from "react";
import { ListGroup } from "react-bootstrap";

class HomepageClient extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  profileClick(event) {
    event.preventDefault();
    window.location.href = "/updateClient";
  }

  render() {
    return (
      <div style={{ alignContent: "center" }}>
        <ListGroup style={{ width: "220px", alignContent: "center" }}>
          <button onClick={this.profileClick}>Profil klijenta</button>
        </ListGroup>
      </div>
    );
  }
}

export default HomepageClient;
