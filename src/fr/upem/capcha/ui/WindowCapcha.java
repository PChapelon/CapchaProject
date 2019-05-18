package fr.upem.capcha.ui;

import fr.upem.capcha.ui.images.I_Images;
import fr.upem.capcha.ui.images.Images;
import fr.upem.capcha.ui.images.panneaux.Panneau;
import fr.upem.capcha.ui.images.panneaux.carres.PanneauCarre;
import fr.upem.capcha.ui.images.panneaux.ronds.PanneauRond;
import fr.upem.capcha.ui.images.ponts.Pont;
import fr.upem.capcha.ui.images.villes.Ville;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WindowCapcha {

    private JFrame frame;
    private ArrayList<URL> selectedImages;
    private ArrayList<String> listImages;
    //private ObjectCapcha imagesToSearch =  ObjectCapcha.PONT;
    private ObjectCapcha imagesToSearch =  ObjectCapcha.randomObject();
    private int numberImages = 0 ;


    private WindowCapcha () {
        this("Capcha");
    }

    public WindowCapcha ( String name) {
        frame = new JFrame(name);
        selectedImages = new ArrayList<URL>();
        listImages = new ArrayList<String>();
    }

    public void initFrame () throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        frame.setLayout(createLayout());
        frame.setSize(1024, 768);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton okButton = createOkButton();

        initListImages();



        frame.add(new JTextArea("Sélectionnez chaque " + imagesToSearch.toString() +" dans les images ci-dessus. \nIl y en a " + numberImages));


        frame.add(okButton);

        frame.setVisible(true);

    }

    private GridLayout createLayout(){
        return new GridLayout(4,3);
    }

    private JButton createOkButton(){
        return new JButton(new AbstractAction("Suis-je un robot?") { //ajouter l'action du bouton

            @Override
            public void actionPerformed(ActionEvent arg0) {
                EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements

                    @Override
                    public void run() { // c'est un runnable
                        System.out.println(verificationImages());
                    }
                });
            }
        });
    }

    private void initListImages() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        numberImages = 0;
        for (int i = 0; i < 9 ; i ++){
            Images dynamicObj;
            ObjectCapcha randObject = ObjectCapcha.randomObject();
            switch (randObject){
                case PONT:
                    dynamicObj = new Pont();
                    break;
                case VILLE:
                    dynamicObj = new Ville();
                    break;
                case PANNEAU:
                    dynamicObj = new Panneau();
                    break;
                case PANNEAUCARRE:
                    dynamicObj = new PanneauCarre();
                    break;
                default:
                    dynamicObj = new PanneauRond();
                    break;
            }
            if(randObject.getTypeClass() == imagesToSearch.getTypeClass())
                numberImages ++;
            listImages.add(dynamicObj.getRandomPhotosURL());
        }
        for( String u : listImages)
            frame.add(createLabelImage(u));
    }

    private boolean verificationImages() {
        System.out.println("images");
        System.out.println(imagesToSearch);
        if (selectedImages.size() != numberImages){
            return false;
        }
        Images dynamicObj;
        switch(imagesToSearch){
            case PONT:
                dynamicObj = new Pont();
                break;
            case VILLE:
                dynamicObj = new Ville();
                break;
            case PANNEAU:
                dynamicObj = new Panneau();
                break;
            case PANNEAUCARRE:
                dynamicObj = new PanneauCarre();
                break;
            default:
                dynamicObj = new PanneauRond() ;
                break;
        }
        for (URL u : selectedImages)
        {
            if (!dynamicObj.isPhotoCorrect(u.getPath()))
            {
                return false;
            }
        }
        return true;
    }

    private JLabel createLabelImage(String imageLocation) throws IOException {

        final URL url = MainUi.class.getResource(imageLocation); //Aller chercher les images !! IMPORTANT

        System.out.println(url);

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
