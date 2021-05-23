
import axios from "axios"

// api config
const API_HOST = 'https://localhost:9090'

export function signup(signup_info: {
    username: string,
    email: string,
    password: string,
})
{
    return axios.post(`${API_HOST}/signup`,
        signup_info,
    )
}

