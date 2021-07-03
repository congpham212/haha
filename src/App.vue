<template>
  <div id="app">

<!--    <img alt="Vue logo" src="./assets/logo.png">-->
<!--    <Home />-->
<!--&lt;!&ndash;    <router-link&ndash;&gt;-->
<!--    <nagakawa-home />-->
    <router-view />
  </div>
</template>

<script>
// import Home from './view/Home.vue'
// import NagakawaHome from "./view/NagakawaHome";

export default {
  name: 'app',
  data(){
    return{
      isShow: false,
    }
  },
  methods:{

  },
  components: {
    // Home,
    // NagakawaHome,
  }
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
}

html{
  width: 100%;
  height: 100%;
}

body{
  width: 100%;
  height: 100%;
  font-family: 'roboto', sans-serif;
}

#app {
  width: 100%;
  height: 100%;
  /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
  /*-webkit-font-smoothing: antialiased;*/
  /*-moz-osx-font-smoothing: grayscale;*/
  /*text-align: center;*/
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
}
</style>
