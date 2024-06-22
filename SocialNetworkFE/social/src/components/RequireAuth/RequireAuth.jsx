import { useContext, useEffect } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import { MyUserContext } from "../../configs/Contexts";
import cookie from "react-cookies";
import { getCurrentUser } from "../user/Login";

//execute authentication
const RequireAuth = ({ children }) => {
  // eslint-disable-next-line no-unused-vars
  const [user, userDispatch] = useContext(MyUserContext);
  const nav = useNavigate();

  useEffect(()=>{
    if (cookie.load("token"))
      getCurrentUser(userDispatch, nav);
    
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return user === null ? (
    <Navigate to="/login"/>
  ) : (
    children
  );
};

export default RequireAuth;