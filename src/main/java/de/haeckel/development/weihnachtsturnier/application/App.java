package de.haeckel.development.weihnachtsturnier.application;

import de.haeckel.development.weihnachtsturnier.logic.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Timo Haeckel on 09.12.2016.
 */
public class App extends Application {


    private ArrayList<Player> players = new ArrayList<>();
    final static Logger LOGGER = LogManager.getFormatterLogger();

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("application.fxml"));
            LOGGER.info("App layout loaded from fxml");

            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("Weihnachtsturnier Creator");
            primaryStage.setScene(scene);

            primaryStage.show();
            LOGGER.info("App now running.");
        } catch (IOException e) {
            LOGGER.error("No fxml file found! @", e);
        }
    }

    public static void main (String [] args) {
        launch(args);
    }
}
