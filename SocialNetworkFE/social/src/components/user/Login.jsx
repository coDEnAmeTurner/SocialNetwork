import { useLocation, useNavigate } from "react-router-dom";
import "./login.css";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import InputField from "../InputFields/Input";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { MyUserContext } from "../../configs/Contexts";
import cookie from "react-cookies";
import { AxiosError } from "axios";

export const getCurrentUser = (userDispatch, navigate) => {
  //fetch real api returning current user
  let entireU = {};
  setTimeout(async () => {
    try {
    
      let u = await authApi().get(endpoints["current-user"]);
      
      let ur = await authApi().get(endpoints["get-user-role"]);
      
      
      if (u.status === 200 && ur.status === 200) {
        entireU = { ...u.data, userRole: { ...ur.data }};
        
        if (ur.data.id !== 1) {
          let dRes = await authApi().get(
            endpoints["get-degree-by-userId"](u.data.id)
          );
          if (dRes.status === 200)
            entireU = { ...entireU, degree: { ...dRes.data } };
          
          let rRes = await authApi().get(
            endpoints["get-rank-by-userId"](u.data.id)
          );
          if (rRes.status === 200)
            entireU = { ...entireU, rank: { ...rRes.data } };
        }
        
        if (ur.data.id === 3) {
          let tRes = await authApi().get(
            endpoints["get-title-by-userId"](u.data.id)
          );
          if (tRes.status === 200)
            entireU = { ...entireU, title: { ...tRes.data } };
        }
        
        if (ur.data.id === 2) {
          let sIdRes = await authApi().get(
            endpoints["get-student-id-by-userId"](u.data.id)
          );
          if (sIdRes.status === 200)
            entireU = { ...entireU, studentId: sIdRes.data };
        }
      }
      
      let pc = await authApi().get(endpoints['count-posts']);
      if (pc.status === 200) {
        entireU = {...entireU, postCount: parseInt(pc.data)}
      }
      
    } catch (ex) {
      console.error(ex);
    }
    
    userDispatch({
      type: "login",
      payload: { ...entireU, theme: "#eb9123" },
    });
    navigate("/");
  }, 100);
};

const Login = () => {
  const { state } = useLocation();
  const [username, setUsername] = useState("username");
  const [password, setPassword] = useState("password");
  const { successMsg } = state ? state : { successMsg: null };
  // eslint-disable-next-line no-unused-vars
  const [errorMsg, setErrorMsg] = useState("");
  const navigate = useNavigate();
  // eslint-disable-next-line no-unused-vars
  const [user, userDispatch] = useContext(MyUserContext);

  const loginUser = async (user) => {
    //fetch real api returning token
    try {
      const res = await APIs.post(endpoints["login"], { ...user });

      if (res.status === 200) {
        cookie.save("token", res.data);

        navigate("/");
      } 
    } catch (ex) {
      if (ex instanceof AxiosError) {
        setErrorMsg(ex.response.data);
      }
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const user = {
      username: username,
      password: password,
    };

    loginUser(user);
  };

  return (
    <section className="login-container">
      <div className="login-title"> Log in </div>
      {successMsg ? (
        <div className="register-success">{successMsg}</div>
      ) : (
        <></>
      )}

      {errorMsg ? <div className="loginError">{errorMsg}</div> : <></>}

      <div className="login-input">
        <form onSubmit={handleSubmit}>
          <InputField
            data={username}
            type="text"
            placeholder="Enter username"
            setData={setUsername}
            label="USERNAME"
            classStyle="login-username"
          />
          <InputField
            data={password}
            type="password"
            placeholder="Enter password"
            setData={setPassword}
            label="PASSWORD"
            classStyle="login-password"
          />

          <button type="submit"> Continue </button>
        </form>

        <div className="login-register"> Don't have an account yet? </div>
        <Link className="login-register-link" to="/signup">
          Register now
        </Link>
      </div>
    </section>
  );
};

export default Login;
