import { useContext, useEffect, useState } from "react";
import Overlay from "../../Overlay/Overlay";
import { EditPostContext, FullPostContext } from "../../../configs/Contexts";
import Loading from "../../Loading/Loading";
import MakePost from "../MakePost";
import "./EditPost.css"
import { MakePostMode } from "../../../utils/accessMode";

const EditPost = () => {
    // eslint-disable-next-line no-unused-vars
    const [editPost, editPostDispatch] = useContext(EditPostContext);
    const [onePost, setOnePost] = useState(null);

    useEffect(() => {
        setOnePost(editPost?.post);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <Overlay>
            {
                !onePost ?
                    <Loading
                        loadingType="BeatLoader"
                        color="white"
                        size="10px"
                        loading={true}
                    />: 
                    <section className="fullpost-container">
                        <MakePost accessMode={MakePostMode.forEdit} currentPost={onePost}/>
                    </section>
            }

        </Overlay>
    );
};

export default EditPost;