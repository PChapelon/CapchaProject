package fr.upem.capcha.ui;

import java.util.Random;

public enum ObjectCapcha {
    PANNEAU, PANNEAUROND,VILLE,PANNEAUCARRE,PONT;

    public static ObjectCapcha randomObject(){
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }

    @Override
    public String toString() {
        switch (this){
            case PANNEAU : return "panneau";
            case PANNEAUCARRE : return "panneau carré";
            case PANNEAUROND : return "panneau rond";
            case VILLE : return "ville";
            case PONT : return "pont";
            default : return "ERROR TYPE";
        }
    }

    public String getTypeClass(){
        switch (this){
            case PANNEAU : return "Panneau";
            case PANNEAUCARRE : return "PanneauCarre";
            case PANNEAUROND : return "PanneauRond";
            case VILLE : return "Ville";
            case PONT : return "Pont";
            default : return "ERROR CLASS";
        }
    }
}
