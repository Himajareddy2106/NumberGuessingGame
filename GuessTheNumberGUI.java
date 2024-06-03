import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame {
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 100;
    private final int MAX_ATTEMPTS = 5;
    private final int POINTS_PER_ATTEMPT = 10;

    private JLabel roundLabel;
    private JLabel attemptsLabel;
    private JTextField guessField;
    private JButton guessButton;

    private Random random;
    private int randomNumber;
    private int attempts;
    private int score;
    private int rounds;

    public GuessTheNumberGUI() {
        setTitle("Guess the Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        roundLabel = new JLabel("Round: ");
        attemptsLabel = new JLabel("Attempts remaining: " + MAX_ATTEMPTS);
        guessField = new JTextField();
        guessButton = new JButton("Guess");

        add(roundLabel);
        add(attemptsLabel);
        add(guessField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(guessButton);
        add(buttonPanel);

        random = new Random();
        randomNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        attempts = 0;
        score = 0;
        rounds = 0;

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attempts < MAX_ATTEMPTS) {
                    int guess = Integer.parseInt(guessField.getText());
                    attempts++;

                    if (guess == randomNumber) {
                        score += POINTS_PER_ATTEMPT * (MAX_ATTEMPTS - attempts + 1);
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + attempts + " attempts.\nYour score: " + score);
                        startNewRound();
                    } else if (guess < randomNumber) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.");
                    }

                    attemptsLabel.setText("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                    guessField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry, you've used all your attempts.\nThe number was: " + randomNumber);
                    startNewRound();
                }
            }
        });

        setVisible(true);
    }

    private void startNewRound() {
        rounds++;
        roundLabel.setText("Round: " + rounds);
        attempts = 0;
        attemptsLabel.setText("Attempts remaining: " + MAX_ATTEMPTS);
        randomNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumberGUI();
            }
        });
    }
}
