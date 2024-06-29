import { Link, useNavigate } from "react-router-dom";
import { Form, Image } from "react-bootstrap";
import "./register.css";
import { useFormik } from "formik";
import * as Yup from "yup";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { useContext, useEffect, useState } from "react";
import { FrbUserContext, IsEditContext, MyUserContext } from "../../configs/Contexts";
import cookie from "react-cookies";
import { getCurrentUser } from "./Login";
import {  collection, addDoc, getDoc, query, where, onSnapshot } from "firebase/firestore";
import { db } from "../../firebase";
import { v4 as uuidv4 } from "uuid";

const Register = (props) => {
  const [degreeList, setDegreeList] = useState([]);
  const [academicRankList, setAcademicRankList] = useState([]);
  const [avatar, setAvatar] = useState(null);
  const [background, setBackground] = useState(null);
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const [isEdit, isEditDispatch] = useContext(IsEditContext);
  const [user, userDispatch] = useContext(MyUserContext);
  const [frbUser, frbUserDispatch] = useContext(FrbUserContext);
  const [titleList, setTitleList] = useState(null);
  const [newPass, setNewPass] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const newUuid = uuidv4();
  
  const getDegrees = async () => {
    try {
      const res = await APIs.get(endpoints["degreeList"]);
      setDegreeList(res.data);
    } catch (ex) {
      console.error(ex);
    }
  };

  const getAcademicRankList = async () => {
    try {
      const res = await APIs.get(endpoints["academicRankList"]);
      setAcademicRankList(res.data);
    } catch (ex) {
      console.error(ex);
    }
  };

  const getTitleList = async () => {
    try {
      const res = await APIs.get(endpoints["titles"]);
      setTitleList(res.data);
    } catch (ex) {
      console.error(ex);
    }
  };

  useEffect(() => {
    if (avatar !== undefined) setAvatar(avatar);

    if (isEdit) {
      setAvatar(user?.avatar);
      setBackground(user?.background);
    }

    if (isEdit && user?.userRole.id === 3) getTitleList();
  }, [isEdit, user]);

  const register = async (newUser, avatarFile) => {
    const form = new FormData();

    for (let key in newUser) form.append(key, newUser[key]);

    if (avatarFile) form.append("file", avatarFile);

    try {
      let res = await APIs.post(endpoints["register"], form, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      
      if (res.status === 201) {
        await addDoc(collection(db, "users"), {
          id: newUuid,
          fullName: newUser.fullName,
          email: newUser.email,
          username: newUser.username,
          createdAt: res.data.createdAt,
          avatar: res.data.avatar
        })

        navigate("/login");
      }
    } catch (ex) {
      setError(ex.response.data);
      setSubmitting(false);
      console.error(ex);
    }
  };

  const updateUser = async (newUser, avatarFile, backgroundFile) => {
    try {
      const form = new FormData();

      for (let key in newUser) form.append(key, newUser[key]);

      if (avatarFile !== undefined) form.append("file", avatarFile);

      if (background !== undefined) form.append("file1", backgroundFile);

      const upURes = await authApi().post(
        endpoints["update-user"](user.id),
        form,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      if (upURes.status === 200) {
        isEditDispatch({
          type: "toggle",
          payload: false,
        });

        if (newPass) {
          cookie.remove("token");
          userDispatch({
            type: "logout",
          });
          navigate("/login");
        } else {
          getCurrentUser(userDispatch, navigate);
        }
      }
    } catch (ex) {
      setError(ex?.response?.data);
      setSubmitting(false);
      console.error(ex);
    }
  };

  const formik = useFormik({
    initialValues: {
      fullname: isEdit ? user?.fullName : "",
      username: isEdit ? user?.username : "",
      email: isEdit ? user?.email : "",
      password: "",
      phone: isEdit ? user?.phone : "",
      dob: isEdit ? user?.dob : "",
      studentId: isEdit ? user?.studentId : "",
      degreeId: isEdit ? user?.degree?.id : null,
      academicRankId: isEdit && user?.rank ? user.rank.id : "",
      titleId: isEdit && user?.userRole?.id === 3 ? user?.title?.id : null,
      theme: isEdit ? user.theme : null,
    },

    validationSchema: Yup.object({
      fullname: !isEdit ? Yup.string().required("Required") : Yup.string(),
      username: !isEdit
        ? Yup.string()
            .max(20, "Maximum 20 characters")
            .min(6, "Minimum 6 characters")
            .required("Required")
        : Yup.string()
            .max(20, "Maximum 20 characters")
            .min(6, "Minimum 6 characters"),
      email: !isEdit
        ? Yup.string()
            .max(50, "Maximum 50 character")
            .required("Required")
            .matches(
              /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
              "Please enter valid email address"
            )
        : Yup.string()
            .max(50, "Maximum 50 character")
            .matches(
              /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
              "Please enter valid email address"
            ),
      password: !isEdit
        ? Yup.string()
            .required("Required")
            .matches(
              /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{6,19}$/,
              "Minimum 6 characters, at least one letter, one number, one special character"
            )
        : Yup.string().matches(
            /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{6,19}$/,
            "Minimum 6 characters, at least one letter, one number, one special character"
          ),
      newPassword:
        isEdit && newPass
          ? Yup.string()
              .required("Required")
              .matches(
                /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{6,19}$/,
                "Minimum 6 characters, at least one letter, one number, one special character"
              )
          : Yup.string(),
      phone: !isEdit
        ? Yup.string()
            .max(11, "Maximum 11 characters")
            .min(9, "Minimum 9 characters")
            .matches(/^\d+$/, "Only numbers allowed.")
            .required("Required")
        : Yup.string()
            .max(11, "Maximum 11 characters")
            .min(9, "Minimum 9 characters")
            .matches(/^\d+$/, "Only numbers allowed."),
      studentId: !isEdit ? Yup.string().required("Required") : Yup.string(),
    }),

    onSubmit: (values) => {
      setSubmitting(true);

      const newUser = {
        //user obj
        fullName: values.fullname,
        username: values.username,
        password: values.password,
        email: values.email,
        phone: values.phone,
        dob: values.dob,
        theme: values.theme,

        //typical_user
        degreeId: !values.degreeId ? degreeList[0].id : values.degreeId,
        academicRankId: values.academicRankId,

        //alumnus
        studentId: values.studentId,

        //lecturer
        titleId: values.titleId,
      };

      if (!isEdit) {
        register(newUser, values.avatar);
      } else {
        newUser["newPass"] = newPass;
        if (newPass) {
          newUser["currentPassword"] = values.currentPassword;

          newUser["newPassword"] = values.newPassword;
        }

        updateUser(newUser, values.avatar, values.background);
      }
    },
  });

  useEffect(() => {
    getAcademicRankList();
    getDegrees();
    if (isEdit && user.userRole.id === 1) {
      setError("Admin can not edit his profile on this page!!!");
    }
  }, []);

  return (
    <section className="register-container">
      {!isEdit ? (
        <>
          <div
            className="register-title"
            style={{
              fontSize: "1.8rem",
              backgroundImage:
                "linear-gradient(225deg, #F58023 0%, #1e0144 100%)",
            }}
          >
            {" "}
            Sign Up{" "}
          </div>
        </>
      ) : (
        <header
          style={{
            backgroundColor: `${user?.theme}`,
            backgroundImage:
              background && typeof background == "string"
                ? `url(${background})`
                : background && typeof background != "string"
                ? `url(${URL.createObjectURL(background)})`
                : `linear-gradient(${user?.theme} 2%, ${user?.theme}, 65%, rgb(24, 24, 24) 100%)`,
            backgroundPosition: "center",
          }}
        >
          <div className="close-container">
            <p
              className="close-x"
              onClick={() =>
                isEditDispatch({
                  type: "toggle",
                  payload: false,
                })
              }
            >
              X
            </p>
          </div>
        </header>
      )}
      <div className="register-error"> {error} </div>
      <div className="register-input">
        <form
          onSubmit={(event) => {
            event.preventDefault();
            formik.handleSubmit(event);
          }}
        >
          {typeof avatar == "string" ? (
            <Image
              src={
                avatar
                  ? avatar
                  : "https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg"
              }
            />
          ) : (
            <Image
              src={
                avatar
                  ? URL.createObjectURL(avatar)
                  : "https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg"
              }
            />
          )}
          <Form.Group controlId="formFile" className="mb-3 avatar-label">
            <Form.Label>AVATAR: </Form.Label>
            <Form.Control
              type="file"
              className="register-file file-label"
              name="avatar"
              onChange={(event) => {
                formik.setFieldValue("avatar", event.target.files[0]);
                setAvatar(
                  event.target.files[0] !== undefined
                    ? event.target.files[0]
                    : avatar
                );
              }}
              files={[avatar]}
              style={{
                display: "inline",
              }}
              required={!isEdit ? true : false}
              disabled={isEdit && user.userRole.id === 1 ? true : false}
            />
          </Form.Group>
          {isEdit ? (
            <Form.Group controlId="formFile" className="mb-3 avatar-label">
              <Form.Label>BACKGROUND: </Form.Label>
              <Form.Control
                type="file"
                className="register-file file-label"
                name="background"
                onChange={(event) => {
                  formik.setFieldValue("background", event.target.files[0]);
                  setBackground(
                    event.target.files[0] !== undefined
                      ? event.target.files[0]
                      : background
                  );
                }}
                files={[background]}
                style={{
                  display: "inline",
                }}
                disabled={isEdit && user.userRole.id === 1 ? true : false}
              />
            </Form.Group>
          ) : (
            <></>
          )}

          {isEdit ? (
            <Form.Group controlId="formFile" className="mb-3 avatar-label">
              <Form.Label>THEME: </Form.Label>
              <input
                className="theme-color"
                type="color"
                style={{
                  width: "200px",
                }}
                name="theme"
                onChange={formik.handleChange}
                value={formik.values.theme}
              />
            </Form.Group>
          ) : (
            <></>
          )}

          <label className="fullname-label"> FULLNAME </label>
          <input
            id="fullname"
            name="fullname"
            type="text"
            placeholder="Enter full name"
            onChange={formik.handleChange}
            value={formik.values.fullname}
            className="register-fullname"
            style={{
              color: isEdit && user.userRole.id === 1 ? "white" : "black",
            }}
            disabled={isEdit && user.userRole.id === 1 ? true : false}
          />
          {formik.errors.fullname && (
            <p className="errorMsg">{formik.errors.fullname}</p>
          )}

          <label className="username-label"> USERNAME </label>
          <input
            id="username"
            name="username"
            type="text"
            placeholder="Enter username"
            onChange={formik.handleChange}
            value={formik.values.username}
            className="register-username"
            style={{
              color: isEdit && user.userRole.id === 1 ? "white" : "black",
            }}
            disabled={isEdit && user.userRole.id === 1 ? true : false}
          />
          {formik.errors.username && (
            <p className="errorMsg">{formik.errors.username}</p>
          )}

          {isEdit && !newPass ? (
            <button
              className="change-password"
              onClick={() => {
                setNewPass(true);
              }}
              style={{
                marginBottom: "1.5rem",
              }}
              disabled={isEdit && user.userRole.id === 1 ? true : false}
            >
              Change Password?
            </button>
          ) : (
            <></>
          )}

          {isEdit && newPass ? (
            <>
              <label className="password-label">
                {" "}
                ENTER CURRENT PASSWORD:{" "}
              </label>
              <input
                id="cpassword"
                name="currentPassword"
                type="password"
                placeholder="Enter current password"
                onChange={formik.handleChange}
                value={formik.values.currentPassword}
                className="register-password"
                autoComplete="new-password"
              />
              <label className="password-label"> ENTER NEW PASSWORD: </label>
              <input
                id="npassword"
                name="newPassword"
                type="password"
                placeholder="Enter new password"
                onChange={formik.handleChange}
                value={formik.values.newPassword}
                className="register-password"
                autoComplete="new-password"
              />
              {formik.errors.password && (
                <p className="errorMsg">{formik.errors.newPassword}</p>
              )}
              <button
                className="change-password"
                onClick={() => {
                  setNewPass(false);
                }}
                style={{
                  marginBottom: "1.5rem",
                }}
              >
                Cancel Password Change?
              </button>
            </>
          ) : !isEdit ? (
            <>
              <label className="password-label"> PASSWORD </label>
              <input
                id="password"
                name="password"
                type="password"
                placeholder="Enter password"
                onChange={formik.handleChange}
                value={formik.values.password}
                className="register-password"
                autoComplete="new-password"
              />
              {formik.errors.password && (
                <p className="errorMsg">{formik.errors.password}</p>
              )}
            </>
          ) : (
            <></>
          )}

          <label className="email-label"> EMAIL </label>
          <input
            required
            id="email"
            name="email"
            type="text"
            className="register-email"
            placeholder="Enter email"
            onChange={formik.handleChange}
            value={formik.values.email}
            style={{
              color: isEdit && user.userRole.id === 1 ? "white" : "black",
            }}
            disabled={isEdit && user.userRole.id === 1 ? true : false}
          />
          {formik.errors.email && (
            <p className="errorMsg">{formik.errors.email}</p>
          )}

          <label className="phone-label"> PHONE </label>
          <input
            required
            id="phone"
            name="phone"
            type="text"
            className="register-phone"
            placeholder="Enter phone number"
            onChange={formik.handleChange}
            value={formik.values.phone}
            style={{
              color: isEdit && user.userRole.id === 1 ? "white" : "black",
            }}
            disabled={isEdit && user.userRole.id === 1 ? true : false}
          />
          {formik.errors.phone && (
            <p className="errorMsg">{formik.errors.phone}</p>
          )}

          <Form.Group controlId="formDob" className="mb-3 avatar-label">
            <Form.Label className="dob-label">DATE OF BIRTH: </Form.Label>
            <Form.Control
              type="date"
              className="register-dob file-dob"
              name="dob"
              onChange={(event) => {
                formik.handleChange(event);
              }}
              value={formik.values.dob}
              required={!isEdit ? true : false}
              disabled={isEdit && user.userRole.id === 1 ? true : false}
            />
          </Form.Group>

          {(isEdit && user?.userRole.id === 2) || !isEdit ? (
            <>
              <label className="student-id-label"> STUDENT ID: </label>
              <input
                required
                id="student-id"
                name="studentId"
                type="text"
                className="register-student-id"
                placeholder="Enter student id"
                onChange={formik.handleChange}
                value={formik.values.studentId}
                disabled={isEdit && user.userRole.id === 1 ? true : false}
              />
            </>
          ) : (
            <></>
          )}

          <label className="degree-label"> DEGREE </label>
          <Form.Select
            aria-label="Select your ultimate degree:"
            className="register-degree"
            name="degreeId"
            onChange={formik.handleChange}
            value={formik.values.degreeId}
            disabled={isEdit && user.userRole.id === 1 ? true : false}
          >
            {degreeList.length > 0 ? (
              degreeList.map((degree) => {
                return (
                  <option
                    key={degree.id}
                    value={degree.id}
                    className="register-option"
                  >
                    {degree.degreeName}
                  </option>
                );
              })
            ) : (
              <option value="" className="register-option">
                Nothing to be shown.
              </option>
            )}
          </Form.Select>

          <label className="academic-rank-label"> ACADEMIC RANK </label>
          <Form.Select
            aria-label="Select your ultimate academic rank:"
            className="register-academic-rank"
            name="academicRankId"
            onChange={(event) => {
              formik.setFieldValue("academicRankId", event.target.value);
            }}
            value={formik.values.academicRankId}
            disabled={isEdit && user.userRole.id === 1 ? true : false}
          >
            {academicRankList.length > 0
              ? academicRankList.map((academicRank) => {
                  return (
                    <option
                      key={academicRank.id}
                      value={academicRank.id}
                      className="register-option"
                    >
                      {academicRank.academicRankName}
                    </option>
                  );
                })
              : []}
            <option value="" className="register-option">
              No Rank.
            </option>
          </Form.Select>

          {isEdit && user.userRole.id === 3 ? (
            <>
              <label className="title-label"> TITLE </label>
              <Form.Select
                aria-label="Select your title:"
                className="register-title"
                name="titleId"
                onChange={(event) => {
                  formik.setFieldValue("titleId", event.target.value);
                }}
                value={formik.values.titleId}
                style={{
                  backgroundColor: "#da6b10",
                  backgroundImage:
                    "linear-gradient(225deg, #ff6e00 0%, #dd0000 100%)",
                }}
              >
                {titleList?.length > 0 ? (
                  titleList.map((t) => {
                    return (
                      <option
                        key={t.id}
                        value={t.id}
                        className="register-option"
                      >
                        {t.titleName}
                      </option>
                    );
                  })
                ) : (
                  <option value="" className="register-option">
                    No Title.
                  </option>
                )}
              </Form.Select>
            </>
          ) : (
            <></>
          )}

          {submitting ? (
            <button
              type="submit"
              disabled
              style={{
                width: "60px",
              }}
            >
              <span
                class="spinner-grow spinner-grow-sm"
                role="status"
                aria-hidden="true"
              ></span>
            </button>
          ) : (
            <button
              type="submit"
              style={{
                marginBottom: "0.1rem",
              }}
              disabled={isEdit && user.userRole.id === 1 ? true : false}
            >
              {isEdit ? "Save" : "Create account"}
            </button>
          )}
          
        </form>

        {isEdit ? (
          <></>
        ) : (
          <>
            <div className="register-login"> Already have an account? </div>
            <Link
              className="register-login-link"
              to="/login"
              style={{
                marginBottom: "3.8rem",
              }}
            >
              Log in
            </Link>
          </>
        )}
      </div>
    </section>
  );
};

export default Register;
