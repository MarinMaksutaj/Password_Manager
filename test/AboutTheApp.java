import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Marin on 8/24/2016.
 */
public class AboutTheApp {
    Label text;
    public void load () {

        text = new Label("This app is designed to keep\n your most important account\n information safe and never forget about them");
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text);

        Scene scene = new Scene(vBox , 300 , 200);

        wind.setScene(scene);
        wind.setResizable(false);
        wind.setAlwaysOnTop(true);
        wind.showAndWait();
    }
}
