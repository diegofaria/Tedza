var tedza = null;

(function (tedza) {
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
		alert(1);
		console.log("onInitializeThemeStep");
		var html = buildThemeChooserPanel();
		$("#q3").empty().append(html);
	}

	function onInitializeVideoPlayerStep() {
		$.ajax({
			url:"video/getResults",
			dataType: "json",
			data: userProfile,
			type: "POST",
			success: function(playlist)
			{
				initPlayer(playlist);
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

	function initPlayer(playlist)
	{
		new jPlayerPlaylist(
			{ jPlayer: "#videpPlayer", cssSelectorAncestor: "#jp_container_1" },
		 	[
				{
					title:"Big Buck Bunny Trailer",
					artist:"Blender Foundation",
					free:true,
					m4v: "http://www.jplayer.org/video/m4v/Big_Buck_Bunny_Trailer.m4v",
					ogv: "http://www.jplayer.org/video/ogv/Big_Buck_Bunny_Trailer.ogv",
					webmv: "http://www.jplayer.org/video/webm/Big_Buck_Bunny_Trailer.webm",
					poster:"http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
				},
				{
					title:"Finding Nemo Teaser",
					artist:"Pixar",
					m4v: "http://www.jplayer.org/video/m4v/Finding_Nemo_Teaser.m4v",
					ogv: "http://www.jplayer.org/video/ogv/Finding_Nemo_Teaser.ogv",
					webmv: "http://www.jplayer.org/video/webm/Finding_Nemo_Teaser.webm",
					poster: "http://www.jplayer.org/video/poster/Finding_Nemo_Teaser_640x352.png"
				},
				{
					title:"Incredibles Teaser",
					artist:"Pixar",
					m4v: "http://www.jplayer.org/video/m4v/Incredibles_Teaser.m4v",
					ogv: "http://www.jplayer.org/video/ogv/Incredibles_Teaser.ogv",
					webmv: "http://www.jplayer.org/video/webm/Incredibles_Teaser.webm",
					poster: "http://www.jplayer.org/video/poster/Incredibles_Teaser_640x272.png"
				}
			],
			{ swfPath: "/swf", supplied: "mp4"}
		);

		$("#jplayer_inspector_1").jPlayerInspector({jPlayer:$("#videpPlayer")});
	}

	var navigation = null,
		userProfile = {},
		steps = {
				q1: onInitializeGeekStep,
				q2: onInitializeSpendingTimeStep,
				q3: onInitializeThemeStep,
				videoplayer: onInitializeVideoPlayerStep
		};

	$(document).ready(function(){
		initialize();
	});
})(tedza);