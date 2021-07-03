<template>
  <div id="account-setting">
    <div class="account-setting-wrap">
      <div class="header">
        <div class="name-page">Account Settings</div>
        <div class="path-page">Main Page/ User Management</div>
      </div>
      <div class="main">
        <div class="account-detail">
          <div class="title">Account Details</div>
          <div class="row">
            <div class="account-name">Email</div>
            <div class="account-value">example@gmail.com</div>
            <div class="account-action">Change Details</div>
          </div>
          <div class="row">
            <div class="account-name">Phone Number</div>
            <div class="account-value">0000000000</div>
            <div class="account-action">Change Details</div>
          </div>
          <div class="row">
            <div class="account-name">Location</div>
            <div class="account-value">Hanoi, Vietnam</div>
            <div class="account-action">Change Details</div>
          </div>
        </div>
        <div class="personal-setting">
          <div class="title">Personal Settings</div>
          <div class="row">
            <div class="personal-info">Lore dasd sa csss dsd sdm nmbck shsl isd aida</div>
            <div class="personal-action">
              <label for="switch1" class="switch">
                <input type="checkbox" id="switch1">
                <span class="slider round"></span>
              </label>
            </div>
          </div>
          <div class="row">
            <div class="personal-info">Lore dasd sa csss dsd sdm nmbck shsl isd aida ádai ádasa</div>
            <div class="personal-action">
              <label for="switch" class="switch">
                <input type="checkbox" id="switch">
                <span class="slider round"></span>
              </label>
            </div>
          </div>
        </div>
        <div class="more-option">
          <div class="title">More Options</div>
          <div class="option">Privacy Policy</div>
          <div class="option">Terms of Use</div>
          <div class="option">Customer Support</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AccountSetting"
}
</script>

<style scoped>
#account-setting {
  width: 100%;
  height: 100%;
}

.account-setting-wrap {
  width: 100%;
  height: 100%;
}

.header {
  width: 98%;
  height: 4%;
  line-height: 240%;
  /*background-color: #DB4437;*/
  margin: 1.5% 1% 1% 1%;
  /*margin-top: 1.5%;*/
  /*margin-bottom: 1%;*/
  display: flex;
  flex-direction: row;
  justify-content: space-between;

}

.name-page {
  color: rgba(0, 0, 0, 0.8);
  font-size: 1.5rem;
  font-weight: bold;
  margin-left: 2%;
}

.path-page {

}

.main {
  width: 98%;
  height: 80%;
  background-color: #fff;
  border: 1px solid #cccccc;
  border-radius: 1.5%;
  margin: auto;
}

.account-detail {
  width: 88%;
  height: 30%;
  margin: auto;
  margin-top: 4%;
  /*background-color: #00833E;*/
}

.title {
  width: 100%;
  height: 10%;
  /*background-color: #DB4437;*/
  margin-bottom: 2%;
  font-size: 1.2rem;
  font-weight: bold;
  color: rgba(0, 0, 0, 0.8);
}

.row {
  width: 100%;
  height: 15%;
  line-height: 2;
  display: flex;
  flex-direction: row;
  margin-top: 0.5%;
  /*background-color: yellow;*/
}

.account-name {
  width: 9%;
  height: 100%;
  font-size: 1rem;
  color: rgba(0, 0, 0, 0.8);

}

.account-value {
  width: 11%;
  height: 100%;
  font-size: 1rem;
  color: rgba(0, 0, 0, 0.4);
  margin-left: 0.5%;
}

.account-action {
  width: 10%;
  height: 100%;
  margin-left: 12%;
  color: #DB4437;
  font-size: 1rem;
  font-weight: bold;
}

.account-action:hover {
  cursor: pointer;
  color: #00833E;
}

.personal-setting {
  width: 88%;
  height: 30%;
  margin: auto;
}

.personal-info{
  width: 32%;
  height: 8%;
  line-height: 2.2;
  font-size: 1rem;
  color: rgba(0, 0, 0, 0.8);
}

.personal-action{
  width: 15%;
  height: 100%;
  /*background-color: chartreuse;*/
}

.switch{
  position: relative;
  display: inline-block;
  width: 30%;
  height:100%;
  margin-left: 40%;
  /*margin-top: 0;*/
  /*margin: 20px;*/
  background-color: #aaa;
  border-radius: 17px;
}

.switch input{
  display: none;
}

.slider{
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;

  background-color: #cccc;

  transition: 0.4s;
}

.slider:before{
  position: absolute;
  cursor: pointer;
  content: "";
  height: 80%;
  width: 48%;
  /*top: 0;*/
  left: 5%;
  bottom: 10%;
  background-color: #fff;

  /*background-color: #DB4437;*/

  transition: 0.4s;
}

input:checked + .slider{
  background-color: #DB4437;
}

input:focus + .slider{
  box-shadow: 0 0 1px #DB4437;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}

.more-option{
  width: 88%;
  height: 30%;
  margin: auto;
  margin-top: -2%;
}

.option{
  width: 30%;
  height: 10%;
  /*background-color: #00833E;*/
  color: #DB4437;
  font-size: 1rem;
  font-weight: bold;
  margin-top: 1%;
}



</style>