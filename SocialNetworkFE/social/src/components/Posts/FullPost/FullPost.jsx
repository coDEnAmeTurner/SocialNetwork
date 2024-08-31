import { useContext, useEffect, useState } from "react";
import Overlay from "../../Overlay/Overlay";
import Posts from "../Posts";
import { EditPostContext, FullPostContext } from "../../../configs/Contexts";
import Loading from "../../Loading/Loading";

const FullPost = () => {
  // eslint-disable-next-line no-unused-vars
  const [fullPost, fullPostDispatch] = useContext(FullPostContext);
  const [editPost, editPostDispatch] = useContext(EditPostContext);
  const [onePost, setOnePost] = useState(null);
  

  useEffect(() => {
    setOnePost(fullPost?.post);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [fullPost]);

  return (
    <Overlay>
      {!onePost? (
        <Loading
          loadingType="BeatLoader"
          color="white"
          size="10px"
          loading={true}
        />
      ) : (
        !editPost.open?
        <section className="fullpost-container">
          <Posts type="fullpost" key={onePost.id} post={onePost} />
        </section>:<></>
      )}
    </Overlay>
  );
};

export default FullPost;
