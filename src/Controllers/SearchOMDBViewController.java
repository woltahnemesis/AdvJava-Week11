package Controllers;

import Models.HighLevelInfo;
import Models.OMDBJsonResponse;
import Utilities.APIUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SearchOMDBViewController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<HighLevelInfo> listView;

    @FXML
    private Label rowsReturnedLabel;

    @FXML
    private ImageView imageView;

    // When the "search" button is pushed on the view,
    // call this method, which will call the OMDB movie search and return
    // a List that will be populated in the listView
    @FXML
    private void getMovies()
    {
        String searchTest = searchTextField.getText();
        listView.getItems().clear();
        try {
            OMDBJsonResponse response = APIUtility.callOmdbAPI(searchTest);
            List<HighLevelInfo> movies = Arrays.asList(response.getSearch());
            listView.getItems().addAll(movies);
            rowsReturnedLabel.setText("Rows returned: " + response.getTotalResults());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rowsReturnedLabel.setText("");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, movieSelected) -> {
                    imageView.setImage(new Image(movieSelected.getPoster()));
                }
        );
    }
}
