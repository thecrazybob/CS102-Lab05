import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.event.ActionListener;

public class PotButton extends JButton {

    private final int col;
    private final int row;
    private       String type;

    PotButton(final int row, final int col, final ActionListener actionListener, int text) {
        this.col = col;
        this.row = col;
        addActionListener(actionListener);
        setText(text + "");
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
    }

    void reset() {
        setType("");
        setEnabled(true);
        setText("");
        setForeground(Color.BLACK);
    }


}
