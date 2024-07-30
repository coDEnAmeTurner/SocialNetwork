import "./custom.scss";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./components/user/Login";
import Signup from "./components/user/Signup";
import { useReducer, useState } from "react";
import {
  ChosenConversationReducer,
  ConversationsReducer,
  FrbUserReducer,
  FullPostReducer,
  IsEditReducer,
  IsOpenPostReducer,
  IsOpenReducer,
  MyUserReducer,
  OpenMsgReducer,
  RoomReducer,
} from "./configs/Reducers";
import {
  ChosenConversationContext,
  ConversationsContext,
  EditPostContext,
  FrbUserContext,
  FullPostContext,
  IsEditContext,
  IsOpenContext,
  IsOpenPostContext,
  MyUserContext,
  OpenMsgContext,
  RoomContext,
} from "./configs/Contexts";
import HomePage from "./components/Feed/HomePage/HomePage";
import RequireAuth from "./components/RequireAuth/RequireAuth";
import UserProfile from "./components/UserProfile/UserProfile";
import MakePost from "./components/Posts/MakePost";
import TestSpinner from "./components/TestComponents/TestSpinner";
import ChatRoom from "./components/ChatOverview/ChatRoom";
import ChatHome from "./components/ChatOverview/ChatHome";
import LandingPage from "./components/LandingPage/LandingPage";

function App() {
  const [user, userDispatch] = useReducer(MyUserReducer, null);
  const [isOpenPost, isOpenPostDispatch] = useReducer(IsOpenPostReducer, false);
  const [isOpen, isOpenDispatch] = useReducer(IsOpenReducer, false);
  const [openMsg, openMsgDispatch] = useReducer(OpenMsgReducer, false);
  const [isEdit, isEditDispatch] = useReducer(IsEditReducer, false);
  const [room, roomDispatch] = useReducer(RoomReducer, null);
  const [frbUser, frbUserDispatch] = useReducer(FrbUserReducer, null);
  const [conversations, conversationsDispatch] = useReducer(
    ConversationsReducer,
    null
  );
  const [chosenConversation, chosenConversationDispatch] = useReducer(
    ChosenConversationReducer,
    null
  );
  const [fullPost, fullPostDispatch] = useReducer(FullPostReducer, {
    postId: null,
    open: false,
  });

  return (
    <div className="App">
      <Router>
        <ChosenConversationContext.Provider
          value={[chosenConversation, chosenConversationDispatch]}
        >
          <ConversationsContext.Provider
            value={[conversations, conversationsDispatch]}
          >
            <FrbUserContext.Provider value={[frbUser, frbUserDispatch]}>
              <RoomContext.Provider value={[room, roomDispatch]}>
                <IsEditContext.Provider value={[isEdit, isEditDispatch]}>
                  <MyUserContext.Provider value={[user, userDispatch]}>
                    <FullPostContext.Provider value={[null, null]}>
                      <EditPostContext.Provider value={[null, null]}>
                        <IsOpenPostContext.Provider
                          value={[isOpenPost, isOpenPostDispatch]}
                        >
                          <IsOpenContext.Provider
                            value={[isOpen, isOpenDispatch]}
                          >
                            <OpenMsgContext.Provider
                              value={[openMsg, openMsgDispatch]}
                            >
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
                                <Route
                                  path="/make-a-post"
                                  element={
                                    <RequireAuth>
                                      <MakePost />
                                    </RequireAuth>
                                  }
                                />
                                <Route
                                  path={`/user/:id`}
                                  element={
                                    <RequireAuth>
                                      <UserProfile />
                                    </RequireAuth>
                                  }
                                />
                                <Route
                                  path="/chat/"
                                  element={
                                    <RequireAuth>
                                      <ChatHome />
                                    </RequireAuth>
                                  }
                                />
                                <Route
                                  path="/landingPage"
                                  element={<LandingPage />}
                                />
                                <Route path="/test" element={<TestSpinner />} />
                              </Routes>
                            </OpenMsgContext.Provider>
                          </IsOpenContext.Provider>
                        </IsOpenPostContext.Provider>
                      </EditPostContext.Provider>
                    </FullPostContext.Provider>
                  </MyUserContext.Provider>
                </IsEditContext.Provider>
              </RoomContext.Provider>
            </FrbUserContext.Provider>
          </ConversationsContext.Provider>
        </ChosenConversationContext.Provider>
      </Router>
    </div>
  );
}

export default App;
