import { useContext, useEffect, useReducer, useState } from "react";
import InputField from "../InputFields/Input";
import { authApi, endpoints } from "../../configs/APIs";
import "./ConverSideBar.css";
import ConverPreview from "./ConverPreview";
import {
  addDoc,
  and,
  collection,
  getDocs,
  onSnapshot,
  or,
  query,
  startAt,
  where,
} from "firebase/firestore";
import { db } from "../../firebase";
import {
  ChosenConversationContext,
  ConversationsContext,
  FrbUserContext,
  MyUserContext,
  PickedConverContext,
} from "../../configs/Contexts";
import { v4 as uuidv4 } from "uuid";
import { VscClose } from "react-icons/vsc";
import { IoExitOutline } from "react-icons/io5";
import { useNavigate } from "react-router-dom";
import { PickedConverReducer } from "../../configs/Reducers";

const ConverSideBar = () => {
  const [search, setSearch] = useState(null);
  const [openSearch, setOpenSearch] = useState(false);
  const [userList, setUserList] = useState(null);
  const [result, setResult] = useState(null);
  const [user, userDispatch] = useContext(MyUserContext);
  const [frbUser, frbUserDispatch] = useContext(FrbUserContext);
  const [conversations, conversationsDispatch] =
    useContext(ConversationsContext);
  const [chosenConversation, chosenConversationDispatch] = useContext(
    ChosenConversationContext
  );
  const [pickedConverKey, pickedConverKeyDispatch] = useReducer(
    PickedConverReducer,
    null
  );

  const navigate = useNavigate();

  useEffect(() => {
    const fetchUser = async () => {
      const uSnap = await getDocs(collection(db, "users"));

      if (!uSnap.empty) {
        setResult(
          uSnap.docs.filter(
            (uDoc) =>
              uDoc?.get("username")?.includes(search) &&
              uDoc?.get("id") !== frbUser.get("id")
          )
        );
      }
    };

    fetchUser();
  }, [search]);

  const getConversations = async () => {
    const converQ = await query(
      collection(db, "conversation"),
      or(
        where("userId", "==", frbUser?.get("id")),
        where("partnerId", "==", frbUser?.get("id"))
      )
    );

    if (converQ)
      onSnapshot(converQ, (converS) => {
        conversationsDispatch({
          type: "set",
          payload: converS.docs,
        });
      });
  };

  const chooseConver = async (partnerDoc) => {
    const getConver = () => {
      return query(
        collection(db, "conversation"),
        and(
          or(
            where("userId", "==", frbUser.get("id")),
            where("userId", "==", partnerDoc.get("id"))
          ),
          or(
            where("partnerId", "==", partnerDoc.get("id")),
            where("partnerId", "==", frbUser.get("id"))
          )
        )
      );
    };

    let converQuery = getConver();

    let converSnap = await getDocs(converQuery);

    if (converSnap)
      if (!converSnap.empty)
        chosenConversationDispatch({
          type: "set",
          payload: {
            partner: partnerDoc,
            conver: converSnap.docs[0],
          },
        });
      else {
        const newUuid = uuidv4();

        await addDoc(collection(db, "conversation"), {
          userId: frbUser.get("id"),
          partnerId: partnerDoc.get("id"),
          id: newUuid,
        });

        getConversations();

        let converQuery = getConver();

        let converSnap = await getDocs(converQuery);

        if (converSnap && !converSnap.empty)
          chosenConversationDispatch({
            type: "set",
            payload: {
              partner: partnerDoc,
              conver: converSnap.docs[0],
            },
          });
      }

    setOpenSearch(false);
  };

  useEffect(() => {
    getConversations();
  }, []);

  return (
    <div className="conversation-sidebar">
      <div
        style={{
          display: "flex",
          width: "100%",
          justifyContent: "left",
        }}
      >
        <img
          src={frbUser?.get("avatar")}
          className="feed-logo-img"
          alt=""
          style={{
            backgroundColor: `rgb(41, 128, 243)`,
            minWidth: "75px"
          }}
        />
        <InputField
          classStyle="search-bar"
          placeholder="🔎 Search for username"
          value={search}
          setData={setSearch}
          inputType="text"
          setOpenSearch={setOpenSearch}
        />
        <VscClose
          onClick={() => {
            setSearch("");
            setOpenSearch(false);
          }}
          className="closeSearch"
        />
        <IoExitOutline
          className="closeChat"
          onClick={() => {
            navigate("/");
          }}
        />
      </div>

      {openSearch && (
        <div className="feed-username-display">
          {result?.map((partnerDoc) => {
            return (
              <div
                className="user-container"
                onClick={() => {
                  chooseConver(partnerDoc);
                }}
              >
                <img
                  src={partnerDoc.get("avatar")}
                  alt="profile pic"
                  className="username-profile"
                />
                
                <div className="username"> u/{partnerDoc.get("username")}</div>
              </div>
            );
          })}
        </div>
      )}

      <PickedConverContext.Provider
        value={[pickedConverKey, pickedConverKeyDispatch]}
      >
        {conversations?.map((c, index) => {
          return <ConverPreview conver={c} id={index} />;
        })}
      </PickedConverContext.Provider>
    </div>
  );
};

export default ConverSideBar;
