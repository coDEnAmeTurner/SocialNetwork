/* eslint-disable no-unused-vars */
import "../feed.css";
import SideBar from "../SideBar/FeedSideBar";
import FeedHeader from "../Header/FeedHeader";
import FeedNavBar from "../FeedNavBar/FeedNavBar";
import Footer from "../../Footer/Footer";
import MakePost from "../../Posts/MakePost";
import ChatOverview from "../../ChatOverview/ChatOverview";
import { useContext } from "react";
import {
  IsOpenContext,
  IsOpenPostContext,
  MyUserContext,
  OpenMsgContext,
} from "../../../configs/Contexts";

const FeedLayout = ({ children, forEdit = false }) => {
  const [user, userDispatch] = useContext(MyUserContext);
  const [isOpenPost, isOpenPostDispatch] = useContext(IsOpenPostContext);
  const [isOpen, isOpenDispatch] = useContext(IsOpenContext);
  const [openMsg, openMsgDispatch] = useContext(OpenMsgContext);

  return (
    <>
      {/* {user && ( */}
      {
        <>
          {!forEdit ? <SideBar /> : <></>}
          {!openMsg ? (
            <section
              className={`${
                isOpen && !forEdit ? "feed-container-opened" : "feed-container"
              }`}
            >
              {!forEdit ? (
                <>
                  <header
                    style={{
                      backgroundColor: `${user?.theme}`,
                      backgroundImage: user.background
                        ? `url(${user.background})`
                        : `linear-gradient(180deg,${user?.theme} 2%,${user?.theme}, 65%,#181818 100%)`,
                      backgroundPosition: "center",
                      height: "250px"
                    }}
                  >
                    <FeedHeader />
                    <FeedNavBar />

                  </header>
                </>
              ) : (
                <></>
              )}
              {children}
            </section>
          ) : (
            <section
              className={`${
                isOpen ? "feed-container-opened" : "feed-container"
              }`}
            >
              <FeedHeader />
              <ChatOverview />
            </section>
          )}
        </>
      }
    </>
  );
};

export default FeedLayout;
