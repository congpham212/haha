<template>
  <div id="login">
    <div class="login-wrap">
      <div class="header text-primary">
        <span>Đăng nhập</span>
      </div>
      <div class="main">
        <form action="" class="form-wrap" @submit.prevent="handleLogin">
          <div class="row-form" @click="enterEmail">
            <div class="label-field"><span>Email</span></div>
            <div class="input-form">
              <input
                  type="text" class="input-field"
                  placeholder="example@gmail.com" autofocus
                  required tabindex="1"
                  v-model="user.email"
                  ref="inputEmail"
              /></div>

          </div>
          <div class="row-form" @click="enterPass">
            <div class="label-field"><span>Mật khẩu</span></div>
            <div class="input-form">
              <input class="input-field" type="password"
                     required tabindex="2"
                     placeholder="************"
                     v-model="user.password"
                     ref="inputPass"
              /></div>
          </div>
          <div class="row-form-min">
            <div class="input-checkbox">
              <label>
                <input type="checkbox" name="key" value="value" class="check-box-field">
                <span class="label-field-checkbox" tabindex="3"></span><span class="label-checkbox">Lưu ý email</span>
              </label>
            </div>
            <div class="forgot-pass text-link">
              <span>
                Quên mật khẩu?
              </span>
            </div>
          </div>
          <div class="row-action">
            <button class="btn btn-submit" tabindex="4">Đăng nhập</button>
          </div>
        </form>

      </div>
      <div class="div-register ">
        <span>Bạn chưa có tài khoản? <span class="text-link" @click="hideShowLogin(false)">Đăng ký ngay!</span></span>
      </div>
    </div>
  </div>

</template>

<script>

import User from "../model/user"
import Validator from "../utils/Validator"
// import user from "../model/user";

export default {
  name: "Login",
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: '',
      show: false,
      type: 'password',
      msg: [],
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      // console.log(this.$validator.validateAll());
      // this.$validator.validateAll().then(isValid => {
      //   if (!isValid) {
      //     this.loading = false;
      //     return;
      //   }
      if (this.user.email && this.user.password) {
        if (Validator.validateEmail(this.user.email)) {
          this.$store.dispatch('auth/login', this.user).then(
              () => {
// this.$emit()
                console.log("thanh conggggggggggggggggggg")
                this.isShow = true;
                console.log("thanh conggggggggggggggggggg")
                this.$emit("updateData", true);
                this.$router.replace('/nagakawahome');
              },
              error => {
                this.loading = false;
                this.message = (error.response && error.response.data) || error.message || error.toString();
              }
          );
        }
        else{
          console.log("emailllllll k hợp lệ")
        }
      } else {
        console.log("loggggggggggin")
      }
      // });
    },
    hideShowLogin(status){
      // this.isLogin = false;
      console.log("status: " + status)
      this.$emit("changeScreen", false)
    },
    enterEmail(){
      this.$refs.inputEmail.focus();

      // this.$refs.inputEmail.$el.focus()
    },
    enterPass(){
      this.$refs.inputPass.focus();

      // this.$refs.inputEmail.$el.focus()
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  // watch: {
  //   email(value) {
  //     if (Validator.validateEmail(value)) {
  //       this.user.email = value;
  //     } else {
  //       console.log("loiiiiiiiiiiii");
  //       this.msg['email'] = 'Please enter a valid email address';
  //     }
  //   }
  // },
  components: {},
  props: {
    isLogin: {
      type: Boolean,
      default: false,
    }
  },
  created() {
    this.msg['email'] = 'emaillllllllllll';
    // console.log(this.$data.loginRequest);
    console.log(this.loggedIn);

    if (this.loggedIn) {
      console.log(this.loggedIn);
      this.$router.push('/nagakawahome');
    }

  },
}
</script>

<style scoped>



#login {
  width: 100%;
  height: 100%;
}

.login-wrap {
  width: 100%;
  height: 100%;
  box-shadow: 0px 2px 4px 1px rgba(0, 0, 0, 0.75);
  /*margin-bottom: 100px;*/
}

.header {
  width: 100%;
  height: 8%;
  padding-top: 5%;
  /*padding-top: 200px;*/
}

