package fr.upem.captcha.ui.images.personnages;

import fr.upem.captcha.ui.images.I_Images;
import fr.upem.captcha.ui.images.Images;

public class Personnage  extends Images implements I_Images {

    public Personnage(){
        path = "images/personnages";
    }

    public String toString(){
        return "personnage";
    }

}