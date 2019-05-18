package fr.upem.capcha.ui.images;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface Images {

    public List<String> getPhotos() ;
    public List<String> getRandomPhotosURL(int nbPhotos);
    public String getRandomPhotosURL();
    public boolean isPhotoCorrect(String address);
    public String toString();
}
