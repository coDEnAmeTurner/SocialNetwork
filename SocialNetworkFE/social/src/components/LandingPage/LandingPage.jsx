import "../user/login.css";

const LandingPage = () => {
  return (
    <>
      <section className="login-container">
        <div className="login-title"> HOW DID I END UP HERE? </div>

        <div className="loginError" style={{
          fontSize: "2.4rem"
        }}>It has already passed 24 hours but your password hasn't been changed from the default value. Contact the admin to unlock your account!</div>
      </section>
    </>
  );
};

export default LandingPage;
