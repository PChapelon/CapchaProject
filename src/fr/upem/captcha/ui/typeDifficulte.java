package fr.upem.captcha.ui;

import java.util.Random;

public enum typeDifficulte {
    ANIMAL, PERSONNAGE, ANIMALFICTIF, ANIMALREEL, PERSONNAGEJEUVIDEO,PERSONNAGEMANGA, PERSONNAGEFILM;

    public static typeDifficulte randomObject(int difficulte){
        Random rand = new Random();
        if (difficulte == 1)
            return values()[rand.nextInt(2)];
        return values()[rand.nextInt(values().length - 2) + 2];
    }

    @Override
    public String toString() {
        switch (this){
            case ANIMAL: return "animal";
            case ANIMALFICTIF: return "animal fictif";
            case ANIMALREEL: return "animal reel";
            case PERSONNAGE: return "personnage";
            case PERSONNAGEFILM: return "personnage film";
            case PERSONNAGEMANGA: return "personnage manga";
            case PERSONNAGEJEUVIDEO: return "personnage jeuvideo";

            default : return "ERROR TYPE";
        }
    }

    public String getTypeClass(){
        switch (this){

            case ANIMAL: return "Animal";
            case ANIMALFICTIF: return "Fictif";
            case ANIMALREEL: return "Reel";
            case PERSONNAGE: return "Personnage";
            case PERSONNAGEFILM: return "Film";
            case PERSONNAGEMANGA: return "Manga";
            case PERSONNAGEJEUVIDEO: return "Jeuvideo";

            default : return "ERROR CLASS";
        }
    }
}
