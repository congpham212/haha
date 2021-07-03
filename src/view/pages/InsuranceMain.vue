<template>
  <div id="insurance">
    <div class="insurance-wrap">
      <div class="header">
        <div class="name-page">Insurance</div>
        <div class="path-page">Main Page/ Insurance</div>
      </div>
      <div class="main">
        <div class="main-header">
          <span>Sản phẩm của bạn</span>
        </div>
        <div class="content">
          <div class="grid">
            <div class="row" v-for="row in numRow" :key="row">
              <div class="cell" v-for="(product,index) in subList(row)" :key="index">
                <div class="product-image">
                  <img :src="getImage(product.image)" alt="" class="image">
<!--                  <div class="image"></div>-->
                </div>
                <div class="product-info">
                  <div class="row-title">
                    <span class="text-link name-product">{{ product.name }}</span>
                    <span class=""><i class="fas fa-pen resize-icon"></i></span>
                  </div>
                  <div class="row-info">
                    <span>Số thiết bị</span>
                    <span>{{ product.serial }}</span>
                  </div>
                  <div class="row-info">
                    <span>Tình trạng</span>
                    <span>{{ product.status }}</span>
                  </div>
                  <div class="row-info">
                    <span>Ngày hiệu lực</span>
                    <span>{{ product.effective_date }}</span>
                  </div>
                  <div class="row-info">
                    <span>Chi phí</span>
                    <span>{{ product.price }}</span>
                  </div>
                </div>
              </div>
            </div>
            <!--          <div class="row">-->
            <!--            <div class="cell">-->
            <!--              <div class="product-image"></div>-->
            <!--              <div class="product-info"></div>-->
            <!--            </div>-->
            <!--          </div>-->
          </div>
        </div>
        <div class="main-footer">
          <span class="text-link name-product">Xem thêm</span>
        </div>
      </div>


    </div>
  </div>

</template>

<script>
export default {
  name: "InsuranceMain",
  data() {
    return {
      myListProduct: [],
      numRow: 0,
      numCell: 0,
    }
  },
  methods: {
    subList(row) {
      // console.log("row: " + (row-1));
      // console.log("row: " + (row-1)*this.numCell);
      // console.log("row: " + ((row-1)*this.numCell + this.numCell));
      return this.myListProduct.slice((row - 1) * this.numCell, (row - 1) * this.numCell + this.numCell);
    },
    getImage(url){
      if(url.includes('http')){
        return  url;
      }
      else{
        return require(`@/assets/${url.split('.')[0]}.${url.split('.')[1]}`);
      }
    }
  },
  created() {
    let img = 'image/product/air_condotion.jpg';
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: 'https://cdn.tgdd.vn/Files/2014/10/20/574805/10-sai-lam-tai-hai-khi-su-dung-may-lanh.jpg'
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })
    this.myListProduct.push({
      id: '1',
      name: 'Điều hòa',
      serial: '123456789',
      status: '1',
      effective_date: '01/01/2021',
      price: '999999',
      image: img
    })

    this.numCell = 5;
    this.numRow = (this.myListProduct.length / this.numCell) + (this.myListProduct.length % this.numCell != 0 ? 1 : 0)
  },
}
</script>

<style scoped>

#insurance {
  width: 100%;
  height: 100%;
  margin: auto;
  /*background-color: white;*/
  /*border: 1px solid #ccc;*/
  /*border-radius: 10px;*/

}

.insurance-wrap {
  width: 100%;
  height: 100%;
  /*border: 1px solid #eee;*/
  /*border-radius: 10px;*/
  /*background-image:url("src/assets/image/product/air_condotion.jpg");*/
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
  border-radius:  10px 1% 1% 1%;
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

.grid {
  width: 80%;
  height: 100%;
  margin: auto;

}

.row {
  width: 100%;
  height: 40%;
  /*background-color: yellow;*/
  margin-bottom: 4%;
  display: flex;
  flex-direction: row;
  padding-left: 3%;
}

.cell {
  width: 17%;
  height: 100%;
  /*background-color: #DB4437;*/
  margin-right: 3%;
  box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.5);
  /*margin-left: 2%;*/
}

.product-image {
  width: 100%;
  height: 59%;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  /*background-color: #ffffff;*/

}

.product-image > img {
  width: 90%;
  height: auto;
  max-width: 90%;
  max-height: 90%;
}

/*.image {*/
/*  width: 80%;*/
/*  height: 80%;*/
/*  margin: auto;*/
/*  background-image: url("../../assets/image/product/air_condotion.jpg");*/
/*  background-size: cover;*/
/*  background-position: center;*/
/*}*/

.product-info {
  width: 100%;
  height: 41%;
  padding-top: 4%;
  background-color: #eeeeee;
}

.row-info {
  width: 90%;
  height: 16%;
  margin: auto;
  display: flex;
  justify-content: space-between;
}


.row-info > span {
  font-size: 0.7rem;
}

.row-title {
  width: 90%;
  height: 20%;
  margin: auto;
  display: flex;
  justify-content: space-between;
}

.resize-icon {
  font-size: 0.7rem;
}

.name-product {
  font-size: 0.9rem !important;
  font-weight: bold;
}

.main-footer {
  width: 100%;
  height: 4%;
  /*background-color: #42b983;*/
  margin-bottom: 7%;
  position: relative;
}

.main-footer > span {
  width: 4.5%;
  height: 100%;
  line-height: 1;
  /*background-color: chartreuse;*/
  position: absolute;
  right: 10%;
}

</style>