public class Weather {

    private String city;
    private int temp_c;
    private float wind_ms;
    private String wind_dir;
    private int humidity;
    private int pressure_mb;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(int temp_c) {
        this.temp_c = temp_c;
    }

    public float getWind_ms() {
        return wind_ms;
    }

    public void setWind_ms(int wind_ms) {
        this.wind_ms = wind_ms;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(int pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    /*public Weather(String city, int temp_c, int wind_ms, String wind_dir, int humidity, int pressure_mb) {

        this.city = city;
        this.temp_c = temp_c;
        this.wind_ms = wind_ms;
        this.wind_dir = wind_dir;
        this.humidity = humidity;
        this.pressure_mb = pressure_mb;

    }*/
}
