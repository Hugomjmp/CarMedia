package pt.hugo.carmedia.models;

import pt.hugo.carmedia.models.data.InternetRadio;
import pt.hugo.carmedia.models.data.Media;
import pt.hugo.carmedia.models.data.enums.PropertyChangeEnum;
import pt.hugo.carmedia.models.data.enums.SourceType;
import pt.hugo.carmedia.models.data.music.Song;
import pt.hugo.carmedia.models.data.radio.Station;
import pt.hugo.carmedia.models.data.system.SystemData;
import pt.hugo.carmedia.ui.resources.MediaManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MediaPlayerManager {
    private SourceType sourceType;
    Media media;
    InternetRadio internetRadio;
    PropertyChangeSupport pcs;
    private boolean isPlaying = false;

    public MediaPlayerManager() {
        media = new Media();
        internetRadio = new InternetRadio();
        this.sourceType = SourceType.Local_Music;
        this.pcs = new PropertyChangeSupport(this);
        MediaManager.addTimeListener(seconds -> {
            pcs.firePropertyChange(PropertyChangeEnum.TIME_PROPERTY.name(), null, seconds);
        });
        MediaManager.setOnSongEnd(() -> {
            nextSong();
        });
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public boolean play(){
        switch (sourceType){
            case Local_Music -> {
                if(MediaManager.play(media.getSongFromPlaylist())){
                    System.out.println("aqui");
                    this.isPlaying = true;
                    pcs.firePropertyChange(PropertyChangeEnum.PLAY.name(), false, true);
                    return true;
                }
            }
            case Internet_Radio -> {
                if(MediaManager.playRadio(internetRadio.getStationFromLibrary())){
                    this.isPlaying = true;
                    pcs.firePropertyChange(PropertyChangeEnum.PLAY.name(),false,true);
                    return true;
                }
            }
        }
        this.isPlaying = false;
        return false;
    }

    public void pause(){
        switch (sourceType){
            case Local_Music -> {
                MediaManager.pause();
                this.isPlaying = false;
                pcs.firePropertyChange(PropertyChangeEnum.PAUSE.name(), false,true);
            }
            case Internet_Radio -> {
                MediaManager.stop();
                this.isPlaying = false;
                pcs.firePropertyChange(PropertyChangeEnum.STOP.name(), false,true);
            }
        }

    }

    public boolean nextSong(){
        switch (sourceType){
            case Local_Music -> {
                media.nextSong();
                this.isPlaying = true;
                pcs.firePropertyChange(PropertyChangeEnum.NEXT_SONG.name(), false,true);
                return MediaManager.play(media.getSongFromPlaylist());
            }
            case Internet_Radio -> {
                internetRadio.nextStation();
                this.isPlaying = true;
                pcs.firePropertyChange(PropertyChangeEnum.NEXT_SONG.name(), false,true);
                return MediaManager.playRadio(internetRadio.getStationFromLibrary());
            }
        }
        return false;
    }
    public boolean previousSong(){
        switch (sourceType){
            case Local_Music -> {
                media.previousSong();
                this.isPlaying = true;
                pcs.firePropertyChange(PropertyChangeEnum.PREVIOUS_SONG.name(), false, true);
                return MediaManager.play(media.getSongFromPlaylist());
            }
            case Internet_Radio -> {
                internetRadio.previousStation();
                this.isPlaying = true;
                pcs.firePropertyChange(PropertyChangeEnum.PREVIOUS_SONG.name(), false,true);
                return MediaManager.playRadio(internetRadio.getStationFromLibrary());
            }
        }
        return false;
    }
    public boolean repeatePlaylist(){
        return false;
    }

    public Station getStationMetaData(){
        return internetRadio.getStationData();
    }

    public Song getMetadata(){
        return media.getSongData();
    }

    public boolean getIsPlaying(){
        return this.isPlaying;
    }

    public Double getVolume(){
        return MediaManager.getVolume();
    }

    public void setVolume(Double volume){
        pcs.firePropertyChange(PropertyChangeEnum.VOLUME.name(), null, null);
        MediaManager.setVolume(volume);
    }

    public String getSongDuration(){
        int trackLengthInSeconds = getMetadata().getTrackLength();
        int minutes = trackLengthInSeconds / 60;
        int seconds = trackLengthInSeconds % 60;
        String duration = String.format("%d:%02d", minutes,seconds);
        return duration;
    }



    public void addPropertyChangeListener(String property, PropertyChangeListener listener){
        pcs.addPropertyChangeListener(property,listener);
    }
}
