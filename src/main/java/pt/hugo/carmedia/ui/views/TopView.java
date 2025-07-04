package pt.hugo.carmedia.ui.views;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pt.hugo.carmedia.ui.resources.ImageManager;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TopView extends GridPane {
    ImageView fanSpeed, bluetooth, WIFI, cellService, gps, gearSettingsImg;
    Label temperature, fanStatus, time;
    Button gearSettings;
    SettingsView settingsView;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public TopView() {
        setupColumns();
        loadImages();
        update();
        createViews();
        registerHandlers();
    }



    private void loadImages() {
        fanSpeed = new ImageView(ImageManager.getImage("fan-solid_W.png"));
        fanSpeed.setFitWidth(25);
        fanSpeed.setPreserveRatio(true);
        bluetooth = new ImageView(ImageManager.getImage("bluetooth-b-brands-solid_off_W.png"));
        bluetooth.setFitWidth(25);
        bluetooth.setFitHeight(25);
        bluetooth.setPreserveRatio(true);
        WIFI = new ImageView(ImageManager.getImage("wifi-solid_none_W.png"));
        WIFI.setFitWidth(25);
        WIFI.setPreserveRatio(true);
        cellService = new ImageView(ImageManager.getImage("signal-solid_0bar_W.png"));
        cellService.setFitWidth(25);
        cellService.setPreserveRatio(true);
        gps = new ImageView(ImageManager.getImage("location-dot-solid_noLocation_W.png"));
        gps.setFitWidth(25);
        gps.setFitHeight(25);
        gps.setPreserveRatio(true);
        gearSettingsImg = new ImageView(ImageManager.getImage("gear-solid_W.png"));
        gearSettingsImg.setFitWidth(25);
        gearSettingsImg.setPreserveRatio(true);

    }

    private void createViews() {

        //this.setStyle("-fx-background-color: #FF0000;");
        temperature = new Label("24ºC");
        temperature.setFont(Font.font("Arial", FontWeight.BOLD,18));
        temperature.setTextFill(Color.WHITE);
        this.add(temperature,1,0); //col row colSpan rowSpan
        hbox1Config();
        hbox2Config();
        this.setAlignment(Pos.CENTER);

    }

    private void registerHandlers() {
        gearSettings.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == gearSettings){
            System.out.println("CLICK SOURCE");
            Stage settingsStage = new Stage();
            settingsView = new SettingsView(settingsStage);
            Scene scene = new Scene(settingsView,750,350);
            settingsStage.setScene(scene);
            settingsStage.initStyle(StageStyle.UNDECORATED);
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            settingsStage.setAlwaysOnTop(true);
            settingsStage.show();
            settingsStage.setResizable(false);
        }
    }

    private void update() {
        animateFan();
    }

    private void animateFan() {
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), fanSpeed);
        rotate.setByAngle(360); // roda 360º
        rotate.setCycleCount(RotateTransition.INDEFINITE); // repete para sempre
        rotate.setInterpolator(javafx.animation.Interpolator.LINEAR); // rotação suave
        rotate.play();
    }

    private void hbox1Config(){
        HBox hBoxCell2 = new HBox();
        fanStatus = new Label("ON");
        fanStatus.setFont(Font.font("Arial", FontWeight.BOLD,18));
        fanStatus.setPadding(new Insets(0,10,0,0));
        fanStatus.setTextFill(Color.WHITE);
        hBoxCell2.getChildren().addAll(fanStatus,fanSpeed);
        hBoxCell2.setFillHeight(true);
        hBoxCell2.setAlignment(Pos.CENTER_LEFT);
        this.add(hBoxCell2 ,2,0);
    }

    private void hbox2Config(){
        timeWatch();
        HBox hBoxCell3 = new HBox();
        HBox.setMargin(bluetooth, new Insets(0,5,0,0));
        HBox.setMargin(WIFI, new Insets(0,5,0,0));
        HBox.setMargin(cellService, new Insets(0,5,0,0));
        HBox.setMargin(gps, new Insets(0,5,0,0));
        gearSettings = new Button();
        gearSettings.setGraphic(gearSettingsImg);
        gearSettings.setBackground(Background.EMPTY);
        HBox.setMargin(gearSettings, new Insets(0,5,0,0));
        hBoxCell3.getChildren().addAll(bluetooth,WIFI,cellService,gps,gearSettings,time);
        hBoxCell3.setAlignment(Pos.CENTER_RIGHT);
        this.add(hBoxCell3,3,0);
    }
    private void timeWatch(){
        time = new Label("00:00");
        time.setFont(Font.font("Arial", FontWeight.BOLD,24));
        time.setTextFill(Color.WHITE);

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event ->{
            time.setText(LocalTime.now().format(formatter ));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
    private void setupColumns(){
        int[] colWidths = {50, 100, 412, 412, 50};
        for (int width : colWidths) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(width);
            getColumnConstraints().add(col);
        }
    }

}

