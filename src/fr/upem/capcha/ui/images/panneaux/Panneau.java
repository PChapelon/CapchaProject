package fr.upem.capcha.ui.images.panneaux;

import fr.upem.capcha.ui.MainUi;
import fr.upem.capcha.ui.images.Images;

import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Panneau implements Images {

    public Panneau(){

    }

    public List<String> getPhotos()  {

        final File folder = new File(System.getProperty("user.dir") + "/src/fr/upem/capcha/ui/images/panneaux");
        ArrayList<String> listUrl = new ArrayList<String>();
        return findPhotos(folder, new StringBuilder("images/panneaux"), listUrl);
    }

    private List<String> findPhotos(File folder, StringBuilder path, ArrayList<String> list )  {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                StringBuilder s = new StringBuilder (path);
                findPhotos(fileEntry, s.append( "/" + fileEntry.getName()), list);
            }
            else {
                if(fileEntry.getName().toLowerCase().endsWith(".jpg") || fileEntry.getName().toLowerCase().endsWith(".jpeg") || fileEntry.getName().toLowerCase().endsWith(".png") )
                {
                    list.add(path.toString() + "/" +fileEntry.getName());
                }
            }
        }
        return list;
    }

    public List<String> getRandomPhotosURL(int nbPhotos){
        ArrayList<String> listPhotos = new ArrayList<String>();
        listPhotos.addAll( getPhotos());
        ArrayList<String> listRandomPhotos = new ArrayList<String>();
        Random rand = new Random();
        for (int i = 0; i < nbPhotos ; i ++){
            int indexRandom = rand.nextInt(listPhotos.size());
            listRandomPhotos.add(listPhotos.get(indexRandom));
            listPhotos.remove(indexRandom);
        }
        return listRandomPhotos;
    }

    public String getRandomPhotosURL(){

       return getRandomPhotosURL(1).get(0);
    }

    public boolean isPhotoCorrect(String address){
        return getPhotos().contains(address);
    }
}
