import Header from "../Header/Header";
import "../../App.css";
import { useContext, useEffect, useReducer, useState } from "react";
import {
  EditPostContext,
  FullPostContext,
  IsEditContext,
  MyUserContext,
} from "../../configs/Contexts";
import { EditPostReducer, FullPostReducer } from "../../configs/Reducers";
import FullPost from "../Posts/FullPost/FullPost";
import Posts from "../Posts/Posts";
import Loading from "../Loading/Loading";
import MakePost from "../Posts/MakePost";
import EditPost from "../Posts/EditPost/EditPost";
import EditPage from "../Edit/EditPage";
import Register from "../user/Signup";
import { authApi, endpoints } from "../../configs/APIs";
import ReactTimeago from "react-timeago";

const UserProfile = (props) => {
  // eslint-disable-next-line no-unused-vars
  const [user, userDispatch] = useContext(MyUserContext);
  const [posts, setPosts] = useState([]);
  const [fullPost, fullPostDispatch] = useReducer(FullPostReducer, {
    postId: null,
    open: false,
  });
  const [editPost, editPostDispatch] = useReducer(EditPostReducer, {
    editPostId: null,
    open: false,
  });
  const [isEdit, isEditDispatch] = useContext(IsEditContext);

  const getPosts = async () => {
    const res = await authApi().get(endpoints["get-posts-by-author"](user?.id));

    if (res.status === 200) {
      const rawPosts = res.data;

      const postccc = await Promise.all(
        rawPosts.map(async (rp) => {
          let post = {
            ...rp,
            locked: !rp.unlocked,
          };

          let ctres = await authApi().get(
            endpoints["get-contentType-by-post"](post?.id)
          );
          if (ctres.status === 200) post.contentType = ctres.data;

          let pires = await authApi().get(
            endpoints["get-postImages-by-post"](post.id)
          );

          if (pires.status === 200) {
            post.images = pires.data.map((pi) => {
              return pi.image;
            });
          }

          let ures = await authApi().get(
            endpoints["get-author-by-post"](post.id)
          );
          if (ures.status === 200) {
            post.author = ures.data;
          }

          /// contentType === 2
          if (post.contentType.id === 2) {
            let ires = await authApi().get(
              endpoints["get-invitation-by-post"](post.id)
            );
            if (ires.status === 200) {
              post.location = ires.data.location;
              post.dateTime = ires.data.dateTime;
            }
          }

          //ContentType ==== 3
          if (post.contentType.id === 3) {
            let qres = await authApi().get(
              endpoints["get-questions-by-post"](post.id)
            );
            if (qres.status === 200) {
              const questions = await Promise.all(
                qres.data.map(async (rawq) => {
                  let q = { ...rawq };
                  let cres = await authApi().get(
                    endpoints["get-choices-by-question"](q.id)
                  );

                  if (cres.status === 200) {
                    q.choices = cres.data;
                  }

                  return q;
                })
              );

              post.questions = questions;
            }
          }

          return post;
        })
      );

      setPosts(postccc);
    }
  };

  useEffect(() => {
    getPosts();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [user]);

  return (
    <section className="userprofile-container">
      {isEdit ? (
        <Register />
      ) : (
        <>
          <Header />

          <FullPostContext.Provider value={[fullPost, fullPostDispatch]}>
            <EditPostContext.Provider value={[editPost, editPostDispatch]}>
              {fullPost?.open && <FullPost />}
              {editPost?.open && <EditPost />}
              <div
                className="follow-container"
                style={{ boxShadow: `0px 0px 10px 3px ${user?.theme}`, marginTop: "12px" }}
              >
                <div
                  className="follower"
                  style={{ borderRight: `1px solid ${user?.theme}` }}
                >
                  <p className="follower-num">
                    {user?.postCount?user.postCount: 0}
                  </p>
                  <p className="follower-title">Post Count</p>
                </div>
                <div className="following">
                  <p className="following-num">
                    {" "}
                    {user?.createdAt ?
                    <ReactTimeago
                      className="comment-date"
                      date={user.createdAt}
                      minPeriod={60}
                    />:<>unknown</>}{" "}
                  </p>
                  <p className="following-title"> Been Around Since</p>
                </div>
              </div>
              <section className="homepage-container">
                <div className="homepage-post" style={{
                   backgroundImage: `linear-gradient(90deg, #0B1129ff, #0F1F46ff, ${user.theme} 50%, #0F1F46ff, #0B1129ff)`
                }}>
                  {posts && posts.length > 0 ? (
                    <>
                      {posts.map((post, idx) => {
                        if (posts.length === idx + 1) {
                          return <Posts key={posts?._id} post={post} />;
                        }
                        return <Posts key={posts?._id} post={post} />;
                      })}
                    </>
                  ) : (
                    <Loading
                      loadingType="BeatLoader"
                      color="white"
                      size="10px"
                      loading={true}
                    />
                  )}
                </div>
              </section>
            </EditPostContext.Provider>
          </FullPostContext.Provider>
        </>
      )}
    </section>
  );
};

export default UserProfile;
