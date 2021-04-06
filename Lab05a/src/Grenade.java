import java.awt.*;
import javax.swing.*;

public class Grenade extends JPanel {

    public void paintComponent(Graphics g) {
    	Graphics2D graphics2d = (Graphics2D) g;
    	graphics2d.setColor(Color.gray);
    	graphics2d.fillRect(94, 70, 15, 10);
        
        g.setColor(Color.red);
    	graphics2d.fillArc(90, 70, 20, 20, 45, 90);
        
        g.setColor(Color.black);
    	graphics2d.fillOval((100-(50/2)), (100-(50/2)), 50, 50);

    }
}