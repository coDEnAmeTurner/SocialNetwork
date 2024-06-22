import "./sidebar.css";
import { RiCopperCoinLine, RiCake3Line } from "react-icons/ri";
import SideNavBar from "../SideNavBar/SideNavBar";
import { useContext, useEffect, useState } from "react";
import { IsOpenContext, MyUserContext } from "../../../configs/Contexts";
import ReactTimeago from "react-timeago";
import { BiCurrentLocation } from "react-icons/bi";

const SideBar = () => {
  // eslint-disable-next-line no-unused-vars
  const [user, userDispatch] = useContext(MyUserContext);
  const [isOpen, isOpenDispatch] = useContext(IsOpenContext);

  return (
    <>
      <div className={`${isOpen ? "feed-sidebar-opened" : "feed-sidebar"}`}>
        <header
          style={{
            backgroundColor: `${user?.theme}`,
            backgroundImage: user.background?`url(${user.background})`:`linear-gradient(180deg,${user?.theme} 2%,${user?.theme}, 65%,#181818 100%)`,
            backgroundPosition: "center",
        }}
        >
          <div
            className="sidebar-close"
            onClick={() => {
              isOpenDispatch({
                type: "toggle",
              });
            }}
          >
            X
          </div>
        </header>

        <section className="sidebar-container">
          <div className="sidebar-ava">
            <img
              src={user?.avatar}
              className="sidebar-img"
              alt="profile pic"
              style={{
                boxShadow: `0px 0px 6px 1px ${
                  user?.theme ? user.theme : "#df811c"
                }`,
              }}
            />
          </div>

          <div className="text-4xl font-bold">u/{user?.username}</div>

          <div className="sidebar-info">
            <div className="karmas-container">
              <RiCopperCoinLine
                size={"24px"}
                color="rgb(2, 88, 158)"
                className="karmas-logo"
              />
              <span className="karmas-title">
                {user?.postCount ? user.postCount : 0}
                <div className="karmas-header">Post Count </div>
              </span>
            </div>

            <div className="age-container">
              <RiCake3Line
                size={"24px"}
                color="rgb(2, 88, 158)"
                className="age-logo"
              />
              <span className="age-title">
                {user?.createdAt ? (
                  <ReactTimeago
                    className="comment-date"
                    date={user.createdAt}
                    minPeriod={60}
                  />
                ) : (
                  <>unknown</>
                )}
                <div className="age-header"> Age </div>{" "}
              </span>
            </div>
          </div>

          <div className="sidebar-nav">
            <SideNavBar id={user?.id} />
          </div>
        </section>
      </div>
    </>
  );
};

export default SideBar;
