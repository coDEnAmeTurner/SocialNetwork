import { useContext, useEffect, useState } from "react";
import { ChosenConversationContext, FrbUserContext, MyUserContext, PickedConverContext } from "../../configs/Contexts";
import "./ConverPreview.css";
import { db } from "../../firebase";
import {
  collection,
  limit,
  onSnapshot,
  orderBy,
  query,
  where,
} from "firebase/firestore";
import { IoPlayOutline } from "react-icons/io5";

const ConverPreview = ({ conver, id}) => {
  const [partner, setPartner] = useState(null);
  const [lastMes, setLastMes] = useState(null);
  const [frbUser, frbUserDispatch] = useContext(FrbUserContext);
  const [chosenConversation, chosenConversationDispatch] = useContext(ChosenConversationContext);
  const [pickedConverKey, pickedConverKeyDispatch ] = useContext(PickedConverContext);

  const getConverPartner = async () => {
    let partnerId =
      frbUser.get("id") === conver.get("userId")
        ? conver.get("partnerId")
        : conver.get("userId");

    const partnerQ = await query(
      collection(db, "users"),
      where("id", "==", partnerId)
    );

    if (partnerQ)
      onSnapshot(partnerQ, (partnerSnap) => {
        setPartner(partnerSnap.docs[0]);
      });
  };

  const getConverLastMes = async () => {
    const lastMesQ = await query(
      collection(db, "message"),
      where("converId", "==", conver.get("id")),
      orderBy("createdAt", "desc"),
      limit(1)
    );

    if (lastMesQ)
      onSnapshot(lastMesQ, (lastMesSnap) => {
        setLastMes(lastMesSnap.docs[0]);
      });
  };

  const openConver = ()=>{
    chosenConversationDispatch({
      type: "set",
      payload: {
        partner: partner,
        conver: conver,
      }
    })
  }

  useEffect(() => {
    getConverPartner();
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    getConverLastMes();
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
      <div
        className="converPreview-container"
        onClick={(e)=>{
          pickedConverKeyDispatch({
            type: "set",
            payload: id
          });
          openConver(e);
        }}
        id={id}
      >
        <img
          src={partner?.get("avatar")}
          className="feed-logo-img"
          alt=""
          style={{
            backgroundColor: `rgb(41, 128, 243)`,
            minWidth: "70px",
          }}
        />
        <div>
          <div className="author-name">u/{partner?.get("username")}</div>
          <p className="preview-content">{lastMes?lastMes.get("content"):"Nothing to be shown yet"}</p>
        </div>
      </div>
  );
};

export default ConverPreview;
