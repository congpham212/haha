import AuthService from '../service/auth.service'

const user = JSON.parse(localStorage.getItem('user'));

const initState = user ? {status: {loggedIn: true}, user} : {status: {loggedIn: false}, user: null};

const roles = user ? user.roles : null;


export const auth = {
    namespaced: true,
    state: initState,
    // role: this.isAdmin(),
    actions: {
        login({commit}, user) {
            return AuthService.login(user).then(
                user => {
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                });
        },

        logout({commit}) {
            AuthService.logout();
            commit('logout');
        },
        register({commit}, user) {
            return AuthService.register(user).then(
                response => {
                    commit('registerSuccess');
                    return Promise.resolve(response.data);
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error);
                }
            );
        },
        isAdmin(){
            if(roles){
                for (var i = 0; i < roles.length; i++){
                    if(roles[i].id == 1){
                        return true;
                    }
                }
            }
            return false;
        }
    },
    mutations: {
        loginSuccess(state, user){
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state){
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state){
            state.status.loggedIn = false;
            state.user = null;
        },
        registerSuccess(state){
            state.status.loggedIn = false;
        },
        registerFailure(state){
            state.status.loggedIn = false;
        }
    },

}