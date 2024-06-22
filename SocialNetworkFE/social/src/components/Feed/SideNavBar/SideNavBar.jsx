import { Link, useNavigate } from "react-router-dom";
import { CgProfile } from "react-icons/cg";
import { BiExit } from "react-icons/bi";
import "./navbar.css";
import { useContext } from "react";
import { MyUserContext } from "../../../configs/Contexts";
import cookie from "react-cookies"

const SideNavBar = (props) => {
  const { id } = props;
  const [user, userDispatch] = useContext(MyUserContext);
  const nav = useNavigate();

  const logout = () => {
    userDispatch({
      type: 'logout'
    });
    cookie.remove('token');
    nav('/login');
  }

  return (
    <nav className="navbar-container">
      <div className="navbar-profile">
        <Link to={`/user/${id}`}>
          <CgProfile size="24px" color="grey" className="navbar-profile-icon" />
          My profile
        </Link>
      </div>
      <div className="navbar-logout" onClick={logout}>
        <BiExit color="grey" size="24px" className="navbar-logout-icon" />
        Log out{" "}
      </div>
    </nav>
  );
};

export default SideNavBar;