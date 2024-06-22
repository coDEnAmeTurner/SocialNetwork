import { useContext, useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineHome, AiOutlineMessage } from "react-icons/ai";
import InputField from "../../InputFields/Input";
import "../feed.css";
import {
  IsOpenContext,
  MyUserContext,
  OpenMsgContext,
} from "../../../configs/Contexts";

const FeedHeader = () => {
  const [search, setSearch] = useState("");
  const navigate = useNavigate();
  const [result] = useState([]);
  const [openSearch, setOpenSearch] = useState(false);
  const [user, userDispatch] = useContext(MyUserContext);
  const [isOpen, isOpenDispatch] = useContext(IsOpenContext);
  const [openMsg, openMsgDispatch] = useContext(OpenMsgContext);

  const goToProfile = (id) => {
    navigate("/user/" + id);
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

        {openMsg ? (
          <AiOutlineHome
            size="24px"
            className="message-outline"
            onClick={() =>
              openMsgDispatch({
                type: "toggle",
              })
            }
          />
        ) : (
          <AiOutlineMessage
            size="24px"
            className="message-outline"
            onClick={() =>
              openMsgDispatch({
                type: "toggle",
              })
            }
          />
        )}

    </header>
  );
};

export default FeedHeader;
