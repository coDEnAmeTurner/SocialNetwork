import { useContext, useEffect, useReducer, useState } from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import FeedLayout from "../Feed/Layout/FeedLayout";
import { authApi, endpoints } from "../../configs/APIs";
import Form from "react-bootstrap/Form";
import Survey from "./Survey/Survey";
import {
  EditPostContext,
  MyUserContext,
  QuestionsContext,
} from "../../configs/Contexts";
import { QuestionsReducer } from "../../configs/Reducers";
import { useNavigate } from "react-router-dom";
import { MakePostMode, SurveyMode } from "../../utils/accessMode";
import { AxiosError } from "axios";
import Spinner from "react-bootstrap/Spinner";
import { Button } from "react-bootstrap";
import { TiDeleteOutline } from "react-icons/ti";

let fileDict = {};
let fileURLDict = {};
let fileCount = 0;
let fileURLCount = 0;

const MakePost = ({ accessMode = MakePostMode.forCreation, currentPost }) => {
  const [previewSource, setPreviewSource] = useState(null);
  const [contentTypes, setContentTypes] = useState([]);
  const [questions, questionsDispatch] = useReducer(QuestionsReducer, null);
  const [pickedCT, setPickedCT] = useState(
    accessMode === MakePostMode.forEdit ? `${currentPost.contentType.id}` : "1"
  );
  // eslint-disable-next-line no-unused-vars
  const [editPost, editPostDispatch] = useContext(EditPostContext);
  const [user, userDispatch] = useContext(MyUserContext);
  const [posting, setPosting] = useState(false);
  const [saved, setSaved] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const [typingMail, setTypingMail] = useState(null);
  const [mailList, setMailList] = useState(null);
  const [filteredMailList, setFilteredMailList] = useState([]);
  const [chosenMailList, setChosenMailList] = useState([]);
  const [mailOutFocus, setMailOutFocus] = useState(true);
  const [mailOptionsOutFocus, setMailOptionsOutFocus] = useState(false);

  const navigate = useNavigate();

  // eslint-disable-next-line no-unused-vars
  const loadContentType = async () => {
    // fetch real api
    try {
      const res = await authApi().get(endpoints["content-types"]);
      if (res.status === 200) {
        setContentTypes(res.data);
      }
    } catch (ex) {
      console.error(ex);
    }
  };

  const displayFiles = (e) => {
    if (e.target) {
      Array.from(e.target.files, (file) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = () => {
          let edit =
            previewSource != null ? structuredClone(previewSource) : [];
          edit.push(reader.result);
          setPreviewSource(edit);
        };
      });
    } else {
      let edit = previewSource != null ? structuredClone(previewSource) : [];
      edit.concat(structuredClone(e.images));
      setPreviewSource(edit);
    }
  };

  const putFilesInDict = (e) => {
    const files = e.target
      ? structuredClone(e.target.files)
      : structuredClone(e.images);

    for (let i = 0; i < files.length; i++) {
      if (accessMode === MakePostMode.forCreation)
        fileDict[`file${fileCount++}`] = files[i];
      else fileURLDict[`file${fileURLCount++}`] = files[i];
    }
  };

  const handlePost = (newPost) => {
    try {
      setPosting(true);

      const form = new FormData();

      for (var key in newPost) form.append(key, newPost[key]);

      //final questions ds
      // {
      //   ...
      //   "question0": "content0",
      //   "choice0_0": "choicecontent0_0",
      //   "choice0_1": "choicecontent0_1",
      //   "question1": "content1",
      //   "choice1_0": "choicecontent1_0",
      //   "choice1_1": "choicecontent1_1",
      //   "choice1_2": "choicecontent1_2",
      //   ...
      // }
      if (questions) {
        for (let i = 0; i < questions.length; i++) {
          form.append(`question${i}`, questions[i].content);
          for (let j = 0; j < questions[i].choices.length; j++) {
            form.append(`choice${i}_${j}`, questions[i].choices[j].content);
          }
        }
      }

      if (accessMode === MakePostMode.forCreation) {
        if (Object.keys(fileDict).length === 0) {
          setErrorMsg("No file found!");
          setPosting(false);
          return;
        }
        for (var key in fileDict) {
          form.append("files", fileDict[key]);
        }
      } else {
        if (Object.keys(fileURLDict).length === 0) {
          setPosting(false);

          setErrorMsg("No file found!");
          return;
        }
        for (var key in fileURLDict) form.append("files", fileURLDict[key]);
      }

      if (chosenMailList) {
        chosenMailList.forEach((mail, index) => {
          form.append(`email${index}`, mail);
        });

        form.append("mailCount", chosenMailList.length);
      }

      setTimeout(async () => {
        try {
          const res = await authApi().post(endpoints["post"], form, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });

          if (res.status === 201) {
            navigate("/", { successMsg: "Post successfully!!!" });
            console.log(res.data);
          }
        } catch (ex) {
          setPosting(false);
          if (ex instanceof AxiosError) setErrorMsg(ex.response.data);
        }
      }, 100);
    } catch (ex) {
      setPosting(false);
      console.error(ex);
      if (ex instanceof AxiosError) setErrorMsg(ex.response.data);
    }
  };

  const handleClose = () => {
    editPostDispatch({
      type: "toggle",
      payload: {
        justSaved: true,
        editPostId: currentPost.id,
        open: false,
      },
    });
  };

  const displaySaveSuccess = () => {
    setPosting(false);
    setSaved(true);
  };

  const handleSave = async () => {
    setPosting(true);

    try {
      const postForm = new FormData();
      postForm.append("title", editPost.post.title);
      postForm.append("content", editPost.post.content);
      postForm.append("unlocked", editPost.post.unlocked);

      const postRes = await authApi().put(
        endpoints["update-post"](editPost.post.id),
        postForm,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (postRes.status !== 200) throw new Error("Post Request failed");

      if (editPost.post.contentType.id === 2) {
        const invitationForm = new FormData();
        for (let key in editPost.post) {
          if (key === "location" || key === "dateTime")
            invitationForm.append(key, editPost.post[key]);
        }

        let invitationRes = await authApi().put(
          endpoints["update-invitation"](editPost.post.id),
          invitationForm,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        if (invitationRes.status !== 200)
          throw new Error("invitation Request failed");

        await Promise.all(
          chosenMailList.map(async (mail) => {
            if (mail.new && !mail.deleted) {
              const form = new FormData();
              form.append("email", mail.email);
              form.append("invitationId", `${currentPost.id}`);
              const mailAddRes = await authApi().post(
                endpoints["create-email"],
                form,
                {
                  headers: {
                    "Content-Type": "application/json",
                  },
                }
              );

              if (mailAddRes.status !== 201)
                throw new Error("invitation Request failed");
            }

            if (mail.deleted && mail.id) {
              const mailDelRes = await authApi().delete(
                endpoints["delete-email"](mail.id)
              );
              if (mailDelRes.status !== 204)
                throw new Error("invitation Request failed");
            }
          })
        );

        displaySaveSuccess();
      }

      if (editPost.post.contentType.id === 3) {
        await Promise.all(
          questions?.map(async (question) => {
            if (question.deleted) {
              const qDelRes = await authApi().delete(
                endpoints["delete-question"](question.id)
              );
              if (qDelRes.status !== 204) console.error(qDelRes);
            } else {
              const qForm = new FormData();
              qForm.append("content", question.content);
              qForm.append("postId", editPost.post.id);
              let qRes = await authApi().put(
                endpoints["update-question"](question.id),
                qForm,
                {
                  headers: {
                    "Content-Type": "application/json",
                  },
                }
              );
              if (qRes.status !== 200 && qRes.status !== 201)
                throw new Error("Question request failed: ", qRes.status);

              await Promise.all(
                question.choices?.map(async (choice, index) => {
                  if (choice.deleted) {
                    const cDelRes = await authApi().delete(
                      endpoints["delete-choice"](choice.id)
                    );
                    if (cDelRes.status !== 204) console.error(cDelRes);
                  } else {
                    const cForm = new FormData();
                    cForm.append("content", choice.content);
                    if (qRes.status === 201)
                      cForm.append("questionId", qRes.data);
                    if (qRes.status === 200)
                      cForm.append("questionId", question.id);
                    let cRes = await authApi().put(
                      endpoints["update-choice"](choice.id),
                      cForm,
                      {
                        headers: {
                          "Content-Type": "application/json",
                        },
                      }
                    );
                    if (cRes.status !== 200 && cRes.status !== 201)
                      throw new Error("Choice request failed");
                  }

                  displaySaveSuccess();
                })
              );
            }
          })
        );
      }

      if (editPost.post.contentType.id === 1) {
        displaySaveSuccess();
      }
    } catch (ex) {
      setPosting(false);
      console.error(ex);
      if (ex instanceof AxiosError) setErrorMsg(ex.response.data);
    }
  };

  useEffect(() => {
    if (accessMode === MakePostMode.forEdit && currentPost.images) {
      const e = { images: currentPost.images };

      displayFiles(e);
      putFilesInDict(e);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    loadContentType();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    if (accessMode === MakePostMode["forEdit"] && pickedCT === "3")
      questionsDispatch({
        type: "set",
        payload: currentPost.questions,
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    if (currentPost?.contentType?.id === 2) setChosenMailList(currentPost.emails.map((mail)=>{
      return {
        id: mail.id,
        mail: mail.email,
        new: false,
        deleted: false
      }
    }));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    getAllEmails();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pickedCT]);

  useEffect(()=>{
    console.log(chosenMailList);
  }, [chosenMailList]);

  const getAllEmails = async () => {
    if (pickedCT === "2") {
      const res = await authApi().get(endpoints["get-all-emails"]);
      if (res.status === 200) setMailList(res.data);
      else console.error(res);
    }
  };

  const formik = useFormik({
    initialValues: {
      title:
        accessMode === MakePostMode["forCreation"] ? "" : currentPost.title,
      desc:
        accessMode === MakePostMode["forCreation"] ? "" : currentPost.content,
      locked:
        accessMode === MakePostMode["forCreation"] ? false : currentPost.locked,
      location:
        accessMode === MakePostMode["forCreation"]
          ? ""
          : currentPost.contentType.id === 2
          ? currentPost.location
          : "",
      dateTime:
        accessMode === MakePostMode["forCreation"]
          ? ""
          : currentPost.contentType.id === 2
          ? currentPost.dateTime
          : "",
      contentTypeId: 1,
    },
    validationSchema: Yup.object({
      title: Yup.string()
        .min(10, "Minimum 10 characters")
        .max(100, "Maximum 100 characters")
        .required("Required"),
      desc: Yup.string().min(4, "Minimum 4 characters").required("Required"),
      datetime:
        pickedCT === 2 && accessMode === MakePostMode.forCreation
          ? Yup.string().required("Required")
          : Yup.string(),
      location:
        pickedCT === 2 && accessMode === MakePostMode.forCreation
          ? Yup.string().required("Required")
          : Yup.string(),
      email:
        pickedCT === 2 && accessMode === MakePostMode.forCreation
          ? Yup.string()
              .max(50, "Maximum 50 character")
              .required("Required")
              .matches(
                /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
                "Please enter valid email address abc@xyz.com"
              )
          : Yup.string()
              .max(50, "Maximum 50 character")
              .matches(
                /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
                "Please enter valid email address abc@xyz.com"
              ),
    }),
    onSubmit: (values) => {
      if (accessMode === MakePostMode["forCreation"]) {
        const newPost = {
          //post obj
          title: values.title,
          contentTypeId: values.contentTypeId,
          locked: values.locked,
          content: values.desc,

          //invitation inst
          location: values.location,
          dateTime: values.datetime,
        };

        handlePost(newPost);
      }

      if (accessMode === MakePostMode["forEdit"]) {
        handleSave();
      }
    },
  });

  return (
    <>
      <FeedLayout forEdit={accessMode === MakePostMode.forEdit ? true : false}>
        {errorMsg ? (
          <div
            className="loginError"
            style={{
              textAlign: "center",
            }}
          >
            {errorMsg}
          </div>
        ) : (
          <></>
        )}

        <Form
          onSubmit={(event) => {
            event.preventDefault();
            formik.handleSubmit(event);
          }}
          className="makepost-container"
        >
          {accessMode === MakePostMode["forEdit"] ? (
            <div
              style={{
                display: "flex",
                justifyContent: "right",
              }}
            >
              <span className="close" onClick={handleClose}>
                Close
              </span>
            </div>
          ) : (
            <></>
          )}

          <label className="Title"> Title </label>
          <textarea
            required
            id="title"
            name="title"
            type="text"
            className="makepost-title"
            placeholder="Enter title"
            onChange={(e) => {
              formik.handleChange(e);
              if (accessMode === MakePostMode.forEdit) {
                editPostDispatch({
                  type: "toggle",
                  payload: {
                    ...editPost,
                    post: { ...editPost.post, title: e.target.value },
                  },
                });
              }
            }}
            value={formik.values.title}
          />
          {formik.errors.title && (
            <p className="errorMsg">{formik.errors.title}</p>
          )}

          <label className="content-type-label"> Content Type: </label>
          <Form.Select
            className="content-type-select"
            name="contentTypeId"
            onChange={(e) => {
              formik.handleChange(e);
              setPickedCT(e.target.value);
              // console.log(e.target.value);
            }}
            value={pickedCT}
            disabled={accessMode === MakePostMode.forEdit ? true : false}
          >
            {contentTypes.length > 0 ? (
              contentTypes.map((ct) => {
                if (user?.userRole?.id !== 1)
                  if (ct.id !== 1) return <></>;
                  else
                    return (
                      <option
                        key={ct.id}
                        value={ct.id}
                        className="content-type-option"
                      >
                        {ct.name}
                      </option>
                    );
                else
                  return (
                    <option
                      key={ct.id}
                      value={ct.id}
                      className="content-type-option"
                    >
                      {ct.name}
                    </option>
                  );
              })
            ) : (
              <option value="" className="content-type-option">
                Nothing to be shown.
              </option>
            )}
          </Form.Select>

          <label className="Desc"> Descriptions </label>
          <textarea
            required
            id="desc"
            name="desc"
            type="text"
            className="makepost-desc"
            placeholder="Enter descriptions"
            onChange={(e) => {
              formik.handleChange(e);
              if (accessMode === MakePostMode.forEdit)
                editPostDispatch({
                  type: "toggle",
                  payload: {
                    ...editPost,
                    post: { ...editPost.post, content: e.target.value },
                  },
                });
            }}
            value={formik.values.desc}
          />
          {formik.errors.desc && (
            <p className="errorMsg">{formik.errors.desc}</p>
          )}

          <div
            key={`default-checkbox`}
            className="mb-3"
            style={{
              display: "flex",
            }}
          >
            <Form.Check // prettier-ignore
              type={"checkbox"}
              id={`default-checkbox`}
              name="locked"
              onChange={(e) => {
                if (accessMode === MakePostMode.forCreation)
                  formik.handleChange(e);
                else
                  editPostDispatch({
                    type: "toggle",
                    payload: {
                      ...editPost,
                      post: { ...editPost.post, unlocked: !e.target.checked },
                    },
                  });
              }}
              checked={
                accessMode === MakePostMode.forCreation
                  ? formik.values.locked
                  : !editPost.post.unlocked
              }
            />
            <label
              style={{
                marginLeft: "1rem",
              }}
            >
              Lock comment section?
            </label>
          </div>

          <label
            className="makepost-file-label"
            style={{
              backgroundColor: "#F58023",
            }}
          >
            <input
              type="file"
              id="fileInput"
              name="image"
              onChange={(e) => {
                displayFiles(e);
                putFilesInDict(e);
              }}
              className="makepost-img"
              multiple
              disabled={accessMode === MakePostMode.forEdit ? true : false}
            />
          </label>

          {pickedCT === "2" ? (
            <>
              <label className="Location"> Location: </label>
              <input
                required
                id="location"
                name="location"
                type="text"
                className="makepost-location"
                placeholder="Enter location"
                onChange={(e) => {
                  formik.handleChange(e);
                  if (accessMode === MakePostMode.forEdit)
                    editPostDispatch({
                      type: "toggle",
                      payload: {
                        ...editPost,
                        post: { ...editPost.post, location: e.target.value },
                      },
                    });
                }}
                value={formik.values.location}
              />
              {formik.errors.location && (
                <p className="errorMsg">{formik.errors.location}</p>
              )}

              <label className="DateTime"> Event Time: </label>
              <input
                required
                id="datetime"
                name="datetime"
                type="datetime-local"
                className="makepost-datetime"
                onChange={(e) => {
                  formik.handleChange(e);
                  if (accessMode === MakePostMode.forEdit)
                    editPostDispatch({
                      type: "toggle",
                      payload: {
                        ...editPost,
                        post: { ...editPost.post, dateTime: e.target.value },
                      },
                    });
                }}
                value={accessMode === MakePostMode.forEdit?editPost.post.dateTime:formik.values.datetime}
              />
              {formik.errors.datetime && (
                <p className="errorMsg">{formik.errors.dateTime}</p>
              )}

              <div style={{ position: "relative" }}>
                <label className="Email"> Email: </label>

                <div className="makepost-email">
                  {chosenMailList?.length > 0 ? (
                    chosenMailList.map((chosenMail, index) => {
                      if (accessMode === MakePostMode.forEdit && !chosenMail.deleted) {
                        return (
                          <div className="the-chosen-mail">
                            <div>
                              {chosenMail.mail}
                            </div>
                            <TiDeleteOutline className="mail-remove-button" onClick={()=>{
                              chosenMailList.at(index).deleted = true;
                              const newList = [...chosenMailList];
                              setChosenMailList(newList);
                            }}/>
                          </div>
                        );
                      }
                      else if (accessMode === MakePostMode.forCreation)
                        return (
                          <div className="the-chosen-mail">
                            <div>
                              {chosenMail}
                            </div>
                            <TiDeleteOutline className="mail-remove-button" onClick={()=>{
                              const newList = [...chosenMailList.slice(0, index), ...chosenMailList.slice(index + 1)];
                              setChosenMailList(newList);
                            }}/>
                          </div>
                        );
                      else 
                        return <></>
                    })
                  ) : (
                    <></>
                  )}

                  <input
                    className="makepost-email-input"
                    id="email"
                    name="email"
                    type="text"
                    data={typingMail}
                    onFocus={() => {
                      setMailOutFocus(false);
                    }}
                    onChange={(e) => {
                      setTypingMail(e.target.value);
                      setFilteredMailList(
                        mailList.filter((mail) => {
                          return mail.includes(e.target.value);
                        })
                      );
                    }}
                    onBlur={() => {
                      setMailOutFocus(true);
                    }}
                    placeholder="Type an email!"
                  />
                </div>

                {formik.errors.email && (
                  <p className="errorMsg">{formik.errors.email}</p>
                )}

                {filteredMailList?.length > 0 &&
                (!mailOutFocus || !mailOptionsOutFocus) ? (
                  <ul
                    className="email-options"
                    onPointerEnter={() => {
                      setMailOptionsOutFocus(false);
                    }}
                    onPointerLeave={() => {
                      setMailOptionsOutFocus(true);
                    }}
                  >
                    {filteredMailList.map((mail) => {
                      return (
                        <li
                          className="email-options-item"
                          onClick={() => {
                            if (
                              (accessMode === MakePostMode.forCreation && !chosenMailList.includes(mail)) 
                              || 
                              (accessMode === MakePostMode.forEdit && !chosenMailList.map((listItem)=>{if(!listItem.deleted) return listItem.mail; return ""}).includes(mail)) 
                            ) {
                              const newList = structuredClone(chosenMailList);
                              newList.push(accessMode === MakePostMode.forEdit?{mail:mail, new:true}:mail);
                              setChosenMailList(newList);
                            }
                          }}
                        >
                          {mail}
                        </li>
                      );
                    })}
                  </ul>
                ) : (
                  <></>
                )}
              </div>
            </>
          ) : (
            <></>
          )}

          {pickedCT === "3" ? (
            <>
              <QuestionsContext.Provider value={[questions, questionsDispatch]}>
                <Survey
                  surveyMode={
                    accessMode === MakePostMode.forCreation
                      ? SurveyMode["forCreation"]
                      : SurveyMode["forEdit"]
                  }
                />
              </QuestionsContext.Provider>
            </>
          ) : (
            <></>
          )}

          {previewSource && (
            <div
              style={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
              }}
            >
              {previewSource.map((src) => (
                <div className="makepost-img-preview">
                  <img src={src} alt="chosen" />
                </div>
              ))}
            </div>
          )}

          {!posting & !saved ? (
            accessMode === SurveyMode.forCreation ? (
              <div className="makepost-save-bottom">
                <button
                  className="submit"
                  type="submit"
                  style={{
                    backgroundColor: "#F58023",
                  }}
                >
                  POST
                </button>
              </div>
            ) : accessMode === SurveyMode.forEdit ? (
              <div className="makepost-save-bottom">
                <button
                  className="submit"
                  type="submit"
                  style={{
                    height: "40px",
                    width: "90px",
                    backgroundColor: "#F58023",
                  }}
                >
                  SAVE
                </button>
              </div>
            ) : (
              <></>
            )
          ) : saved ? (
            <h2 className="text text-center text-bg-success ">
              Saved successfully!!!
            </h2>
          ) : (
            <Button variant="primary" className="makepost-spinner" disabled>
              <Spinner
                as="span"
                animation="border"
                size="md"
                role="status"
                aria-hidden="true"
                style={{
                  backgroundColor: "#F58023",
                }}
              />
            </Button>
          )}
        </Form>
      </FeedLayout>
    </>
  );
};

export default MakePost;
