package pt.hugo.carmedia.models.data.music;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongLibrary {
    private static List<Song> playList = new ArrayList<>();
    private final String MUSIC_FOLDER = "music/";

    public SongLibrary() {

    }

    public boolean loadLibrary(){

        File folder = new File(MUSIC_FOLDER);
        if (!folder.exists() || !folder.isDirectory()){
            folder.mkdir();
        }

        File songFiles[] = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (songFiles != null){
            for(File file : songFiles){
                try {
                    AudioFile audioFile = AudioFileIO.read(file);
                    Tag tag = audioFile.getTag();
                    byte[] imageData = null;
                    Artwork cover = tag.getFirstArtwork();

                    if(cover != null)
                        imageData = cover.getBinaryData();
                    playList.add(new Song(tag.getFirst(FieldKey.TITLE),
                            audioFile.getAudioHeader().getTrackLength(),
                            tag.getFirst(FieldKey.ARTIST),
                            tag.getFirst(FieldKey.ALBUM),
                            tag.getFirst(FieldKey.GENRE),
                            imageData,
                            tag.getFirst(FieldKey.MUSICBRAINZ_TRACK_ID),
                            tag.getFirst(FieldKey.COMPOSER),
                            tag.getFirst(FieldKey.YEAR),
                            file.getPath()));

                } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                         IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        }
        return false;
    }
    public List<Song> getPlayList(){
        return playList;
    }
}
