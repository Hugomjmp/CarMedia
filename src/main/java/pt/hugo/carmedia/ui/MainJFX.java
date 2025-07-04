package pt.hugo.carmedia.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.hugo.carmedia.models.MediaPlayerManager;
import pt.hugo.carmedia.models.data.Media;
import pt.hugo.carmedia.ui.views.RootPane;

public class MainJFX extends Application {
    MediaPlayerManager mediaPlayerManager;
    @Override
    public void start(Stage stage) throws Exception {
        mediaPlayerManager = new MediaPlayerManager();
        RootPane root = new RootPane(mediaPlayerManager, stage);
        Scene scene = new Scene(root,1024,600);
        stage.setScene(scene);
        stage.setTitle("CarMedia");
        stage.setResizable(false);
        stage.show();
    }
}
