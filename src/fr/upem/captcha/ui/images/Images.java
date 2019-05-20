package fr.upem.captcha.ui.images;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Images implements I_Images {

    protected String path;
    private String pathDirectory = System.getProperty("user.dir") + "/src/fr/upem/captcha/ui/";


    public Images(){
        path = "images";
    }

    /**
     *
     * @return the list of the url of each images associated to this class
     */
    public List<URL> getPhotos()  {
        final File folder = new File(pathDirectory + path);
        return findPhotos(folder, new StringBuilder(path), new ArrayList<URL>());
    }

    /**
     *
     * @param folder current folder
     * @param path the path of the current folder
     * @param list the list of the URL of the images
     * @return the completed list with URL of the images in the current folder
     */
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
                }
            }
        }
        return list;
    }


    /**
     *
     * @param nbPhotos the number of random photos to return
     * @return the list of the URL of the photos according to the number chosen
     */
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

    /**
     *
     * @return return one random image according to the current class
     */
    public URL getRandomPhotosURL(){
        return getRandomPhotosURL(1).get(0);
    }

    /**
     *
     * @param address the URL of the address to check
     * @return Check if the URL in parameter is in the directories and subdirectories of the current class
     */
    public boolean isPhotoCorrect(URL address){ return getPhotos().contains(address); }

    public String getPath(){ return path; }

    public String toString(){ return "panneau"; }

}
