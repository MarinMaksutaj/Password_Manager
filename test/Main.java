import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {
    javafx.scene.control.TableView<Pass_Manager> table;
    AboutTheApp ata = new AboutTheApp();
    Pass_Generator pass_gen = new Pass_Generator();
    Stage window;
    Button add;
    Button remove;
    TextField email;
    TextField pass;
    TextField note;
    CheckBox check  = new CheckBox();
    TableColumn<Pass_Manager , String> emaill;
    ArrayList<String> SavestoRemove = new ArrayList<String>();
    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Bottom elements*/
        email = new TextField("Email");
        pass = new TextField("Password");
        note = new TextField("Note");
        add = new Button("Add");
        remove = new Button ("Remove");
        // Breaks here
        window = primaryStage;
        BorderPane pane = new BorderPane();
        Menu open = new Menu("Advanced");
        MenuItem aboutapp = new MenuItem("About App...");
        MenuItem rand_pass_generator = new MenuItem("Password Generator...");
        aboutapp.setOnAction(e -> ata.load());
        rand_pass_generator.setOnAction(e -> pass_gen.load());
        open.getItems().addAll(aboutapp , rand_pass_generator);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(open);
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10 , 10 , 10 ,10));
        hBox.getChildren().addAll(email , pass , note , add , remove);
        add.setOnAction(e-> add());
        remove.setOnAction(e-> remove());
        //table
        table = new TableView<>();

        // emails
         emaill= new TableColumn<>("Emails");
        emaill.setMinWidth(200);
        emaill.setCellValueFactory(new PropertyValueFactory<Pass_Manager , String>("email"));


        // passwords
        TableColumn<Pass_Manager , String> pass = new TableColumn<>("Passwords");
        pass.setMinWidth(200);
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));

        // note
        TableColumn<Pass_Manager , String> note = new TableColumn<>("Notes");
        note.setMinWidth(200);
        note.setCellValueFactory(new PropertyValueFactory<>("note"));

        //adds the columns to the ui
        table.getColumns().addAll(emaill , pass , note);
        //chose the selection model
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //sets the items
        table.setItems(getProducts());


        pane.setTop(menuBar);
        pane.setCenter(table);
        pane.setBottom(hBox);
        Scene scene = new Scene(pane , 602 , 500);
        scene.getStylesheets().add("styling.css");
        window.setScene(scene);
        window.setMinWidth(602);
        window.setMinHeight(500);
        window.show();
        window.setOnCloseRequest(e -> closeWind(e) );
    }

    private void closeWind(WindowEvent e) {
        check.load();

        e.consume();
        if (check.answer) {
            try {
                if (makeChanges()) {
                    System.out.println("Success");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            window.close();
        } else {
            window.close();
        }
    }

    private boolean makeChanges() throws IOException {
        if (SavestoRemove.isEmpty()) {
            return true;
        }
        File inputFile = new File("Emails");
        File tempFile = new File("EmailsTemp");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        // needs construction
        ArrayList<String> removerList = SavestoRemove;
        String currentLIne;
        while ((currentLIne = reader.readLine()) != null) {
            String trimmedLine = currentLIne.trim();
            boolean verify = false;
            for (String s : removerList) {
                if (trimmedLine.equals(s.trim())) {
                    verify = true;
                }

            }
            if (verify == false) {
                writer.write(currentLIne + System.lineSeparator());
            }

        }
        writer.close();
        reader.close();
        PrintWriter writer3 = new PrintWriter(inputFile);
        writer3.print("");
        writer3.close();
        //boolean successful = tempFile.renameTo(inputFile);
        //return successful;

        BufferedReader reader1 = new BufferedReader(new FileReader(tempFile));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(inputFile));

        String currentLineToCopy;
        while ( (currentLineToCopy = reader1.readLine()) != null) {
            writer1.write(currentLineToCopy + "\n");
        }

        writer1.close();
        reader1.close();

        PrintWriter writer2 = new PrintWriter(tempFile);
        writer2.print("");
        writer2.close();

        return true;
    }

    private void remove() {
        ObservableList<Pass_Manager> selected , all;
        selected = table.getSelectionModel().getSelectedItems();
        all = table.getItems();

        for (Pass_Manager p : selected) {
            all.remove(p);
            SavestoRemove.add(p.getEmail().trim() + " " + p.getPassword().trim() + " " + p.getNote().trim() + "\n");
        }
        // do a test here
    }

    private void add() {
        Pass_Manager passwrd = new Pass_Manager();
        if (email.getText() == "" && pass.getText() == "" && note.getText() == "") {
            return;
        }
        passwrd.setEmail(email.getText());
        passwrd.setPassword(pass.getText());
        passwrd.setNote(note.getText());
        table.getItems().add(passwrd);
        try {
            FileWriter filewr = new FileWriter("Emails" , true);
            filewr.write(email.getText() + " " + pass.getText() + " " + note.getText() + "\n");
            filewr.close();
            System.out.println("Writed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        email.setText("Email");
        pass.setText("Password");
        note.setText("Note");
        }
        public ObservableList<Pass_Manager> getProducts() {
        ObservableList<Pass_Manager> products = FXCollections.observableArrayList();
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("Emails");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String email = "", pass = "", note = "";
                int a = 0;
                for (int i = 0 ; i < line.length(); i++) {
                    if (Character.isWhitespace(line.charAt(i))) {
                        a++;
                    }
                    if (a == 0) {
                        email += line.charAt(i);
                    } else if (a == 1) {
                        pass += line.charAt(i);
                    } else if (a == 2) {
                        note += line.charAt(i);
                    }
                }
                products.add(new Pass_Manager(email , pass , note));


            }

            // Always close files.
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
