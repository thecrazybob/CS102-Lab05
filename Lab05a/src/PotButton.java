/**
 * Instructions for Lab05 are in CS102_Lab05.pdf file located in the root
 * directory of Lab05 Revisions can be seen on the following GitHub URL:
 * https://github.com/thecrazybob/CS102-lab05 Style Guidelines:
 * http://www.cs.bilkent.edu.tr/~adayanik/cs101/practicalwork/styleguidelines.htm
 *
 * @author Mohammed Sohail
 * @version 31/03/2021
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.event.ActionListener;
public class PotButton extends JButton {

    // constants
    private static final long serialVersionUID = 1L;
    private final int col;
    private final int row;

    // variables
    private boolean gameFinished;
    private String type;

    // constructor
    PotButton(final int row, final int col, final ActionListener actionListener, int text) {
        this.col = col;
        this.row = col;
        addActionListener(actionListener);
        setText(text + "");
    }

    
    /** 
     * Returns the type of button (bomb/prize)
     * @return String
     */
    public String getType() {
        return this.type;
    }

    
    /** 
     * Sets the type of the button (bomb/prize)
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Reveals the contents of the button
     */
    void reveal() {
        setEnabled(false);
        if (type == "bomb") {
            setText(type);
        }
        
        else if (type == "prize") {
            setText("\u2605");
            setFont(new FontUIResource("Arial", FontUIResource.PLAIN, 40));
            setOpaque(true);
            setEnabled(true);
            setForeground(Color.ORANGE);
        }

        gameFinished = true;
    }

    void reset() {
        setType("");
        setEnabled(true);
        setText("");
        setForeground(Color.BLACK);
    }

    
    /** 
     * Overrides the paintComponent method to show a bomb icon on reveal for bomb type buttons
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        if (type == "bomb" && gameFinished) {
            
            int height = getHeight() - 20;
            int width = getWidth() - 20;

            Graphics2D g2d = (Graphics2D) g;

            // Arc
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.RED);
            g2d.drawArc(30,8,width,height,-270,50);
            
            // Rectangle
            g2d.setColor(Color.GRAY);
            g2d.fillRect(35, 13, getWidth()-70, height/6);
            
            // Circle
            g2d.setColor(Color.BLACK);
            g2d.fillOval((50-(55/2)),45-(55/2),55,55);
            
        }

    }

}
