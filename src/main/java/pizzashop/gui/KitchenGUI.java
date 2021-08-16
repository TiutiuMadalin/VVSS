package pizzashop.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class KitchenGUI implements GUI {

	@Override
	public void show() {
		VBox vBoxKitchen = null;

		try {
			vBoxKitchen = FXMLLoader.load(getClass().getResource("/fxml/kitchenGUIFXML.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Objects.nonNull(vBoxKitchen)) {
			Stage stage = new Stage();
			stage.setTitle("Kitchen");
			stage.setResizable(false);
			stage.setOnCloseRequest(event -> {
				Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION,
				                            "Would you like to exit Kitchen window?",
				                            ButtonType.YES,
				                            ButtonType.NO);
				ButtonType result = exitAlert.showAndWait().orElse(ButtonType.NO);
				if (result == ButtonType.YES) {
					stage.close();
				} else {
					event.consume();
				}
			});
			stage.setAlwaysOnTop(false);
			stage.setScene(new Scene(vBoxKitchen));
			stage.show();
			stage.toBack();
		}
	}
}

