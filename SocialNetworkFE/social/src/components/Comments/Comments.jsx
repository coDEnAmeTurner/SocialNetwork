import "./comments.css";
import { BsTrash } from "react-icons/bs";
import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import ReactTimeago from "react-timeago";
import {
  CommentsContext,
  EditComContext,
  MyUserContext,
} from "../../configs/Contexts";
import { authApi, endpoints } from "../../configs/APIs";
import { SiTicktick } from "react-icons/si";

const Comments = (props) => {
  const [openDelete, setOpen] = useState(false);
  const navigate = useNavigate();
  const { id, ownerId, content, username, avaUrl, theme, createdAt, post } =
    props;
  const [comments, commentsDispatch] = useContext(CommentsContext);

  const [user, userDispatch] = useContext(MyUserContext);

  const [hidden, setHidden] = useState(false);

  const [editCom, editComDispatch] = useContext(EditComContext);

  const [comment, setComment] = useState(content);

  const [disabled, setDisabled] = useState(true);

  const goToProfile = (userId) => {
    navigate(`/user/${userId}`);
  };

  const editComment = async () => {
    const comForm = new FormData();
    comForm.append("content", comment);
    const cUpdateRes = await authApi().put(
      endpoints["update-comment"](id),
      comForm,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    if (cUpdateRes.status === 200) {
      comments.find((c) => c.id === id).content = comment;

      commentsDispatch({
        type: "set",
        payload: [...comments],
      });

      setDisabled(true);
    }
  };

  const handleDelete = async () => {
    const cDelRes = await authApi().delete(endpoints["delete-comment"](id));
    if (cDelRes.status === 204) {
      let edited = comments.filter((c) => c.id !== id);
      commentsDispatch({
        type: "set",
        payload: edited,
      });
      setHidden(true);
    }
  };

  const handleEdit = () => {
    setDisabled(!disabled);
  };

  return hidden ? (
    <></>
  ) : (
    <>
      <section className="comments-container">
        <div
          className="comments-info-container"
          style={{ boxShadow: `0px 0px 6px 1px ${theme}` }}
        >
          <div className="comments-author-info">
            <div
              style={{
                display: "flex",
              }}
            >
              <div className="author-ava-container">
                <img
                  className="author-ava"
                  onClick={() => goToProfile(ownerId)}
                  style={{ backgroundColor: `${theme}` }}
                  src={avaUrl}
                  alt="comment avatar"
                />
              </div>

              <div className="author-name">u/{username}</div>

              <ReactTimeago
                className="comment-date"
                date={createdAt}
                minPeriod={60}
              />
            </div>

            {user?.id === ownerId ? (
              <div class="info-edit" onClick={handleEdit}>
                Edit
              </div>
            ) : (
              <></>
            )}

            {ownerId === user.id || post?.author.id === user.id ? (
              <BsTrash
                size={"18px"}
                className="comment-delete"
                color="red"
                onClick={handleDelete}
              />
            ) : (
              <></>
            )}
          </div>
          <div className="comment-content">
            <input type="text" onChange={(e)=>{setComment(e.target.value)}} value={disabled?content:comment} disabled={disabled} style={{
              background: disabled?"#3b3b3b":"white",
              color: disabled?"white":"black",
            }} />
          </div>
          {disabled ? (
            <></>
          ) : (
            <div style={{
              display: "flex",
              justifyContent: "right",
            }}>
              <SiTicktick 
                size="24px"
                className="submit-comment"
                onClick={editComment}
              />

            </div>
          )}
        </div>
      </section>
    </>
  );
};

export default Comments;
