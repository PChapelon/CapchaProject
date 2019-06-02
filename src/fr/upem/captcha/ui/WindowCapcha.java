package fr.upem.captcha.ui;

import fr.upem.captcha.ui.images.CategorieImage;
import fr.upem.captcha.ui.images.animaux.Animal;
import fr.upem.captcha.ui.images.animaux.fictifs.Fictif;
import fr.upem.captcha.ui.images.animaux.reels.Reel;
import fr.upem.captcha.ui.images.personnages.Personnage;
import fr.upem.captcha.ui.images.personnages.films.Film;
import fr.upem.captcha.ui.images.personnages.jeuxvideos.JeuVideo;
import fr.upem.captcha.ui.images.personnages.mangas.Manga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author paulchapelon
 * Class which manage the window to display on the screen, refresh it ...
 */

public class WindowCapcha {

    public static int LIMIT_DIFFICULTE = 5;

    private JFrame frameResult;
    private JFrame frame;
    private JFrame frameFailure;

    private ArrayList<URL> selectedImages;
    private ArrayList<URL> listImages;

    private int difficulte = 1;
    private int hauteurGrille = 3;
    private int largeurGrille = 3;
    private typeDifficulte imagesToSearch =  typeDifficulte.randomObject(difficulte);
    private int numberImages = 0 ;

	/**
	 * Default constructor using the one parameter constructor with a default name
	 */
    private WindowCapcha () {
        this("Captcha");
    }
    
    /**
     * Constructor with one parameter which initialize the three frames and the different list
     * @param name Define the name of the frame containing the captcha
     */
    public WindowCapcha ( String name ) {
        frame = new JFrame(name);
        frameFailure = new JFrame();
        frameResult = new JFrame();
        selectedImages = new ArrayList<URL>();
        listImages = new ArrayList<URL>();
    }
    
    /**
     * Function initializing all the parameters of the three frame (captcha, failure, success) and only set visible the main one
     * @throws IOException Error of display
     * @throws IllegalAccessException Error of access
     * @throws InstantiationException Error of new instance
     * @throws ClassNotFoundException Error when finding the class in pakcges
     */
    public void initFrame () throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        frame.setLayout(createLayout()); //initializing the main frame
        frame.setSize(1024, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initListImages();

        frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus."));
        frame.add(createOkButton());
        frame.add(new JTextArea("Niveau de difficulté "+difficulte+"\n"));
        frame.setVisible(true);
        
        frameResult.setLayout(new GridLayout(2,1)); //initializing the frame of success
        frameResult.setSize(500, 300);
        frameResult.setResizable(false);

        frameResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameResult.add(new JTextArea("Vous n'êtes pas un robot !!!!"), BorderLayout.CENTER);
        frameResult.add(createQuitButton(), BorderLayout.CENTER);
        frameResult.setVisible(false);
        
    	frameFailure.setLayout(new GridLayout(2,1));  //initializing the frame of failure
    	frameFailure.setSize(500, 300);
        frameFailure.setResizable(false);

        frameFailure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameFailure.add(new JTextArea("Vous avez échoué le test"), BorderLayout.CENTER);
        frameFailure.add(createQuitButton(), BorderLayout.CENTER);
        frameFailure.setVisible(false);

    }

    /**
     * Function creating a gridlayout with a variable number of rows and columns
     * @return A gridlayout with a number of rows and columns chosen
     */
    private GridLayout createLayout(){
        return new GridLayout(hauteurGrille + 1, largeurGrille);
    }

