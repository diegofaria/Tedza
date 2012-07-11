var tedza = null;

(function (tedza){
	function loadData(callback){
		$.ajax({
			url:"home/applicationData",
			dataType: "json",
			type: "GET",
			success: function(data) {
				loadedData = data;
				callback();
			}
		});
	}

	function drawButton( image, label, callback ) {
		var a = $("<a href='javascript:void(0)' />").click(callback);
		var li = $("<li />")
			.append(
				a.append(
					$("<img src='"+ image + "' />"),
					$("<span>"+label+ "</span>")
				)
			);

		return li;
	}

	function buildGeekChooserPanel() {
		var ul = $("<ul class='buttons' />");
		var dataList = loadedData.data;
		$(dataList).each(function(i, data) {
			ul.append(
				 drawButton(
				 	data.img,
				 	data.label,
				 	function () {
				 		userProfile.geekLabel = data.label;
						userProfile.geekType = i;
						navigation.showNextStep();
					})
			 );
		});

		return $("<div />").append(
			$('<h2>' + loadedData.steps[0] + '</h2>'), ul
		);
	}

	function onInitializeGeekStep () {
		console.log("onInitializeGeekStep");
		var html = buildGeekChooserPanel();
		$("#q1").empty().append(html);
	}

	function buildSpendingTimeStep() {
		var minutes = [ 5, 10, 15, 30, 45, 60 ];
		var ul = $("<ul class='buttons' />");
		$(minutes).each(function(i, minute) {
			ul.append(
				drawButton( "images/icon_" + minute + "min.png",
					minute + " Minutos",
					function () {
						userProfile.spendingTime = minute;
						navigation.showNextStep();
					})
			 );
		});

		return $("<div />").append(
			$('<h2>' + loadedData.steps[1] + '</h2>'), ul
		);
	}

	function onInitializeSpendingTimeStep () {
		console.log("onInitializeSpendingTimeStep");
		var html = buildSpendingTimeStep();
		$("#q2").empty().append(html);
	}

	function buildThemeChooserPanel() {
		var ul = $("<ul class='buttons themes' />");
		var themeList = loadedData.data[userProfile.geekType].themes;

		$(themeList).each(function(i, theme){
			ul.append(
				drawButton(
				 	theme.imageUrl,
				 	theme.name,
				 	function (){
						userProfile.themeId = theme.id;
						navigation.showNextStep();
					}
				)
			 );
		});

		return $("<div />").append(
			$('<h2>' + loadedData.steps[2] + '</h2>'), ul
		);
	}

	function onInitializeThemeStep () {
		console.log("onInitializeThemeStep");
		var html = buildThemeChooserPanel();
		$("#q3").empty().append(html);
	}

	function onInitializeVideoPlayerStep () {
		$.ajax({
			url:"video/getResults",
			dataType: "json",
			data: userProfile,
			type: "POST",
			success: function(data) {
				if (data.length > 0) {	
					var video = $("#player")[0];
					video.src = data[0].high;
					video.load();
					video.play();
			  	}
			  	else {
			  		alert('no videos found');
			  	}
			}
		});
	}

	function initialize() {
		loadData(function (){
			navigation = new Navigation({
				objects: ["q1","q2","q3","videoplayer"],
				listener: function (id) {
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
				q2: onInitializeSpendingTimeStep,
				q3: onInitializeThemeStep,
				videoplayer: onInitializeVideoPlayerStep
		};

	$(initialize);

})(tedza);
