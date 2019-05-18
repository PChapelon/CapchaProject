package fr.upem.capcha.ui.images.panneaux.carres;

import fr.upem.capcha.ui.images.I_Images;
import fr.upem.capcha.ui.images.panneaux.Panneau;


public class PanneauCarre extends Panneau implements I_Images {

    public PanneauCarre(){
        path = "images/panneaux/carres";
    }

    public String toString(){
        return "panneaucarre";
    }
}
