package fr.upem.capcha.ui.images.panneaux.ronds;

import fr.upem.capcha.ui.images.Images;

import java.net.URL;
import java.util.List;

public abstract class PanneauRond implements Images {
    public List<String> getPhotos(){
        return null;
    }

    public List<String> getRandomPhotosURL(int nbPhotos){
        return null;

    }
    public String getRandomPhotosURL(){
        return null;

    }
    public boolean isPhotoCorrect(URL address){
        return true;

    }
}
