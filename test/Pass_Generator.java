import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by Marin on 8/24/2016.
 */
public class Pass_Generator extends AboutTheApp {
    static String item;
    static String pass;
    Random random = new Random();
    public char[] characters= {'A',	'B'	,'C',	'D',	'E',	'F'	,'G',	'H',	'I',	'J',	'K',
            'L',	'M',	'N',	'O',	'P',	'Q',
            'R',	'S',	'T',	'U',	'V',	'W',	'X',	'Y',	'Z',
            'a',	'b',	'c',	'd',	'e',	'f',	'g',	'h',	'i',	'j',	'k',	'l',	'm'
            ,'n',	'o',	'p',	'q',	'r',	's',	't',	'u',	'v',	'w',	'x',	'y',	'z',
            '1',	'2',	'3',	'4',	'5',	'6',	'7',	'8',	'9'};

    @Override
    public void load () {
        Button button = new Button("Generate");
        Label label = new Label();
        text = new Label();
        Stage wind = new Stage();
        wind.setTitle("Password Generator");
        VBox vBox = new VBox(10);
        ChoiceBox<String> mode = new ChoiceBox<String>();
        String a = "Normal" , b = "Secure" , c = "Very Secure" , d = "Unbreakable";
        mode.getItems().addAll(a , b , c, d);

        mode.setOnAction(e-> {
            item = mode.getSelectionModel().getSelectedItem();
        });

        button.setOnAction( e -> {
            if (item == a) {
                pass = generate_pass(5);
            } else if (item == b) {
                pass = generate_pass(10);
            } else if (item == c) {
                pass = generate_pass(15);
            } else if (item == d) {
                pass = generate_pass(20);
            }
            label.setText("Password Generated : \n"+ pass);
        });

        vBox.getChildren().addAll(text , mode , button , label);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox , 300 , 200);
        wind.setScene(scene);
        wind.show();
    }
    public String generate_pass (int num) {
        String rand_pass = "";

        for (int i = 0 ; i < num ; i ++) {
            int value = random.nextInt(61);
            rand_pass += characters[value];

        }
        return rand_pass;
    }

}
