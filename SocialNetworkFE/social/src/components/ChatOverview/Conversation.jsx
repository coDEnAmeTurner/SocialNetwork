import { useState } from "react";
import "./chatroom.css";

const Conversation = (props) => {
  const [user] = useState(null);
  

  return (
    <section className="contact-container">
      <div className="contact-img-container">
        <img
          src={user?.profilePicture}
          alt="profile pic"
          className="contact-img"
          style={{ backgroundColor: `${user?.theme}` }}
        />
      </div>
      <div className="preview-container">
        <div className="preview-username">{user?.username}</div>
      </div>
    </section>
  );
};

export default Conversation;