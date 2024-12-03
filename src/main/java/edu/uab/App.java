package edu.uab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import edu.uab.model.Dashboard;

public class App extends Application {
  private static double WIDTH = 640;
  private static double HEIGHT = 480;
  private static String TITLE = "Dashboard";

  @Override
  public void start(Stage stage) {
    try {
      Dashboard dashboard = Dashboard.getInstance();

      FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/edu/uab/view/MainView.fxml"));
      Parent root = loader.load();

      Scene scene = new Scene(root, App.WIDTH, App.HEIGHT);

      stage.setScene(scene);
      stage.setTitle(App.TITLE);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
