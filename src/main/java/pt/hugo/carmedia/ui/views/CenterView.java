package pt.hugo.carmedia.ui.views;

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import pt.hugo.carmedia.models.MediaPlayerManager;
import pt.hugo.carmedia.models.data.enums.PropertyChangeEnum;
import pt.hugo.carmedia.models.data.enums.SourceType;
import pt.hugo.carmedia.ui.resources.ImageManager;
import java.io.ByteArrayInputStream;


public class CenterView extends GridPane {
    MediaPlayerManager facade;
    SourceType sourceType;
    ImageView musicImage, previousSongIcon, playSongIcon, pauseSongIcon, nextSongIcon, repeat;
    Button playSong, pauseSong, nextSong, previousSong;
    Label songName, currentSongTime, finalSongTime;
    ProgressBar songProcessbar;
    HBox hBox, hboxButtons;
    public CenterView(MediaPlayerManager mediaPlayerManager) {
        this.facade = mediaPlayerManager;
        this.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        createViews();
        update();
        registerHandlers();
    }

    private void createViews() {
        //this.setStyle("-fx-background-color: #00FF00");
        setupColumns();
        setupRows();
        loadImages();
        vboxLeftConfig();
        vboxRightConfig();

        this.setAlignment(Pos.CENTER);
    }

    private void registerHandlers() {
        listeners();
        previousSong.setOnMouseClicked(this::handleMouseClicked);
        playSong.setOnMouseClicked(this::handleMouseClicked);
        pauseSong.setOnMouseClicked(this::handleMouseClicked);
        nextSong.setOnMouseClicked(this::handleMouseClicked);
        repeat.setOnMouseClicked(this::handleMouseClicked);
    }

    private void update() {
        System.out.println("update chamado");
        loadSongImage();

        if (songName!= null){
            switch (facade.getSourceType()){
                case Local_Music -> {
                    songName.setText(facade.getMetadata().getTitle() + " - " + facade.getMetadata().getArtistName());
                    repeat.setDisable(false);
                    repeat.setOpacity(100);
                }
                case Internet_Radio -> {
                    songName.setText(facade.getStationMetaData().getName());
                    repeat.setDisable(true);
                    repeat.setOpacity(0);
                }
            }

            finalSongTime.setText(facade.getSongDuration());
        }
        if (facade.getIsPlaying()){
            playSong.setDisable(true);
            playSong.setOpacity(0);
            pauseSong.setDisable(false);
            pauseSong.setOpacity(100);
        } else {
            playSong.setDisable(false);
            playSong.setOpacity(100);
            pauseSong.setDisable(true);
            pauseSong.setOpacity(0);
        }
    }

    private void handleMouseClicked(MouseEvent event){
        if(event.getSource() == playSong){
            facade.play();
        } else if (event.getSource() == nextSong) {
            facade.nextSong();
        } else if (event.getSource() == previousSong) {
            facade.previousSong();
        } else if (event.getSource() == pauseSong) {
            facade.pause();
        } else if (event.getSource() == repeat){
            System.out.println("REPEAT CLICKED");
        }


    }
    private void listeners(){
        facade.addPropertyChangeListener(PropertyChangeEnum.PLAY.name(), evt -> {update();});
        facade.addPropertyChangeListener(PropertyChangeEnum.PAUSE.name(), evt -> {update();});
        facade.addPropertyChangeListener(PropertyChangeEnum.STOP.name(), evt -> {update();});
        facade.addPropertyChangeListener(PropertyChangeEnum.NEXT_SONG.name(), evt -> {update();});
        facade.addPropertyChangeListener(PropertyChangeEnum.PREVIOUS_SONG.name(), evt -> {update();});
        facade.addPropertyChangeListener(PropertyChangeEnum.TIME_PROPERTY.name(), evt -> {
            double seconds = (double) evt.getNewValue();
            double totalSeconds = facade.getMetadata().getTrackLength();
            double progress = seconds / totalSeconds;

            Platform.runLater(() -> {
                songProcessbar.setProgress(progress);
                currentSongTime.setText(formatTime(seconds));
            });
        });


    }

