import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherService extends HttpServlet {

    private String weatherLocations = "weather.json";

    private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String ERROR_MSG = null;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String city = request.getParameter("city");

        String weather = getWeather(city);

        if (weather == null) {
            response.sendError(500, ERROR_MSG);
        } else {
            pw.println(weather);
        }


    }

    private String getWeather(String city) {
        Gson gson = new Gson();
        String weatherResponse = null;

        try {
            File locationsJSON = new File(getClass().getClassLoader().getResource(weatherLocations).getFile());
            Reader reader = new FileReader(locationsJSON);

            List<Weather> weatherList = gson.fromJson(reader, new TypeToken<List<Weather>>() {
            }.getType());
            for (Weather weather : weatherList) {
                if (city.equals(weather.getCity())) {
                    weatherResponse = gson.toJson(weather);
                }
            }

            if (weatherResponse == null) {
                ERROR_MSG = "Service doesn't provide weather for " + city;
                log.log(Level.WARNING, ERROR_MSG);
            }

        } catch (NullPointerException e) {
            ERROR_MSG = "Failed to get " + weatherLocations +" file.";
            log.log(Level.WARNING, ERROR_MSG);
        } catch (FileNotFoundException e) {
            ERROR_MSG = "File " + weatherLocations + " not found.";
            log.log(Level.WARNING, ERROR_MSG);
        }

        return weatherResponse;

    }
}
