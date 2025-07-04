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
import pt.hugo.carmedia.models.MediaPlayerManager;
import pt.hugo.carmedia.models.data.enums.SourceType;
import pt.hugo.carmedia.ui.resources.ImageManager;

public class SourceView extends VBox {
    Stage stage;
    MediaPlayerManager facade;
    HBox hBoxTop, hBox1, hBox2, hBox3, hBox4;
    Label title, radioInternet,radio, localMusic;
    ImageView crossIcon, radioImg, radioInternetImg ,soundNote;
    Button cross;
    public SourceView(Stage sourceStage, MediaPlayerManager facade){
        this.stage = sourceStage;
        this.facade = facade;
        createViews();
        registerHandlers();
        update();
    }

    private void update() {

    }

    private void registerHandlers() {
        cross.setOnMouseClicked(this::handleMouseClicked);
        hBox1.setOnMouseClicked(this::handleMouseClicked);
        hBox2.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cross){
            stage.close();
        }else if (mouseEvent.getSource() == hBox1){
            facade.setSourceType(SourceType.Internet_Radio);
            stage.close();
        } else if (mouseEvent.getSource() == hBox2) {
            facade.setSourceType(SourceType.Local_Music);
            stage.close();
        }
    }

    private void createViews() {
        this.setStyle("-fx-background-color: #121212");
        hboxTop();
        hboxOption1();
        hboxOption2();
        this.getChildren().addAll(hBoxTop,hBox1,hBox2);
        this.setFillWidth(true);
        //this.setStyle("-fx-border-color: #FFFFFF");
        //vBox.setAlignment(Pos.CENTER);


    }
    private void hboxTop(){
        hBoxTop = new HBox();
        title = new Label("Source Selection");
        title.setFont(Font.font("Arial", FontWeight.BOLD,18));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(0,50,0,0));
        crossIcon = new ImageView(ImageManager.getImage("x-solid_W.png"));
        crossIcon.setFitWidth(25);
        crossIcon.setFitHeight(25);
        crossIcon.setPreserveRatio(true);
        cross = new Button();
        cross.setGraphic(crossIcon);
        cross.setBackground(Background.EMPTY);
        hBoxTop.setPadding(new Insets(0,10,0,10));
        hBoxTop.getChildren().addAll(title,cross);
        hBoxTop.setAlignment(Pos.CENTER_RIGHT);
        hBoxTop.setStyle("-fx-border-color: #FFFFFF");
    }
    private void hboxOption1(){
        hBox1 = new HBox();
        radioInternetImg = new ImageView(ImageManager.getImage("dark_logo.png"));
        radioInternetImg.setFitWidth(200);
        radioInternetImg.setFitHeight(60);
        radioInternetImg.setPreserveRatio(true);
        radioInternet = new Label("Internet Radio");
        radioInternet.setFont(Font.font("Arial", FontWeight.BOLD,18));
        radioInternet.setTextFill(Color.WHITE);
        radioInternet.setPadding(new Insets(0,0,0,10));
        hBox1.setPadding(new Insets(0,0,0,10));
        hBox1.getChildren().addAll(radioInternetImg, radioInternet);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.setStyle("-fx-border-color: #FFFFFF");
    }
    private void hboxOption2(){
        hBox2 = new HBox();
        soundNote = new ImageView(ImageManager.getImage("music-solid_W.png"));
        soundNote.setFitWidth(200);
        soundNote.setFitHeight(60);
        soundNote.setPreserveRatio(true);
        localMusic = new Label("Local Music");
        localMusic.setFont(Font.font("Arial", FontWeight.BOLD,18));
        localMusic.setTextFill(Color.WHITE);
        localMusic.setPadding(new Insets(0,0,0,10));
        hBox2.setPadding(new Insets(0,0,0,10));
        hBox2.getChildren().addAll(soundNote, localMusic);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setStyle("-fx-border-color: #FFFFFF");
    }
    private void hboxOption3(){}
    private void hboxOption4(){}

}
