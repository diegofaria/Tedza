var step = 0;
$(function() {
    $("ul.buttons a").click(function(event) {
      event.preventDefault();
    
      var elem = $(this);
      var category = elem.next().text();
      var div = elem.closest("div");
      var divId = div.attr("id");
      $("#h" + divId).val(category);
      step = divId.substring(1);
      
      changeBreadCrumb();
    
	  if (divId != "q3") {
	      div.fadeOut("slow", function() {
	      	  div.next().fadeIn("fast");
	      });
	  }
	  else {
		  $("#watchBtn").fadeIn("slow");
		  alert
		  $.post("/filter/getResults", { q1: $("#hq1").val(), q2: $("#hq2").val(), q3: $("#hq3").val() },
	        function(data) {
		        alert(data);
		    }
		  );
	  }
   });
});

function changeBreadCrumb() {
	var breadcrumb = $("#breadcrumb");
	breadcrumb.fadeOut("fast", function () {
		var html;
		if (step == 0) html = "";
	if (step > 0) html = "IDEAS for <a href=\"javascript:void(0)\" onclick=\"historyBack(0)\" title=\"Click to get back to beginning\">" + $("#hq1").val() + "</a>";
	    if (step > 1) html += " at <a href=\"javascript:void(0)\" onclick=\"historyBack(1)\" title=\"Click to get back to second step\">" + $("#hq2").val() + "</a>";
	    if (step > 2) html += " to be <a href=\"javascript:void(0)\" onclick=\"historyBack(2)\" title=\"Click to get back to last step\">" + $("#hq3").val() + "</a>";
		
	    breadcrumb.html(html);
	    breadcrumb.fadeIn("slow");
	});
}

function historyBack(page) {
	var div = $("#q" + (page + 1));
	var allDivs = $("div.question");
	var divToHide;
	for (i = 0; i<allDivs.length; i++) {
		var theDiv = $(allDivs.get(i)); 
		if (theDiv.is(":visible")) {
			divToHide = theDiv;
		}
	}
	divToHide.fadeOut("fast", function() {
		div.fadeIn("slow");
	});
	
	$("#watchBtn").hide();
	step = page;
	changeBreadCrumb();
}