package fr.upem.captcha.ui.images;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upem.captcha.ui.MainUi;

/**
 * @author paulchapelon
 * Abstract class implementing the methods of Images and representing all the types of images
 */
public abstract class CategorieImage implements Images {
    

    /**
     * Contructor of the class to initialize the property path
     */
    public CategorieImage(){
    }

    /**
     * Function initializing the recursivity to fing all the images
     * @return the list of the url of each image associated to this class
     */
    public List<URL> getPhotos()  {
    	String pathDirectory = System.getProperty("user.dir") + File.separator + "src"+ File.separator;
        final File folder = new File(pathDirectory +  getClass().getPackageName().replace(".", File.separator));
        return findPhotos(folder,  new ArrayList<URL>());
    }

    /**
     * Recursive method which check in a current folder all files inside if it is a folder it calls this function with the folder in parameter
     * otherwise if it's an image type, it is added to the List
     * @param folder current folder
     * @param list the list of the URL of the images
     * @return the completed list with URL of the images in the current folder
     */
    private List<URL> findPhotos(File folder, ArrayList<URL> list ) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                findPhotos(fileEntry,  list);
            }
            else {
                if(fileEntry.getName().toLowerCase().endsWith(".jpg") || fileEntry.getName().toLowerCase().endsWith(".jpeg") || fileEntry.getName().toLowerCase().endsWith(".png") )
                {
                    
                    	URL u = MainUi.class.getResource(fileEntry.toString());
                    	//list.add(u);
                        try {
							list.add(fileEntry.toURI().toURL());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    
                }
            }
        }
        return list;
    }
    
    public Path getPathe() {
		StringBuilder fileName = new StringBuilder(this.getClass().getSimpleName());
		fileName.append(".class");
		URL catUrl = this.getClass().getResource(fileName.toString()); 
		File classFile =  new File(catUrl.getPath());
		return Paths.get(classFile.getParent());
	}


    /**
     * Function which returns a list of url equivalent to the number in parameter
     * @param nbPhotos the number of random photos to return
     * @return the list of the URL of the photos 
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
     * Function which returns one random url among the photos corresponding to the class
     * @return A random url of an image
     */
    public URL getRandomPhotosURL(){
        return getRandomPhotosURL(1).get(0);
    }

    /**
     * Function which checks if the url in parameter is contained among the list of images corresponding to the class
     * @param address the URL of the address to check
     * @return true if the url is contained
     */
    public boolean isPhotoCorrect(URL address){ 
    	return getPhotos().contains(address); 
    }


    /**
     * Function to display the name of the class
     * @return the name of the class in lower case
     */
    public String toString(){ return "panneau"; }

}
