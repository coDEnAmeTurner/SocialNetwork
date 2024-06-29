import { useContext, useEffect, useState } from "react";
import "./chatroom.css";
import {
  ChosenConversationContext,
  FrbUserContext,
} from "../../configs/Contexts";
import {
  addDoc,
  collection,
  onSnapshot,
  orderBy,
  query,
  serverTimestamp,
  where,
} from "firebase/firestore";
import { db } from "../../firebase";
import InputField from "../InputFields/Input";
import { BiSend } from "react-icons/bi";
import { v4 as uuidv4 } from "uuid";

function ChatRoom() {
  const [chosenConversation, chosenConversationDispatch] = useContext(
    ChosenConversationContext
  );
  const [messages, setMessages] = useState(null);
  const [frbUser, frbUserDispatcher] = useContext(FrbUserContext);
  const [newMsg, setNewMsg] = useState(null);

  const getMessages = async () => {
    const mesQuery = await query(
      collection(db, "message"),
      where("converId", "==", chosenConversation?.conver?.get("id")),
      orderBy("createdAt", "asc")
    );

    if (mesQuery) {
      onSnapshot(mesQuery, (mesSnap) => {
        if (!mesSnap.empty) setMessages(mesSnap.docs); else setMessages(null);
      });
    } else {
      setMessages(null);
    }
  };

  const submitMessage = (event) => {
    if (newMsg) {
      const newUuid = uuidv4();

      const sendMes = async () => {
        return await addDoc(collection(db, "message"), {
          id: newUuid,
          senderId: frbUser.get("id"),
          receiverId: chosenConversation.partner.get("id"),
          converId: chosenConversation.conver.get("id"),
          content: newMsg,
          createdAt: serverTimestamp(),
        });
      };

      sendMes().then((res) => {
        getMessages();
        setNewMsg("");
      });
    }
  };

  useEffect(() => {
    if (chosenConversation) getMessages();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [chosenConversation]);

  return (
    <div className="chatroom-container">
      {!chosenConversation ? (
        <></>
      ) : (
        <>
          <div className="chatroom-header">
            <img
              src={chosenConversation?.partner?.get("avatar")}
              className="feed-logo-img"
              alt=""
              style={{
                backgroundColor: `rgb(41, 128, 243)`,
                height: "35px",
                width: "35px"
              }}
            />
            <div>
              <div className="author-name" style={{
                marginTop: "5px",
                fontSize: "16px"
              }}>
                u/{chosenConversation?.partner?.get("username")}
              </div>
            </div>
          </div>

          <div className="chatroom-body">
            {messages ? (
              messages.map((mesDoc) => {
                return (
                  <div
                    className="message-box"
                    style={{
                      alignSelf:
                        mesDoc.get("senderId") === frbUser.get("id")
                          ? "flex-end"
                          : "flex-start",
                    }}
                  >
                    {mesDoc.get("content")}
                  </div>
                );
              })
            ) : (
              <h6 className="text text-center">There's no chat activity.</h6>
            )}
          </div>

          <div class="message-input-box">
            <textarea
              class="message-input"
              placeholder="Type a message..."
              style={{
                margin: "0",
              }}
              value={newMsg}
              onChange={(e) => setNewMsg(e.target.value)}
            />

            <button
              class="send-button"
              style={{
                marginTop: "3px",
              }}
              onClick={submitMessage}
            >
              <BiSend />
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default ChatRoom;
