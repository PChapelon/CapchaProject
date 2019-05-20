package fr.upem.captcha.ui.images;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface I_Images {

    public List<URL> getPhotos() ;
    public List<URL> getRandomPhotosURL(int nbPhotos);
    public URL getRandomPhotosURL();
    public boolean isPhotoCorrect(URL address);
    public String toString();
    public String getPath();
}
