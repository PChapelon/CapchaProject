package fr.upem.captcha.ui.images.personnages.mangas;

import fr.upem.captcha.ui.images.I_Images;
import fr.upem.captcha.ui.images.Images;

public class Manga extends Images implements I_Images {

    public Manga(){
        path = "images/personnages/mangas";
    }

    public String toString(){
        return "personnage manga";
    }

}