import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeoLocationService extends HttpServlet {

    private String API_KEY = "K81NQ3MCGJOG";
    private String zipcode;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        zipcode = request.getParameter("zip-code");

        //pw.println(getCoordinates()[0] + " : " + getCoordinates()[1]);
        String timezoneResponse = getTimeZone();
        String[] coordinates = getCoordinates();

        JsonObject timezoneResponseJSON = new Gson().fromJson(timezoneResponse, JsonObject.class);
        Map<String, String> geoLocationMap = new LinkedHashMap<>();
        geoLocationMap.put("status", timezoneResponseJSON.get("status").getAsString());
        geoLocationMap.put("countryCode", timezoneResponseJSON.get("countryCode").getAsString());
        geoLocationMap.put("countryName", timezoneResponseJSON.get("countryName").getAsString());
        geoLocationMap.put("timezoneName", timezoneResponseJSON.get("zoneName").getAsString());
        geoLocationMap.put("timezoneAbbr", timezoneResponseJSON.get("abbreviation").getAsString());
        geoLocationMap.put("latitude", coordinates[0]);
        geoLocationMap.put("longitude", coordinates[1]);

        Gson gson = new Gson();
        String geoResponse = gson.toJson(geoLocationMap);
        pw.println(geoResponse);

    }

    public String[] getCoordinates() {
        String lat = "null";
        String lng = "null";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest coordinatesRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://wikizipcode.info/geocode"))
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("type=2&address=" + zipcode))
                .build();

        try {
            HttpResponse<String> coordinatesResponse = client.send(coordinatesRequest,
                    HttpResponse.BodyHandlers.ofString());

            JsonObject coordinatesResponseJSON = new Gson().fromJson(coordinatesResponse.body(), JsonObject.class);
            lat = coordinatesResponseJSON.get("lat").getAsString();
            lng = coordinatesResponseJSON.get("lng").getAsString();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return new String[]{lat, lng};
    }

    public String getTimeZone() {
        String[] coordinates = getCoordinates();

        if (coordinates != null) {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest timezoneRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.timezonedb.com/v2.1/get-time-zone?" +
                            "key=" + API_KEY + "&" +
                            "format=json" + "&" +
                            "by=position" + "&" +
                            "lat=" + coordinates[0] + "&" +
                            "lng=" + coordinates[1]
                    )).build();

            try {
                HttpResponse<String> timezoneResponse = client.send(timezoneRequest,
                        HttpResponse.BodyHandlers.ofString());

                return timezoneResponse.body();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