.header span {
  width: 26%;
  height: 100%;
  /*color: #00833E;*/
  font-size: 2vw;
  font-weight: bold;
  text-align: left;
  opacity: 80%;
  margin-left: 32%;


}

.main {
  width: 100%;
  height: 60%;
  margin-top: 7%;
}

.form-wrap {
  width: 100%;
  height: 100%;
}

.row-form {
  width: 70%;
  height: 18%;
  border: 1px solid rgba(112, 112, 112, 0.1);
  background-color: rgba(112, 112, 112, 0.1);
  border-radius: 5px;
  margin: auto;
  margin-top: 2%;
}

.input-form {
  width: 100%;
  height: 30%;
}

.input-form span {
  width: 10%;
  height: 0.7rem;
  margin-top: 100px;
}

.label-field {

}

.label-field span {
  width: 8%;
  height: 21%;
  /*font-size: 10px;*/
  font-size: 0.7vw;
  font-weight: bold;
  margin-left: 4%;
}

.input-field {
  width: 92%;
  height: 100%;
  /*margin-bottom: 46%;*/
  margin-left: 4%;
  margin-top: 2%;
  outline: none;
  border: none;
  background-color: rgba(112, 112, 112, 0.001);
}

.input-field input:hover {
  outline: none;
}

.row-form-min {
  width: 70%;
  height: 10%;
  line-height: 100%;
  margin: auto;
  margin-top: 2%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.input-checkbox {
  width: 40%;

}

.input-checkbox > label > input[type="checkbox"] {
  display: none;
}

.input-checkbox > label {
  /*vertical-align: middle;*/
  /*display: flex;*/
  /*flex-direction: row;*/
  /*justify-content: space-between;*/
}

.input-checkbox > label > input[type="checkbox"] + *::before {
  content: "";
  line-height: 1;
  display: inline-block;
  vertical-align: bottom;
  width: 1em;
  height: 1em;
  border-radius: 10%;
  border: 0.2em solid #00833E;
  /*margin-right: 10%;*/
  /*border-style: solid;*/
  /*border-width: 0.1rem;*/
  /*border-color: gray;*/
}

.input-checkbox > label > input[type="checkbox"]:checked + *::before {
  content: "✓";
  color: white;
  line-height: 100%;
  text-align: center;
  background: teal;
  border-color: teal;
}

.input-checkbox > label > input[type="checkbox"]:checked + * {
  color: teal;
}

.check-box-field {

}

.label-field-checkbox {
  /*margin-left: 10%;*/
  font-size: 0.8vw;
  /*width: 100%;*/
  line-height: 100%;
  /*text-align: center;*/
}

.label-checkbox{
  font-size: 0.8vw;
  /*width: 100%;*/
  line-height: 100%;
  margin-left: 5%;
}

.forgot-pass {
  width: 32%;
  height: 100%;
  line-height: 240%;
  font-size: 0.8vw;
  /*color: #00833E;*/
}

/*.forgot-pass:hover{*/
/*  cursor: pointer;*/
/*  color: red;*/
/*}*/

.forgot-pass > span {
  width: 30%;
  height: 100%;
  text-align: center;
  line-height: 1;
}

.row-action {
  width: 67%;
  height: 18%;
  margin: auto;
  margin-top: 3%;

}

.btn-submit {
  width: 84%;
  height: 100%;
  margin: auto;
  margin-left: 8%;
  border: 1px solid #00833E;
  border-radius: 10px;
  background-color: rgba(0, 131, 62, 0.8);
  color: white;
  font-size: 1vw;
  font-weight: bold;
}

.btn-submit:hover {
  cursor: pointer;
  background-color: white;
  color: rgba(0, 131, 62, 0.8);
}

.div-register {
  width: 51%;
  height: 12%;
  margin: auto;
}

.div-register > span {
  width: 100%;
  height: 100%;
  /*margin: auto;*/
  /*margin-left: 30%;*/
  /*font-weight: re;*/
  font-size: 0.8vw;
}

.div-register > span > span {
  font-weight: bold;
  /*color: #00833E;*/
}


</style>