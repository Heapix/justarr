<template>
			<div>
						<h3>Statistics</h3>
						<div class="row">
									<div v-for="(el, index) in data" :key="index" class="col s2" >
												<input :value="el.organization" @click="chooseOrganization(index)" type="button" class="waves-effect waves-light btn green">
									</div>
						</div>
						<div class="row">
									<input v-if=(this.company.years) value="statistics by years" @click="showByYears()" type="button" class="waves-effect waves-teal btn-flat">
									<input v-if=(this.company.month) value="statistics by month" @click="showByMonth()" type="button" class="waves-effect waves-teal btn-flat">
									<input v-if=(this.company.days) value="statistics by days" @click="showByDays()" type="button" class="waves-effect waves-teal btn-flat">
									<input v-if=(this.company.hours) value="statistics by days" @click="showByHours()" type="button" class="waves-effect waves-teal btn-flat">
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
	  created () {
	    this.getData();
	  },
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
				this.chartData = null
				this.company = this.data[index];
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
						} else {
							if (data.hours) {
				  			return (this.company.hours)
							}
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
			showByHours: function () {
				this.chartData = this.company.hours
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