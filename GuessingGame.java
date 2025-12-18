import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int random;
    private int no_of_guess = 0;
    private JTextField guessField;
    private JTextArea resultArea;
    private JButton guessButton;

    public GuessingGame() {
        setTitle("Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Generate a random number between 1 and 100
        Random rand = new Random();
        random = rand.nextInt(100) + 1;

        // Create components
        guessField = new JTextField();
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        guessButton = new JButton("Guess");

        // Add components to the frame
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your guess (1-100):"));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Add action listener for the guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        // Set background with numbers
        setBackgroundWithNumbers();
    }

    private void makeGuess() {
        String input = guessField.getText();
        int guess;

        try {
            guess = Integer.parseInt(input);
            no_of_guess++;

            if (guess < 1 || guess > 100) {
                resultArea.append("Please enter a number between 1 and 100.\n");
            } else if (guess < random) {
                resultArea.append("Enter a larger number.\n");
            } else if (guess > random) {
                resultArea.append("Enter a smaller number.\n");
            } else {
                resultArea.append(String.format("Congratulations!!! You have successfully guessed it in %d attempts.\n", no_of_guess));
                resultArea.append("Thank You for Playing the Game.\n");
                guessButton.setEnabled(false); // Disable the button after winning
            }
        } catch (NumberFormatException e) {
            resultArea.append("That's not a valid number! Please enter an integer between 1 to 100.\n");
        }

        guessField.setText(""); // Clear the input field
    }

    private void setBackgroundWithNumbers() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw numbers on the background
                for (int i = 0; i < 100; i++) {
                    g.setColor(new Color(200, 200, 200, 50)); // Light gray with transparency
                    g.drawString(String.valueOf(i + 1), (int) (Math.random() * getWidth()), (int) (Math.random() * getHeight()));
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessingGame game = new GuessingGame();
            game.setVisible(true);
        });
    }
}
