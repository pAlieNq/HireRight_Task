# HireRight_Task

Weather Service is available on the /weather

GET method
Parameters:
  city
Example:
  http://localhost:8080/weather?city=Tallinn
  
  Output:
  {
    "location": {
        "name": "Tallinn",
        "region": "Harjumaa",
        "country": "Estonia",
        "lat": 59.43,
        "lon": 24.73,
        "tz_id": "Europe/Tallinn",
        "localtime_epoch": 1578896625,
        "localtime": "2020-01-13 8:23"
    },
    "current": {
        "last_updated_epoch": 1578896105,
        "last_updated": "2020-01-13 08:15",
        "temp_c": 0.0,
        "temp_f": 32.0,
        "is_day": 0,
        "condition": {
            "text": "Partly cloudy",
            "icon": "//cdn.weatherapi.com/weather/64x64/night/116.png",
            "code": 1003
        },
        "wind_mph": 9.4,
        "wind_kph": 15.1,
        "wind_degree": 230,
        "wind_dir": "SW",
        "pressure_mb": 1005.0,
        "pressure_in": 30.2,
        "precip_mm": 0.0,
        "precip_in": 0.0,
        "humidity": 100,
        "cloud": 25,
        "feelslike_c": -4.4,
        "feelslike_f": 24.0,
        "vis_km": 10.0,
        "vis_miles": 6.0,
        "uv": 0.0,
        "gust_mph": 19.2,
        "gust_kph": 31.0
    }
  }

Geo-Location service is available on the /geolocation

POST method
Parameters:
  zip-code
Example:
  zip-code=11913
  
  Output:
  {
    "status": "OK",
    "countryCode": "EE",
    "countryName": "Estonia",
    "timezoneName": "Europe/Tallinn",
    "timezoneAbbr": "EET",
    "latitude": "59.4833280868919",
    "longitude": "24.8784389077015"
  }
  
