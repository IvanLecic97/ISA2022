import "./App.css";
import Registration from "./main/registration";

import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  useNavigate,
} from "react-router-dom";

import React, { useState } from "react";
import Confirm from "./main/confirm";
import Login from "./main/login";
import HomepageClient from "./main/client/homepageClient";
import UpdateClient from "./main/client/updateClient";
import UnregisteredHomepage from "./main/unregisteredHomepage";
import AdminHomepage from "./main/admin/adminHomepage";
import SearchEntities from "./main/client/searchEntities";

const App = () => {
  const [value, setValue] = useState(true);
  localStorage.setItem("currList", null);
  localStorage.setItem("prevList", null);

  let navigate = useNavigate();

  let onLogout = (event) => {
    event.preventDefault();
    console.log(localStorage.getItem("username"));
    console.log(localStorage.getItem("token"));
    console.log(localStorage.getItem("role"));
    localStorage.removeItem("username");
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    console.log(localStorage.getItem("username"));
    console.log(localStorage.getItem("token"));
    console.log(localStorage.getItem("role"));
    navigate("/unregisteredHomepage");
  };

  return (
    <React.Fragment>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div>
          <button>
            {value && <Link to="/registration">Client registration</Link>}
          </button>
        </div>
        <div>
          <button>
            <Link to="/login">Login</Link>
          </button>
        </div>
        <div>
          <button onClick={onLogout}>Logut</button>
        </div>
      </nav>
      <body className="body"></body>
      <Routes>
        <Route path="/registration" element={<Registration />} />
        <Route path="/confirm" element={<Confirm />} />
        <Route path="/login" element={<Login />} />
        <Route path="/homepageClient" element={<HomepageClient />} />
        <Route path="/updateClient" element={<UpdateClient />} />
        <Route
          path="/unregisteredHomepage"
          element={<UnregisteredHomepage />}
        />
        <Route path="/homepageAdmin" element={<AdminHomepage />} />
        <Route path="/searchEntities" element={<SearchEntities />} />
      </Routes>
    </React.Fragment>
  );
};

export default App;