    private String formatTime(double totalSeconds) {
        int minutes = (int) totalSeconds / 60;
        int seconds = (int) totalSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    private void loadImages() {
        previousSongIcon = new ImageView(ImageManager.getImage("backward-step-solid_W.png"));
        previousSongIcon.setFitWidth(25);
        previousSongIcon.setFitHeight(25);
        previousSongIcon.setPreserveRatio(true);
        playSongIcon = new ImageView(ImageManager.getImage("play-solid_W.png"));
        playSongIcon.setFitWidth(25);
        playSongIcon.setFitHeight(25);
        playSongIcon.setPreserveRatio(true);
        pauseSongIcon = new ImageView(ImageManager.getImage("pause-solid_W.png"));
        pauseSongIcon.setFitWidth(25);
        pauseSongIcon.setFitHeight(25);
        pauseSongIcon.setPreserveRatio(true);
        nextSongIcon = new ImageView(ImageManager.getImage("forward-step-solid_W.png"));
        nextSongIcon.setFitWidth(25);
        nextSongIcon.setFitHeight(25);
        nextSongIcon.setPreserveRatio(true);
        repeat = new ImageView(ImageManager.getImage("repeat-solid_W.png"));
        repeat.setFitWidth(25);
        repeat.setFitHeight(25);
        repeat.setPreserveRatio(true);
    }
    private void vboxLeftConfig(){
        VBox vBox = new VBox();
        songName = new Label();
        songName.setFont(Font.font("Arial", FontWeight.BOLD,18));
        songName.setTextFill(Color.WHITE);
        songName.setPadding(new Insets(0,0,50,0));
        songProcessbar = new ProgressBar(0);
        songProcessbar.setPrefHeight(12);
        songProcessbar.setPadding(new Insets(0,50,0,50));
        songProcessbar.setMaxWidth(450);


        hboxSongTime();
        hboxSongButtons();
        musicImage = new ImageView();
        musicImage.setFitWidth(200);
        musicImage.setFitHeight(200);
        //musicImage.setPreserveRatio(true);
        VBox.setMargin(musicImage, new Insets(0,0,40,0));
        vBox.getChildren().addAll(songName,musicImage, hBox, songProcessbar, hboxButtons);
        vBox.setAlignment(Pos.CENTER);
        //vBox.setStyle("-fx-background-color: #FF0000");
        this.add(vBox,0,0,1,3); //col row colSpan rowSpan
    }

    private void hboxSongTime(){
        hBox = new HBox();
        hBox.setPadding(new Insets(0,80,0,80));
        currentSongTime = new Label("0:00");
        currentSongTime.setFont(Font.font("Arial", FontWeight.BOLD,14));
        currentSongTime.setTextFill(Color.WHITE);
        finalSongTime = new Label();
        finalSongTime.setFont(Font.font("Arial", FontWeight.BOLD,14));
        finalSongTime.setTextFill(Color.WHITE);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hBox.getChildren().addAll(currentSongTime,spacer,finalSongTime);
        hBox.setAlignment(Pos.CENTER);
    }
    private void hboxSongButtons(){
        hboxButtons = new HBox();
        playSong = new Button();
        playSong.setGraphic(playSongIcon);
        playSong.setBackground(Background.EMPTY);
        pauseSong = new Button();
        pauseSong.setGraphic(pauseSongIcon);
        pauseSong.setBackground(Background.EMPTY);
        pauseSong.setDisable(true);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(playSong,pauseSong);
        hboxButtons.setPadding(new Insets(20,0,0,0));
        previousSong = new Button();
        previousSong.setGraphic(previousSongIcon);
        previousSong.setBackground(Background.EMPTY);
        nextSong = new Button();
        nextSong.setGraphic(nextSongIcon);
        nextSong.setBackground(Background.EMPTY);
        HBox.setMargin(previousSong, new Insets(0,50,0,0));
        HBox.setMargin(stackPane, new Insets(0,50,0,0));
        HBox.setMargin(nextSong, new Insets(0,50,0,0));
        HBox.setMargin(repeat, new Insets(0,0,0,0));
        hboxButtons.getChildren().addAll(previousSong,stackPane,nextSong,repeat);
        hboxButtons.setAlignment(Pos.CENTER);
    }

    private void vboxRightConfig(){
        VBox vBox = new VBox();

/*        MapView mapView = new MapView();
        mapView.setZoom(5.0);
        mapView.flyTo(0, new MapPoint(40.201192, -8.394854), 1.0); // coimbra


        vBox.getChildren().add(mapView);*/

/*        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setUserAgent("Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36");


        // Exemplo: abre Waze com um destino (coordenadas)
        //String wazeUrl = "https://www.waze.com/ul?ll=40.201192,-8.394854&navigate=yes";
        //String wazeUrl = "https://www.waze.com/pt-PT/live-map";
        String wazeUrl = "https://m.youtube.com";

        webEngine.load(wazeUrl);

        vBox.getChildren().add(webView);*/

        this.add(vBox,1,0,1,3); //col row colSpan rowSpan
    }

    private void loadSongImage(){
        switch (facade.getSourceType()){
            case Local_Music -> {
                byte[] imageData = facade.getMetadata().getAlbumArtWork();
                if (imageData != null && imageData.length > 0){
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    musicImage.setImage(image);
                }else {
                    musicImage.setImage(ImageManager.getImage("defaultMusic.png"));
                }
            }
            case Internet_Radio -> {

                if (facade.getStationMetaData().getFavicon() != null && !facade.getStationMetaData().getFavicon().isEmpty()){
                    try {
                        Image image = new Image(facade.getStationMetaData().getFavicon(),true);
                        musicImage.setImage(image);
                    } catch (Exception e) {
                        musicImage.setImage(ImageManager.getImage("defaultMusic.png"));
                    }

                }else {
                    musicImage.setImage(ImageManager.getImage("defaultMusic.png"));
                }
            }
        }
    }

    private void setupColumns(){
        int[] colWidths = {/*100,*/ 412, 412}; // soma = 100,412,412
        for (int width : colWidths) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(width);
            getColumnConstraints().add(col);
        }
    }

    private void setupRows() {
        int[] rowHeights = {200, 200, 100}; // soma = 600
        for (int height : rowHeights) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(height);
            getRowConstraints().add(row);
        }
    }

}
