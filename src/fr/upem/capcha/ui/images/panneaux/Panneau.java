package fr.upem.capcha.ui.images.panneaux;

import fr.upem.capcha.ui.images.Images;
import fr.upem.capcha.ui.images.I_Images;


public class Panneau extends Images implements I_Images {

    public Panneau(){
        path = "images/panneaux";
    }

    public String toString(){
        return "panneau";
    }

}
