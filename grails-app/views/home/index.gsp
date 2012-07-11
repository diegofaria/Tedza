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
	  <link rel="stylesheet" href="css/style.css">
	  <script src="${resource(dir:'js',file:'modernizr-2.5.3.min.js')}"></script>
	</head>
	<body>
	  <!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
	  <div class="page">
		  <header>
			<a href="${createLink(controller:'home')}"><img src="images/tedza.gif" /></a>
		  </header>
		  <div id="main" role="main">
		  	<div id="breadcrumb"></div>
		  	<div id="q1" class="question"></div>
			<div id="q2" class="question"></div>
			<div id="q3" class="question">
				<h2>Select a playlist <span>...</span></h2>
				<a href="javascript:void(0)" class="add">Suggest new...</a>
				<ul class="buttons">
					<li><a href="javascript:void(0)" id="p1"><img src="images/icon_hightech.png" /></a><span>What's Next in Tech</span></li>
					<li><a href="javascript:void(0)" id="p2"><img src="images/icon_design.png" /></a><span>Tales of Invention</span></li>
					<li><a href="javascript:void(0)" id="p3"><img src="images/icon_engaged.png" /></a><span>The Creative Spark</span></li>
					<li><a href="javascript:void(0)" id="p4"><img src="images/icon_science.png" /></a><span>Technology, History and Destiny</span></li>
					<li><a href="javascript:void(0)" id="p5"><img src="images/icon_startup.png" /></a><span>Medicine Without Borders</span></li>
					<li><a href="javascript:void(0)" id="p6"><img src="images/icon_travel.png" /></a><span>Bold Predictions, Stern Warnings</span></li>
				</ul>
			</div>
			<div id="videoplayer">
				<video id="player" class="video-js vjs-default-skin" height="450" width="562" controls preload="none" data-setup="{}">
				   	<source src="" type='video/mp4' />
				</video>
			</div>
			<input type="hidden" id="hq1" name="q1" value="" />
			<input type="hidden" id="hq2" name="q2" value="" />
			<input type="hidden" id="hq3" name="q3" value="" />
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
	  <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.1.min.js"><\/script>')</script>
	  <script src="js/plugins.js"></script>
	  <script src="js/script2.js"></script>
	  <script>
	    var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
	    (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
	    g.src=('https:'==location.protocol?'//ssl':'http://www')+'.google-analytics.com/ga.js';
	    s.parentNode.insertBefore(g,s)}(document,'script'));
	  </script>
	</body>
</html>