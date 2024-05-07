package com.example.demo1;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;
    private Stage stage;
    private final Pane currentScene = new Pane();
    private Pane mainPage;
    private Pane notificationPage;
    private Pane mapPage;
    private Pane header;
    private NotificationController notificationController;
    private BinAppController binController;

    private SceneManager() {}

    public static SceneManager getInstance(){
        if(instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public void setStage(Stage stage, int height, int width) throws IOException{
        this.stage = stage;
        FXMLLoader headerLoader = new FXMLLoader(getClass().getResource("header.fxml"));
        header = headerLoader.load();
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        mainPage = mainLoader.load();
        binController = mainLoader.getController();
        FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("notificationPage.fxml"));
        notificationPage = notificationLoader.load();
        notificationController = notificationLoader.getController();
        FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("map.fxml"));
        mapPage = mapLoader.load();
        currentScene.getChildren().addAll(mainPage, header);
        stage.setScene(new Scene(currentScene, height, width));
        stage.show();
    }

    public void switchToMainPage() {
        currentScene.getChildren().clear();
        currentScene.getChildren().addAll(mainPage, header);
        stage.getScene().setRoot(currentScene);
    }

    public void switchToNotificationPage() {
        currentScene.getChildren().clear();
        currentScene.getChildren().addAll(notificationPage, header);
        stage.getScene().setRoot(currentScene);
    }

    public void switchToMapPage(){
        currentScene.getChildren().clear();
        currentScene.getChildren().addAll(mapPage, header);
        stage.getScene().setRoot(currentScene);
    }

    public void switchToStatsPage(){
        //TO-DO
    }

    public BinAppController getBinController(){
        return this.binController;
    }

    public NotificationController getNotificationController(){
        return this.notificationController;
    }
}
