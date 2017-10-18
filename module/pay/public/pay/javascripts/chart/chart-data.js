var randomScalingFactor = function(){ return Math.round(Math.random()*1000)};
	
	var lineChartData = {
			labels : eval($("#chart_data_title").val()),
			datasets : [
				{
					label: "最近15天访问趋势",
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 1)",
					pointColor : "rgba(48, 164, 255, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 255, 1)",
					data : eval($("#chart_data_content").val())
				}
			]

		}
		
	var pieData = [
				{
					value: new Number($("#alipay").val()),
					color:"#30a5ff",
					highlight: "#62b9fb",
					label: "支付宝"
				},
				{
					value: new Number($("#weixin").val()),
					color: "#f9243f",
					highlight: "#f6495f",
					label: "微信"
				}

			];
			
	

window.onload = function(){
//	var chart1 = document.getElementById("line-chart").getContext("2d");
//	window.myLine = new Chart(chart1).Line(lineChartData, {
//		responsive: true
//	});
	var chart4 = document.getElementById("usetime-pie-chart").getContext("2d");
	window.myPie = new Chart(chart4).Pie(pieData, {responsive : true
	});
	
};