    /**
     * Function creating a button permitting to dispose every frames of the program
     * @return The button quit of the program
     */
    private JButton createQuitButton(){
        return new JButton(new AbstractAction("Quitter") { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() { 
                		frameResult.dispose();
                    	frame.dispose();
                    	frameFailure.dispose();
                    }
                });
            }
        });
    }

    /**
     * Function creating a button which checks if the selected images matched with the topic and recreate a new captcha if it failed 
     * or display a success window if it corresponds 
     * if there is more than 5 failure, a failure window appears displaying that the test is failed
     * @return the button of the main frame 
     */
    private JButton createOkButton() {
        return new JButton(new AbstractAction("Suis-je un robot?") { 

            @Override
            public void actionPerformed(ActionEvent arg0) {
                EventQueue.invokeLater(new Runnable() { 

                    @Override
                    public void run() {
                        System.out.println("Résultat du captcha : " + verificationImages());
                        if (verificationImages()){   
                            
                            frameResult.setVisible(true); //the user succeed the test
                        }
                        else {
                            frame.setVisible(false);
                            frame.getContentPane().removeAll();
                            difficulte ++; //increase the difficulty
                            switch(difficulte) {
                            	case 3: largeurGrille ++; break; //level 3 one more column
                            	case 5: largeurGrille ++; break; //level 5 one more too
                            	default: break;
                            }
                            if(difficulte <= LIMIT_DIFFICULTE) { //if the user don't fail too much times
                            	frame.setLayout(createLayout()); //recreation of a new window
                            	initListImages(); //loading new images and a new objective
                                frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus."));
                                frame.add(createOkButton());
                                frame.add(new JTextArea("Niveau de difficulté "+difficulte+"\n"));
                                frame.repaint();
                                frame.setVisible(true);
                            }
                            else {
                            	
                                frameFailure.setVisible(true); //display the frame of failure
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * Function initialize a topic of images to search depending on the difficulty and then add the frame different random images
     */
    private void initListImages()  {
        numberImages = 0;
        listImages.clear();
        imagesToSearch = typeDifficulte.randomObject(difficulte); //select a random type of image to search


        for (int i = 0; i < largeurGrille * hauteurGrille  ; i ++){
            typeDifficulte randObject = typeDifficulte.randomObject(difficulte);
            CategorieImage dynamicObj = getClassInstance(randObject); //create a random object related of the images
            
            if(randObject.getTypeClass() == imagesToSearch.getTypeClass()) // if the random object type equals to the type of image to search
                numberImages ++; //increase the number reprenting the images contained in the frame
            URL u = dynamicObj.getRandomPhotosURL(); //get a random photo 
            listImages.add(u);
        }
        
        for( URL u : listImages) {
            try {
                frame.add(createLabelImage(u));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function which do the verification between the selected images and the one in the tree and counts them to verify if no one miss
     * @return True if the selected images correspond to the topic of the captcha and if no one miss
     */
    private boolean verificationImages() {
        if (selectedImages.size() != numberImages){
        	return false;
        }
        CategorieImage dynamicObj = getClassInstance(imagesToSearch);
        /*CategorieImage dynamicObj = null;
        try {
            dynamicObj = (CategorieImage) Class.forName(imagesToSearch.getTypeClass()).getDeclaredConstructor().newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        for (URL u : selectedImages)
        {
            if (!dynamicObj.isPhotoCorrect(u))
            {
                return false;
            }
        }
        //CategorieImage dynamicObj = getClassInstance(imagesToSearch);


        return true;
    }

    /**
     * Function which makes the correspondance between the value of the enumeration and the constructor of the class
     * @param obj Value of the enumeration 
     * @return A new object of the sub classes of CategorieImage
     */
    private CategorieImage getClassInstance(typeDifficulte obj){
        switch(obj){
            case ANIMAL: return new Animal();
            case ANIMALFICTIF: return  new Fictif();
            case ANIMALREEL: return new Reel();
            case PERSONNAGE: return new Personnage();
            case PERSONNAGEFILM: return new Film();
            case PERSONNAGEMANGA: return new Manga();
            case PERSONNAGEJEUVIDEO: return new JeuVideo();
            default: return new JeuVideo() ;
        }
    }

    
    private JLabel createLabelImage(URL url) throws IOException {
        BufferedImage img = ImageIO.read(url); //lire l'image
        Image sImage = img.getScaledInstance(1024/3,768/4, Image.SCALE_SMOOTH); //redimentionner l'image

        final JLabel label = new JLabel(new ImageIcon(sImage)); // créer le composant pour ajouter l'image dans la fenêtre

        label.addMouseListener(new MouseListener() { //Ajouter le listener d'évenement de souris
            private boolean isSelected = false;


            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseClicked(MouseEvent arg0) { //ce qui nous intéresse c'est lorsqu'on clique sur une image, il y a donc des choses à faire ici
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if(!isSelected){
                            label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                            isSelected = true;
                            selectedImages.add(url);
                        }
                        else {
                            label.setBorder(BorderFactory.createEmptyBorder());
                            isSelected = false;
                            selectedImages.remove(url);
                        }

                    }
                });

            }
        });

        return label;
    }



}
