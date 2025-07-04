package pt.hugo.carmedia.ui.views;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import pt.hugo.carmedia.models.MediaPlayerManager;
import pt.hugo.carmedia.models.data.Media;

public class RootPane extends GridPane {
    Stage stage;
    MediaPlayerManager facade;
    TopView topView;
    BottomView bottomView;
    LeftView leftView;
    RightView rightView;
    CenterView centerView;



    public RootPane(MediaPlayerManager mediaPlayerManager, Stage stage) {
        this.stage = stage;
        this.facade = mediaPlayerManager;
        topView = new TopView();
        bottomView = new BottomView(facade);
        leftView = new LeftView(facade);
        rightView = new RightView();
        centerView = new CenterView(facade);

        update();
        createViews();
        registerHandlers();
    }

    private void registerHandlers() {

    }

    private void createViews() {
        this.setStyle("-fx-background-color: #121212");
        setupColumns();
        setupRows();
        this.add(topView,0,0,5,1); //col row colSpan rowSpan
        this.add(leftView,0,1,1,3);
        this.add(rightView,4,1,1,3);
        this.add(centerView,1,1,3,3);
        this.add(bottomView,0,4,5,1);

    }

    private void update() {

    }

    private void setupRows() {
        int[] rowHeights = {50, 200, 200, 100, 50}; // soma = 600
        for (int height : rowHeights) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(height);
            getRowConstraints().add(row);
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
