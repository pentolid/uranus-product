var map;
var geocoder;
var xmlDoc;

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
			return (xmlDoc);
		} catch (e) {
			alert("Cannot instantiate XMLDOM object\n\nError:\n" + e.message);
			return (false);
		}
	}
}

function initializeMap() {
	geocoder = new google.maps.Geocoder();

	var mapOptions = {
		center : new google.maps.LatLng(57.706635, 11.938045),
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	loadXML('restaurants.xml');
	geocode();
}

function geocode() {
	var address, info, titel;
	var childNodes = xmlDoc.getElementsByTagName("markers")[0].childNodes;
	var marker = xmlDoc.getElementsByTagName("markers")[0];

	for ( var i = 0; i <= childNodes.length; i++) {
		address = marker.childNodes[i].getAttribute("rStreet") + " "
				+ marker.childNodes[i].getAttribute("rStreetNumber")
				+ ", Gothenburg";
		info = marker.childNodes[i].getAttribute("rInfo");
		titel = marker.childNodes[i].getAttribute("rName");
		
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				// map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location,
					titel : titel
				});
				 //alert(address + "/" + info + "/" + titel);

				var infoWindowOptions = {
					content : '<h3>' + titel + '</h3>' + info
				};
				var infoWindow = new google.maps.InfoWindow(infoWindowOptions);
				google.maps.event.addListener(marker, 'click', function(e) {
					infoWindow.open(map, this);
				});
			} else {
				alert("Geocode was not successful for the following reason: "
						+ status);
			}
		});
	}
}