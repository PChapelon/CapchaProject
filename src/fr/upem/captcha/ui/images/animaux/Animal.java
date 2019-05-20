package fr.upem.captcha.ui.images.animaux;

import fr.upem.captcha.ui.images.I_Images;
import fr.upem.captcha.ui.images.Images;

public class Animal extends Images implements I_Images {

    public Animal(){
        path = "images/animaux";
    }

    public String toString(){
        return "animal";
    }

}
