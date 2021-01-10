package io.nextree.javafx.starter;

import io.nextree.javafx.starter.angrycircle.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AngryCircleApp extends Application {
    //
    private static final String fxmlResourceUrl = "/fxml/AngryCircleMainView.fxml";
    private static final int windowWidth = 520;
    private static final int windowHeight = 580;
    private static final String title = "Angry Circle";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlResourceUrl));
        loader.setController(controller);

        Pane rootLayout = loader.load();
        Scene scene = new Scene(rootLayout, windowWidth, windowHeight);
        scene.getStylesheets().add("/css/layout.css");

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            if (Platform.isFxApplicationThread()) {
                primaryStage.close();
                Platform.exit();
            }

            System.exit(0);
        });

        primaryStage.show();
    }
}
