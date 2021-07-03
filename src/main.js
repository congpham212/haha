import Vue from 'vue'
import App from './App.vue'
// import VeeValidate from 'vee-validate'\
// import VeeValidate from 'vee-validate'
import VueRouter from 'vue-router'
import store from "./store";
import {router} from './router/router'

import './assets/css/main.css'

import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

Vue.config.productionTip = false

// Vue.use(VeeValidate)
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
