package pt.hugo.carmedia.models.data;

import pt.hugo.carmedia.models.data.music.Song;
import pt.hugo.carmedia.models.data.music.SongLibrary;
import pt.hugo.carmedia.models.data.radio.StationLibrary;


public class Media {
    SongLibrary songLibrary;
    private int track = 0;

    public Media() {
        this.songLibrary = new SongLibrary();
        songLibrary.loadLibrary();
    }

    public String getSongFromPlaylist(){

        if (this.track >= 0 && this.track < songLibrary.getPlayList().size()){
            return songLibrary.getPlayList().get(this.track).getFileLocation();
        }
            return null;
    }
    public Song getSongData(){
        if (songLibrary.getPlayList().get(this.track)!= null)
            return songLibrary.getPlayList().get(this.track);
        return null;
    }

    private int getPlaylistSize(){
        return songLibrary.getPlayList().size();
    }

    public void nextSong(){
        if (this.track >= getPlaylistSize() - 1){
            this.track = 0;
            return;
        }
            this.track++;
    }

    public void previousSong(){
        if (this.track <= 0){
            this.track = getPlaylistSize() - 1;
            return;
        }
        this.track--;
    }
}
