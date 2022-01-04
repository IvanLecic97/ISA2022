import "./App.css";
import Registration from "./main/registration";
import { render } from "react-dom";
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  useNavigate,
} from "react-router-dom";

import React, { Component } from "react";
import Confirm from "./main/confirm";
import Login from "./main/login";
import HomepageClient from "./main/client/homepageClient";
import UpdateClient from "./main/client/updateClient";

class App extends React.Component {
  render() {
    const value = true;
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
        </nav>
        <Routes>
          <Route path="/registration" element={<Registration />} />
          <Route path="/confirm" element={<Confirm />} />
          <Route path="/login" element={<Login />} />
          <Route path="/homepageClient" element={<HomepageClient />} />
          <Route path="/updateClient" element={<UpdateClient />} />
        </Routes>
      </React.Fragment>
    );
  }
}

export default App;
