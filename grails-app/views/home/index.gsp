<!doctype html>
	<head>
		<meta name="layout" content="main" />
		<r:require module="tedza" />
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
			<div id="q3" class="question"></div>
			<div id="videoplayer">
				<video id="player" class="video-js vjs-default-skin" height="450" width="562" controls preload="none"></video>
			</div>
			<input type="hidden" id="hq1" name="q1" value="" />
			<input type="hidden" id="hq2" name="q2" value="" />
			<input type="hidden" id="hq3" name="q3" value="" />
		  </div>
	  </div>
	</body>
</html>