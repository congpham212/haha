import axios from 'axios';
import authHeader from "./auth-header";
import {url} from '../key/Constants';

class UserService {
    actionUser(action, endpoint) {
        switch (action) {
            case 'get':
                return axios.get(url.API_USER + endpoint, {headers: authHeader()});
            case 'post':
                return axios.post(url.API_USER + endpoint, {headers: authHeader()});
            case 'put':
                return axios.put(url.API_USER + endpoint, {headers: authHeader()});
            case 'delete':
                return axios.delete(url.API_USER + endpoint, {headers: authHeader()});
            default :
                return axios.patch(url.API_USER + endpoint, {headers: authHeader()});
        }

    }


    // thao tác với user
    addUser() {
        return this.actionUser('post');
    }

    getUser(id) {
        return this.actionUser('get', '/' + id);
    }

    getAllUser() {
        return this.actionUser('get', '/alluser');
    }

    getAllUserActive() {
        return this.actionUser('get');
    }

    getUserByPageUser(keyword, page) {
        if (!page) {
            page = 1;
        }
        return this.actionUser('get', '/search?keyword=' + keyword + '&page=' + page);
    }

    getTotalPageUser() {
        return this.actionUser('get', '/totalPage');
    }

    getPageSizeUser() {
        return this.actionUser('get', '/pageSize');
    }

    deleteUser(id) {
        return this.actionUser('delete', '/' + id);
    }

    editUser(id) {
        return this.actionUser('put', '/' + id);
    }

    getUserSearchAdvanceByPage(username, fullname, address, gender, deptId, birthStart, birthEnd, page) {
        console.log("getUserSearchAdvanceByPage: ");
        console.log(birthStart);
        console.log(birthStart);
        return this.actionUser('get', '/searchAdvance?username=' + username + '&fullname=' + fullname
            + '&address=' + address + '&gender=' + gender + '&deptId=' + deptId + '&birthStart=' + birthStart +
            '&birthEnd=' + birthEnd + '&page=' + page);
    }

    getTotalPageSearchAdvance(username, fullname, address, gender, deptId, birthStart, birthEnd) {
        return this.actionUser('get', '/totalPageAdv?username=' + username + '&fullname=' + fullname
            + '&address=' + address + '&gender=' + gender + '&deptId=' + deptId + '&birthStart=' + birthStart +
            '&birthEnd=' + birthEnd + '&page=');
    }




    getRole() {
        return axios.get(url.API_ROLE, {headers: authHeader()});
    }
}

export default new UserService();