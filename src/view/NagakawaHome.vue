<template>
  <div id="nagakawa-home">
    <div class="nagakawa-home-wrap">
      <div class="navbar">
        <div class="logo"></div>
        <div class="nav-border"></div>
        <div class="nav-menu">
          <ul class="ul-menu">
            <li class="child-element-menu" v-for="(category, index) in categories" :key="index"
                :class="index==current_category? 'catagory-active':''"
                @click="changeCategory(index)"
            >
              <router-link :to="category.path" class="link-category" :class="index==current_category? 'catagory-active':''">
                <i :class="[category.class]"></i>
                <span>{{ category.name }}</span>
              </router-link>
            </li>
            <!--            <li class="child-element-menu">-->
            <!--              <i class="fas fa-newspaper"></i>-->
            <!--              <span>News</span>-->
            <!--            </li>-->
            <!--            <li class="child-element-menu">2</li>-->
            <!--            <li class="child-element-menu">3</li>-->
            <!--            <li class="child-element-menu">-->
            <!--              <i class="fas fa-user-shield"></i>-->
            <!--              <span>Insurance</span>-->
            <!--            </li>-->
          </ul>
        </div>
      </div>
      <div class="main">
        <div class="menu">
          <div class="list-menu"><i class="fas fa-bars resize-icon-2"></i></div>
          <div class="right-menu">
            <div class="language-choose">
              <div class="select-country">
                <ul>
                  <li>
                    <span>VN </span>
                    <img src="../assets/icon/vn.png" alt="">
                  </li>
                  <li>
                    <span>VN </span>
                    <img src="../assets/icon/vn.png" alt="">
                  </li>
                  <li>
                    <span>VN </span>
                    <img src="../assets/icon/vn.png" alt="">
                  </li>
                </ul>
              </div>
              <span><i class="fas fa-caret-down"></i></span>
              <!--              <select name="" id="">-->
              <!--                <option value="1">-->
              <!--                  <div class="country-name">VN</div>-->
              <!--                  <div class="nationnal-flag"><img src="../assets/icon/vn.png" alt=""></div>-->
              <!--                </option>-->
              <!--                <option value="2" style='background-image: url("../assets/icon/england.png");'>-->
              <!--                  <div class="country-name">EN</div>-->
              <!--                  <img src="../assets/icon/england.png" alt="">-->
              <!--                </option>-->
              <!--                <option value="2" style='background-image: url("../assets/icon/england.png");'>-->
              <!--                  <div class="country-name">JP</div>-->
              <!--                  <img src="../assets/icon/jp.png" alt="">-->
              <!--                </option>-->
              <!--              </select>-->
            </div>
            <div class="action-user" @click="hideShowDropMenu(true)" v-closable="onClose">
              <div class="avatar-user">
                <i class="fas fa-user-circle resize-icon"></i>
              </div>
              <div class="username-action">
                <span>username</span>
                <span><i class="fas fa-caret-down"></i></span>
                <!--                <button @click="logOut" >logout</button>-->
              </div>
              <div class="drop-menu-action" v-if="isShowDropMenu"
              >
                <ul class="ul-dropdown-menu">
                  <li class="dropdown-menu-item"><router-link to="/accountsetting" class="dropdown-menu-item">Thông tin</router-link></li>
                  <li class="dropdown-menu-separator"></li>
                  <li class="dropdown-menu-item">Đổi mật khẩu</li>
                  <li class="dropdown-menu-separator"></li>
                  <li class="dropdown-menu-item" @click.prevent="logOut">Đăng xuất</li>
                </ul>
              </div>

            </div>
          </div>
        </div>
        <div class="content">
          <router-view></router-view>
          <!--          <account-setting />-->
<!--          <div class="info-page">-->
<!--            <div class="title"></div>-->
<!--            <div class="path-page"></div>-->
<!--          </div>-->
<!--          <div class="page-contain">-->
<!--            <router-view/>-->
<!--            &lt;!&ndash;            <InsuranceMain/>&ndash;&gt;-->
<!--          </div>-->
        </div>
      </div>
      <!--      <div> <router-view /></div>-->
    </div>
  </div>
