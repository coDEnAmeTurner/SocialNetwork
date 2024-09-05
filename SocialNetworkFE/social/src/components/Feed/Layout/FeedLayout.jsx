/* eslint-disable no-unused-vars */
import "../feed.css";
import SideBar from "../SideBar/FeedSideBar";
import FeedHeader from "../Header/FeedHeader";
import FeedNavBar from "../FeedNavBar/FeedNavBar";
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
                    height: "250px",
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
        </>
      }
    </>
  );
};

export default FeedLayout;
