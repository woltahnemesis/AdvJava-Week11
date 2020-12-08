package Utilities;

import Models.OMDBJsonResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {

    // This method will receive a "search" String and send that to
    // the OMDB API to receive a JSON file. The file will
    // be written to movieInfo.json
    public static OMDBJsonResponse callOmdbAPI(String searchText) throws IOException, InterruptedException {
        // This is the same as what you would put in a browser if you wanted to
        // call the API
        String uri = "http://www.omdbapi.com/?i=tt3896198&apikey=ba89f23c&s="+searchText;
        String jsonLocation = "src/Utilities/movieInfo.json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(uri))
                                        .build();
        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers
                                                            .ofFile(Paths.get(jsonLocation)));
        return getMoviesFromAPI(new File(jsonLocation));
    }

    // This method receives a JSON file and extracts the
    // high level movie data out of of it and stores it in
    // a OMDBJsonResponse object
    public static OMDBJsonResponse getMoviesFromAPI(File jsonFile)
    {
        Gson gson = new Gson();
        OMDBJsonResponse searchResults = null;
        // Using try "with resources"
        try(
                FileReader fileReader = new FileReader(jsonFile);
                JsonReader jsonReader = new JsonReader(fileReader);
                )
        {
            searchResults = gson.fromJson(jsonReader, OMDBJsonResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchResults;
    }
}
