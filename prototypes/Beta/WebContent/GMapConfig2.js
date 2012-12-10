/**
 * Java Script for Google Maps API
 */

var map, xmlDoc;
var geocoder = new google.maps.Geocoder();
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

/**
 * Connects to XML file which includes all data used for the map
 */
function downloadUrl(url,callback) {
	 var request = window.ActiveXObject ?
	     new ActiveXObject('Microsoft.XMLHTTP') :
	     new XMLHttpRequest;

	 request.onreadystatechange = function() {
	   if (request.readyState == 4) {
	     request.onreadystatechange = doNothing;
	     callback(request, request.status);
	   }
	 };

	 request.open('GET', url, true);
	 request.send(null);
	}
function doNothing(){alert("Fail");}
/**
 * initialize Map, set center and create markers
 */
function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(57.706635, 11.938045),
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	/**
	 * call function read xml file and create markers on map
	 */
	addPins();
}
/**
 * creates a marker including info window
 */
function makeMarker(options) {
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
}
/**
 * reads xml file and creates all markers stored in the file
 */
function addPins(){
downloadUrl("restaurants.xml", function(data) {
	  var xml = data.responseXML;
	  var markers = xml.documentElement.getElementsByTagName("marker");
	  for (var i = 0; i < markers.length; i++) {
	    var name = markers[i].getAttribute("rName");
	    var info = markers[i].getAttribute("rInfo");
	    var address = markers[i].getAttribute("rStreet") + markers[i].getAttribute("rStreetNumber");
	    var rID = markers[i].getAttribute("rID");
	    var point = new google.maps.LatLng(
	        parseFloat(markers[i].getAttribute("rLat")),
	        parseFloat(markers[i].getAttribute("rLng")));
	    var html = '<b>' + name + '</b><br>' + info + '<br><br>' + address +
		/**
		 * link for iFrame including comment jsp
		 */
		"<br><a class=\"various\" data-fancybox-type=\"iframe\" href=\"Comments.jsp?rID=" + rID + "\">view ratings</a>" +
		/**
		 * link for iFrame including pictures
		 * link will also be read from database and pictures will be stored on the servers in final version
		 * ...sample pictures source: http://www.kaptennemos.se
		 */
		"<br><br><a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.kooperativet.se/res/img/kooperativet_lindholmen899t300_005.jpg\" title=\"sample picture1\">show pictures</a>" +
		"<a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.lindholmen.se/sites/default/files/restaurang.jpg\" title=\"sample picture2\"></a>" +
		"<a class=\"fancybox\" rel=\"gallery1\" href=\"http://chalmerskonferens.se/wp-content/uploads/lindholmen-450x300.jpg\" title=\"sample picture3\"></a>"+
		"<a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.kaptennemos.se/files/4.jpg\" title=\"sample picture4\"></a>";
	    makeMarker({
			position : point,
			title : name,
			maxWidth: 200,
			content : html
			});
	  }
	});
}


//function readXML() {
//	var childNodes = xmlDoc.getElementsByTagName("markers")[0].childNodes;
//	var marker = xmlDoc.getElementsByTagName("markers")[0];
//	/**
//	 * count through childNodes
//	 */
//	for ( var i = 0; i < childNodes.length; i++) {
//		var info = marker.childNodes[i].getAttribute("rInfo");
//		var title = marker.childNodes[i].getAttribute("rName");
//		var rID = marker.childNodes[i].getAttribute("rID");
//		geocode(marker.childNodes[i].getAttribute("rStreet") + " "
//				+ marker.childNodes[i].getAttribute("rStreetNumber")
//				+ ", Gothenburg", title, info, rID, function(result) {
//			if (result === 0)
//				alert('Adress not found');
//			else {
//				//alert(result[3]);
//				makeMarker({
//					position : result[0],
//					title : result[1],
//					maxWidth: 200,
//					content : '<h3>' + result[1] + '</h3>' + result[2] +
//					/**
//					 * link for iFrame including comment jsp
//					 */
//					"<br><a class=\"various\" data-fancybox-type=\"iframe\" href=\"Comments.jsp?rID=" + result[3] + "\">view ratings</a>" +
//					/**
//					 * link for iFrame including pictures
//					 * link will also be read from database and pictures will be stored on the servers in final version
//					 * ...sample pictures source: http://www.kaptennemos.se
//					 */
//					"<br><br><a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.kaptennemos.se/files/1.jpg\" title=\"sample picture1\">show pictures</a>" +
//					"<a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.kaptennemos.se/files/2.jpg\" title=\"sample picture2\"></a>" +
//					"<a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.kaptennemos.se/files/3.jpg\" title=\"sample picture3\"></a>"+
//					"<a class=\"fancybox\" rel=\"gallery1\" href=\"http://www.kaptennemos.se/files/4.jpg\" title=\"sample picture4\"></a>"
//					});
//			}
//			;
//		});
//	}
//}
///**
// * converts string address to Lng and Lat
// * includes callback function to ensure that all markers are getting created
// */
//function geocode(address, title, info, rID, callback) {
//	var done = new Array();
//	if (typeof (geocoder) == 'undefined')
//		geocoder = new google.maps.Geocoder();
//	geocoder.geocode({
//		'address' : address
//	}, function(results, status) {
//		if (status == google.maps.GeocoderStatus.OK) {
//			done[0] = results[0].geometry.location;
//			done[1] = title;
//			done[2] = info;
//			done[3] = rID;
//			callback(done);
//		} else {
//			callback(0);
//			alert("Geocode was not successful for the following reason: "
//					+ status);
//		}
//	});
//}