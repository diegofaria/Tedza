// APP Configuration
var steps = {
	q1: function (){
		
	},
	q2: function (){

	},
	q3: function (){
		
	},
	videoplayer: function (){
		$.post("/video/getResults", { q1: $("#hq1").val(), q2: $("#hq2").val(), q3: $("#hq3").val() },
	        function(data) {
			  	if (data.length > 0) {	
			  		player.src(data[0].high);
			  		player.play();
			  	}
			  	else {
			  		alert('no videos found');
			  	}
		    }
	     );
	}
};

var navigation = null;
var player = null;

$(function() {
	player = _V_("player");
	
	navigation = new Navigation({
		objects: ["q1","q2","q3","videoplayer"],
		listener: function ( id ) {
			steps[id]();
		}
	});
	
	$.getJSON("js/steps.json", function(data) {
		var html = '<h2>' + data.question1 + '</h2><a href="javascript:void(0)" class="add">Suggest new...</a><ul class="buttons">';
		for (var i=0; i<data.list.length; i++) {
			html += '<li><a href="javascript:void(0)" id="g' + data.list[i].id + '"><img src="' + data.list[i].img + '" /></a><span>' + data.list[i].title + '</span></li>';
		}
		html += '</ul>';
		$("#q1").html(html);
		
		$("ul.buttons a").click(function () {
			$("#h"+navigation.current).val(this.id.substring(1));
			navigation.showNextStep();
		});
	});
	
	$("ul.buttons a").click(function () {
		$("#h"+navigation.current).val(this.id.substring(1));
		navigation.showNextStep();
	});
});