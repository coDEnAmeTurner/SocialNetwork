import { useContext } from "react";
import "./footer.css";
import { IsOpenPostContext } from "../../configs/Contexts";

const Footer = () => {
  const [isOpenPost, isOpenPostDispatch] = useContext(IsOpenPostContext);

  return (
    <footer className="footer">
      <div className="footer-title" onClick={()=>{
        isOpenPostDispatch({
        "type": "toggle"
      })}}>
        {console.log('isOpenPost', isOpenPost)}
        {isOpenPost ? "x" : "+"}
      </div>
    </footer>
  );
};

export default Footer;