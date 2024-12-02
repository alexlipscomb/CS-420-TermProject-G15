package edu.uab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
  private static double WIDTH = 640;
  private static double HEIGHT = 480;

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/edu/uab/view/MainView.fxml"));
      Parent root = loader.load();

      Scene scene = new Scene(root, App.WIDTH, App.HEIGHT);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
