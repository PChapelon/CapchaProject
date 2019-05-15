package fr.upem.capcha.ui.images.panneaux.carres;

import fr.upem.capcha.ui.images.Images;

import java.util.List;

public class PanneauCarre implements Images {

    @Override
    public List<String> getPhotos() {
        return null;
    }

    @Override
    public List<String> getRandomPhotosURL(int nbPhotos) {
        return null;
    }

    @Override
    public String getRandomPhotosURL() {
        return null;
    }

    @Override
    public boolean isPhotoCorrect(String address) {
        return false;
    }
}
