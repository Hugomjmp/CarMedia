package pt.hugo.carmedia.ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pt.hugo.carmedia.ui.resources.ImageManager;

public class AppsView extends VBox {
    Stage stage;
    HBox hboxTop,appsLine1, appsLine2;
    VBox app1, app2, app3;
    ImageView crossIcon, calculator, mapsIcon, cameraIcon;
    Button cross;
    Label title, calLabel, mapsLabel, cameraLabel;
    public AppsView(Stage stage) {
        this.stage = stage;

        createViews();
        registerHandlers();
        update();
    }

    private void update() {

    }

    private void registerHandlers() {
        app1.setOnMouseClicked(this::handleMouseClicked);
        app2.setOnMouseClicked(this::handleMouseClicked);
        app3.setOnMouseClicked(this::handleMouseClicked);
        cross.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == app1){
            System.out.println("Calculator Clicked!");
        } else if (mouseEvent.getSource() == app2) {
            System.out.println("Maps Clicked!");
        } else if (mouseEvent.getSource() == app3) {
            System.out.println("Camera Clicked!");
        } else if (mouseEvent.getSource() == cross) {
            stage.close();
        }
    }

    private void createViews() {
        this.setStyle(
                "-fx-background-color: #121212;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 5;"
        );

        loadImages();

        appsLine1 = new HBox();
        app1 = new VBox();
        app2 = new VBox();
        app3 = new VBox();
        hBoxTop();
        calLabel = new Label("Calculator");
        calLabel.setFont(Font.font("Arial", FontWeight.BOLD,12));
        calLabel.setTextFill(Color.WHITE);
        calLabel.setPadding(new Insets(10,0,0,0));
        mapsLabel = new Label("Maps");
        mapsLabel.setFont(Font.font("Arial", FontWeight.BOLD,12));
        mapsLabel.setTextFill(Color.WHITE);
        mapsLabel.setPadding(new Insets(10,0,0,0));
        cameraLabel = new Label("Camera");
        cameraLabel.setFont(Font.font("Arial", FontWeight.BOLD,12));
        cameraLabel.setTextFill(Color.WHITE);
        cameraLabel.setPadding(new Insets(10,0,0,0));
        app1.getChildren().addAll(calculator,calLabel);
        app1.setAlignment(Pos.CENTER);
        app1.setPadding(new Insets(0,10,0,0));
        app2.getChildren().addAll(mapsIcon,mapsLabel);
        app2.setAlignment(Pos.CENTER);
        app2.setPadding(new Insets(0,10,0,0));
        app3.getChildren().addAll(cameraIcon,cameraLabel);
        app3.setAlignment(Pos.CENTER);
        app3.setPadding(new Insets(0,10,0,0));
        //app1.setStyle("-fx-background-color: #FFFF00");
        //app2.setStyle("-fx-background-color: #00FFFF");
        appsLine1.getChildren().addAll(app1, app2, app3);
        appsLine1.setAlignment(Pos.CENTER_LEFT);
        appsLine1.setPadding(new Insets(10,10,0,10));
        appsLine2 = new HBox();
        //appsLine2.setStyle("-fx-background-color: #FFFF00");
        this.getChildren().addAll(hboxTop,appsLine1,appsLine2);

    }

    private void hBoxTop(){
        hboxTop = new HBox();
        title = new Label("Applications");
        title.setFont(Font.font("Arial", FontWeight.BOLD,18));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(0,215,0,0));
        cross = new Button();
        cross.setBackground(Background.EMPTY);
        cross.setGraphic(crossIcon);
        hboxTop.setPadding(new Insets(0,10,0,10));
        hboxTop.getChildren().addAll(title,cross);
        hboxTop.setAlignment(Pos.CENTER_RIGHT);
        //hboxTop.setStyle("-fx-border-color: #FFFFFF");
        //hboxTop.setStyle("-fx-background-color: #FF00FF");
    }



    private void loadImages(){
        calculator = new ImageView(ImageManager.getImage("calculator-solid_W.png"));
        calculator.setFitWidth(40);
        calculator.setFitHeight(40);
        calculator.setPreserveRatio(true);
        crossIcon = new ImageView(ImageManager.getImage("x-solid_W.png"));
        crossIcon.setFitWidth(25);
        crossIcon.setFitHeight(25);
        crossIcon.setPreserveRatio(true);
        mapsIcon = new ImageView(ImageManager.getImage("map-solid_W.png"));
        mapsIcon.setFitWidth(45);
        mapsIcon.setFitHeight(45);
        mapsIcon.setPreserveRatio(true);
        cameraIcon = new ImageView(ImageManager.getImage("camera-solid_W.png"));
        cameraIcon.setFitWidth(40);
        cameraIcon.setFitHeight(40);
        cameraIcon.setPreserveRatio(true);

    }
}
