import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherService extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String city = request.getParameter("city");

        String weather = getWeather(city);
        pw.println(weather);

    }

    public String getWeather(String city) {
        Gson gson = new Gson();
        String weatherJSON = null;

        try {
            File jsonLocations = new File(getClass().getClassLoader().getResource("locations.json").getFile());
            Reader reader = new FileReader(jsonLocations);

            List<Weather> weatherList = gson.fromJson(reader, new TypeToken<List<Weather>>() {
            }.getType());

            for (Weather weather : weatherList) {
                if (city.equals(weather.getCity())) {
                    weatherJSON = gson.toJson(weather);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("There is no such file" + e);
        }

        if (weatherJSON != null) {
            return weatherJSON;
        } else {
            return "";
        }

    }
}
