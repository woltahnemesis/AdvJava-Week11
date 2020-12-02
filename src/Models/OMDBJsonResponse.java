package Models;

import com.google.gson.annotations.SerializedName;

public class OMDBJsonResponse {

    @SerializedName("Search")
    private HighLevelInfo[] search;

    private String totalResults;

    public HighLevelInfo[] getSearch() {
        return search;
    }

    public void setSearch(HighLevelInfo[] search) {
        this.search = search;
    }
}
