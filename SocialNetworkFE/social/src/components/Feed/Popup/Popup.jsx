import "./popups.css";
import Overlay from "../../Overlay/Overlay";

const Popup = (props) => {
  const { h1, h2, button2 } = props;

  

  return (
    <Overlay>
      <div className="popups-close">
        X
      </div>
      <div className="popups-h1">{h1}</div>
      <div className="popups-h2">{h2}</div>
      <button className="delete">
        {" "}
        {button2}{" "}
      </button>
    </Overlay>
  );
};

export default Popup;