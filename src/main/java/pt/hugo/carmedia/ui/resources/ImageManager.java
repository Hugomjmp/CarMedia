package pt.hugo.carmedia.ui.resources;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ImageManager {
    private ImageManager(){}

    private static final HashMap<String, Image> images = new HashMap<>();

    public static Image getImage(String filename){
        Image image = images.get(filename);
        if (image == null)
            try (InputStream inputStream = ImageManager.class.getResourceAsStream("/img/"+filename)){
                image = new Image(inputStream);
                images.put(filename,image);
            } catch (IOException e) {
                return null;
            }
        return image;
    }
}
