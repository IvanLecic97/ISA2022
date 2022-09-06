import React from "react";
import { ListGroup } from "react-bootstrap";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { withRouter } from "react-router";

const AdminHomepage = () => {
  return (
    <div>
      <ul className="ul">
        <li>
          <button>
            <Link to="/addNewAdmin">Add new admin</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/deleteRequests">Delete requests</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/registrationRequests">Registration requests</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/reviewsList">Reviews list</Link>
          </button>
        </li>
        <li>
          <button>
            <Link to="/complaintsList">Complaints list</Link>
          </button>
        </li>
      </ul>
    </div>
  );
};

export default AdminHomepage;
