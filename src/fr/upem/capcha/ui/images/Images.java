package fr.upem.capcha.ui.images;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Images implements I_Images {
    protected String path;
    private String pathDirectory = System.getProperty("user.dir") + "/src/fr/upem/capcha/ui/";


    public Images(){
        path = "images";
    }

    public List<URL> getPhotos()  {

        final File folder = new File(pathDirectory + path);
        return findPhotos(folder, new StringBuilder(path), new ArrayList<URL>());
    }

    private List<URL> findPhotos(File folder, StringBuilder path, ArrayList<URL> list ) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                StringBuilder s = new StringBuilder (path);
                findPhotos(fileEntry, s.append( "/" + fileEntry.getName()), list);
            }
            else {
                if(fileEntry.getName().toLowerCase().endsWith(".jpg") || fileEntry.getName().toLowerCase().endsWith(".jpeg") || fileEntry.getName().toLowerCase().endsWith(".png") )
                {
                    try {
                        list.add(fileEntry.toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    //list.add( path.toString() + "/" +fileEntry.getName());
                }
            }
        }
        return list;
    }

    public List<URL> getRandomPhotosURL(int nbPhotos){
        ArrayList<URL> listPhotos = new ArrayList<URL>();
        listPhotos.addAll(getPhotos());
        ArrayList<URL> listRandomPhotos = new ArrayList<URL>();
        Random rand = new Random();
        for (int i = 0; i < nbPhotos ; i ++){
            int indexRandom = rand.nextInt(listPhotos.size());
            listRandomPhotos.add(listPhotos.get(indexRandom));
            listPhotos.remove(indexRandom);
        }
        return listRandomPhotos;
    }

    public URL getRandomPhotosURL(){
        return getRandomPhotosURL(1).get(0);
    }

    public boolean isPhotoCorrect(URL address){
        List<URL> l = getPhotos();
        //int fileLength = pathDirectory.length();
        boolean b = l.contains(address);
        return b;
    }

    public String getPath(){
        return path;
    }

    public String toString(){
        return "panneau";
    }

}
