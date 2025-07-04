package pt.hugo.carmedia.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import pt.hugo.carmedia.models.MediaPlayerManager;
import pt.hugo.carmedia.models.data.enums.PropertyChangeEnum;
import pt.hugo.carmedia.ui.resources.ImageManager;
import pt.hugo.carmedia.ui.resources.MediaManager;

public class BottomView extends GridPane {
    MediaPlayerManager facade;
    StackPane stackPane;
    ImageView volumeHigh, volumeMed, volumeLow, volumeNone, volumeMute;
    Slider slider;
    public BottomView(MediaPlayerManager facade) {
        this.facade = facade;
        this.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        setupColumns();
        loadImages();
        update();
        createViews();
        registerHandlers();
    }



    private void loadImages() {
        volumeHigh = new ImageView(ImageManager.getImage("volume-high-solid_W.png"));
        volumeHigh.setFitWidth(25);
        volumeHigh.setPreserveRatio(true);
        volumeLow = new ImageView(ImageManager.getImage("volume-low-solid_W.png"));
        volumeLow.setFitWidth(25);
        volumeLow.setPreserveRatio(true);
        volumeNone = new ImageView(ImageManager.getImage("volume-none-solid_W.png"));
        volumeNone.setFitWidth(25);
        volumeNone.setPreserveRatio(true);
        volumeMute = new ImageView(ImageManager.getImage("volume-xmark-solid_W.png"));
        volumeMute.setFitWidth(25);
        volumeMute.setPreserveRatio(true);
        volumeMute.setOpacity(0);
        volumeMed = new ImageView(ImageManager.getImage("volume-med-solid_W.png"));
        volumeMed.setFitWidth(25);
        volumeMed.setPreserveRatio(true);

    }

    private void createViews() {

        //this.setStyle("-fx-background-color: #FF0000;");
        HBox hBox = new HBox();
        stackPane = new StackPane();
        stackPane.getChildren().addAll(volumeHigh,volumeLow,volumeNone,volumeMute,volumeMed);
        slider = new Slider(0.0,1.0,1.0);
        slider.setOpacity(0);
        hBox.getChildren().addAll(stackPane,slider);
        hBox.setAlignment(Pos.CENTER_LEFT);
        this.add(hBox,1,0,2,1);//col row colSpan rowSpan
/*        this.add(stackPane,1,0);
        this.add(slider,2,0);*/
        //this.setAlignment(Pos.CENTER_LEFT);
    }

    private void registerHandlers() {
        listeners();
        slider.setOnMouseClicked(this::handleMouseClicked);
        volumeHigh.setOnMouseClicked(this::handleMouseClicked);
        volumeMed.setOnMouseClicked(this::handleMouseClicked);
        volumeLow.setOnMouseClicked(this::handleMouseClicked);
        volumeNone.setOnMouseClicked(this::handleMouseClicked);
        slider.setOnMouseDragged(this::handleMouseDragged);

    }

    private void handleMouseDragged(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == slider){
            System.out.println(slider.getValue());
            facade.setVolume(slider.getValue());
        }
    }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == slider){
            System.out.println(slider.getValue());
            facade.setVolume(slider.getValue());
        } else if (mouseEvent.getSource() == volumeHigh
                || mouseEvent.getSource() == volumeMed
                || mouseEvent.getSource() == volumeLow
                || mouseEvent.getSource() == volumeNone) {
            if (slider.getOpacity() == 0)
                slider.setOpacity(1);
            else
                slider.setOpacity(0);
        }
    }

    private void listeners() {
        facade.addPropertyChangeListener(PropertyChangeEnum.VOLUME.name(), evt -> {update();});
    }

    private void update() {
        System.out.println("bot update chamado");
        if (facade.getVolume() >= 0.66){
            volumeHigh.setOpacity(1);
            volumeMed.setOpacity(0);
            volumeLow.setOpacity(0);
            volumeNone.setOpacity(0);
        } else if (facade.getVolume() >= 0.33 && facade.getVolume() < 0.66) {
            volumeHigh.setOpacity(0);
            volumeMed.setOpacity(1);
            volumeLow.setOpacity(0);
            volumeNone.setOpacity(0);
        } else if (facade.getVolume() > 0 &&facade.getVolume() < 0.33) {
            volumeHigh.setOpacity(0);
            volumeMed.setOpacity(0);
            volumeLow.setOpacity(1);
            volumeNone.setOpacity(0);
        } else if (facade.getVolume() == 0) {
            volumeHigh.setOpacity(0);
            volumeMed.setOpacity(0);
            volumeLow.setOpacity(0);
            volumeNone.setOpacity(1);
        }
    }

    private void setupColumns(){
        int[] colWidths = {50, 100, 412, 412, 50}; // soma = 1024
        for (int width : colWidths) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(width);
            getColumnConstraints().add(col);
        }
    }
}
