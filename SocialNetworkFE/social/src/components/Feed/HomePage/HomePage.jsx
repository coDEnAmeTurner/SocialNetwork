import FeedLayout from "../Layout/FeedLayout";
import "./homepage.css";
import "../../Posts/post.css";
import { useEffect, useReducer, useState } from "react";
import Posts from "../../Posts/Posts";
import { EditPostReducer, FullPostReducer } from "../../../configs/Reducers";
import { EditPostContext, FullPostContext } from "../../../configs/Contexts";
import FullPost from "../../Posts/FullPost/FullPost";
import EditPost from "../../Posts/EditPost/EditPost";
import { authApi, endpoints } from "../../../configs/APIs";

export const getEntirePost = async (rawPostInst) => {
  let post = {
    ...rawPostInst,
    locked: !rawPostInst.unlocked,
  };

  let ctres = await authApi().get(
    endpoints["get-contentType-by-post"](post?.id)
  );
  if (ctres.status === 200) post.contentType = ctres.data;

  let pires = await authApi().get(endpoints["get-postImages-by-post"](post.id));

  if (pires.status === 200) {
    post.images = pires.data.map((pi) => {
      return pi.image;
    });
  }

  let ures = await authApi().get(endpoints["get-author-by-post"](post.id));
  if (ures.status === 200) {
    post.author = ures.data;
  }

  try {
    let cRes = await authApi().get(endpoints["get-comments-by-post"](post.id));
    if (cRes.status === 200) {
      post.comments = await Promise.all(
        cRes.data.map(async (rawc) => {
          const uRes = await authApi().get(
            endpoints["get-author-by-comment"](rawc.id)
          );
          if (uRes.status === 200) {
            return {
              ...rawc,
              owner: uRes.data,
            };
          } else {
            console.error(uRes);
          }
        })
      );
    }
  } catch (ex) {}

  /// contentType === 2
  if (post.contentType.id === 2) {
    let ires = await authApi().get(
      endpoints["get-invitation-by-post"](post.id)
    );
    if (ires.status === 200) {
      post.location = ires.data.location;
      post.dateTime = ires.data.dateTime;

      let eRes = await authApi().get(endpoints['get-emails'](post.id));
      if (eRes.status === 200) {
        post.emails = eRes.data;

      }
    }
  }

  //ContentType ==== 3
  if (post.contentType.id === 3) {
    let qres = await authApi().get(endpoints["get-questions-by-post"](post.id));
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

          try {
            let vRes = await authApi().get(endpoints["get-vote"](q.id));

            if (vRes.status === 200) {
              q.vote = vRes.data.id;
            }
          } catch (ex) {
            q.vote = "";
          }

          return q;
        })
      );

      post.questions = questions;
    }
  }

  return post;
};

const HomePage = () => {
  const [posts, setPosts] = useState([]);
  const [fullPost, fullPostDispatch] = useReducer(FullPostReducer, {
    postId: null,
    open: false,
  });
  const [editPost, editPostDispatch] = useReducer(EditPostReducer, {
    editPostId: null,
    open: false,
  });

  const getPosts = async () => {
    //fetch real api
    try {
      let res = await authApi().get(endpoints["get-posts"]);
      if (res.status === 200) {
        const rawPosts = res.data;

        const postccc = await Promise.all(
          rawPosts.map(async (rp) => {
            return getEntirePost(rp);
          })
        );

        setPosts(postccc);
      }
    } catch (ex) {
      console.error(ex);
    }
  };

  useEffect(() => {
    getPosts();
  }, [editPost, fullPost]);

  return (
    <FullPostContext.Provider value={[fullPost, fullPostDispatch]}>
      <EditPostContext.Provider value={[editPost, editPostDispatch]}>
        <FeedLayout>
          <section className="homepage-container">
            <div className="homepage-post">
              {fullPost?.open && <FullPost />}
              {editPost?.open && <EditPost />}

              {posts.length > 0 && !fullPost.open && !editPost.open ? (
                posts.map((post, idx) => {
                  if (posts.length === idx + 1) {
                    return <Posts key={posts?._id} post={post} />;
                  }
                  return <Posts key={posts?._id} post={post} />;
                })
              ) : (
                posts.length === 0?
                <div
                  style={{
                    display: "flex",
                    justifyContent: "center",
                  }}
                >
                  <div class="spinner-grow "></div>
                </div> : <></>
              )}
            </div>
          </section>
        </FeedLayout>
      </EditPostContext.Provider>
    </FullPostContext.Provider>
  );
};

export default HomePage;
