package de.haeckel.development.weihnachtsturnier.popups;

import de.haeckel.development.weihnachtsturnier.logic.player.Gender;
import de.haeckel.development.weihnachtsturnier.logic.player.Group;
import de.haeckel.development.weihnachtsturnier.logic.player.Player;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Timo Haeckel on 09.12.2016.
 */
public class NewPlayerBox {
    public static Player display(){
        final boolean[] submit = new boolean[1];
        Stage window = new Stage ();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Spieler anlegen");
        window.setMinWidth(250);

        Label label = new Label("Bitte geben sie die Daten des Spielers ein.");

        Label firstNameLabel = new Label("Vorname:");
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(90);
        HBox firstNameBox = new HBox();
        firstNameBox.getChildren().addAll(firstNameLabel,firstNameField);

        Label lastNameLabel = new Label("Nachname:");
        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(90);
        HBox lastNameBox = new HBox();
        lastNameBox.getChildren().addAll(lastNameLabel,lastNameField);

        Label handicapLabel = new Label("Vorgabe:");
        TextField handicapField = new TextField();
        handicapField.setPrefWidth(90);
        HBox handicapBox = new HBox();
        handicapBox.getChildren().addAll(handicapLabel,handicapField);

        Label groupLabel = new Label("Gruppe:");
        ChoiceBox<Group> groupChoiceBox = new ChoiceBox<>();
        groupChoiceBox.setValue(Group.UNKNOWN);
        groupChoiceBox.getItems().addAll(Group.E,Group.J);
        HBox groupBox = new HBox();
        groupBox.getChildren().addAll(groupLabel,groupChoiceBox);

        Label genderLabel = new Label("Geschlecht:");
        ChoiceBox<Gender> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.setValue(Gender.UNKNOWN);
        genderChoiceBox.getItems().addAll(Gender.M,Gender.W);
        HBox genderBox = new HBox();
        genderBox.getChildren().addAll(genderLabel,genderChoiceBox);

        Button closeButton = new Button("Schließen");
        closeButton.setOnAction(e -> {window.close();
            submit[0] = false;});

        Button submitButton = new Button("Hinzufügen");
        submitButton.setOnAction(e -> {window.close();
            submit[0] = true;});

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(submitButton,closeButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,firstNameBox, lastNameBox, genderBox, groupBox, handicapBox, buttonBox );
        layout.setAlignment(Pos.CENTER_LEFT);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        if(submit[0] == false){
            return null;
        }

        Player player = new Player();

        //get entered values
        String firstName = firstNameField.getText();
        if(firstName.equals("")){
            AlertBox.display("Missing Parameter", "Kein Vorname eingegeben");
            return null;
        }
        player.setFirstName(firstName);

        String lastName = lastNameField.getText();
        if(lastName.equals("")){
            AlertBox.display("Missing Parameter", "Kein Nachname eingegeben");
            return null;
        }
        player.setLastName(lastName);

        int handicap;
        try{
            handicap = Integer.parseInt(handicapField.getText());
        }catch (NumberFormatException e){
            handicap = 0;
        }
        player.setHandicap(handicap);

        Group group;
        try{
            group = groupChoiceBox.getValue();
        }catch (ClassCastException e){
            group = Group.UNKNOWN;
        }
        player.setGroup(group);

        Gender gender;
        try{
            gender = genderChoiceBox.getValue();
        }catch (ClassCastException e){
            gender = Gender.UNKNOWN;
        }
        player.setGender(gender);

        return player;
    }

}
