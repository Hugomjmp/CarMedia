package pt.hugo.carmedia.models.data.radio;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StationLibrary {
    private List<Station> stations = new ArrayList<>();

    public StationLibrary() {
    }

    public void loadStationList(){
        try {
            URL url = new URL("https://de2.api.radio-browser.info/json/stations/bycountry/Portugal");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(connection.getInputStream())).getAsJsonArray();

            for (JsonElement element : jsonArray){
                var obj = element.getAsJsonObject();

                if (obj.get("codec").getAsString().equalsIgnoreCase("MP3")){
                    String name = obj.get("name").getAsString();
                    String codec = obj.get("codec").getAsString();
                    String streamUrl = obj.get("url").getAsString();
                    String homepage = obj.get("homepage").getAsString();
                    String favicon = obj.get("favicon").getAsString();
                    String countrycode = obj.get("countrycode").getAsString();
                    String country = obj.get("country").getAsString();
                    String countrysubdivisioncode = obj.has("statecode") && !obj.get("statecode").isJsonNull() ? obj.get("statecode").getAsString() : "";
                    String countrysubdivision = obj.has("state") && !obj.get("state").isJsonNull() ? obj.get("state").getAsString() : "";
                    String languagecodes = obj.has("languagecodes") ? obj.get("languagecodes").getAsString() : "";
                    String languages = obj.has("language") ? obj.get("language").getAsString() : "";

                    double geoLat = obj.has("latitude") && !obj.get("latitude").isJsonNull() ? obj.get("latitude").getAsDouble() : 0.0;
                    double geoLong = obj.has("longitude") && !obj.get("longitude").isJsonNull() ? obj.get("longitude").getAsDouble() : 0.0;
                    String geoinfo = geoLat + " / " + geoLong;

                    Station station = new Station(name, codec ,streamUrl, homepage, favicon, countrycode, country,
                            countrysubdivisioncode, countrysubdivision, languagecodes, languages, geoinfo);

                    stations.add(station);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Station> getStations(){
        return stations;
    }
}
