var map, xmlDoc;
var geocoder = new google.maps.Geocoder();
var infoWindow = new google.maps.InfoWindow();

$(".fancybox")
.attr('rel', 'gallery')
.fancybox({
    type: 'iframe',
    autoSize : false,
    beforeLoad : function() {                    
        this.width = parseInt(this.href.match(/width=[0-9]+/i)[0].replace('width=',''));  
        this.height = parseInt(this.href.match(/height=[0-9]+/i)[0].replace('height=',''));
    }
});

function loadXML(fileName) {
	// IE ActiveX
	try {
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	} catch (e) {
		// Mozilla/Firefox, Opera (Also WebKit fallthrough)
		try {
			xmlDoc = document.implementation.createDocument("", "", null);
		} catch (e) {
			// Error
			alert("Cannot instantiate XMLDOM object\n\nError:\n" + e.message);
			return (false);
		}
	}

	try {
		xmlDoc.async = false;
		xmlDoc.load(fileName);
		//alert("using XMLDOM");
		return (xmlDoc);
	} catch (e) {
		// WebKit (Safari, Chrome) - AJAX fallback
		try {
			var xhr = new XMLHttpRequest();
			xhr.open("GET", fileName, false);
			xhr.send(null);
			xmlDoc = xhr.responseXML;
			if (!xmlDoc)
				return (false);
			//aler("using HTTPRequest");
			return (xmlDoc);
		} catch (e) {
			alert("Cannot instantiate XMLDOM object\n\nError:\n" + e.message);
			return (false);
		}
	}
}

function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(57.706635, 11.938045),
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	loadXML('restaurants.xml');
	readXML();
}

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

function readXML() {
	var childNodes = xmlDoc.getElementsByTagName("markers")[0].childNodes;
	var marker = xmlDoc.getElementsByTagName("markers")[0];

	for ( var i = 0; i < childNodes.length; i++) {
		var info = marker.childNodes[i].getAttribute("rInfo");
		var title = marker.childNodes[i].getAttribute("rName");
		var rID = marker.childNodes[i].getAttribute("rID");
		geocode(marker.childNodes[i].getAttribute("rStreet") + " "
				+ marker.childNodes[i].getAttribute("rStreetNumber")
				+ ", Gothenburg", title, info, rID, function(result) {
			if (result === 0)
				alert('Adress not found');
			else {
				//alert(result[3]);
				makeMarker({
					position : result[0],
					title : result[1],
					content : '<h3>' + result[1] + '</h3>' + result[2] +
					"<br><a class=\"fancybox\" href=\"dbTest.jsp?width=400&height=500&rID=" + result[3] + "\">view ratings</a>"
					});
			}
			;
		});
	}
}

function geocode(address, title, info, rID, callback) {
	var done = new Array();
	if (typeof (geocoder) == 'undefined')
		geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			done[0] = results[0].geometry.location;
			done[1] = title;
			done[2] = info;
			done[3] = rID;
			callback(done);
		} else {
			callback(0);
			alert("Geocode was not successful for the following reason: "
					+ status);
		}
	});
}