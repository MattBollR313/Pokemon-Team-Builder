import axios from 'axios';

export default axios.create({
    baseURL: 'https://e53a-67-20-253-125.ngrok-free.app',
    headers: {"ngrok-skip-browser-warning": true, "Access-Control-Allow-Origin": "*"}
});