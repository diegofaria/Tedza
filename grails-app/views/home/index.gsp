<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  <title></title>
	  <meta name="description" content="">
	  <meta name="viewport" content="width=device-width">
	  <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}">
	  <script src="${resource(dir:'js',file:'modernizr-2.5.3.min.js')}"></script>
	</head>
	<body>
	  <!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
	  <div class="page">
		  <header>
			<a href="/"><img src="${resource(dir:'images',file:'tedza.gif')}"/></a>
			<!-- 
			<ul>
				<li>Anderson Gomes</li>
			</ul>
			 -->
		  </header>
		  <div id="main" role="main">
		  	<div id="breadcrumb"></div>
		  	<div id="q1" class="question">
				<h2>What kind of geek are you<span>?</span></h2>
				<a href="javascript:void(0)" class="add">Suggest new...</a>
				<ul class="buttons">
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_hightech.png')}" /></a><span>Hightech</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_design.png')}" /></a><span>Design</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_engaged.png')}" /></a><span>Engaged</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_science.png')}" /></a><span>Science</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_startup.png')}" /></a><span>Startup</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_travel.png')}" /></a><span>Travel</span></li>
				</ul>
			</div>
			<div id="q2" class="question">
				<h2>How much time do you have now<span>?</span></h2>
				<a href="javascript:void(0)" class="add">Suggest new...</a>
				<ul class="buttons">
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_5min.png')}" /></a><span>5 minutes</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_10min.png')}" /></a><span>10 minutes</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_15min.png')}" /></a><span>15 minutes</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_30min.png')}" /></a><span>30 minutes</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_45min.png')}" /></a><span>45 minutes</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_60min.png')}" /></a><span>60 minutes</span></li>
				</ul>
			</div>
			<div id="q3" class="question">
				<h2>Select a playlist <span>...</span></h2>
				<a href="javascript:void(0)" class="add">Suggest new...</a>
				<ul class="buttons">
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_hightech.png')}" /></a><span>What's Next in Tech</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_design.png')}" /></a><span>Tales of Invention</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_engaged.png')}" /></a><span>The Creative Spark</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_science.png')}" /></a><span>Technology, History and Destiny</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_startup.png')}" /></a><span>Medicine Without Borders</span></li>
					<li><a href="javascript:void(0)"><img src="${resource(dir:'images',file:'icon_travel.png')}" /></a><span>Bold Predictions, Stern Warnings</span></li>
				</ul>
				<!--<form action="/" method="post">
					<fieldset>-->
						<input type="hidden" id="hq1" name="q1" value="" />
						<input type="hidden" id="hq2" name="q2" value="" />
						<input type="hidden" id="hq3" name="q3" value="" />
						<!--<input type="submit" id="watchBtn" value="Watch" /> -->
					<!--</fieldset>
				</form>-->
			</div>
			<div id="video-player">
				<video id="player" class="video-js vjs-default-skin" height="450" width="562" controls preload="none" data-setup="{}">
				   	<source src="" type='video/mp4' />
				</video>
			</div>
		  </div>
		  <!--
		  <footer>
			<a href="javascript:void(0)">Subscribe</a>
			<a href="javascript:void(0)">Team</a>
			<a href="javascript:void(0)">About</a>
		  </footer>
		  -->
	  </div>
	  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	  <script>window.jQuery || document.write('<script src="${resource(dir:'js/libs',file:'jquery-1.7.1.min.js')}"><\/script>')</script>
	  <script src="${resource(dir:'js',file:'plugins.js')}"></script>
	  <script src="${resource(dir:'js',file:'script.js')}"></script>
	  <script>
	  	var jsonFilter = ${jsonFilter};
	    var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
	    (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
	    g.src=('https:'==location.protocol?'//ssl':'http://www')+'.google-analytics.com/ga.js';
	    s.parentNode.insertBefore(g,s)}(document,'script'));
	  </script>
	</body>
</html>