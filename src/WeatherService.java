import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherService extends HttpServlet {
    
    String API_KEY = "14a2960224784b21a3504533201001";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String city = request.getParameter("city");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest weatherRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://api.weatherapi.com/v1/current.json?" +
                        "key=" + API_KEY + "&" +
                        "q=" + city
                )).build();

        try {
            HttpResponse<String> weatherResponse = client.send(weatherRequest,
                    HttpResponse.BodyHandlers.ofString());
            pw.println(weatherResponse.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
