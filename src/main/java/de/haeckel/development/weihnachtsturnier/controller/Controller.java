package de.haeckel.development.weihnachtsturnier.controller;


import com.sun.org.apache.bcel.internal.generic.NEW;
import de.haeckel.development.weihnachtsturnier.application.App;
import de.haeckel.development.weihnachtsturnier.logic.player.Gender;
import de.haeckel.development.weihnachtsturnier.logic.player.Group;
import de.haeckel.development.weihnachtsturnier.logic.player.Player;
import de.haeckel.development.weihnachtsturnier.popups.AlertBox;
import de.haeckel.development.weihnachtsturnier.popups.NewPlayerBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by Timo Haeckel on 09.12.2016.
 */
public class Controller {
    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView<Player> playerTable;

    //fields to add a player
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private ChoiceBox<Gender> genderField;
    @FXML private ChoiceBox<Group> groupField;
    @FXML private TextField handicapField;

    @FXML
    private Label status;

    private App app;
    private File writeFile;

    @FXML
    private void menuFileNewClicked(){
        System.out.println("menu file new clicked");
    }



    public void setApp(App app) {
        this.app = app;
    }

    public String getStatus() {
        return status.getText();
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    @FXML
    private void menuEditNewPlayer(ActionEvent actionEvent) {
        Player player = NewPlayerBox.display();
        if (player == null){
            return;
        }

        ObservableList<Player> data = playerTable.getItems();
        data.addAll(player);
    }

    @FXML
    private void openTable(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lade eine Tabelle");

        File file = fileChooser.showOpenDialog(new Stage());

        try {
            readFromFile(file);
        } catch (Exception e) {
        }
    }

    private void readFromFile (File file) throws IOException, ClassNotFoundException {
        ObservableList<Player> data = playerTable.getItems();
        data.clear();

        FileInputStream fout = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(fout);

        ArrayList<Player> players = (ArrayList<Player>)oos.readObject();

        oos.close();
        fout.close();

        for (Player p : players){
            data.add(p);
        }
    }

    private void writeToFile (File file) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        ObservableList<Player> data = playerTable.getItems();
        for (Player p : data){
            players.add(p);
        }

        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        oos.writeObject(players);

        oos.close();
        fout.close();
    }

    @FXML
    private boolean saveTable(){

        if(writeFile == null){
            writeFile = getWriteFile();
        }
        try {
            writeToFile(writeFile);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @FXML
    private void saveTableAs(){
        writeFile = null;
        saveTable();
    }

    private File getWriteFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle einen Speicherort");

        return fileChooser.showSaveDialog(new Stage());
    }


    public void newTable() {
        saveTable();
        playerTable.getItems().clear();
    }

    public void close() {
        while(true){
            saveTable();
        }
    }
}
