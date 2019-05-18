package fr.upem.capcha.ui.images.panneaux.ronds;

import fr.upem.capcha.ui.images.I_Images;
import fr.upem.capcha.ui.images.panneaux.Panneau;

public class PanneauRond extends Panneau implements I_Images {

    public PanneauRond(){
        path = "images/panneaux/ronds";
    }

    @Override
    public String toString(){
        return "panneaurond";
    }
}
