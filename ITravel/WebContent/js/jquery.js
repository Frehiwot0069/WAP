var map;

$(document)
    .ready(
        function() {
        	$("a.location").live("click", function(){
        		 map = new GMaps({
                     el: '#map',
                     lat: -12.043333,
                     lng: -77.028333
                 });
        		// alert($(this).text());
               //  e.preventDefault();
              //   alert(document.getElementById("location").innerHTML);
                 GMaps
                     .geocode({
                         address: $(this).text(),
                         callback: function(
                             results, status) {
                             if (status == 'OK') {
                                 var latlng = results[0].geometry.location;
                                 map
                                     .setCenter(
                                         latlng
                                         .lat(),
                                         latlng
                                         .lng());
                                 map.addMarker({
                                     lat: latlng
                                         .lat(),
                                     lng: latlng
                                         .lng()
                                 });
                                 var infoWindow = new google.maps.InfoWindow({
                                     content: "current location:<br/>longitude:" + latlng
                                         .lat() + "<br/>latitude:" + latlng
                                         .lng()
                                 });
                                 // open window info.
                                 infoWindow
                                     .open(map);
                                 map
                                     .drawOverlay({
                                         lat: latlng
                                             .lat(),
                                         lng: latlng
                                             .lng(),
                                         // content:
                                         // '<div
                                         // class="overlay">Lima<div
                                         // class="overlay_arrow
                                         // above"></div></div>',
                                         verticalAlign: 'top',
                                         horizontalAlign: 'center'
                                     });
                                 var weatherLayer = new google.maps.weather.WeatherLayer({
                                     temperatureUnits: google.maps.weather.TemperatureUnit.FAHRENHEIT
                                 });
                                 weatherLayer
                                     .setMap(map);
                                 var cloudLayer = new google.maps.weather.CloudLayer();
                                 cloudLayer
                                     .setMap(map);
                             }
                         }
                     });
                 var root = 'http://api.openweathermap.org/data/2.5/forecast?q=';
                 $.ajax({
                         url: root +$(this).text()+'&mode=json&appid=bb1a4c96b8b59549e207db648ab58fed',
                         method: 'GET'
                     })
                     .then(
                         function(data) {

                             +',' + data.city.country;
                             // alert(data.list.length);

                             var fiveDaysInfo = [];

                             $.each(data.list, function(idx, elem) {
                                 // alert(elem.dt_txt.split(" ")[0]);
                                 if (idx == 0)
                                     fiveDaysInfo.push({
                                         date: elem.dt_txt
                                             .split(" ")[0],
                                         weather: elem
                                     });
                                 else {
                                     var state = 0;

                                     fiveDaysInfo.forEach(function(
                                         elm) {
                                         // alert(elm.date + "=== " +
                                         // elem.dt_txt.split("
                                         // ")[0]);
                                         if (elm.date == elem.dt_txt
                                             .split(" ")[0])
                                             state = 1;
                                     });
                                     if (state == 0) {
                                         fiveDaysInfo.push({
                                             date: elem.dt_txt
                                                 .split(" ")[0],
                                             weather: elem
                                         });
                                     }
                                 }
                             });
                             // alert(fiveDaysInfo[0].weather.dt_txt);
                             // alert(fiveDaysInfo.length);
                             for (let i = 0; i < 5; i++) {
                                 var iconCode = fiveDaysInfo[i].weather.weather[0].icon;
                                 document.getElementById("city" + (i + 1)).innerHTML = data.city.name
                                 document.getElementById("weather" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.weather[0].main;
                                 document.getElementById("temp" + (i + 1)).innerHTML = Math
                                     .round(fiveDaysInfo[i].weather.main.temp - 272);
                                 document.getElementById("date" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.dt_txt
                                     .split(" ")[0];
                                 document
                                     .getElementById("weather_icon_" + (i + 1)).src = "http://openweathermap.org/img/w/" + iconCode + ".png";

                             }

                         });
                 
                 $('#geocoding_form')
                     .submit(
                         function(e) {
                             e.preventDefault();
                             GMaps
                                 .geocode({
                                     address: $('#address')
                                         .val().trim(),
                                     callback: function(
                                         results, status) {
                                         if (status == 'OK') {
                                             var latlng = results[0].geometry.location;
                                             map
                                                 .setCenter(
                                                     latlng
                                                     .lat(),
                                                     latlng
                                                     .lng());
                                             map.addMarker({
                                                 lat: latlng
                                                     .lat(),
                                                 lng: latlng
                                                     .lng()
                                             });
                                             var infoWindow = new google.maps.InfoWindow({
                                                 content: "current location:<br/>longitude:" + latlng
                                                     .lat() + "<br/>latitude:" + latlng
                                                     .lng()
                                             });
                                             // open window info.
                                             infoWindow
                                                 .open(map);
                                             map
                                                 .drawOverlay({
                                                     lat: latlng
                                                         .lat(),
                                                     lng: latlng
                                                         .lng(),
                                                     // content:
                                                     // '<div
                                                     // class="overlay">Lima<div
                                                     // class="overlay_arrow
                                                     // above"></div></div>',
                                                     verticalAlign: 'top',
                                                     horizontalAlign: 'center'
                                                 });
                                             var weatherLayer = new google.maps.weather.WeatherLayer({
                                                 temperatureUnits: google.maps.weather.TemperatureUnit.FAHRENHEIT
                                             });
                                             weatherLayer
                                                 .setMap(map);
                                             var cloudLayer = new google.maps.weather.CloudLayer();
                                             cloudLayer
                                                 .setMap(map);
                                         }
                                     }
                                 });
                             var root = 'http://api.openweathermap.org/data/2.5/forecast?q=';
                             $.ajax({
                                     url: root +$('#address').val().trim()+'&mode=json&appid=bb1a4c96b8b59549e207db648ab58fed',
                                     method: 'GET'
                                 })
                                 .then(
                                     function(data) {

                                         +',' + data.city.country;
                                         // alert(data.list.length);

                                         var fiveDaysInfo = [];

                                         $.each(data.list, function(idx, elem) {
                                             // alert(elem.dt_txt.split(" ")[0]);
                                             if (idx == 0)
                                                 fiveDaysInfo.push({
                                                     date: elem.dt_txt
                                                         .split(" ")[0],
                                                     weather: elem
                                                 });
                                             else {
                                                 var state = 0;

                                                 fiveDaysInfo.forEach(function(
                                                     elm) {
                                                     // alert(elm.date + "=== " +
                                                     // elem.dt_txt.split("
                                                     // ")[0]);
                                                     if (elm.date == elem.dt_txt
                                                         .split(" ")[0])
                                                         state = 1;
                                                 });
                                                 if (state == 0) {
                                                     fiveDaysInfo.push({
                                                         date: elem.dt_txt
                                                             .split(" ")[0],
                                                         weather: elem
                                                     });
                                                 }
                                             }
                                         });
                                         // alert(fiveDaysInfo[0].weather.dt_txt);
                                         // alert(fiveDaysInfo.length);
                                         for (let i = 0; i < 5; i++) {
                                             var iconCode = fiveDaysInfo[i].weather.weather[0].icon;
                                             document.getElementById("city" + (i + 1)).innerHTML = data.city.name
                                             document.getElementById("weather" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.weather[0].main;
                                             document.getElementById("temp" + (i + 1)).innerHTML = Math
                                                 .round(fiveDaysInfo[i].weather.main.temp - 272);
                                             document.getElementById("date" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.dt_txt
                                                 .split(" ")[0];
                                             document
                                                 .getElementById("weather_icon_" + (i + 1)).src = "http://openweathermap.org/img/w/" + iconCode + ".png";

                                         }

                                     });

                         });
        	});
            map = new GMaps({
                el: '#map',
                lat: -12.043333,
                lng: -77.028333
            });
          //  e.preventDefault();
         //   alert(document.getElementById("location").innerHTML);
            GMaps
                .geocode({
                    address: $('#location')
                        .text(),
                    callback: function(
                        results, status) {
                        if (status == 'OK') {
                            var latlng = results[0].geometry.location;
                            map
                                .setCenter(
                                    latlng
                                    .lat(),
                                    latlng
                                    .lng());
                            map.addMarker({
                                lat: latlng
                                    .lat(),
                                lng: latlng
                                    .lng()
                            });
                            var infoWindow = new google.maps.InfoWindow({
                                content: "current location:<br/>longitude:" + latlng
                                    .lat() + "<br/>latitude:" + latlng
                                    .lng()
                            });
                            // open window info.
                            infoWindow
                                .open(map);
                            map
                                .drawOverlay({
                                    lat: latlng
                                        .lat(),
                                    lng: latlng
                                        .lng(),
                                    // content:
                                    // '<div
                                    // class="overlay">Lima<div
                                    // class="overlay_arrow
                                    // above"></div></div>',
                                    verticalAlign: 'top',
                                    horizontalAlign: 'center'
                                });
                            var weatherLayer = new google.maps.weather.WeatherLayer({
                                temperatureUnits: google.maps.weather.TemperatureUnit.FAHRENHEIT
                            });
                            weatherLayer
                                .setMap(map);
                            var cloudLayer = new google.maps.weather.CloudLayer();
                            cloudLayer
                                .setMap(map);
                        }
                    }
                });
            var root = 'http://api.openweathermap.org/data/2.5/forecast?q=';
            $.ajax({
                    url: root +$('#location').text()+'&mode=json&appid=bb1a4c96b8b59549e207db648ab58fed',
                    method: 'GET'
                })
                .then(
                    function(data) {

                        +',' + data.city.country;
                        // alert(data.list.length);

                        var fiveDaysInfo = [];

                        $.each(data.list, function(idx, elem) {
                            // alert(elem.dt_txt.split(" ")[0]);
                            if (idx == 0)
                                fiveDaysInfo.push({
                                    date: elem.dt_txt
                                        .split(" ")[0],
                                    weather: elem
                                });
                            else {
                                var state = 0;

                                fiveDaysInfo.forEach(function(
                                    elm) {
                                    // alert(elm.date + "=== " +
                                    // elem.dt_txt.split("
                                    // ")[0]);
                                    if (elm.date == elem.dt_txt
                                        .split(" ")[0])
                                        state = 1;
                                });
                                if (state == 0) {
                                    fiveDaysInfo.push({
                                        date: elem.dt_txt
                                            .split(" ")[0],
                                        weather: elem
                                    });
                                }
                            }
                        });
                        // alert(fiveDaysInfo[0].weather.dt_txt);
                        // alert(fiveDaysInfo.length);
                        for (let i = 0; i < 5; i++) {
                            var iconCode = fiveDaysInfo[i].weather.weather[0].icon;
                            document.getElementById("city" + (i + 1)).innerHTML = data.city.name
                            document.getElementById("weather" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.weather[0].main;
                            document.getElementById("temp" + (i + 1)).innerHTML = Math
                                .round(fiveDaysInfo[i].weather.main.temp - 272);
                            document.getElementById("date" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.dt_txt
                                .split(" ")[0];
                            document
                                .getElementById("weather_icon_" + (i + 1)).src = "http://openweathermap.org/img/w/" + iconCode + ".png";

                        }

                    });
            
            $('#geocoding_form')
                .submit(
                    function(e) {
                        e.preventDefault();
                        GMaps
                            .geocode({
                                address: $('#address')
                                    .val().trim(),
                                callback: function(
                                    results, status) {
                                    if (status == 'OK') {
                                        var latlng = results[0].geometry.location;
                                        map
                                            .setCenter(
                                                latlng
                                                .lat(),
                                                latlng
                                                .lng());
                                        map.addMarker({
                                            lat: latlng
                                                .lat(),
                                            lng: latlng
                                                .lng()
                                        });
                                        var infoWindow = new google.maps.InfoWindow({
                                            content: "current location:<br/>longitude:" + latlng
                                                .lat() + "<br/>latitude:" + latlng
                                                .lng()
                                        });
                                        // open window info.
                                        infoWindow
                                            .open(map);
                                        map
                                            .drawOverlay({
                                                lat: latlng
                                                    .lat(),
                                                lng: latlng
                                                    .lng(),
                                                // content:
                                                // '<div
                                                // class="overlay">Lima<div
                                                // class="overlay_arrow
                                                // above"></div></div>',
                                                verticalAlign: 'top',
                                                horizontalAlign: 'center'
                                            });
                                        var weatherLayer = new google.maps.weather.WeatherLayer({
                                            temperatureUnits: google.maps.weather.TemperatureUnit.FAHRENHEIT
                                        });
                                        weatherLayer
                                            .setMap(map);
                                        var cloudLayer = new google.maps.weather.CloudLayer();
                                        cloudLayer
                                            .setMap(map);
                                    }
                                }
                            });
                        var root = 'http://api.openweathermap.org/data/2.5/forecast?q=';
                        $.ajax({
                                url: root +$('#address').val().trim()+'&mode=json&appid=bb1a4c96b8b59549e207db648ab58fed',
                                method: 'GET'
                            })
                            .then(
                                function(data) {

                                    +',' + data.city.country;
                                    // alert(data.list.length);

                                    var fiveDaysInfo = [];

                                    $.each(data.list, function(idx, elem) {
                                        // alert(elem.dt_txt.split(" ")[0]);
                                        if (idx == 0)
                                            fiveDaysInfo.push({
                                                date: elem.dt_txt
                                                    .split(" ")[0],
                                                weather: elem
                                            });
                                        else {
                                            var state = 0;

                                            fiveDaysInfo.forEach(function(
                                                elm) {
                                                // alert(elm.date + "=== " +
                                                // elem.dt_txt.split("
                                                // ")[0]);
                                                if (elm.date == elem.dt_txt
                                                    .split(" ")[0])
                                                    state = 1;
                                            });
                                            if (state == 0) {
                                                fiveDaysInfo.push({
                                                    date: elem.dt_txt
                                                        .split(" ")[0],
                                                    weather: elem
                                                });
                                            }
                                        }
                                    });
                                    // alert(fiveDaysInfo[0].weather.dt_txt);
                                    // alert(fiveDaysInfo.length);
                                    for (let i = 0; i < 5; i++) {
                                        var iconCode = fiveDaysInfo[i].weather.weather[0].icon;
                                        document.getElementById("city" + (i + 1)).innerHTML = data.city.name
                                        document.getElementById("weather" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.weather[0].main;
                                        document.getElementById("temp" + (i + 1)).innerHTML = Math
                                            .round(fiveDaysInfo[i].weather.main.temp - 272);
                                        document.getElementById("date" + (i + 1)).innerHTML = fiveDaysInfo[i].weather.dt_txt
                                            .split(" ")[0];
                                        document
                                            .getElementById("weather_icon_" + (i + 1)).src = "http://openweathermap.org/img/w/" + iconCode + ".png";

                                    }

                                });

                    });
        });

