package Utilities;

import Models.OMDBJsonResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

public class APIUtility {

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
