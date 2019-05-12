package fr.upem.capcha.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WindowCapcha {

    private JFrame frame;
    private ArrayList<URL> selectedImages;
    private ArrayList<String> listImages;

    private WindowCapcha () {
        this("Capcha");
    }

    public WindowCapcha ( String name) {
        frame = new JFrame(name);
        selectedImages = new ArrayList<URL>();
        listImages = new ArrayList<String>();
    }

    public void initFrame () throws IOException {
        frame.setLayout(createLayout());
        frame.setSize(1024, 768);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton okButton = createOkButton();

        initListImages();

        for( String s : listImages)
            frame.add(createLabelImage(s)); //ajouter des composants à la fenêtre

        frame.add(new JTextArea("Cliquez n'importe où ... juste pour tester l'interface !"));


        frame.add(okButton);

        frame.setVisible(true);

    }

    public void listFilesForFolder(final File folder, StringBuilder str) throws IOException {

        for (final File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
            if (fileEntry.isDirectory()) {
                StringBuilder s = new StringBuilder (str.toString());
                listFilesForFolder(fileEntry, s.append( "/" + fileEntry.getName()));
            }
            else {
                if(fileEntry.getName().toLowerCase().endsWith(".jpg"))
                {
                    listImages.add(str.toString() + "/" + fileEntry.getName());
                    System.out.println(fileEntry.getName() + " " + true);
                }
            }
        }
    }

    private void initListImages () throws IOException {

        final File folder = new File(System.getProperty("user.dir") + "/src/fr/upem/capcha/ui/images");
        listFilesForFolder(folder, new StringBuilder("images"));
    }


    private boolean verificationMatch () {
        return true;
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
                        System.out.println(verificationMatch());
                    }
                });
            }
        });
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
