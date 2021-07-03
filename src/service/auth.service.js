import axios from 'axios'
import {url} from '../key/Constants'

class AuthService{
    login(user){
        return axios.post(url.API_AUTH + 'login', {
            email: user.email,
            password: user.password
        })
            .then(response => {
                if(response.data.accessToken){
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout(){
        localStorage.removeItem('user');
    }

    register(user){
        return axios.post(url.API_AUTH + 'register', {
            email: user.email,
            password: user.password,
            fullname: user.fullname,
            birthday: user.birthday,
            address: user.address,
            deptId: user.deptId,
            gender: user.gender,
            role: user.role
        })
    }
}

export default new AuthService();