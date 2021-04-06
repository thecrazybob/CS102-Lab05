import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * TODO: Bomb icon
 * TODO: Part (b)
 * PotLuck
 */
public class PotLuck {

    // CONSTANTS

    // no. of columns / rows in grid (5 = 5x5)
    private final int GRIDSIZE = 5;
    
    // size of grid in pixels
    private static final int SIZE = 500;

    // number of bombs
    final int BOMBS = 2;

    // number of prizes
    final int PRIZES = 1;

    // number of maximum attempts
    final int MAXATTEMPTS = 26;

    // instance of random number generator
    final Random random = new Random();

    // VARIABLES

    // no. of attempts by the user
    private int attempts;

    // the main JFrame
    private JFrame frame;

    // the reset button
    private JButton reset;

    // the label for number of attempts
    JLabel attemptsText;

    // the cells for PotButton
    private PotButton[][] cells;

    // action listener for all buttons
    private final ActionListener actionListener = actionEvent -> {

        Object source = actionEvent.getSource();

        if (source == reset) {
            reset();
        }

        else {
            this.handleClick((PotButton) source);
        }

    };

    // constructor
    PotLuck() {

        // Initialize attempts variable
        attempts = 0;

        // Create 5x5 cells using PotButton
        cells = new PotButton[GRIDSIZE][GRIDSIZE];

        // Create new frame using SIZE (in px)
        frame = new JFrame("PotLuck");
        frame.setSize(SIZE, SIZE);
        frame.setLayout(new BorderLayout());

        // Create Panel for Attempts
        JPanel attemptsPanel = new JPanel();
        attemptsText = new JLabel();
        updateStatus(0);
        attemptsPanel.add(attemptsText);
        
        // Create Panel for Reset Button
        JPanel buttonPanel = new JPanel();
        reset = new JButton("Reset");
        reset.addActionListener(actionListener);
        buttonPanel.add(reset);

        // Add Reset Panel to bottom of Frame
        frame.add(attemptsPanel, BorderLayout.PAGE_END);
        frame.add(buttonPanel, BorderLayout.PAGE_START);

        this.createCells();
        this.initializeBoard();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Creates cell with bombs and prizes
     * @return void
     */
    private void createCells() {

        // Create Grid the size of GRIDSIZE
        Container grid = new Container();
        grid.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));

        // Add PotButton to cells[][]
        for (int row = 0; row < GRIDSIZE; row++) {

            for (int col = 0; col < GRIDSIZE; col++) {
                cells[row][col] = new PotButton(row, col, actionListener, 0);
                grid.add(cells[row][col]);
            }
            
        }

        // Add grid to center of frame
        frame.add(grid, BorderLayout.CENTER);
    }

    public void initializeBoard() {

        // Map all (row, col) pairs to unique integers
        Set<Integer> positions = new HashSet<>(GRIDSIZE * GRIDSIZE);
        
        // Add PotButton to cells[][]
        for (int row = 0; row < GRIDSIZE; row++) {

            for (int col = 0; col < GRIDSIZE; col++) {
                int text = (col+1) + (row*GRIDSIZE);
                cells[row][col].setText(text + "");
                positions.add(row * GRIDSIZE + col);
            }
            
        }

        // Add bombs
        for (int index = 0; index < BOMBS; index++) {
            int choice = random.nextInt(positions.size());
            positions.remove(choice);
            int row    = choice / GRIDSIZE;
            int col    = choice % GRIDSIZE;
            cells[row][col].setType("bomb");
            positions.remove(choice);
        }

        // Add prize(s)
        for (int index = 0; index < PRIZES; index++) {
            int choice = random.nextInt(positions.size());
            int row    = choice / GRIDSIZE;
            int col    = choice % GRIDSIZE;
            cells[row][col].setType("prize");
            positions.remove(choice);
        }

    }

    private void reset() {

        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                cells[row][col].reset();
            }
        }

        this.attempts = 0;
        this.updateStatus(0);
        this.initializeBoard();

    }

    private boolean isLimitExceeded() {
        return attempts >= MAXATTEMPTS - 1;
    }

    private void updateStatus(int code) {
        /*
         1: won
         0: lost
         -1: limit
        */
        if (code == 1) {
            attemptsText.setText("You got it in " + (MAXATTEMPTS - this.attempts) + " attempts!");
        }
        else if (code == -1) {
            attemptsText.setText("Sorry! You are blown up at attempt " + (MAXATTEMPTS - this.attempts) + "!");
        }
        else {
            attemptsText.setText("Number of attempts left: " + (MAXATTEMPTS - this.attempts));
        }
    }
    
    private void handleClick(PotButton cell) {

        if (!isLimitExceeded()) {
            attempts++;
            cell.reveal();

            if (cell.getType() == "prize" || cell.getType() == "bomb") {

                if (cell.getType() == "prize") {
                    updateStatus(1);
                    announce("Congratulations! You found the prize!", 1);
                }

                else if (cell.getType() == "bomb") {
                    cell.setForeground(Color.RED);
                    updateStatus(0);
                    announce("You clicked on a bomb!", -1);
                }

                for (int row = 0; row < GRIDSIZE; row++) {
                    for (int col = 0; col < GRIDSIZE; col++) {
                        if (cells[row][col].getType() == "prize" || cells[row][col].getType() == "bomb") {
                            cells[row][col].reveal();
                        }    
                    }
                }

            }

            else {
                updateStatus(0);
            }
        }
        else {
            updateStatus(0);
            announce("You have exceeded the maximum number of limits", 0);
        }
    
    }

    private void announce(String message, int result) {
        if (result == 1) {
            JOptionPane.showMessageDialog(
                    frame, message, "Game Won!",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            JOptionPane.showMessageDialog(
                    frame, message, "Game Over",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    
}