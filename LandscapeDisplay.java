/*
	Originally written by Bruce A. Maxwell a long time ago.
	Updated by Brian Eastwood and Stephanie Taylor more recently

	Creates a window using the JFrame class.

	Creates a drawable area in the window using the JPanel class.

	The JPanel calls the Landscape's draw method to fill in content, so the
	Landscape class needs a draw method.
 */
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics2D;

/**
 * Displays a Board graphically using Swing. In this version, we use a Board
 * class rather than a Landscape) and we do not make the assumption that
 * we are displaying a grid.
 * @author bseastwo
 */
 public class LandscapeDisplay
{
    JFrame win;
    protected Board scape; 
    private LandscapePanel canvas;
    private int gridScale; // width (and height) of each square in the grid

    /**
     * Initializes a display window for a Landscape.
     * @param scape the Landscape to display
     * @param scale controls the relative size of the display
     */
    public LandscapeDisplay(Board scape, int scale)
    {
        // setup the window
        this.win = new JFrame("Sudoku");
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.scape = scape;
        this.gridScale = scale;

        // create a panel in which to display the Landscape
        this.canvas = new LandscapePanel( (int) (this.scape.getCols()+2) * this.gridScale,
																					(int) (this.scape.getRows()+2) * this.gridScale);

        // add the panel to the window, layout, and display
        this.win.add(this.canvas, BorderLayout.CENTER);
        this.win.pack();
        this.win.setVisible(true);
    }

    /**
     * Saves an image of the display contents to a file.  The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename  the name of the file to save
     */
    public void saveImage(String filename)
    {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.win.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), 
                                                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try
                {
                        ImageIO.write(image, ext, new File(filename));
                }
        catch (IOException ioe)
                {
                        System.out.println(ioe.getMessage());
                }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel implements ActionListener // prof wolfe helped me with this
    {
        /**
         * Creates the panel.
         * @param width     the width of the panel in pixels
         * @param height        the height of the panel in pixels
         */
        protected JButton b; // prof wolfe helped me with this
            public void actionPerformed (ActionEvent event){ 
                if(event.getSource()==b){
                    reset();
            }
        }
    
        public LandscapePanel(int width, int height)
        {
                super();
                this.setPreferredSize(new Dimension(width, height));
                this.setBackground(Color.black);

                b = new JButton("reset"); //https://www.javatpoint.com/java-jbutton
    
                Dimension size = b.getPreferredSize();
                b.setBounds(205, 285, size.width, size.height);
                b.addActionListener(this);
                add(b);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g     the Graphics object used for drawing
         */
        public void paintComponent(Graphics g)
        {
            
            super.paintComponent(g);
            scape.draw(g, gridScale );   
            Dimension size = b.getPreferredSize();
            b.setBounds(205, 285, size.width, size.height);
            Graphics2D g2d = (Graphics2D) g; //https://examples.javacodegeeks.com/desktop-java/awt/draw-text-example/
            Font font = new Font("Serif", Font.PLAIN, 30);
            g2d.setFont(font);
            g2d.setColor(Color.white);
            g2d.drawString("SUDOKU", 45, 310);
            
            
        } // end paintComponent
        
    } // end LandscapePanel

    public void repaint() {
	this.win.repaint();
    }
    Board sudokuBoard;
    public void reset(){
        win.dispose();
        //win.repaint();
        new LandscapeDisplay(scape, gridScale);

        // win.dispose();
        // Sudoku test = new Sudoku();
        // test.solve(10);

        //LandscapeDisplay(scape, gridScale);
        //this.win.repaint();
    }
    public static void main(String[] args) throws InterruptedException {
        Board scape = new Board();

	if(args.length > 0)
	    scape.read( args[0] );

        //LandscapeDisplay display = new LandscapeDisplay(scape, 30);
    }
}