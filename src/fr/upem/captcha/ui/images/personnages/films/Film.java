package fr.upem.captcha.ui.images.personnages.films;

import fr.upem.captcha.ui.images.I_Images;
import fr.upem.captcha.ui.images.Images;

public class Film  extends Images implements I_Images {

    public Film(){
        path = "images/personnages/films";
    }

    public String toString(){
        return "personnage film";
    }

}