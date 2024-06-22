import "./chatroom.css";
import { useContext, useState } from "react";
import Conversation from "./Conversation";
import Loading from "../Loading/Loading";
import { MyUserContext } from "../../configs/Contexts";

const ChatOverview = () => {
    //dummy data
    const [conversation] = useState([]);
    const [isLoading] = useState(false);
    //io("ws://localhost:8900")
    const [user] = useContext(MyUserContext);

    return (
        <section className="message-container">
            <ul className="contact-list">
                <li> Chats </li>
            </ul>
            <div className="contact-container-div">
                {isLoading ? (
                    <Loading
                        loadingType="ClipLoader"
                        color="white"
                        size="32px"
                        loading={isLoading}
                    />
                ) : (
                    <>
                        {conversation.map((conversation) => {
                            return (
                                <div
                                    className="conversation-container"

                                >
                                    <Conversation
                                        key={conversation._id}
                                        conversation={conversation}
                                        currentUser={user}
                                    />
                                </div>
                            );
                        })}
                    </>
                )}
            </div>
        </section>
    );
};

export default ChatOverview;