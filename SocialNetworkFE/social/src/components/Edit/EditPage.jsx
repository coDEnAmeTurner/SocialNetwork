import { useContext } from "react";
import InputField from "../InputFields/Input";
import "./edit.css";
import { MyUserContext } from "../../configs/Contexts";

const EditPage = (props) => {
    // eslint-disable-next-line no-unused-vars
    const [user, userDispatch] = useContext(MyUserContext);
    const {setIsEdit} = props;

    return (
        <>
            <form
                className="edit-form"
                data-testid="editForm"
            >
                <section className="edit-container">
                    <div className="close-container">
                        <p className="close-x" >
                            X
                        </p>
                        <button type="submit" className="close">
                            SAVE
                        </button>
                    </div>
                    <div className="edit-profile"> Edit Profile </div>
                    <div className="input-container">
                        <InputField
                            type="text"
                            value={user?.fullName}
                            label="Display name"
                        />
                        <InputField
                            type="date"
                            value={user?.dob}
                            label="Date of Birth"
                        />
                        <label> Profile Picture </label>
                        <section className="input-image-container">
                            <img
                                className={`input-image-selected`}
                                src={user?.avatar}
                                alt=""
                            />
                        </section>
                        <div className="theme-container">
                            <label> Theme </label>
                            <input
                                className="theme-color"
                                type="color"
                            />
                        </div>
                    </div>
                </section>
            </form>
        </>
    );
};

export default EditPage;