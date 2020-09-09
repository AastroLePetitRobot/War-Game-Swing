package wargame;

import wargame.gui.Tile;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareLayout;
import wargame.gui.square.SquareTile;
import wargame.Armee;
import wargame.Joueur;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math;

/**
 * @author Franck Vandewiele
 *
 */
    
public class MainIHM implements ActionListener{
    

    private JButton hexButton = new JButton("Interface hexa");
    private JButton squareButton = new JButton("Interface square");
    private JFrame frame = new JFrame("Wargame");
    public static final int width = 8;
    public static final int height = 8;
    public static Tile[][] listeTiles ;

   

    
    /** 
     * @return 
     * on construit la fenetre 
     */
    public MainIHM(){
        frame.setLocationRelativeTo(null);
        // on construit notre JFrame et on définit son contentPane
        frame.getContentPane().setLayout(new FlowLayout());

        frame.add(hexButton);
        hexButton.addActionListener(this);
        frame.add(squareButton);
        squareButton.addActionListener(this);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        // on demande à notre JFrame de se dimensionner automatiquement en fonction de la taille de son contenu
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        listeTiles = new Tile[height][width];
    }
        
    

    
    /** 
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

        final int width = 8;
        final int height = 5;

        // Fermeture du choix
        frame.dispose();

        if(source==hexButton){
            mapHexa();
        }
        else if(source==squareButton){
            mapSquare();
        }
    }

    // Carte hexagonale
    public void mapHexa(){
        TileLayout hexLayout = new HexLayout(height, width);
        JPanel hexPanel = new JPanel(hexLayout);
        hexPanel.setPreferredSize(hexLayout.getPreferredDimension(600));

        try {
            Main.image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            Main.base = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            Main.plaine = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            Main.desert = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/desert.jpg"));
            Main.eau = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/water.bmp"));
            Main.montagne = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/mountain.png"));
        } catch (IOException ex) {
            System.out.println("ERROR: file not found");
        } catch (NullPointerException ex) {
            System.err.println("ERROR: an unexpected error occurred");
        }

        if (Main.plaine == null || Main.desert == null || Main.eau == null || Main.montagne == null) {
            // si on n'a pas réussi à charger l'imagess, on quitte le programme
            System.exit(-1);
        }
        
        /*
            Remplissage des JPanel avec des tuiles graphiques
        */
        for (int row = 0 ; row < height ; row++) {
            for (int column = 0 ; column < width ; column++) {
                // construction et ajout d'une HexTile au JPanel agencé en grille hexagonale
                double type = Math.random()* 4;
                /* 0 = plaine
                   1 = desert
                   2 = eau
                   3 = montagne
                */ 
            if ((int)type == 0 ){
                Main.image = Main.plaine ;
                Tile hexTile = new HexTile(row , " ; " , column, Main.plaine);
                hexPanel.add(hexTile);
                listeTiles[row][column] = hexTile ;
            }else if ((int) type == 1){
                Main.image = Main.desert ;
                Tile hexTile = new HexTile(row , " ; " , column, Main.desert);
                hexPanel.add(hexTile);
                listeTiles[row][column] = hexTile ;
            }else if ((int) type == 2){
                Main.image = Main.eau ;
                Tile hexTile = new HexTile(row , " ; " , column, Main.eau);
                hexPanel.add(hexTile);
                listeTiles[row][column] = hexTile ;
            }else if ((int) type == 3){
                Main.image = Main.montagne ;
                Tile hexTile = new HexTile(row , " ; " , column, Main.montagne);
                hexPanel.add(hexTile);
                listeTiles[row][column] = hexTile ;
            } 
            }
        }

        // on construit le contentPane de notre JFrame, et on y ajoute les JPanel contenant nos grilles
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(hexPanel);

        JFrame hexaFrame = new JFrame("Wargame : Map Hexagonale");
        hexaFrame.setLocationRelativeTo(null);
        hexaFrame.setContentPane(contentPane);
        hexaFrame.pack();
        hexaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hexaFrame.setVisible(true);
    }

    // Carte carrée
    public void mapSquare(){
        TileLayout squareLayout = new SquareLayout(height, width);
        JPanel squarePanel = new JPanel(squareLayout);
        squarePanel.setPreferredSize(squareLayout.getPreferredDimension(600));


        try {
            Main.image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            Main.base = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            Main.plaine = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            Main.desert = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/desert.jpg"));
            Main.eau = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/water.bmp"));
            Main.montagne = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/mountain.png"));
        } catch (IOException ex) {
            System.out.println("ERROR: file not found");
        } catch (NullPointerException ex) {
            System.err.println("ERROR: an unexpected error occurred");
        }

        if (Main.plaine == null || Main.desert == null || Main.eau == null || Main.montagne == null) {
            // si on n'a pas réussi à charger l'image, on quitte le programme
            System.exit(-1);
        }

        /*
            Remplissage des JPanel avec des tuiles graphiques
        */
        
        for (int row = 0 ; row < height ; row++) {
            for (int column = 0 ; column < width ; column++) {
                // construction et ajout d'une SquareTile au JPanel agencé en grille carrée
                double type = Math.random()* 4;
                /* 0 = plaine
                   1 = desert
                   2 = eau
                   3 = montagne
                */ 
                if ((int)type == 0 ){
                    Main.image = Main.plaine ;
                    Tile squareTile = new SquareTile(row , " ; " , column, Main.plaine);
                    squarePanel.add(squareTile);
                    listeTiles[row][column] = squareTile ;
                }else if ((int) type == 1){
                    Main.image = Main.desert ;
                    Tile squareTile = new SquareTile(row , " ; " , column, Main.desert);
                    squarePanel.add(squareTile);
                    listeTiles[row][column] = squareTile ;
                }else if ((int) type == 2){
                    Main.image = Main.eau ;
                    Tile squareTile = new SquareTile(row , " ; " , column, Main.eau);
                    squarePanel.add(squareTile);
                    listeTiles[row][column] = squareTile ;
                }else if ((int) type == 3){
                    Main.image = Main.montagne ;
                    Tile squareTile = new SquareTile(row , " ; " , column, Main.montagne);
                    squarePanel.add(squareTile);
                    listeTiles[row][column] = squareTile ;
                } 
                

            }
        }
        
        // on construit le contentPane de notre JFrame, et on y ajoute les JPanel contenant nos grilles
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(squarePanel);

        JFrame squareFrame = new JFrame("Wargame : Map Carrée");
        squareFrame.setLocationRelativeTo(null);
        squareFrame.setContentPane(contentPane);
        squareFrame.pack();
        squareFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        squareFrame.setVisible(true);

        



    }
    
} 
    



