import axios from "axios";

const baseUrl = axios.create({
    baseUrl: process.env.APOLLO_APP_BASE_URL
});

export default baseUrl;