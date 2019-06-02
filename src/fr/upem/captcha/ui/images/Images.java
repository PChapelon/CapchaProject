package fr.upem.captcha.ui.images;

import java.net.URL;
import java.util.List;

/**
 * @author paulchapelon
 * Interface which define of the methods for the classes defining a set of images
 */
public interface Images {

    public List<URL> getPhotos() ;
    public List<URL> getRandomPhotosURL(int nbPhotos);
    public URL getRandomPhotosURL();
    public boolean isPhotoCorrect(URL address);
    public String toString();
}
