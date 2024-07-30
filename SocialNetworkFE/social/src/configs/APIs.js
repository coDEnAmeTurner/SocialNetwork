import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8288/SocialNetworkBE/';

export const endpoints = {
    'register': '/api/users/',
    'degreeList': '/api/degrees/',
    'academicRankList': '/api/academic_ranks/',
    'login': '/api/login/',
    'current-user': '/api/current-user/',
    'content-types': '/api/content-types/', 
    'post': "/api/posts/",
    'get-posts': '/api/posts/',
    'get-invitation-by-post': (postId) => `/api/posts/${postId}/get-invitation/`,
    'get-questions-by-post': (postId) => `/api/posts/${postId}/get-questions/`,
    'get-choices-by-question': (questionId) => `/api/questions/${questionId}/get-choices/`,
    'get-contentType-by-post': (postId) => `/api/posts/${postId}/get-contentType/`,
    'get-author-by-post': (postId) => `/api/posts/${postId}/get-author/`,
    'get-postImages-by-post': (postId) => `/api/posts/${postId}/get-postImages/`,
    'edit-post': '/api/posts/',
    'get-user-role': '/api/users/get-user-role/',
    'update-post': (postId) => `/api/posts/${postId}/`,
    'update-invitation': (invitationId) => `/api/invitations/${invitationId}/`,
    'update-question': (questionId) => `/api/questions/${questionId}/`,
    'update-choice': (choiceId) => `/api/choices/${choiceId}/`,
    'delete-post': (postId) => `/api/posts/${postId}/`,
    'delete-question': (questionId) => `/api/questions/${questionId}/`,
    'delete-choice': (choiceId) => `/api/choices/${choiceId}/`,
    'get-posts-by-author': (authorId) => `/api/users/${authorId}/get-posts/`,
    'get-comments-by-post': (postId) => `/api/posts/${postId}/get-comments/`,
    'get-comments-by-parent': (commentId) => `/api/comments/${commentId}/get-comments/`,
    'comment-a-post': (postId) => `/api/posts/${postId}/post-comment/`,
    'reply-a-comment': (commentId) => `/api/comments/${commentId}/post-comment/`,
    'get-post-detail': (postId) => `/api/posts/${postId}/`,
    'get-author-by-comment': (commentId) => `/api/comments/${commentId}/get-author/`,
    'delete-comment': (commentId) => `/api/comments/${commentId}/`,
    'update-comment': (commentId) => `/api/comments/${commentId}/`,
    'do-action': (postId) => `/api/posts/${postId}/do-action/`,
    'get-emails': (inviId) => `/api/invitations/${inviId}/get-emails/`,
    'update-user': (userId) => `/api/users/${userId}/`,
    'titles': "/api/titles/",
    'get-rank-by-userId': (userId) => `/api/users/${userId}/get-rank/`,
    'get-degree-by-userId': (userId) => `/api/users/${userId}/get-degree/`,
    'get-title-by-userId': (userId) => `/api/users/${userId}/get-title/`,
    'get-student-id-by-userId': (userId) => `/api/users/${userId}/get-student-id/`,
    'update-user-avatar': (userId) => `/api/users/${userId}/update-avatar/`,
    'count-posts': `/api/users/count-posts/`,
    'vote': "/api/questions/vote/",
    'get-vote': (questionId)=> `/api/questions/${questionId}/get-vote/`,
    'get-users-by-username': "/api/users/",
    "check-locked": "/api/users/check-locked/",
    "get-inviIds": "/api/users/get-inviIds/",
    "create-email": "/api/emails/",
    "delete-email": (emailId)=>`/api/emails/${emailId}/`
}

export default axios.create({
    baseURL: BASE_URL
});

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load('token')
        }
    })
}