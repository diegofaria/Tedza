var tedza = null;

(function (tedza){
	var breadcrumb = $("#breadcrumb");
	
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
	
	function buildGeekBreadCrumb() {
		breadcrumb.fadeOut("fast", function () {
			breadcrumb.html("");
			breadcrumb.fadeIn("slow");
		});
	}

	function onInitializeGeekStep () {
		console.log("onInitializeGeekStep");
		var html = buildGeekChooserPanel();
		$("#q1").empty().append(html);
		buildGeekBreadCrumb();
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
	
	function buildSpendingTimeBreadCrumb() {
		breadcrumb.fadeOut("fast", function () {
			breadcrumb.html("IDEAS for <a href=\"javascript:void(0)\">" + userProfile.geekLabel + " GEEK</a>");
			breadcrumb.fadeIn("slow");
		});
	}

	function onInitializeSpendingTimeStep () {
		console.log("onInitializeSpendingTimeStep");
		var html = buildSpendingTimeStep();
		$("#q2").empty().append(html);
		buildSpendingTimeBreadCrumb();
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
						userProfile.themeName = theme.name;
						navigation.showNextStep();
					}
				)
			 );
		});

		return $("<div />").append(
			$('<h2>' + loadedData.steps[2] + '</h2>'), ul
		);
	}
	
	function buildThemeBreadCrumb() {
		var html = breadcrumb.html()  + " in <a href=\"javascript:void(0)\">" + userProfile.spendingTime + " MINUTES</a>";
		breadcrumb.fadeOut("fast", function () {
			breadcrumb.html(html);
			breadcrumb.fadeIn("slow");
		});
	}

	function onInitializeThemeStep () {
		console.log("onInitializeThemeStep");
		var html = buildThemeChooserPanel();
		$("#q3").empty().append(html);
		buildThemeBreadCrumb();
	}
	
	function buildVideoBreadCrumb() {
		var html = breadcrumb.html()  + " inside <a href=\"javascript:void(0)\">" + userProfile.themeName + "</a>";
		breadcrumb.fadeOut("fast", function () {
			breadcrumb.html(html);
			breadcrumb.fadeIn("slow");
		});
	}

	function onInitializeVideoPlayerStep () {
		buildVideoBreadCrumb();
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
