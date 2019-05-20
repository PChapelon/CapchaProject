package fr.upem.captcha.ui.images.animaux.reels;

import fr.upem.captcha.ui.images.I_Images;
import fr.upem.captcha.ui.images.Images;

public class Reel extends Images implements I_Images {

    public Reel(){
        path = "images/animaux/reels";
    }

    public String toString(){
        return "animal reel";
    }

}
