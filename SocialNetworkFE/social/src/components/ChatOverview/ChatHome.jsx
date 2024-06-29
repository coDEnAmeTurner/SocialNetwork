import ChatRoom from "./ChatRoom";
import ConverSideBar from "./ConverSideBar";
import "./ChatHome.css"


const ChatHome = () => {
  return (
    <div className="chathome-container">

      <ConverSideBar />

      <ChatRoom />
      
    </div>
  );
};

export default ChatHome;
