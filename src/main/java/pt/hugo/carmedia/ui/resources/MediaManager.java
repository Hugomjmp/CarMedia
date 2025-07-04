package pt.hugo.carmedia.ui.resources;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import pt.hugo.carmedia.models.data.interfaces.TimeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaManager {

    private MediaManager() {}

    private static MediaPlayer mediaPlayer;
    private static File file;
    private static final List<TimeListener> timeListeners = new ArrayList<>();
    private static Runnable onSongEndCallback;

    public static void addTimeListener(TimeListener listener) {
        timeListeners.add(listener);
    }

    public static void removeTimeListener(TimeListener listener) {
        timeListeners.remove(listener);
    }

    public static boolean play(String filename){
        try {
            file = new File(filename);
            Media music = new Media(file.toURI().toString());
            stop();
            mediaPlayer = new MediaPlayer(music);
            mediaPlayer.setStartTime(Duration.ZERO);
            mediaPlayer.setStopTime(music.getDuration());
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                double seconds = t1.toSeconds();
                for (TimeListener listener : timeListeners){
                    listener.onTimeUpdate(seconds);
                }
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                if (onSongEndCallback != null) {
                    onSongEndCallback.run();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean playRadio(String filename){
        try {

            Media music = new Media(filename);
            stop();
            mediaPlayer = new MediaPlayer(music);
            mediaPlayer.setStartTime(Duration.ZERO);
            mediaPlayer.setStopTime(music.getDuration());
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                double seconds = t1.toSeconds();
                for (TimeListener listener : timeListeners){
                    listener.onTimeUpdate(seconds);
                }
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                if (onSongEndCallback != null) {
                    onSongEndCallback.run();
                }
            });
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static void pause(){
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            mediaPlayer.pause();
    }

    public static Double getVolume(){
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            return mediaPlayer.getVolume();
        return 100.0;
    }

    public static void setVolume(double volume){
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            mediaPlayer.setVolume(volume);
    }

    public static void setOnSongEnd(Runnable callback){
        onSongEndCallback = callback;
    }

    public static boolean isPlaying(){
        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
    public static void stop(){
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            mediaPlayer.stop();
    }
}
