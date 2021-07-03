import VueRouter from "vue-router";
import Vue from 'vue';
import NagakawaHome from "../view/NagakawaHome";
import Login from "../view/Login";
import Register from "../view/Register";
import Home from "@/view/Home";
import InsuranceMain from "../view/pages/InsuranceMain";
import AccountSetting from "../view/pages/AccountSetting";
import HelloWorld from "@/components/HelloWorld";
import ListRegisterWarranty from "@/view/pages/ListRegisterWarranty";
import WarrantyStatistics from "@/view/pages/WarrantyStatistics";
import WarrantyStatisticsForMonth from "@/view/pages/WarrantyStatisticsForMonth";

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: 'history',
    routes: [
        // {path: '/', name: 'NagakawaHome', component: NagakawaHome},
        {
            path: '/nagakawahome', name: 'NagakawaHome', component: NagakawaHome,
            children: [
                {path: '/', name: 'Insurance', component: InsuranceMain},
                {path: '/insurance', name: 'Insurance', component: InsuranceMain},
                {path: '/accountsetting', name: 'AccountSetting', component: AccountSetting},
                {path: '/product', name: 'Product', component: ListRegisterWarranty},
                {path: '/news', name: 'News', component: WarrantyStatistics},
                {path: '/system', name: 'System', component: WarrantyStatisticsForMonth},
            ]
        },

        {
            path: '/home', name: 'Home', component: Home,
            children: [
                {path: '/login', name: 'Login', component: Login},
                {path: '/register', name: 'Register', component: Register},
            ]
        },
        {
            path: '/', name: 'Home', component: Home,
            children: [
                {path: '/login', name: 'Login', component: Login},
                {path: '/register', name: 'Register', component: Register},
            ]
        },

        // {path: '/', name: 'Home', component: Home},
        // {path: '/', name: 'Home', component: Home},
        {path: '/logout', name: 'Logout'},
        {path: '/:pathMatch(.*)*', name: 'NotFound', component: HelloWorld},

    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register', '/home', '/'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});