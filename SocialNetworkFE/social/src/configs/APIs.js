import axios from "axios";

const BASE_URL = 'http://localhost:8288/SocialNetworkBE/';

export const endpoints = {
    'register': '/api/users/',
    'degreeList': '/api/degrees/',
    'academicRankList': '/api/academic_ranks/',
}

export default axios.create({
    baseURL: BASE_URL
});