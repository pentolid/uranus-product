var patricia = new google.maps.LatLng(57.706629, 11.937128);
var parking = new google.maps.LatLng(57.707002, 11.935315);

var locationArray = [ patricia, parking ];
var locationNameArray = [ 'Patricia Buliding', 'Parking lot' ];

function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(57.706635, 11.938045),
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById('map'), mapOptions);

	for ( var i = 0; i < locationArray.length; i++) {
		var marker = new google.maps.Marker({
			position : locationArray[i],
			map : map,
			title : locationNameArray[i]
		});
	}
}