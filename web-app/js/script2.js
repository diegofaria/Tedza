// APP Configuration
var tedza = null;

(function (tedza){

	/**
	 * Load application meta-data
	 */
	function loadData( callback ){
		$.getJSON("js/steps.json", function(data) {
			loadedData = data;
			callback();
		});
	}

	/**
	 * Draw the Geek-Choose Panel
	 */
	function buildGeekChooserPanel() {		
		var html = '<h2>' + loadedData.question1 + '</h2><a href="javascript:void(0)" '
		  + 'class="add">Suggest new...</a>'
		  + '<ul class="buttons">';

		var dataList = loadedData.list;
		for (var i=0; i< dataList.length; i++) {
			html += '<li><a href="javascript:void(0)" id="geekType' + dataList[i].id + '">'
			+  '<img src="' + dataList[i].img + '" /></a><span>' + dataList[i].title + '</span></li>';
		}

		html += '</ul>';
		return html;
	}

	/**
	 * Dispatched when Geek-Panel step is initialized
	 * @returns
	 */
	function onInitializeGeekStep () {
		
		console.log("onInitializeGeekStep");
		
		var html = buildGeekChooserPanel();
		$("#q1").html(html);

		$("#q1 ul.buttons a").click(function () {
			userProfile.geekType = this.id.substring(1);
			navigation.showNextStep();
		});
	}

	/**
	 * Dispatched when video player step is initialized
	 * @returns
	 */
	function onInitializeVideoPlayerStep () {
		$.post("/video/getResults", { q1: $("#hq1").val(), q2: $("#hq2").val(), q3: $("#hq3").val() },
	        function(data) {
			  	if (data.length > 0) {	
			  		player.src(data[0].high);
			  		player.play();
			  	}
			  	else {
			  		alert('no videos found');
			  	}
		    });
	}

	/**
	 * Initialize the TedZa Application
	 */
	function initialize() {
		loadData(function (){
			player = _V_("player");
	
			navigation = new Navigation({
				objects: ["q1","q2","q3","videoplayer"],
				listener: function ( id ) {
					steps[id]();
				}
			});
			
			console.log("Initialized");
		});
	}

	var navigation = null,
		player = null,
		userProfile = {},
		steps = {
				q1: onInitializeGeekStep,
				q2: function (){},
				q3: function (){},
				videoplayer: onInitializeVideoPlayerStep
		};

	// On document ready, dispatch the TedZa initializer
	$(initialize);

})(tedza);
