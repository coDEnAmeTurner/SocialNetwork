import HomePage from "../components/Feed/HomePage/HomePage";
import Login from "../components/user/Login";
import Register from "../components/user/Signup";
import MakePost from "../components/Posts/MakePost";


export const publicRoute = [
    {
        path:"/",
        component: HomePage
    },
    {
        path:"/login",
        component: Login
    },
    {
        path:"/signup",
        component: Register
    },
    {
        path:"/make-a-post",
        component: MakePost
    },
    // {
    //     path:"/",
    //     component: HomePage
    // },
]