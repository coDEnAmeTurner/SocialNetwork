import "./custom.scss";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./components/user/Login";
import Signup from "./components/user/Signup";
import { useReducer, useState } from "react";
import {
  IsEditReducer,
  IsOpenPostReducer,
  IsOpenReducer,
  MyUserReducer,
  OpenMsgReducer,
} from "./configs/Reducers";
import {
  EditPostContext,
  IsEditContext,
  IsOpenContext,
  IsOpenPostContext,
  MyUserContext,
  OpenMsgContext,
} from "./configs/Contexts";
import HomePage from "./components/Feed/HomePage/HomePage";
import RequireAuth from "./components/RequireAuth/RequireAuth";
import UserProfile from "./components/UserProfile/UserProfile";
import MakePost from "./components/Posts/MakePost";
import TestSpinner from "./components/TestComponents/TestSpinner";

function App() {
  const [user, userDispatch] = useReducer(MyUserReducer, null);
  const [isOpenPost, isOpenPostDispatch] = useReducer(IsOpenPostReducer, false);
  const [isOpen, isOpenDispatch] = useReducer(IsOpenReducer, false);
  const [openMsg, openMsgDispatch] = useReducer(OpenMsgReducer, false);
  const [isEdit, isEditDispatch] = useReducer(IsEditReducer, false);

  return (
    <div className="App">
      <Router>
        <IsEditContext.Provider value={[isEdit, isEditDispatch]}>
          <MyUserContext.Provider value={[user, userDispatch]}>
            <EditPostContext.Provider value={[null, null]}>
              <IsOpenPostContext.Provider
                value={[isOpenPost, isOpenPostDispatch]}
              >
                <IsOpenContext.Provider value={[isOpen, isOpenDispatch]}>
                  <OpenMsgContext.Provider value={[openMsg, openMsgDispatch]}>
                    <Routes>
                      <Route
                        path="/"
                        element={
                          <RequireAuth>
                            <HomePage />
                          </RequireAuth>
                        }
                      />
                      <Route path="/login" element={<Login />} />
                      <Route path="/signup" element={<Signup />} />
                      <Route path="/make-a-post" element={
                        <RequireAuth>
                          <MakePost />

                        </RequireAuth>
                        } />
                      <Route
                        path={`/user/:id`}
                        element={
                          <RequireAuth>
                            <UserProfile />
                          </RequireAuth>
                        }
                      />
                      <Route path="/test" element={<TestSpinner />} />
                    </Routes>
                  </OpenMsgContext.Provider>
                </IsOpenContext.Provider>
              </IsOpenPostContext.Provider>
            </EditPostContext.Provider>
          </MyUserContext.Provider>
        </IsEditContext.Provider>
      </Router>
    </div>
  );
}

export default App;
