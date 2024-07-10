package DTC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class makeTheGuess implements ActionListener{
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JTextArea textArea = new JTextArea();
    ImageIcon image = new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src\\DTC\\guess.png");
    String text;
    int index = 0;
    String addCharacter;
    JPanel panel = new JPanel();
    JButton guess;
    JButton giveUp;
    JTextField textField = new JTextField();
    JLabel ansLabel = new JLabel();
    JButton checkButton = new JButton("check");

    JTextArea finalPage = new JTextArea();
    String addedChar;
    int indexOfFinal = 0;


    int operatorIndex;
    int levelIndex;
    int size;



    makeTheGuess(int operatorIndex, int levelIndex, String text, int size){
        this.operatorIndex = operatorIndex;
        this.levelIndex = levelIndex;
        this.text = text;
        this.size = size;

        // set the frame.
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // set the label which is used to contain all the components.
        label.setBounds(0, 0, 600, 400);
        label.setIcon(image);
        label.setOpaque(true);

        // set the textArea which is used to ask whether the player wants to make the guess.
        textArea.setBounds(100, 50, 400, 200);
        textArea.setOpaque(false);
        textArea.setForeground(Color.white);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("MV Boli", Font.ITALIC, size));
        slowDown.start();
        label.add(textArea);

        // set the panel which contains tow button. One says yes and another says later.
        panel.setBounds(50, 250, 500, 50);
        panel.setLayout(new GridLayout(1, 1, 5, 5));

        // set the yes button which means that the player wants to make the guess.
        guess = new JButton("Yes");
        guess.setBorder(BorderFactory.createEmptyBorder());
        guess.addActionListener(this);
        guess.setFocusable(false);
        guess.setBackground(Color.gray);
        guess.setEnabled(true);
        guess.setForeground(Color.white);

        // set the later button which means that the player does not want to make the guess this time.
        giveUp = new JButton("Later");
        giveUp.setBorder(BorderFactory.createEmptyBorder());
        giveUp.addActionListener(this);
        giveUp.setFocusable(false);

        // if no lives, no enough energies, entered all the room, the player have to make the guess now.
        // the player is not allowed to choose the later button.
        if(StaminaSystemClues.chance == 0 || StaminaSystemClues.realEnergyGiven < 10
                || StaminaSystemClues.life == 0){
            giveUp.setEnabled(false);
            giveUp.setBackground(Color.BLACK);
        }
        giveUp.setBackground(Color.gray);
        giveUp.setForeground(Color.white);
        panel.add(guess);
        panel.add(giveUp);
        frame.add(label);
        frame.setVisible(true);
    }

    // This class is used to print the letter in the text on by one.
    Timer slowDown = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            char[] character = text.toCharArray();
            // if not all the letters in the text being printed out, keep printing the letters.
            if(index < character.length){
                addCharacter = String.valueOf(character[index]);
                textArea.append(addCharacter);
                index ++;
            }
            // otherwise, stop the timer and show the buttons to the players.
            else{
                slowDown.stop();
                label.add(panel);
                frame.setVisible(true);
            }
        }
    });

    @Override
    public void actionPerformed(ActionEvent e) {
        // if the player wants to make the guess, the frame that used ot verify appears.
        if(e.getSource() == guess){
            // the frame that the player enters the answers and to verify.
            new enterTheAns();
        }
        // Otherwise, reload the game from the map part.
        // dispose the frame.
        if(e.getSource() == giveUp){
            frame.dispose();
            new StartGame(operatorIndex, levelIndex);
        }
    }

    // This class is used to let the player enter the guess and to see if they are right.
    class enterTheAns extends StaminaSystemClues implements ActionListener{
        enterTheAns(){
            // reuse the frame.
            frame.getContentPane().removeAll();
            frame.repaint();

            // set the label to contain all the components includes background, textField, textArea and button.
            ansLabel.setBounds(0, 0, 600, 400);
            ansLabel.setIcon(image);
            ansLabel.setLayout(new FlowLayout());
            ansLabel.setOpaque(true);

            // set the textField which is used to let the player enter the answer.
            textField.setText("Enter your answer");
            textField.setFont(new Font("Ink free", Font.ITALIC, 20));

            ansLabel.add(textField);
            ansLabel.add(checkButton);
            frame.add(ansLabel);
            frame.setVisible(true);
            checkButton.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // If the player click the check button, start verify.
            if(e.getSource() == checkButton){
                // if the entered answer fits the requirement, see right or wrong.
                try{
                    // if correct, the player wins.
                    if(Objects.equals(String.format("%02d", Integer.parseInt
                            (textField.getText())), two_digit_number)){
                        // print the congratulations to the player.
                        new congratulation("Congratulations! Is the task too easy for you? Let’s" +
                                " increase the difficulty and see how much you can do", Color.green);

                    }
                    // if wrong, the player loses.
                    else{
                        // print the answer to the player and tell them that they lose.
                        new congratulation("The answer is " + two_digit_number +
                                ". You are just a short distance away from success." +
                                "Try to reduce the difficulty and try again.", Color.red);

                    }
                    // no longer change the answer and recheck.
                    checkButton.setEnabled(false);
                    textField.setEditable(false);

                }
                // If the entered answer does not fit the requirement, do nothing.
                catch (NumberFormatException exception){
                }
            }
        }

        // This class is used to print the congratulation text or loss text to the player.
        class congratulation{
            String win;
            Color color;
            congratulation(String win, Color color){
                this.win = win;
                this.color = color;
                // set the label used to contain all the components.
                ansLabel.setLayout(null);
                // set the textArea used to contain the text.
                finalPage.setBounds(50, 100, 500, 200);
                finalPage.setOpaque(false);
                finalPage.setLineWrap(true);
                finalPage.setWrapStyleWord(true);
                finalPage.setForeground(color);
                finalPage.setFont(new Font("Ink free", Font.ITALIC, 35));
                ansLabel.add(finalPage);
                frame.add(ansLabel);
                // print the letter on by one.
                delay.start();
        }
            // This class is used to print the letter in the text on by one.
            Timer delay = new Timer(70, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char[] character = win.toCharArray();
                    // If not all the letters being printed out, keep printing.
                    if(indexOfFinal < character.length){
                        addedChar = String.valueOf(character[indexOfFinal]);
                        finalPage.append(addedChar);
                        indexOfFinal ++;
                    }
                    // else stop the timer.
                    else{
                        delay.stop();
                    }
                }
            });
        }
    }
}
