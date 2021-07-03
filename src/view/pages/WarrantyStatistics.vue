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
        <chart
            :data-chart="dataChart"
            :options="options"
        />
        </div>
<!--        <LineChart/>-->
      </div>
    </div>
  </div>

</template>

<script>
import Chart from "../pages/chartjs.vue";
// import LineChart from '../pages/LineChart'
export default {
  name: "WarrantyStatistics",
  components: {
    Chart,
    // LineChart,
  },
  // data() {
  //   return {
  //     chartData: {
  //       Books: 24,
  //       Magazine: 30,
  //       Newspapers: 10
  //     }
  //   };
  // },
  data() {
    return{
      dataChart:{
        labels: ['1/2021', '2/2021', '3/2021', '4/2021', '5/2021', '6/2021'],
        datasets: [{
          data: [70, 95, 100, 120, 257, 271],
          label: 'Total Active',
          borderColor: '#3e95cd',
          backgroundColor: 'rgba(255, 255, 255, 0) ',
          pointHoverBackgroundColor: 'rgba(255, 0, 0, 1)',
          pointHoverRadius: 5,

        },
        {
          data: [70, 80, 111, 129, 135, 209],
          label: 'Process Warranty',
          borderColor: '#8e5ea2',
          backgroundColor: 'rgba(255, 255, 255, 0) ',
        },
          {
            data: [70, 78, 128, 150, 203, 276],
            label: 'Success Warranty',
            borderColor: '#3cba9f',
            backgroundColor: 'rgba(255, 255, 255, 0) ',
          },
          {
            data: [70, 107, 170, 200, 254, 293],
            label: 'Reject Warranty',
            borderColor: '#e8c3b9',
            backgroundColor: 'rgba(255, 255, 255, 0) ',
          },
        ]
      },
      options:{
        title:{
          display: true,
          text: 'Graph Statistics Warranty'
        },
        responsive: true,
        maintainAspectRatio: false,
        interaction:{
          mode: 'datasets',
        },
        tooltips: {
          mode : 'index',
          position: 'nearest',
          callbacks: {
            label(tooltipItem, data) {
              // Get the dataset label.
              const label = data.datasets[tooltipItem.datasetIndex].label;

              // Format the y-axis value.
              // eslint-disable-next-line no-undef
              const value = tooltipItem.yLabel;

              return `${label}: ${value}`;
            }
          }
        }

      }
    }
  },

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