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

public class RightView extends GridPane {
    AppsView appsView;
    ImageView phone, appsIcon, car;
    Button apps;
    public RightView() {
            setupRows();
            loadImages();
            update();
            createViews();
            registerHandlers();
        }

        private void loadImages() {
            phone = new ImageView(ImageManager.getImage("phone-solid_W.png"));
            phone.setFitWidth(25);
            phone.setPreserveRatio(true);
            appsIcon = new ImageView(ImageManager.getImage("grip-solid_W.png"));
            appsIcon.setFitWidth(25);
            appsIcon.setPreserveRatio(true);
            car = new ImageView(ImageManager.getImage("car-solid_W.png"));
            car.setFitWidth(25);
            car.setPreserveRatio(true);
        }

        private void createViews() {

            //this.setStyle("-fx-background-color: #FF0000;");
            apps = new Button();
            apps.setBackground(Background.EMPTY);
            apps.setGraphic(appsIcon);
            this.add(phone,0,0);
            this.add(apps,0,1);
            this.add(car,0,2);
            this.setAlignment(Pos.CENTER);
        }

        private void registerHandlers() {
            apps.setOnMouseClicked(this::handleMouseClicked);
        }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == apps){
            System.out.println("APPS CLICKED");
            Stage appsStage = new Stage();
            appsView = new AppsView(appsStage);
            Scene scene = new Scene(appsView,600,400);
            appsStage.setScene(scene);
            appsStage.initStyle(StageStyle.UNDECORATED);
            appsStage.initModality(Modality.APPLICATION_MODAL);
            appsStage.setAlwaysOnTop(true);
            appsStage.show();
            appsStage.setResizable(false);
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