</template>

<script>

import Vue from 'vue'
// import InsuranceMain from "./pages/InsuranceMain";
// import AccountSetting from "./pages/AccountSetting";

Vue.directive('closable', {
  inserted: (el, binding, vnode) => {
// assign event to the element
    el.clickOutsideEvent = function (event) {
      // here we check if the click event is outside the element and it's children
      if (!(el == event.target || el.contains(event.target))) {
        // if clicked outside, call the provided method
        vnode.context[binding.expression](event)
      }
    }
// register click and touch events
    document.body.addEventListener('click', el.clickOutsideEvent)
    document.body.addEventListener('touchstart', el.clickOutsideEvent)
  },
  unbind: function (el) {
// unregister click and touch events before the element is unmounted
    document.body.removeEventListener('click', el.clickOutsideEvent)
    document.body.removeEventListener('touchstart', el.clickOutsideEvent)
  },
  stopProp(event) {
    event.stopPropagation()
  },
})

export default {
  name: "NagakawaHome",
  data() {
    return {
      isShowDropMenu: false,
      categories: [],
      current_category: 0,
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/home');
    },
    hideShowDropMenu(isShow) {
      this.isShowDropMenu = isShow;
    },
    onClose() {
      this.hideShowDropMenu(false);
    },
    changeCategory(index) {
      this.current_category = index;
    }
  },
  created() {
    this.categories.push({name: 'News', id: '1', class: 'fas fa-newspaper', path: '/news'});
    // <i class="fas fa-newspaper"></i>
    this.categories.push({name: 'Customer Loyalty', id: '2', class: 'fas fa-user-friends', path: '/customerloyalty'});
    //<i class="fas fa-user-friends"></i>
    this.categories.push({name: 'Product', id: '3', class: 'fas fa-fan', path: '/product'});
    //<i class="fas fa-fan"></i>
    this.categories.push({name: 'User', id: '4', class: 'fas fa-user-circle', path: '/user'});
    //<i class="fas fa-user-circle"></i>
    this.categories.push({name: 'System', id: '5', class: 'fas fa-sliders-h', path: '/system'});
    //<i class="fas fa-sliders-h"></i>
    this.categories.push({name: 'Insurance', id: '6', class: 'fas fa-user-shield', path: '/insurance'});
    //<i class="fas fa-user-shield"></i>
    this.categories.push({
      name: 'Insurance Station',
      id: '7',
      class: 'fas fa-question-circle',
      path: '/insurancestation'
    });

    //<i class="fas fa-question-circle"></i>
    this.categories.push({name: 'Stamps', id: '8', class: 'fas fa-stamp', path: '/stamp'});
    //  <i class="fas fa-stamp"></i>


    for (let index in this.categories){
      if(this.categories[index].name == this.$router.currentRoute.name){
        this.current_category = index;
        console.log("hahah")
        break;
      }
      // console.log(this.categories[index].path.slice(1));
      // if(category.path == this.$router.currentRoute.name){
      //
      // }
    }
    console.log(this.$router.currentRoute.name);
  },
  components: {
    // InsuranceMain,
    // AccountSetting,
  }
}
</script>

<style scoped>

#nagakawa-home {
  width: 100%;
  height: 100%;
  background-color: #cccccc;
}

.nagakawa-home-wrap {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
}

.navbar {
  width: 16%;
  height: 100%;
  background-color: white;
  /*box-shadow: 4px 0px 0px 4px rgba(255, 255, 255, 0.3);*/
}

.logo {
  width: 100%;
  height: 10%;
  background: url("../assets/logo_nagakawa1.png");
  background-size: cover;
  background-position: center;
}

.nav-border {
  width: 100%;
  height: 4%;
  background-color: #00833E;
}

.nav-menu {
  width: 100%;
  height: 86%;

}

.ul-menu {
  width: 100%;
  height: 100%;
  list-style-type: none;
}

