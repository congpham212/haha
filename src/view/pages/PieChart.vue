
<script>
import {Pie} from 'vue-chartjs'
export default {
  mixins: [Pie],
  props: ['dataChart', 'options'],
  mounted() {
    // console.log(this.options)
    this.renderChart(this.dataChart, this.options);
  }
}
</script>

<style scoped>

</style>