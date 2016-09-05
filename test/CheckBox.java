import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Marin on 8/24/2016.
 */
public class CheckBox {
    Button yes;
    Button no;
    Label text;
    static boolean answer;
    public void load () {
        yes = new Button("Yes");
        no = new Button("No");
        text = new Label("Do you want to save the changes");
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text , yes , no);
        yes.setOnAction(e -> {
            wind.close();
            answer = true;
        });
        no.setOnAction(e -> {
            wind.close();
            answer = false;
        });
        Scene scene = new Scene(vBox , 200 , 200);

        wind.setScene(scene);
        wind.setResizable(false);
        wind.setAlwaysOnTop(true);
        wind.showAndWait();
    }
}
