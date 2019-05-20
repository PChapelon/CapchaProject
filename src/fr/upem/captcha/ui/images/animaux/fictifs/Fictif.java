package fr.upem.captcha.ui.images.animaux.fictifs;

import fr.upem.captcha.ui.images.I_Images;
import fr.upem.captcha.ui.images.Images;

public class Fictif extends Images implements I_Images {

    public Fictif(){
        path = "images/animaux/fictifs";
    }

    public String toString(){
        return "animal fictif";
    }

}