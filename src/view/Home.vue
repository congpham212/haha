<template>
  <!--<div id="home">-->
  <div class="home-wrap">
    <div class="banner-contain">
      <img src="../assets/nagakawa-banner.jpg" class="banner" alt="banner nagakawa">
    </div>
    <div class="main">
      <div class="logo-contain">
        <img src="../assets/logo-nagakawa.png" alt="logo nagakawa" class="logo">
        <!--        <div class="logo"></div>-->
      </div>
      <div class="slogan">
        <span>Tiêu chuẩn cuộc sống</span>
      </div>
<!--      <router-link v-bind:to="'/login'" replace/>-->
<!--      <router-link v-bind:to="'/register'" replace />-->
      <div class="div-form-acton div-login-wrap" v-if="isLogin">

<!--        <router-view />-->
        <login :is-show="isShow"
               :is-login="isLogin"
               @changeScreen="changeScreen"
               />
<!--        @updateData="updateData"-->
      </div>

      <div class="div-form-acton div-register-wrap" v-else>
<!--        <router-view />-->
        <register :is-login="isLogin"
                  @changeScreen="changeScreen"
        />
      </div>
    </div>

  </div>
  <!--</div>-->
</template>

<script>
import Login from "./Login";
import Register from "./Register";

export default {
  name: "Home",
  components: {
    Login,
    Register,
  },
  methods:{
    updateData(isShow){
      this.isShow = isShow;
      console.log("isShoow: " + isShow);
      this.$emit("updateData", isShow);
    },
    changeScreen(status){
      this.isLogin = status;
      console.log("parent status: " + status)
    }
  },
  data(){
    return{
      isLogin: true,
    }
  },
  props:{
    isShow: {
      type: Boolean,
      default: false,
    },
  }
}
</script>

<style scoped>

.home-wrap {
  width: 100%;
  height: 100%;
  margin: auto;
  position: relative;
}

.banner-contain {
  width: 100%;
  height: 50%;

}

.banner {
  width: 100%;
  height: 100%;
  background-size: cover;
  border-bottom: 8px solid #DB4437;

}

.main {
  width: 100%;
  height: 36%;
}

.logo-contain {
  width: 100%;
  height: 60%;


}

.logo {
  width: 45%;
  height: 90%;
  margin-left: 9%;
  /*background-image:url('../assets/logo-nagakawa.png') ;*/
  background-size: cover;
  background-position: bottom;
  /*box-shadow: 0px 3px 4px #00000029;*/
}

.slogan {
  font-style: normal;
  font-weight: bold;
  color: #00833E;
  font-size: 2vw;

}

.slogan span {
  width: 22%;
  height: 8%;
  margin-left: 19%;
}

.div-form-acton {
  position: absolute;
  top: 25%;
  right: 10%;
  z-index: 10;
  margin: 0;
  background-color: white;
}

.div-login-wrap {
  width: 28%;
  height: 45%;

}

.div-register-wrap {
  width: 28%;
  height: 55%;
}

</style>