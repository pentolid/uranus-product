var map;
var infoWindow = new google.maps.InfoWindow();

/**
 * object for iFrame (fancybox) and option setting
 */
$(".various").fancybox({
	maxWidth	: 500,
	maxHeight	: 400,
	fitToView	: false,
	width		: '70%',
	height		: '70%',
	autoSize	: false,
	closeClick	: false,
	openEffect	: 'none',
	closeEffect	: 'none'
});

$(document).ready(function() {
	$(".fancybox").fancybox({
		openEffect	: 'none',
		closeEffect	: 'none'
	});
});

function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(57.706635, 11.938045),
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
}

function makeMarker(options, callback) {
	var marker = new google.maps.Marker({
		map : map
	});
	marker.setOptions(options);
	google.maps.event.addListener(marker, "click", function() {
		infoWindow.setOptions(options);
		infoWindow.open(map, marker);
	});
	google.maps.event.addListener(map, "click", function() {
		infoWindow.close();
	});
//	alert("marker");
	callback();
}

function readXML(Lat, Lng, Info, Name, callback) {
	initialize();
	for ( var i = 0; i < Lat.length; i++) {
		var position = new google.maps.LatLng(parseFloat(Lat[i]),parseFloat(Lng[i]));
		var info = Info[i];
		var name = Name[i];
		var icon = new google.maps.MarkerImage("GMapMarkers/restaurant.png");
		var html = '<b>' + name + '</b><br>' + info + '<br>';
		makeMarker({
			position : position,
			title : name,
			maxWidth: 200,
			icon: icon,
			content : html
		}, function(){});
		callback();
		//alert("readXML");
	}
}