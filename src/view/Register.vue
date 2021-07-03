<template>
<div id="register">
  <div class="register-wrap">
    <div class="header text-primary">
      <span>Đăng ký</span>
    </div>
    <div class="main">
      <form action="" class="form-wrap">
        <div class="row-form">
          <div class="label-field"><span>Họ</span></div>
          <div class="input-form"><input type="text" class="input-field" placeholder="Phạm"></div>
        </div>
        <div class="row-form">
          <div class="label-field"><span>Tên</span></div>
          <div class="input-form"><input type="text" class="input-field" placeholder="Công"></div>
        </div>
        <div class="row-form">
          <div class="label-field"><span>Mật khẩu</span></div>
          <div class="input-form"><input type="password" class="input-field" placeholder="************"></div>
        </div>
        <div class="row-form">
          <div class="label-field"><span>Xác nhận mật khẩu</span></div>
          <div class="input-form"><input type="password" class="input-field" placeholder="************"></div>
        </div>
        <div class="row-form">
          <div class="label-field"><span>Email</span></div>
          <div class="input-form"><input type="text" class="input-field" placeholder="example@gmail.com"></div>
        </div>
        <div class="row-form-min">
          <div class="input-checkbox">
            <label >
              <input type="checkbox" name="key" value="value" class="check-box-field">
              <span class="label-field-checkbox" ></span>
            </label>
            <span class="label-field-span"  >Tôi đồng ý nhận email về các bản cập nhật mới
                và khuyến mãi từ nakagawa.</span>

          </div>
        </div>
        <div class="row-action">
          <button class="btn btn-submit">Đăng ký</button>
        </div>
      </form>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "Register",
}
</script>

<style scoped>



#register{
  width: 100%;
  height: 100%;
}

.register-wrap{
  width: 100%;
  height: 100%;
  box-shadow: 0px 2px 4px 1px rgba(0, 0, 0, 0.75);
  /*margin-bottom: 100px;*/
}

.main{
  width: 100%;
  height: 60%;
  margin-top: 4%;
}

.row-action{
  width: 60%;
  height: 12%;
  margin: auto;
  margin-top: 14%;

}

.form-wrap{
  width: 100%;
  height: 100%;
}

.row-form{
  width: 70%;
  height: 14%;
  border: 1px solid rgba(112, 112, 112, 0.1);
  background-color: rgba(112, 112, 112, 0.1);
  border-radius: 5px;
  margin: auto;
  margin-top: 2%;
}

.header{
  width: 100%;
  height: 8%;
  padding-top: 5%;
  /*padding-top: 200px;*/
}

.header span{
  width: 30%;
  height: 100%;
  font-size: 2vw;
  font-weight: bold;
  text-align: left;
  opacity: 80%;
  margin-left: 35%;
}

.input-form{
  width: 100%;
  height: 45%;
}

.label-field{

}

.label-field span{
  width: 8%;
  height: 21%;
  /*font-size: 10px;*/
  font-size: 0.7vw;
  font-weight: bold;
  margin-left: 4%;
}

.input-field{
  width: 92%;
  height: 100%;
  /*margin-bottom: 46%;*/
  margin-left: 4%;
  margin-top: 1%;
  outline: none;
  border: none;
  background-color: rgba(112, 112, 112, 0.001);
}

.input-field input:hover{
  outline: none;
}

.row-form-min{
  width: 70%;
  height: 3%;
  line-height: 100%;
  margin: auto;
  /*margin-top: 1%;*/
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.input-checkbox{
  width: 90%;
  height: 150%;
  margin-top: 5%;
  display: flex;
  flex-direction: row;

}

.input-checkbox > span{
  height: 100%;
  line-height: 1.2;
}

.input-checkbox > label > input[type="checkbox"] {
  display: none;
}

.input-checkbox > label{
  height: 150%;
  position: relative;
  /*vertical-align: middle;*/
  /*display: flex;*/



  /*flex-direction: row;*/
  /*justify-content: space-between;*/
}

.input-checkbox > label > input[type="checkbox"] + *::before {
  position: absolute;
  top: 0;
  bottom: 0;
  /*margin: 0;*/
  content: "";
  line-height: 1;
  height: 100%;
  display: inline-block;
  vertical-align: bottom;
  width: 0.8em;
  height: 0.8em;
  border-radius: 10%;
  border: 0.1em solid #00833E;
  /*margin-right: 5%;*/
  /*margin-top: 5%;*/
  /*margin-left: ;*/
  /*border-style: solid;*/
  /*border-width: 0.1rem;*/
  /*border-color: gray;*/
}

.input-checkbox > label > input[type="checkbox"]:checked + *::before {
  /*height: 100%;*/
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

.label-field-span{
  margin-left: 10%;
  font-size: 0.5vw;
  /*width: 100%;*/
  line-height: 100%;
  /*text-align: center;*/
}

.label-field-checkbox{
  margin-left: 10%;
  font-size: 1vw;
  /*!*width: 100%;*!*/
  /*line-height: 100%;*/
  /*text-align: center;*/
}


</style>