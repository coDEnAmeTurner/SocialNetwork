import { Link, useNavigate } from "react-router-dom";
import { Form, Image } from "react-bootstrap";
import "./register.css";
import { useFormik } from "formik";
import * as Yup from "yup";
import APIs, { endpoints } from "../../configs/APIs";
import { useEffect, useState } from "react";

const Register = () => {
  const [degreeList, setDegreeList] = useState([]);
  const [academicRankList, setAcademicRankList] = useState([]);
  const [avatar, setAvatar] = useState(null);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const getDegrees = async () => {
    try {
      const res = await APIs.get(endpoints['degreeList']);
      setDegreeList(res.data);

    } catch (ex) {
      console.error(ex);
    }
  }

  const getAcademicRankList = async () => {
    try {
      const res = await APIs.get(endpoints['academicRankList']);
      console.log(res.data);
      setAcademicRankList(res.data);

    } catch (ex) {
      console.error(ex);
    }
  }

  useEffect(() => {
    if (avatar !== undefined)
      setAvatar(avatar);
  }, [avatar])

  const register = async (newUser, avatarFile) => {
    const fileForm = new FormData();
    fileForm.append("file", avatarFile)
    try {
      let res = await APIs.post(endpoints['register'], fileForm, {
        headers: {
          'content-type': 'multipart/form-data; application/x-www-form-urlencoded'
        },
        params: newUser
      });
      if (res.status === 201)
        navigate("/login", );
    } catch (ex) {
      console.log(ex);
      setError(ex.response.data['message']);
    }
  }

  const formik = useFormik({
    initialValues: {
      username: "",
      email: "",
      password: "",
      phone: ""
    },

    validationSchema: Yup.object({
      username: Yup.string()
        .max(20, "Maximum 20 characters")
        .min(6, "Minimum 6 characters")
        .required("Required"),
      email: Yup.string()
        .max(50, "Maximum 50 character")
        .required("Required")
        .matches(
          /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
          "Please enter valid email address"
        ),
      password: Yup.string()
        .required("Required")
        .matches(
          /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{6,19}$/,
          "Minimum 6 characters, at least one letter, one number, one special character"
        ),
      phone: Yup.string()
        .max(10, "Maximum 10 characters")
        .min(9, "Minimum 9 characters")
        .required("Required")
        .matches(
          /^\d+$/,
          "Only numbers allowed."
        ),
    }),

    onSubmit: (values) => {
      const newUser = {
        //user obj
        "fullName": values.fullname,
        "username": values.username,
        "pw": values.password,
        "email": values.email,
        "phone": values.phone,
        "dob": values.dob,
        
        //typical_user
        "degreeId": !values.degreeId?degreeList[0].id:values.degreeId,
        "academicRankId": !values.academicRankId?academicRankList[0].id:values.academicRankId,
  
        //alumnus
        "studentId": values.studentId
      };

      register(newUser, values.avatar);
    }
    
  });

  useEffect(()=> {
    getDegrees();
    getAcademicRankList();
  }, []);

  return (
    <section className="register-container">
      <div className="register-title"> Sign Up </div>
      <div className="register-error"> {error} </div>
      <div className="register-input">
        <form onSubmit={(event) => { event.preventDefault(); formik.handleSubmit(event); }}>
          <Image src={avatar ? URL.createObjectURL(avatar) : "https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg"} />

          <Form.Group controlId="formFile" className="mb-3 avatar-label">
            <Form.Label>AVATAR: </Form.Label>
            <Form.Control type="file" className="register-file file-label"
              name="avatar"
              onChange={(event) => {
                formik.setFieldValue("avatar", event.target.files[0]);
                setAvatar(event.target.files[0] !== undefined ? event.target.files[0] : avatar);
              }}
              files={[avatar]}
            />
          </Form.Group>

          <label className="fullname-label"> FULLNAME </label>
          <input
            id="fullname"
            name="fullname"
            type="text"
            placeholder="Enter full name"
            onChange={formik.handleChange}
            value={formik.values.fullname}
            className="register-fullname"
          />

          <label className="username-label"> USERNAME </label>
          <input
            id="username"
            name="username"
            type="text"
            placeholder="Enter username"
            onChange={formik.handleChange}
            value={formik.values.username}
            className="register-username"
          />
          {formik.errors.username && (
            <p className="errorMsg">{formik.errors.username}</p>
          )}
          <label className="password-label"> PASSWORD </label>
          <input
            id="password"
            name="password"
            type="password"
            placeholder="Enter password"
            onChange={formik.handleChange}
            value={formik.values.password}
            className="register-password"
          />
          {formik.errors.password && (
            <p className="errorMsg">{formik.errors.password}</p>
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
          />
          {formik.errors.phone && (
            <p className="errorMsg">{formik.errors.phone}</p>
          )}
          <Form.Group controlId="formDob" className="mb-3 avatar-label">
            <Form.Label  className="dob-label">DATE OF BIRTH: </Form.Label>
            <Form.Control type="date" className="register-dob file-dob"
              name="dob"
              onChange={(event) => {
                formik.handleChange(event);
              }}
              value={formik.values.dob}
            />
          </Form.Group>
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
          />
          <label className="degree-label"> DEGREE </label>
          <Form.Select aria-label="Select your ultimate degree:" className="register-degree" name="degreeId"
            onChange={formik.handleChange} value={formik.values.degreeId}>
            {degreeList.length > 0 ? degreeList.map(
              (degree) => {
                return <option key={degree.id} value="{degree.id}" className="register-option">{degree.degreeName}</option>
              }
            ) : <option value="" className="register-option">Nothing to be shown.</option>
            }

          </Form.Select>

          <label className="academic-rank-label"> ACADEMIC RANK </label>
          <Form.Select aria-label="Select your ultimate academic rank:" className="register-academic-rank" name="academicRankId"
            onChange={formik.handleChange} value={formik.values.academicRankId}>
            {academicRankList.length > 0 ? academicRankList.map(
              (academicRank) => {
                return <option key={academicRank.id} value="{academicRank.id}" className="register-option">{academicRank.academicRankName}</option>
              }
            ) : []
            }
            <option value="" className="register-option">No Rank.</option>
          </Form.Select>

          <button type="submit"> Create account </button>
        </form>

        <div className="register-login"> Already have an account? </div>
        <Link className="register-login-link" to="/login">
          Log in
        </Link>
      </div>
    </section>
  );
};

export default Register;
