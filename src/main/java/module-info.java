module com.yourcompany {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  opens edu.uab.controller to javafx.fxml;

  exports edu.uab;
}
