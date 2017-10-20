var randomScalingFactor = function(){ return Math.round(Math.random()*1000)};
	
	var lineChartData = {
			labels : eval($("#chart_data_title").val()),
			datasets : [
				{
					label: "支付宝",
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 1)",
					pointColor : "rgba(48, 164, 255, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 255, 1)",
					data : eval($("#chart_data_alipay").val())
				},
				{
					label: "手机支付宝",
					fillColor : "rgba(48, 164, 5, 0.2)",
					strokeColor : "rgba(48, 164, 5, 1)",
					pointColor : "rgba(48, 164, 5, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 5, 1)",
					data : eval($("#chart_data_wapalipay").val())
				},
				{
					label: "微信扫码",
					fillColor : "rgba(220, 164, 255, 0.2)",
					strokeColor : "rgba(220, 164, 255, 1)",
					pointColor : "rgba(220, 164, 255, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220, 164, 255, 1)",
					data : eval($("#chart_data_weixin").val())
				},
				{
					label: "5",
					fillColor : "rgba(220, 52, 65, 0.2)",
					strokeColor : "rgba(220, 52, 65, 1)",
					pointColor : "rgba(220, 52, 65, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220, 52, 65, 1)",
					data : eval($("#chart_data_wxclient").val())
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
				},{
					value: new Number($("#wapalipay").val()),
					color:"#30f5ff",
					highlight: "#62b9fb",
					label: "手机支付宝"
				},
				{
					value: new Number($("#wxclient").val()),
					color: "#f92f3f",
					highlight: "#f6495f",
					label: "微信公众号"
				}

			];
			
	

window.onload = function(){
	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
		responsive: true
	});
	var chart4 = document.getElementById("usetime-pie-chart").getContext("2d");
	window.myPie = new Chart(chart4).Pie(pieData, {responsive : true
	});
	
};