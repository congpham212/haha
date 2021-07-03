<template>
  <div id="warranty-statistics">
    <div class="warranty-statistics-wrap">
      <div class="page-header">
        <div class="name-page">List Register Warranty</div>
        <div class="path-page">Main Page/ List Register Warranty</div>
      </div>
      <div class="main">
        <div class="main-header">

        </div>
        <div class="content">
          <pie-chart
          :data-chart="dataChart"
          :options="options"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.4.0/dist/chartjs-plugin-datalabels.js"></script>
<script>
import PieChart from "@/view/pages/PieChart";
export default {
  name: "WarrantyStatisticsForMonth",
  components:{
    PieChart,
  },
  data(){
    return {
      dataChart: {
        labels: ['Total Active', 'Process Warranty', 'Success Warranty', 'Reject Warranty',],
        datasets: [{
          label: 'đơn vị ',
          backgroundColor: ['#3e95cd', '#ffff00', '#00ff00', '#ff0000'],
          data: [63.2, 52.1, 73.4, 18.4]
        }]
      },
      options: {
        title: {
          display: true,
          text: 'Graph Statistics Register Warranty For Month'
        },
        responsive: true,
        maintainAspectRatio: false,
        mode: 'datasets',
      },
      plugins: {
        labels: [
          {
            render: 'label',
            position: 'outside'
          },
          {
            render: 'percentage'
          }
        ]
      }
    }
  }
}
</script>

<style scoped>

#warranty-statistics{
  width: 100%;
  height: 100%;
}

.warranty-statistics-wrap{
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
  height: 10%;
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
  height: 74%;
  /*background-color: red;*/
  margin-top: 1.5%;
}

</style>