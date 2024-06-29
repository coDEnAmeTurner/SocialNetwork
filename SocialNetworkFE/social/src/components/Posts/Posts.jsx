/* eslint-disable no-unused-vars */
import { useNavigate } from "react-router-dom";
import "./post.css";
import "../Feed/HomePage/homepage.css";
import React, { useContext, useEffect, useReducer, useState } from "react";
import {
  CommentsContext,
  EditComContext,
  EditPostContext,
  FullPostContext,
  MyUserContext,
  QuestionsContext,
} from "../../configs/Contexts";
import Survey from "./Survey/Survey";
import {
  CommentsReducer,
  EditComReducer,
  QuestionsReducer,
} from "../../configs/Reducers";
import { SurveyMode } from "../../utils/accessMode";
import { BiLike, BiHeart, BiLaugh, BiCommentDetail } from "react-icons/bi";
import { BsTrash } from "react-icons/bs";
import InputField from "../InputFields/Input";
import { MdSend } from "react-icons/md";
import Comments from "../Comments/Comments";
import ReactTimeago from "react-timeago";
import { authApi, endpoints } from "../../configs/APIs";

let nextFeId = 0;

const Posts = React.forwardRef((props, ref) => {
  const { post, setDeleteComment, deleteComment } = props;
  const navigate = useNavigate();
  const [fullPost, fullPostDispatch] = useContext(FullPostContext);
  const [questions, questionsDispatch] = useReducer(
    QuestionsReducer,
    post?.survey?.questions
  );
  const [author, setAuthor] = useState(null);
  const [user, userDispatch] = useContext(MyUserContext);
  const [editPost, editPostDispatch] = useContext(EditPostContext);
  const [isLike, setIsLike] = useState(false);
  const [isLove, setIsLove] = useState(false);
  const [isHaha, setIsHaha] = useState(false);
  const [comment, setComment] = useState("");
  const [hidden, setHidden] = useState(false);
  const [comments, commentsDispatch] = useReducer(
    CommentsReducer,
    post.comments
  );
  const [editCom, editComDispatch] = useReducer(EditComReducer, null);

  const handleClose = () => {
    fullPostDispatch({
      type: "toggle",
      payload: {
        justCommented: true,
        postId: post.id,
        open: false,
      },
    });
  };

  const handleEdit = () => {
    editPostDispatch({
      type: "toggle",
      payload: {
        postId: post.id,
        post: post,
        open: true,
      },
    });
  };

  const handleDelete = async (postId) => {
    try {
      let delRes = await authApi().delete(endpoints["delete-post"](postId));
      if (delRes.status === 204) {
        setHidden(true);
      }
    } catch (ex) {
      console.error(ex);
    }
  };

  const handleLikeDisplay = () => {
    setIsLike(!isLike);
    setIsHaha(false);
    setIsLove(false);
  };

  const handleLoveDisplay = () => {
    setIsLove(!isLove);
    setIsHaha(false);
    setIsLike(false);
  };

  const handleHahaDisplay = () => {
    setIsHaha(!isHaha);
    setIsLove(false);
    setIsLike(false);
  };

  const handleAction = async (actionId) => {
    try {
      const form = new FormData();
      form.append("id", actionId);
      const actRes = await authApi().post(
        endpoints["do-action"](post.id),
        form,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (
        actRes.status === 204 ||
        actRes.status === 200 ||
        actRes.status === 201
      ) {
        switch (actionId) {
          case 1:
            handleLikeDisplay();
            break;
          case 2:
            handleHahaDisplay();
            break;
          case 3:
            handleLoveDisplay();
            break;
          default:
            break;
        }
      }
    } catch (ex) {
      console.error(ex);
    }
  };

  const handleLike = () => {
    //fetch real api update actionPost
    handleAction(1);
  };

  const handleLove = () => {
    //fetch real api update actionPost\
    handleAction(3);
  };

  const handleHaha = () => {
    //fetch real api update actionPost
    handleAction(2);
  };

  const handleReadmore = (postId) => {
    fullPostDispatch({
      type: "toggle",
      payload: {
        post: post,
        postId: postId,
        open: true,
      },
    });
  };

  const commentPost = async (e) => {
    const cmForm = new FormData();
    cmForm.append("content", comment);

    const cmRes = await authApi().post(
      endpoints["comment-a-post"](post?.id),
      cmForm,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (cmRes.status === 201) {
      setComment("");
      commentsDispatch({
        type: "set",
        payload: [
          ...comments,
          {
            id: parseInt(cmRes.data),
            content: comment,
            owner: {
              id: user.id,
              username: user.username,
              avatar: user.avatar,
            },
          },
        ],
      });
    }
  };

  useEffect(() => {
    questionsDispatch({
      type: "set",
      payload: post.questions,
    });

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return hidden ? (
    <></>
  ) : (
    <EditComContext.Provider value={[editCom, editComDispatch]}>
      <CommentsContext.Provider value={[comments, commentsDispatch]}>
        <div
          key={post?.id}
          ref={ref}
          className="post-container"
          style={{
            backgroundImage:
              "linear-gradient(0deg, #324E7D 8%, #224173 30%, #0A2A5D 60%)",
          }}
        >
          {fullPost?.open ? (
            <div className="close-post" onClick={handleClose}>
              Close
            </div>
          ) : (
            <></>
          )}

          <div className="post-info">
            <div
              style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
              }}
            >
              <div
                className="post-ava-container"
                style={{ backgroundColor: `rgb(41, 128, 243)` }}
              >
                <img
                  className="post-ava"
                  src={post.author?.avatar}
                  onClick={() => navigate(`/user/${post.author?.id}`)}
                  alt="post user img"
                />
              </div>
              <div className="post-author">
                u/{post.author?.username}
                <div className="post-time">
                  {post.author?.createdAt ? (
                    <ReactTimeago
                      className="post-date"
                      date={post.author.createdAt}
                      minPeriod={60}
                    />
                  ) : (
                    "unknown"
                  )}
                </div>
              </div>
            </div>

            <div
              style={{
                display: "flex",
                justifyContent: "flex-end",
              }}
            >
              {user?.id === post.author.id ? (
                <div class="info-edit" onClick={handleEdit}>
                  Edit
                </div>
              ) : (
                <></>
              )}

              <div className="features-container">
                {user?.id === post.author?.id && (
                  <div className="post-edit-delete">
                    <BsTrash
                      size={"24px"}
                      color="red"
                      onClick={() => handleDelete(post?.id)}
                    />
                  </div>
                )}
              </div>
            </div>
          </div>

          <div className="post-context">
            <div className="post-title">{post?.title}</div>

            {!fullPost.open ? (
              <span
                className="post-desc-readmore"
                onClick={() => {
                  handleReadmore(post?.id);
                }}
              >
                Click to read more
              </span>
            ) : (
              <></>
            )}

            {fullPost?.open ? (
              <div
                className={`${
                  fullPost?.postId === post?.id ? "post-desc-full" : "post-desc"
                }`}
              >
                {post?.content}
                <b>
                  {post?.location ? (
                    <div>Location: {post.location}</div>
                  ) : (
                    <></>
                  )}
                  {post?.dateTime ? (
                    <div>Date and Time: {post.dateTime}</div>
                  ) : (
                    <></>
                  )}
                  {post?.emails ? (
                    <div>
                      <div>Receivers:</div>
                      <div style={{
                        paddingLeft: "3rem"
                      }}>
                        {post.emails.map(mail => {return (mail.email + ";\n")})}

                      </div>
                    </div>
                  ) : (
                    <></>
                  )}
                  {post?.questions ? (
                    <QuestionsContext.Provider
                      value={[questions, questionsDispatch]}
                    >
                      <Survey surveyMode={SurveyMode.forDisplay} />
                    </QuestionsContext.Provider>
                  ) : (
                    <></>
                  )}
                </b>
              </div>
            ) : (
              <></>
            )}

            {post?.images?.length > 0 && (
              <div style={{ display: "flex", flexWrap: "wrap" }}>
                {post.images.map((img) => {
                  return (
                    <img
                      className="post-image"
                      src={img}
                      alt="postImg"
                      style={{
                        width: "300px",
                        height: "190px",
                        margin: "0.65rem",
                        borderRadius: 0,
                      }}
                    />
                  );
                })}
              </div>
            )}

            <div className="post-interactions">
              <div className="post-vote">
                <div
                  className="upvote"
                  style={{
                    margin: "3px",
                  }}
                >
                  {isLike ? (
                    <BiLike
                      size={"24px"}
                      color="#ff9051"
                      onClick={handleLike}
                    />
                  ) : (
                    <BiLike size={"24px"} color="white" onClick={handleLike} />
                  )}
                </div>

                <div
                  className="haha"
                  style={{
                    margin: "3px",
                  }}
                >
                  {isHaha ? (
                    <BiLaugh
                      size={"24px"}
                      color="#ff9051"
                      onClick={handleHaha}
                    />
                  ) : (
                    <BiLaugh size={"24px"} color="white" onClick={handleHaha} />
                  )}
                </div>
                <div
                  className="love"
                  style={{
                    margin: "3px",
                  }}
                >
                  {isLove ? (
                    <BiHeart
                      size={"24px"}
                      color="#ff9051"
                      onClick={handleLove}
                    />
                  ) : (
                    <BiHeart size={"24px"} color="white" onClick={handleLove} />
                  )}
                </div>
              </div>
              <div className="comments">
                <BiCommentDetail
                  size={"24px"}
                  onClick={() => handleReadmore(post?.id)}
                  color="white"
                />
              </div>
            </div>

            {fullPost?.open && (
              <>
                <div className="comments-opened">
                  <div className="comments-title">All comments</div>
                  {comments && comments.length > 0 ? (
                    comments.map((comment) => {
                      console.log(comment);
                      return (
                        <Comments
                          key={comment?.id ? comment.id : comment.feId}
                          id={comment?.id ? comment.id : comment.feId}
                          setDeleteComment={setDeleteComment}
                          deleteComment={deleteComment}
                          post={post}
                          ownerId={comment?.owner?.id}
                          username={comment?.owner?.username}
                          avaUrl={comment?.owner?.avatar}
                          theme={
                            comment?.owner?.theme
                              ? comment.owner.theme
                              : "#5272F2"
                          }
                          createdAt={comment?.createdAt}
                          updatedAt={comment?.updatedAt}
                          content={comment?.content}
                        />
                      );
                    })
                  ) : (
                    <></>
                  )}
                  {post.locked && user.id !== post.userId ? (
                    <div className="comments-interact">
                      The comment section is not accessible!!!
                    </div>
                  ) : (
                    <form className="comments-interact">
                      <img
                        src={user?.avatar}
                        className="user-avatar"
                        style={{ backgroundColor: `${user?.theme}` }}
                        alt="user avatar"
                      />
                      <InputField
                        data={comment}
                        value={comment}
                        setData={setComment}
                        type="text"
                        placeholder="Add a comment"
                        classStyle="comment-input"
                      />
                      <MdSend
                        size="32px"
                        className="submit-comment"
                        onClick={commentPost}
                      />
                    </form>
                  )}
                </div>
              </>
            )}
          </div>
        </div>
      </CommentsContext.Provider>
    </EditComContext.Provider>
  );
});

export default Posts;
<></>;
