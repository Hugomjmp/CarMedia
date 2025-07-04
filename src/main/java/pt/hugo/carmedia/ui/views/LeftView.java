package pt.hugo.carmedia.ui.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.hugo.carmedia.models.MediaPlayerManager;
import pt.hugo.carmedia.ui.resources.ImageManager;



public class LeftView extends GridPane {
    MediaPlayerManager facade;
    ImageView sourceIcon, navigation, fanSpeed;
    Button source;
    SourceView sourceView;
    public LeftView(MediaPlayerManager facade) {
        this.facade = facade;
        setupRows();
        loadImages();
        update();
        createViews();
        registerHandlers();
    }

    private void loadImages() {
        sourceIcon = new ImageView(ImageManager.getImage("music-solid_W.png"));
        sourceIcon.setFitWidth(25);
        sourceIcon.setPreserveRatio(true);
        navigation = new ImageView(ImageManager.getImage("location-arrow-solid_W.png"));
        navigation.setFitWidth(25);
        navigation.setPreserveRatio(true);
        fanSpeed = new ImageView(ImageManager.getImage("fan-solid_W.png"));
        fanSpeed.setFitWidth(25);
        fanSpeed.setPreserveRatio(true);
    }

    private void createViews() {

        //this.setStyle("-fx-background-color: #FF00FF;");
        source = new Button();
        source.setBackground(Background.EMPTY);
        source.setGraphic(sourceIcon);
        this.add(source,0,0);
        this.add(navigation,0,1);
        this.add(fanSpeed,0,2);
        this.setAlignment(Pos.CENTER);
    }

    private void registerHandlers() {
        source.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseClicked(MouseEvent event){
        if (event.getSource() == source){
            System.out.println("CLICK SOURCE");
            Stage sourceStage = new Stage();
            sourceView = new SourceView(sourceStage, facade);
            Scene scene = new Scene(sourceView,300,350);
            sourceStage.setScene(scene);
            sourceStage.initStyle(StageStyle.UNDECORATED);
            sourceStage.initModality(Modality.APPLICATION_MODAL);
            sourceStage.setAlwaysOnTop(true);
            sourceStage.show();
            sourceStage.setResizable(false);
        }
    }

    private void update() {

    }

    private void setupRows() {
        int[] rowHeights = { 200, 200, 100};
        for (int height : rowHeights) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(height);
            getRowConstraints().add(row);
        }
    }


}
