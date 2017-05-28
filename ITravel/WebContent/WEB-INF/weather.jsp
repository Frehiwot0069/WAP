$(function(){
    var map;
  var geoJSON;
  var request;
  var fRequest;
  var gettingData = false;
  var openWeatherMapKey = "7efe5b5e8f4df64ab68df3ff66d187ae"

  var currentUserCity = "";
  var currentUserState = "IOWA";
  var currentUserCountry = "USA";
  var currentUserZip = "52557"
	 
 /* var currentUserCity = $("#userCity").val();
  var currentUserState =  $("#userState").val();
  var currentUserCountry =  $("#userCountry").val();
  var currentUserZip =  $("#userZipCode").val();*/
  
  alert(currentUserCity +"  "+currentUserState );
	  
  var currentUserAddress = currentUserCity + " , " + currentUserState + " , " + currentUserCountry + " , " + currentUserZip;
  var currentUserLat = 0;
  var currentUserLon = 0;
  


  //get geolocation from an address
  var geocoder =  new google.maps.Geocoder();
  geocoder.geocode( { 'address': currentUserAddress }, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        //alert("location : " + results[0].geometry.location.lat() + " " +results[0].geometry.location.lng());
        currentUserLat = results[0].geometry.location.lat();
        currentUserLon = results[0].geometry.location.lng();
      } else {
        console.log("Something got wrong " + status);
      }
  });
  
  function timeConverter(UNIX_timestamp){
	  var a = new Date(UNIX_timestamp * 1000);
	  var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	  var year = a.getFullYear();
	  var month = months[a.getMonth()];
	  var date = a.getDate();
	  var hour = a.getHours();
	  var min = a.getMinutes();
	  var sec = a.getSeconds();
	  var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
	  return time;
	}

  function reloadMapOnSearch(place){
    currentUserLat = place.geometry.location.lat();
    currentUserLon = place.geometry.location.lng();

    initialize();
  }


  function initialize() {
    var mapOptions = {
      zoom: 12,
      center: new google.maps.LatLng(currentUserLat, currentUserLon)
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    // Add interaction listeners to make weather requests
    google.maps.event.addListener(map, 'idle', checkIfDataRequested);

    // Sets up and populates the info window with details
    map.data.addListener('click', function(event) {
      infowindow.setContent(
       "<img src=" + event.feature.getProperty("icon") + ">"
       + "<br /><strong>" + event.feature.getProperty("city") + "</strong>"
       + "<br />" + event.feature.getProperty("temperature") + "&deg;F"
       + "<br />" + event.feature.getProperty("weather")
       );
      infowindow.setOptions({
          position:{
            lat: event.latLng.lat(),
            lng: event.latLng.lng()
          },
          pixelOffset: {
            width: 0,
            height: -15
          }
        });
      infowindow.open(map);
    });

    var input = /** @type {!HTMLInputElement} */(
            document.getElementById('pac-input'));
        var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.addListener('place_changed', function() {
          var place = autocomplete.getPlace();
          reloadMapOnSearch(place);
        });
  }

  var checkIfDataRequested = function() {
    // Stop extra requests being sent
    while (gettingData === true) {
      request.abort();
      gettingData = false;
    }
    getWeather();
  };

  // Get the coordinates from the Map bounds
  /*var getCoords = function() {
    var bounds = map.getBounds();
    var NE = bounds.getNorthEast();
    var SW = bounds.getSouthWest();
    getWeather(NE.lat(), NE.lng(), SW.lat(), SW.lng());
  };*/

  // Make the weather request
  var getWeather = function() {
    gettingData = true;
    /*var requestString = "http://api.openweathermap.org/data/2.5/box/city?bbox="
                        + westLng + "," + northLat + "," //left top
                        + eastLng + "," + southLat + "," //right bottom
                        + map.getZoom()
                        + "&cluster=yes&format=json"
                        + "&APPID=" + openWeatherMapKey;*/


    //var requestString = "http://api.openweathermap.org/data/2.5/weather?lat=41.00761139999999&lon=-91.96369140000002&appid=062be6ca6eb46770302023717dee68dc";

    var requestString = "http://api.openweathermap.org/data/2.5/weather?"
                        + "&lat=" + currentUserLat + "&lon=" + currentUserLon
                        + "&APPID=" + openWeatherMapKey+ "&units=imperial";

    var fRequestString = "http://api.openweathermap.org/data/2.5/forecast?"
                        + "&lat=" + currentUserLat + "&lon=" + currentUserLon
                        + "&APPID=" + openWeatherMapKey+ "&units=imperial";
    
    


                        //http://api.openweathermap.org/data/2.5/forecast?q=Fairfield,IOWA,US&appid=062be6ca6eb46770302023717dee68dc

    //console.log(requestString);

    fRequest = new XMLHttpRequest();
    fRequest.onload = proccessfResults;
    fRequest.open("get", fRequestString, true);
    fRequest.send();

    request = new XMLHttpRequest();
    request.onload = proccessResults;
    request.open("get", requestString, true);
    request.send();
  };
  
 

  // Take the JSON results and proccess them
  var proccessfResults = function() {
    console.log(this);
    var results = JSON.parse(this.responseText);

    //alert(results);
    if (results.list.length > 0) {
        //resetData();
        //alert("data lengthdddd"+results.list[0].dt_txt);

        var htmlStr = "<table><tr><th>Date &amp; Time</th><th>Temp (&deg; F)</th></tr>";
        for (var i = 0; i < results.list.length; i++) {
          //geoJSON.features.push(jsonToGeoJson(results.list[i]));

          htmlStr += "<tr><td>" + timeConverter(results.list[i].dt) + "</td><td>" + results.list[i].main.temp + "</td></tr>"
        }
        htmlStr+="</table>";

        $("#fdForecast").empty();
        $("#fdForecast").append(htmlStr);
    }

  };

  // Take the JSON results and proccess them
  var proccessResults = function() {
    console.log(this);
    var results = JSON.parse(this.responseText);

    //alert(results);
    /*if (results.list.length > 0) {
        resetData();
        for (var i = 0; i < results.list.length; i++) {
          geoJSON.features.push(jsonToGeoJson(results.list[i]));
        }
        drawIcons(geoJSON);
    }*/

    if (true) {
        resetData();
        geoJSON.features.push(jsonToGeoJson(results));
        drawIcons(geoJSON);
    }

  };

  var infowindow = new google.maps.InfoWindow();

  // For each result that comes back, convert the data to geoJSON
  var jsonToGeoJson = function (weatherItem) {
    //alert(weatherItem);
    //weatherItem = {"coord":{"lon":-91.96,"lat":41.01},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],"base":"cmc stations","main":{"temp":292.4,"pressure":1019,"humidity":93,"temp_min":291.15,"temp_max":294.15},"wind":{"speed":2.6,"deg":360},"clouds":{"all":20},"dt":1472642100,"sys":{"type":1,"id":868,"message":0.0026,"country":"US","sunrise":1472643273,"sunset":1472690418},"id":4855967,"name":"Fairfield","cod":200};
    //alert(weatherItem);
    var feature = {
      type: "Feature",
      properties: {
        city: weatherItem.name,
        weather: weatherItem.weather[0].main,
        temperature: weatherItem.main.temp,
        min: weatherItem.main.temp_min,
        max: weatherItem.main.temp_max,
        humidity: weatherItem.main.humidity,
        pressure: weatherItem.main.pressure,
        windSpeed: weatherItem.wind.speed,
        windDegrees: weatherItem.wind.deg,
        windGust: weatherItem.wind.gust,
        icon: "http://openweathermap.org/img/w/"
              + weatherItem.weather[0].icon  + ".png",
        coordinates: [weatherItem.coord.lon, weatherItem.coord.lat]
      },
      geometry: {
        type: "Point",
        coordinates: [weatherItem.coord.lon, weatherItem.coord.lat]
      }
    };
    // Set the custom marker icon
    map.data.setStyle(function(feature) {
      return {
        icon: {
          url: feature.getProperty('icon'),
          anchor: new google.maps.Point(25, 25)
        }
      };
    });

    // returns object
    return feature;
  };

  // Add the markers to the map
  var drawIcons = function (weather) {
     map.data.addGeoJson(geoJSON);
     // Set the flag to finished
     gettingData = false;
  };

  // Clear data layer and geoJSON
  var resetData = function () {
    geoJSON = {
      type: "FeatureCollection",
      features: []
    };
    map.data.forEach(function(feature) {
      map.data.remove(feature);
    });
  };

  google.maps.event.addDomListener(window, 'load', initialize);

})();
  //$(alert("Heellooooooo"));