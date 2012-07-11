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
				<video id="player" class="video-js vjs-default-skin" height="450" width="562" controls preload="none"></video>
			</div>
			<g:render template="/video/player" model="${[jPlayer:'videoPlayer', cssSelectorAncestor:'jp_container_1', jplayer_inspector_1:'jplayer_inspector_1']}" />
			<input type="hidden" id="hq1" name="q1" value="" />
			<input type="hidden" id="hq2" name="q2" value="" />
			<input type="hidden" id="hq3" name="q3" value="" />
		  </div>
	  </div>
	</body>
</html>