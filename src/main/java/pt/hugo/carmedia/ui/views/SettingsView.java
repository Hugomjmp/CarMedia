package pt.hugo.carmedia.ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pt.hugo.carmedia.ui.resources.ImageManager;

public class SettingsView extends VBox {
    Stage stage;
    Label version, title;
    Button cross;
    ImageView crossImg;


    public SettingsView(Stage settingsStage) {
        this.stage = settingsStage;

        loadImages();
        createViews();
        registerHandlers();
        update();
    }

    private void loadImages() {
        crossImg = new ImageView(ImageManager.getImage("x-solid_W.png"));
        crossImg.setFitWidth(25);
        crossImg.setFitHeight(25);
        crossImg.setPreserveRatio(true);
    }

    private void createViews() {
        this.setStyle("-fx-background-color: #121212");
        title = new Label("Settings");
        title.setFont(Font.font("Arial", FontWeight.BOLD,18));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(0,300,0,0));
        cross = new Button();
        cross.setGraphic(crossImg);
        cross.setBackground(Background.EMPTY);
        HBox header = new HBox();
        header.getChildren().addAll(title,cross);
        header.setAlignment(Pos.CENTER_RIGHT);
        header.setStyle("-fx-border-color: #FFFFFF");
        version = new Label("Version: 0.01");
        version.setFont(Font.font("Arial", FontWeight.BOLD,18));
        version.setTextFill(Color.WHITE);
        version.setPadding(new Insets(10,0,10,10));
        this.getChildren().addAll(header,version);
    }

    private void registerHandlers() {
        cross.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cross){
            stage.close();
        }
    }

    private void update() {

    }
}
