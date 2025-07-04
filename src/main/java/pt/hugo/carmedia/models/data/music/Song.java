package pt.hugo.carmedia.models.data.music;

import java.util.Arrays;

public class Song {
    private String title;
    private String ArtistName;
    private String AlbumTitle;
    private String Genre;
    private byte[] AlbumArtWork;
    private String trackNumber;
    private String Composer;
    private String yearOfRelease;
    private int trackLength;
    private String fileLocation;

    public Song(String title, int trackLength ,String artistName, String albumTitle, String genre, byte[] albumArtWork, String trackNumber, String composer, String yearOfRelease, String fileLocation) {
        this.title = title;
        this.trackLength = trackLength;
        this.ArtistName = artistName;
        this.AlbumTitle = albumTitle;
        this.Genre = genre;
        this.AlbumArtWork = albumArtWork;
        this.trackNumber = trackNumber;
        this.Composer = composer;
        this.yearOfRelease = yearOfRelease;
        this.fileLocation = fileLocation;
    }
    public String getFileLocation() {
        return fileLocation;
    }
    public byte[] getAlbumArtWork(){
        return AlbumArtWork;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public int getTrackLength() {
        return trackLength;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", ArtistName='" + ArtistName + '\'' +
                ", AlbumTitle='" + AlbumTitle + '\'' +
                ", Genre='" + Genre + '\'' +
                ", AlbumArtWork=" + Arrays.toString(AlbumArtWork) +
                ", trackNumber='" + trackNumber + '\'' +
                ", Composer='" + Composer + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", trackLength=" + trackLength +
                ", fileLocation='" + fileLocation + '\'' +
                '}';
    }
}

