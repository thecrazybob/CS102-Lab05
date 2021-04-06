import javax.swing.UIManager;

public class PotLuckTester {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) { }
        // Launch the program
        new PotLuck();
    }
}
