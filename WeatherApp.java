import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {

        try {
            // 🌍 API URL (Mumbai Coordinates Example)
            String url = "https://api.open-meteo.com/v1/forecast?latitude=19.07&longitude=72.87&current_weather=true";

            // 1️⃣ Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // 2️⃣ Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // 3️⃣ Send Request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4️⃣ Parse JSON
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");

            double temperature = currentWeather.getDouble("temperature");
            double windspeed = currentWeather.getDouble("windspeed");
            String time = currentWeather.getString("time");


            System.out.println("====== WEATHER REPORT ======");
            System.out.println("Location: Mumbai");
            System.out.println("Time: " + time);
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Wind Speed: " + windspeed + " km/h");
            System.out.println("============================");

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
