import React, { Component } from "react";
import { ListGroup } from "react-bootstrap";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { withRouter } from "react-router";

const HomepageClient = () => {
  return (
    <div>
      <ul className="ul">
        <li>
          <button>
            <Link to="/updateClient">Profil klijenta</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/bungalowReservationHistory">
              Bungalow reservation history
            </Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/shipsReservationHistory">Ship reservation history</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/instructorReservationHistory">
              Instructor reservation history
            </Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/searchEntities">Search entities</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/deleteAccount">Delete account</Link>
          </button>
        </li>
      </ul>
    </div>
  );
};

export default HomepageClient;
