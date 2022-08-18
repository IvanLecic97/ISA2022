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
import ReservationForm from "./main/client/reservationForm";
import ReservationFormShip from "./main/client/reservationFormShip";
import ReservationFormFishing from "./main/client/reservationFormFishing";
import DiscountedPage from "./main/client/discountedPage";
import AddBungalow from "./main/owners/addBungalow";
import OwnerRegistration from "./main/ownerRegistration";
import AddShip from "./main/owners/addShip";
import AddInstructor from "./main/owners/addInstructor";
import GetOwnAttractions from "./main/owners/getOwnAttractions";
import SetDiscount from "./main/owners/setDiscount";
import RegistrationRequests from "./main/admin/registrationRequests";
import RegistrationEntity from "./main/admin/registrationEntity";
import Testing from "./main/testing";

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
        <Route path="/reservationForm" element={<ReservationForm />} />
        <Route path="/reservationFormShip" element={<ReservationFormShip />} />
        <Route
          path="/reservationFormFishing"
          element={<ReservationFormFishing />}
        />
        <Route path="/discountedPage" element={<DiscountedPage />} />

        <Route path="/addBungalow" element={<AddBungalow />} />
        <Route path="/ownerRegistration" element={<OwnerRegistration />} />
        <Route path="/addShip" element={<AddShip />} />
        <Route path="addInstructor" element={<AddInstructor />} />
        <Route path="/getOwnAttractions" element={<GetOwnAttractions />} />
        <Route path="/setDiscount" element={<SetDiscount />} />
        <Route
          path="/registrationRequests"
          element={<RegistrationRequests />}
        />
        <Route path="/registrationEntity" element={<RegistrationEntity />} />
        <Route path="/testing" element={<Testing />} />
      </Routes>
    </React.Fragment>
  );
};

export default App;
