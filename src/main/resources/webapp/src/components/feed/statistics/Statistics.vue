<template>
	<div :onload='getData()'  >
		<h3>Statistics</h3>
			<div v-for="(el, index) in data" :key="index" class="col s1" >
				<input :value="el.organization" @click="chooseOrganization(index)" type="button" class="waves-effect waves-light btn green marge">
			</div>
			<div class="row">
				<input v-if=(this.company.years) value="statistics by years" @click="showByYears()" type="button" class="waves-effect waves-teal btn-flat">
				<input v-if=(this.company.month) value="statistics by month" @click="showByMonth()" type="button" class="waves-effect waves-teal btn-flat">
				<input v-if=(this.company.days) value="statistics by days" @click="showByDays()" type="button" class="waves-effect waves-teal btn-flat">
			</div>
			<GChart class="chart"
							type="LineChart"
							:data="chartData"
							:options="chartOptions"/>
	</div>
</template>

<script>
	import {GChart} from 'vue-google-charts'
  import axios from 'axios'

	export default {
		name: "Statistics",
		components: {
			GChart
		},

		data() {
			return {
				chartData: '',
				company: {},
		  	data: [],
				chartOptions: {
					title: 'Title',
					chartArea: {
						backgroundColor: '#F1F8E9'
					}
				}
			}
		},
		methods: {
			chooseOrganization: function (index) {
				this.chartData = null,
				this.company = this.data[index]
				this.chartData = this.checkData(this.company);
		  	this.chartOptions.title = this.company.organization;
			},
			checkData: function (data) {
				if (data.years) {
					return (this.company.years)
				} else {
					if (data.month) {
						return (this.company.month)
					} else {
						if (data.days) {
							return (this.company.days)
						}
					}
				}
			},
			showByYears: function () {
				this.chartData = this.company.years
			},
			showByMonth: function () {
				this.chartData = this.company.month
			},
			showByDays: function () {
				this.chartData = this.company.days
			},
			getData () {
			axios.get('http://localhost:3000/organizations')
				.then(response => {
					this.data = response.data
				})
		}
		}
	}
</script>

<style scoped>
			.chart {
						width: 1000px;
						height: 500px;
			}
</style>