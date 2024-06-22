import { useContext } from "react";
import "./header.css";
import { IsEditContext, MyUserContext } from "../../configs/Contexts";
import { useNavigate } from "react-router-dom";
import { IoIosArrowRoundBack } from "react-icons/io"
import ReactTimeago from "react-timeago";


const Header = (props) => {
    // eslint-disable-next-line no-unused-vars
    const [user, userDispatch] = useContext(MyUserContext);
    const navigate = useNavigate();
    const [isEdit, isEditDispatch]  = useContext(IsEditContext);

    const handleEdit = () => {
        isEditDispatch({
            type: "toggle",
            payload: !isEdit
        })
    }

    return (
        <>
            <header
                style={{
                    backgroundColor: `${user?.theme}`,
                    backgroundImage: user.background?`url(${user.background})`:`linear-gradient(180deg,${user?.theme} 2%,${user?.theme}, 65%,#181818 100%)`,
                    backgroundPosition: "center"
                }}
            >
                <div className="info-container">
                    <div className="edit-goback">
                        <p className="go-back">
                            <IoIosArrowRoundBack
                                size={"52px"}
                                onClick={() => navigate("/")}
                            />
                        </p>

                        <div className="info-edit" onClick={handleEdit}>
                            Edit
                        </div>

                    </div>
                    <img className="info-ava" src={user?.avatar} alt="" />
                    <div className="info-displayname">
                        {`${user?.fullName}`}
                        <span className="info-username"> (u/{user?.username})</span>
                    </div>
                    <div className="info-age">
                        Age:
                        {
                            user?.createdAt ?
                                <ReactTimeago
                                    className="comment-date"
                                    date={user.createdAt}
                                    minPeriod={60}
                                /> :
                                <>unknown</>
                        }
                    </div>
                </div>
            </header>
        </>
    );
};

export default Header;