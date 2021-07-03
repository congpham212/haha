<template>
<div id="list-register-warranty">
  <div class="list-register-warranty-wrap">
    <div class="page-header">
      <div class="name-page">List Register Warranty</div>
      <div class="path-page">Main Page/ List Register Warranty</div>
    </div>
    <div class="main">
      <div class="main-header">
        <span>Danh sách đăng ký bảo hành</span>
      </div>
      <div class="content">
        <div class="title-info">
          
<!--          <div class="select-row"><input type="checkbox"></div>-->
          <div class="id-register-warranty">Id</div>
          <div class="register-warranty-action">Hành động</div>
          <div class="product-name">Tên sản phẩm</div>
          <div class="customer-name">Tên khách hàng</div>
          <div class="description-issue">Mô tả lỗi</div>
          <div class="required-date">Ngày yêu cầu</div>

        </div>
        <div class="content-info">
          <div class="id-register-warranty">1</div>
          <div class="register-warranty-action">
            <span class="receive-warranty"><i class="fas fa-shield-alt"></i></span>
            <span class="detail-info"><i class="fas fa-info-circle"></i></span>
          </div>
          <div class="product-name">3</div>
          <div class="customer-name">4</div>
          <div class="description-issue">5</div>
          <div class="required-date">6</div>
        </div>
      </div>
    </div>
  </div>
</div>

</template>

<script>
export default {
  name: "ListRegisterWarranty",
  data(){
    return{
      listRegisterWarranty:[],
    }
  },
  created() {
    this.listRegisterWarranty.push()
  }
}
</script>

<style scoped>



#list-register-warranty{
  width: 100%;
  height: 100%;
}

.list-register-warranty-wrap{
  width: 100%;
  height: 100%;
}

.name-page {
  color: rgba(0, 0, 0, 0.8);
  font-size: 1.5rem;
  font-weight: bold;
  margin-left: 2%;
}

.main {
  width: 98%;
  height: 80%;
  background-color: #fff;
  border: 1px solid #cccccc;
  border-radius:  1.225% 3%;
  margin: auto;
}

.main-header {
  width: 80%;
  height: 4%;
  /*background-color: #42b983;*/
  margin: auto;
  margin-top: 2%;
  padding-left: 4%;
}

.main-header span {
  color: #000;
  font-weight: bold;
  font-size: 1.5rem;
}

.content {
  width: 100%;
  height: 80%;
  /*background-color: red;*/
  margin-top: 1.5%;
}

.title-info{
  width: 80%;
  height: 45px;
  margin: auto;
  margin-bottom: 30px;
  line-height: 40px;
  display: flex;
  flex-direction: row;
  background-color: rgba(0, 131, 62, 0.8);
  text-align: center;
  border: 1px solid rgba(0, 131, 62, 0.8);
  border-radius: 10px;
  box-shadow: 1px 1px 1px 1px rgba(0, 131, 62, 0.8);
}

.id-register-warranty{
  width: 7%;
  height: 100%;
  /*text-align: center;*/
}

.register-warranty-action{
  width: 10%;
  height: 100%;
  /*display: flex;*/
  /*flex-direction: row;*/
  /*align-items: center;*/
}

.receive-warranty{
  margin-right: 10%;
}

.product-name{
  width: 18%;
  height: 100%;
  /*text-align: center;*/
}

.customer-name{
  width: 18%;
  height: 100%;
}

/*.customer-addr{*/
/*  width: 15%;*/
/*  height: 100%;*/
/*}*/

.description-issue{
  width: 35%;
  height: 100%;
}

.required-date{
  width: 12%;
  height: 100%;
}

.content-info{
  width: 80%;
  height: 45px;
  margin: auto;
  margin-top: 10px;
  line-height: 40px;
  display: flex;
  flex-direction: row;
  /*background-color: #00833E;*/
  text-align: center;
  border: 1px solid #eee;
  border-radius: 10px;
  box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.4   );
}



</style>