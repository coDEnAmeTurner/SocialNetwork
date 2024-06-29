import { useContext, useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineHome, AiOutlineMessage } from "react-icons/ai";
import InputField from "../../InputFields/Input";
import "../feed.css";
import {
  FullPostContext,
  IsOpenContext,
  MyUserContext,
  OpenMsgContext,
} from "../../../configs/Contexts";
import { MdCircleNotifications } from "react-icons/md";
import { authApi, endpoints } from "../../../configs/APIs";
import { auth } from "../../../firebase";
import { getEntirePost } from "../HomePage/HomePage";

const FeedHeader = () => {
  const [search, setSearch] = useState("");
  const navigate = useNavigate();
  const [result] = useState([]);
  const [openSearch, setOpenSearch] = useState(false);
  const [user, userDispatch] = useContext(MyUserContext);
  const [isOpen, isOpenDispatch] = useContext(IsOpenContext);
  const [openMsg, openMsgDispatch] = useContext(OpenMsgContext);
  const [openNoti, setOpenNoti] = useState(false);
  const [fullPost, fullPostDispatch] = useContext(FullPostContext);

  const goToProfile = (id) => {
    navigate("/user/" + id);
  };

  const openPost = async (id) => {
    setOpenNoti(false);
    const rawPost = await authApi().get(endpoints["get-post-detail"](id));

    if (rawPost.status === 200) {
      getEntirePost(rawPost.data).then((ePost) => {
        fullPostDispatch({
          type: "toggle",
          payload: {
            post: ePost,
            postId: ePost.id,
            open: true,
          },
        });
      });
    }
  };

  useEffect(() => {
    if (search === "") {
      setOpenSearch(false);
    } else {
      setOpenSearch(true);
    }
  }, [search]);

  return (
    <header className="feed-logo">
      <div
        style={{
          display: "flex",
        }}
      >
        <img
          onClick={() => {
            isOpenDispatch({
              type: "toggle",
            });
          }}
          src={user?.avatar}
          className="feed-logo-img"
          alt=""
          style={{ backgroundColor: `rgb(41, 128, 243)` }}
        />

        <div className="search-container">
          <InputField
            classStyle="search-bar"
            placeholder="🔎 Search for username"
            data={search}
            setData={setSearch}
          />

          {openSearch && (
            <div className="feed-username-display">
              {result?.map((username) => {
                return (
                  <div
                    className="user-container"
                    onClick={() => goToProfile(username._id)}
                  >
                    <img
                      style={{ backgroundColor: `${username.theme}` }}
                      src={username.profilePicture}
                      alt="profile pic"
                      className="username-profile"
                    />
                    <div className="username"> u/{username.username}</div>
                  </div>
                );
              })}
            </div>
          )}
        </div>
      </div>

      <div>
        <div>
          <MdCircleNotifications
            size="45px"
            className="message-outline"
            style={{
              margin: "1rem",
            }}
            onClick={(e) => {
              setOpenNoti(!openNoti);
            }}
          />

          <AiOutlineMessage
            size="45px"
            className="message-outline"
            onClick={() => {
              openMsgDispatch({
                type: "toggle",
              });

              navigate("/chat/");
            }}
            style={{
              margin: "1rem",
            }}
          />
        </div>

        {openNoti && user.invis && user.invis.length > 0 && (
          <div
            className="feed-username-display"
            style={{
              right: "10px",
            }}
          >
            {user.invis.map((invi) => {
              return (
                <div
                  className="noti-container"
                  onClick={(e) => {
                    openPost(invi[0]);
                  }}
                >
                  <div className="user-container">
                    <img
                      src={invi[3]}
                      alt="profile pic"
                      className="username-profile"
                    />

                    <div className="username"> u/{invi[2]}</div>
                  </div>
                  <div
                    style={{
                      textAlign: "start",
                      height: "5rem",
                      paddingLeft: "1rem",
                      fontSize: "1rem",
                    }}
                  >
                    You are invited to an event! Click to readmore:{" "}
                    <b>"{invi[1]}"</b>
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>
    </header>
  );
};

export default FeedHeader;