.child-element-menu {
  width: 100%;
  height: 4%;
  margin: auto;
  margin-top: 8%;
  padding-left: 12%;
  /*border: 1px solid #DB4437;*/

}

.link-category{
  text-decoration: none;
  color: rgba(0, 0, 0, 1);
  font-size: 1.2rem;
}

.link-category:hover{
  color: #DB4437;
  cursor: pointer;
}

/*.child-element-menu i {*/
/*  margin-left: 10%;*/
/*}*/

.child-element-menu span {
  margin-left: 6%;
  font-size: 1.2rem;
  /*font-weight: 600;*/
}

.child-element-menu:hover {
  color: #DB4437;
  cursor: pointer;
}

.catagory-active {
  color: #00833E;
}

.main {
  width: 84%;
  margin-left: 0.2%;
  height: 100%;
  background-color: #cccccc;
}

.menu {
  width: 100%;
  height: 6%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: white;
}

.list-menu {
  width: 10%;
  height: 33%;
  margin-left: 2%;
  margin-top: 0.7%;
  color: #DB4437;
}

.resize-icon-2 {
  font-size: 2rem;
}

.right-menu {
  width: 25%;
  height: 100%;
  line-height: 1;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  position: relative;
}

.language-choose {
  position: relative;
  width: 39%;
  height: 70%;
  line-height: 100%;
  text-align: center;
  margin: auto;
  font-family: Arial;
  display: flex;
  flex-direction: row;
  margin-right: 5%;
}

.language-choose span {
  text-align: center;
  margin: auto;
}

.select-country {
  width: 80%;
  height: 100%;
  /*border: 1px solid red;*/
  overflow: hidden;
  margin: 0;
}

.select-country:hover {
  overflow: visible;
}

.select-country ul {
  list-style-type: none;
  height: 100%;
  width: 100%;
}

.select-country ul li {
  height: 100%;
  width: 100%;
  text-align: right;
  padding-top: 7%;
}


.language-choose > select {
  height: 100%;
  /*display: none;*/
}

.select-selected {

}

.action-user {
  width: 80%;
  height: 70%;
  line-height: 100%;
  text-align: center;
  margin: auto;
  margin-right: 10%;
  display: flex;
  flex-direction: row;
  /*justify-content: space-between;*/
}

.avatar-user {
  width: 20%;
  color: #DB4437;
  line-height: 2;
}

/*.avatar-user > i{*/
/*  color: #DB4437;*/
/*  height: 30px;*/
/*  width: 30px;*/
/*  margin: auto;*/
/*}*/

.resize-icon {
  width: 80%;
  height: 80%;
}

.username-action {
  width: 70%;
  /*border: 1px solid red;*/
  line-height: 1.5;
  /*margin-left: 0%;*/
}

.username-action:focus {
  background-color: yellow;
}

.drop-menu-action {
  /*display: none;*/
  background-color: white;
  width: 45%;
  height: 200%;
  position: absolute;
  top: 100%;
  left: 45%;
  text-align: left;
  z-index: 3;
  box-shadow: 1px 2px 1px 1px rgba(0, 0, 0, 0.3);
}

.drop-menu-action ul {
  list-style-type: none;
  height: 100%;
}

.drop-menu-action ul:hover {
  cursor: pointer;
}

.dropdown-menu-item {
  height: 30%;
  line-height: 150%;
  padding-left: 3%;
}

.dropdown-menu-item:hover {
  background-color: #DB4437;
  color: white;
}

.dropdown-menu-separator {
  border-bottom: 1px solid rgba(0, 0, 0, 0.25);
}

.username-action > span {
  margin-right: 10%;
}

.username-action:hover {
  cursor: pointer;
  color: #DB4437;
  /*display: block;*/
}


.content {
  width: 98%;
  height: 94%;
  margin: auto;
}

.info-page {
  width: 100%;
  height: 4%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.page-contain {
  width: 99%;
  height: 80%;
  margin: auto;
  margin-top: 3%;
}

</style>