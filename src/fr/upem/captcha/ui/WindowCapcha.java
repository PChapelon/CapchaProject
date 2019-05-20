package fr.upem.captcha.ui;

import fr.upem.captcha.ui.images.Images;
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
import java.net.URL;
import java.util.ArrayList;

public class WindowCapcha {

    private JFrame frameResult;
    private JFrame frame;

    private ArrayList<URL> selectedImages;
    private ArrayList<URL> listImages;

    private int difficulte = 1;
    private typeDifficulte imagesToSearch =  typeDifficulte.randomObject(difficulte);
    private int numberImages = 0 ;



    private WindowCapcha () {
        this("Captcha");
    }

    public WindowCapcha ( String name) {
        frame = new JFrame(name);
        selectedImages = new ArrayList<URL>();
        listImages = new ArrayList<URL>();
    }

    public void initFrame () throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        frame.setLayout(createLayout());
        frame.setSize(1024, 768);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton okButton = createOkButton();

        initListImages();

        //frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus. \nIl y en a " + numberImages));                            frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus."));
        frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus."));


        frame.add(okButton);

        frame.setVisible(true);

    }

    private GridLayout createLayout(){
        return new GridLayout(4,3);
    }

    private JButton createQuitButton(){
        return new JButton(new AbstractAction("Quitter") { //ajouter l'action du bouton

            @Override
            public void actionPerformed(ActionEvent arg0) {
                EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements

                    @Override
                    public void run() { // c'est un runnable
                        frameResult.dispose();
                        frame.dispose();
                    }
                });
            }
        });
    }

    private JButton createOkButton() {
        return new JButton(new AbstractAction("Suis-je un robot?") { //ajouter l'action du bouton

            @Override
            public void actionPerformed(ActionEvent arg0) {
                EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements

                    @Override
                    public void run() {
                        System.out.println("Résultat du captcha : "+verificationImages());
                        if (verificationImages()){   // le captcha est réussi
                            frameResult = new JFrame();
                            frameResult.setLayout(new GridLayout(2,1));
                            frameResult.setSize(500, 300);
                            frameResult.setResizable(false);

                            frameResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JButton buttonExit = createQuitButton();
                            frameResult.add(new JTextArea("Vous n'êtes pas un robot !!!!"), BorderLayout.CENTER);
                            frameResult.add(buttonExit, BorderLayout.CENTER);
                            frameResult.setVisible(true);
                        }
                        else {
                            frame.setVisible(false);
                            frame.getContentPane().removeAll();
                            difficulte = 2;
                            //System.out.println(difficulte);
                            initListImages();
                            //frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus. \nIl y en a " + numberImages));
                            frame.add(new JTextArea("Sélectionnez chaque \n" + imagesToSearch.toString() +" \ndans les images ci-dessus."));
                            frame.add(createOkButton());
                            frame.repaint();
                            frame.setVisible(true);

                        }
                    }
                });
            }
        });
    }

    private void initListImages()  {
        numberImages = 0;
        listImages.clear();
        imagesToSearch = typeDifficulte.randomObject(difficulte);


        for (int i = 0; i < 9 ; i ++){
            typeDifficulte randObject = typeDifficulte.randomObject(difficulte);
            Images dynamicObj = getClassInstance(randObject);

            if(randObject.getTypeClass() == imagesToSearch.getTypeClass())
                numberImages ++;
            listImages.add(dynamicObj.getRandomPhotosURL());
        }

        for( URL u : listImages) {
            try {
                frame.add(createLabelImage(u));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verificationImages() {
        if (selectedImages.size() != numberImages){
             return false;
        }
        Images dynamicObj = getClassInstance(imagesToSearch);

        for (URL u : selectedImages)
        {
            if (!dynamicObj.isPhotoCorrect(u))
            {
                return false;
            }
        }
        return true;
    }

    private Images getClassInstance(typeDifficulte obj){
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
