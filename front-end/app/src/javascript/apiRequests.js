const axios = require('axios');
const apiUrl = 'http://localhost:8090';

async function getRequest(url) {
    let response = await axios.get(apiUrl + url);

    return response.data
}

export default {
    apiUrl,
    getRequestToApi(url) {
        return getRequest(url);
    }

}