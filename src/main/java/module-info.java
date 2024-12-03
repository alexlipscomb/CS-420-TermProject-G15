module com.yourcompany {
  requires javafx.controls;
  requires javafx.fxml;

  opens edu.uab.controller to javafx.fxml;
  opens edu.uab.controller.ItemContainerCommands to javafx.fxml;

  exports edu.uab;
}
