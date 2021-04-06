
/**
 * Instructions for Lab05 are in CS102_Lab05.pdf file located in the root
 * directory of Lab05 Revisions can be seen on the following GitHub URL:
 * https://github.com/thecrazybob/CS102-lab05 Style Guidelines:
 * http://www.cs.bilkent.edu.tr/~adayanik/cs101/practicalwork/styleguidelines.htm
 *
 * @author Mohammed Sohail
 * @version 31/03/2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConvertNumber extends JFrame {

    // variables
    private JLabel hexLabel;
    private JLabel decimalLabel;
    private JLabel binaryLabel;
    private JTextField hexTextField;
    private JTextField binaryTextField;
    private JTextField decimalTextField;
    private JPanel panel;
    private GridLayout grid;

    // constructor
    public ConvertNumber() {

        setSize(300, 300);
        setTitle("Convert Numbers");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create Grid
        grid = new GridLayout(3, 3);
        grid.setHgap(5);
        grid.setVgap(10);

        // create Panel
        panel = new JPanel();
        panel.setLayout(grid);

        // create Labels
        decimalLabel = new JLabel("Decimal");
        hexLabel = new JLabel("Hexadecimal");
        binaryLabel = new JLabel("Binary");

        // create decimal text field
        decimalTextField = new JTextField();

        decimalTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decTxtAction(e);
            }
        });

        // create binary text field
        binaryTextField = new JTextField();

        binaryTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                binaryTextFieldAction(e);
            }
        });

        // create hex text field
        hexTextField = new JTextField();

        hexTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hexTextFieldAction(e);
            }
        });

        // add text fields to panel
        panel.add(decimalLabel);
        panel.add(decimalTextField);
        panel.add(hexLabel);
        panel.add(hexTextField);
        panel.add(binaryLabel);
        panel.add(binaryTextField);

        // add panel to frame
        add(panel);

    }

    /**
     * Calls Converstion methods when text is added to decimal field
     * 
     * @param e
     */
    private void decTxtAction(ActionEvent e) {
        int decimal = Integer.parseInt(decimalTextField.getText());
        hexTextField.setText(decToHex(decimal));
        binaryTextField.setText(decToBin(decimal));
    }

    /**
     * Calls Converstion methods when text is added to Binary field
     * 
     * @param e
     */
    private void binaryTextFieldAction(ActionEvent e) {
        int decimal = Integer.parseInt(binaryTextField.getText());
        hexTextField.setText(decToHex(Integer.parseInt(binToDec(decimal))));
        decimalTextField.setText(binToDec(decimal));
    }

    /**
     * Calls Converstion methods when text is added to hexadecimal field
     * 
     * @param e
     */
    private void hexTextFieldAction(ActionEvent e) {
        String hexString = hexTextField.getText();
        decimalTextField.setText(hexToDec(hexString));
        binaryTextField.setText(decToBin(Integer.parseInt(hexToDec(hexString))));
    }

    /**
     * Calls converstion methods when text is added to hex field
     * 
     * @param hex
     * @return String
     */
    private String hexToDec(String hex) {

        String hexstring = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int num = 0;
        String convDec = "";

        for (int i = 0; i < hex.length(); i++) {
            char ch = hex.charAt(i);
            int n = hexstring.indexOf(ch);
            num = 16 * num + n;
        }

        return (convDec + num);

    }

    /**
     * Converts decimal to hex
     * 
     * @param num
     * @return String
     */
    private String decToHex(int num) {

        int reminder;
        String convHex = "";
        char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        while (num > 0) {
            reminder = num % 16;
            convHex = hex[reminder] + convHex;
            num = num / 16;
        }

        return convHex;
    }

    /**
     * Converts binary to decimal
     * 
     * @param num
     * @return String
     */
    private String binToDec(int num) {

        String convDecimal = "";
        int decimal = 0;
        int temp;
        int i = 0;

        while (true) {
            if (num == 0) {
                break;
            } else {
                temp = num % 10;
                decimal = (int) (decimal + temp * Math.pow(2, i));
                num = num / 10;
                i++;
            }
        }

        convDecimal = convDecimal + decimal;

        return convDecimal;
    }

    /**
     * Converts decimal to binary
     * 
     * @param num
     * @return String
     */
    private String decToBin(int num) {

        int binary[] = new int[50];
        int i = 0;
        String convDecimal = "";

        while (num > 0) {
            binary[i++] = num % 2;
            num = num / 2;
        }

        for (int j = i - 1; j >= 0; j--) {
            convDecimal = convDecimal + binary[j];
        }

        return convDecimal;

    }

}