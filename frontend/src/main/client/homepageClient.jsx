import React, { Component } from "react";
import { ListGroup } from "react-bootstrap";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { withRouter } from "react-router";

class HomepageClient extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  profileClick = () => {
    const { history } = this.props;
    history.push("/updateClient");
  };

  render() {
    return (
      <div style={{ alignContent: "center" }}>
        <ListGroup style={{ width: "220px", alignContent: "center" }}>
          <button>
            <Link to="/updateClient">Profil klijenta</Link>
          </button>
        </ListGroup>
      </div>
    );
  }
}

export default HomepageClient;
