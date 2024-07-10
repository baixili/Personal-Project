package DTC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class award implements ActionListener{
    int operatorIndex;
    int levelIndex;
    JTextArea clueText = new JTextArea();

    static JFrame awardFrame = new JFrame("Award");
    JTextArea awardText = new JTextArea();
    String addCharacter;
    String totalCharacter;
    JButton energyButton;
    JButton clueButton;
    JButton[] allButton;
    JPanel buttonPanel = new JPanel();
    String text;
    String clue;
    JLabel label = new JLabel();
    JLabel imageLabel = new JLabel();
    static JLabel verifyLabel = new JLabel();
    static JTextArea Boolean = new JTextArea();
    int answerRight = 0;
    int clueIndex = 0;


    static JFrame checkFrame = new JFrame("Award");
    JLabel StaminaLabel = new JLabel();
    JTextArea StaminaTextArea = new JTextArea();


    JButton check = new JButton("check");
    JTextField number = new JTextField("Enter a number, we will verify");
    ImageIcon background = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\awardBG.png")
            .getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT));
    ArrayList<Integer> randomClue = StaminaSystemClues.randomClue;


    award(int operatorIndex, int levelIndex) {
        this.operatorIndex = operatorIndex;
        this.levelIndex = levelIndex;

        // set the frame
        awardFrame.setSize(600, 400);
        awardFrame.setLayout(null);
        awardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the label which contains the energies and the lives
        StaminaLabel.setBounds(0, 0, 150, 110);
        StaminaLabel.setOpaque(true);
        // show the lives and the energies to the players.
        new StartGame.information(StaminaSystemClues.realEnergyGiven,
                StaminaSystemClues.life, StaminaTextArea, StaminaLabel);
        awardFrame.add(StaminaLabel);
        text = "Congratulations! Choose what you want below";

        // set the textArea used to tell the players that they answer correctly.
        awardText.setBounds(30, 100, 550, 120);
        awardText.setOpaque(false);
        awardText.setForeground(new Color(0, 255, 51));
        awardText.setFont(new Font("MV Boli", Font.BOLD, 35));
        awardText.setEditable(false);
        awardText.setWrapStyleWord(true);
        awardText.setLineWrap(true);

        // make the location in the center and set the frame to be visible.
        awardFrame.setVisible(true);
        awardFrame.setLocationRelativeTo(null);

        // the button panel is used to contain the two awards buttons.
        buttonPanel.setBounds(50, 300, 500, 50);
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));

        // the award buttons.
        energyButton = new JButton("20 energies");
        clueButton = new JButton("1 clue");
        allButton = new JButton[]{clueButton, energyButton};

        // add the two award buttons to the button panel.
        for (int i = 0; i < 2; i++) {
            allButton[i].setBackground(new Color(51, 51, 51));
            allButton[i].setBorder(BorderFactory.createEmptyBorder());
            allButton[i].setFocusable(false);
            allButton[i].setForeground(Color.white);
            allButton[i].addActionListener(this);
            allButton[i].setFont(new Font("Ink free", Font.BOLD, 20));
            buttonPanel.add(allButton[i]);
        }

        // print the congratulation part one letter by one letter.
        congratulation.start();
        awardFrame.add(awardText);
        awardFrame.setVisible(true);
    }

    // This class is used to print the letters in the congratulation text one by one.
    Timer congratulation = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            char[] character = text.toCharArray();
            // keep printing the letters as long as it is not finishing the printing.
            if(answerRight < character.length){
                addCharacter = String.valueOf(character[answerRight]);
                awardText.append(addCharacter);
                totalCharacter += addCharacter;
                answerRight++;
            }
            // Otherwise, stop the timer and show the buttons to the players.
            if(answerRight >= character.length){
                congratulation.stop();
                awardFrame.add(buttonPanel);
                awardFrame.setVisible(true);
                answerRight = 0;
                addCharacter = "";
                totalCharacter = "";
            }
        }
    });

    // This function is used to print out all the clues.
    public void getClue(int remain){
        // reset the frame to reuse it.
        awardFrame.getContentPane().removeAll();
        awardFrame.repaint();
        awardFrame.setSize(600, 400);
        label.setLayout(null);

        // set the label to contain all the components includes NPC image, words, backgrounds.
        label.setBounds(0, 0, 600, 400);
        label.setIcon(background);
        imageLabel.setBounds(200, 100, 200, 300);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(0, 0, 0, 0));
        label.setOpaque(true);
        imageLabel.setIcon(StaminaSystemClues.totalClues.get(remain));
        awardFrame.add(label);
        label.add(imageLabel);

        // get the clue text and set the textArea to contain the text.
        clue = StaminaSystemClues.initial_clue.get(randomClue.get(remain));
        clueText.setEditable(false);
        clueText.setForeground(new Color(255, 102, 102));
        clueText.setOpaque(false);
        clueText.setFont(new Font("MV Boli", Font.PLAIN, 35));
        clueText.setBounds(20, 20, 550, 200);
        clueText.setLineWrap(true);
        clueText.setWrapStyleWord(true);
        label.add(clueText);

        // set the text to be visible.
        awardFrame.setVisible(true);
    }

    // This class is used to judge if the player's entering
    // answer is correct or not if they enter the surprised room.
    public static class TrueOrFalse{
        String input;
        Color colour;
        int operatorIndex;
        int levelIndex;
        TrueOrFalse(String input, Color colour, int operatorIndex, int levelIndex){
            this.operatorIndex = operatorIndex;
            this.levelIndex = levelIndex;
            this.input = input;
            this.colour = colour;
            // Set the textArea to print True/False
            Boolean = new JTextArea();
            Boolean.append(input);
            Boolean.setEditable(false);
            Boolean.setBounds(150, 150, 300, 200);
            Boolean.setFont(new Font("MV Boli", Font.BOLD, 100));
            Boolean.setForeground(colour);
            Boolean.setOpaque(false);
            verifyLabel.add(Boolean);
            checkFrame.add(verifyLabel);
            checkFrame.validate();
            checkFrame.repaint();
        }
    }

    // This class is used to verify the surprise rooms
    public class verify implements ActionListener{
        verify(int remain){
            // if the clue number the entered room has is 5, add the energies to the player.
            if(randomClue.get(remain) == 5){
                StaminaSystemClues.realEnergyGiven += StaminaSystemClues.good_surprise;
            }
            // if the clue number the entered room has is 7, minus the energies to the player.
            if(randomClue.get(remain) == 7){
                StaminaSystemClues.realEnergyGiven -= StaminaSystemClues.bad_surprise;
            }
            // if the number is 9 or 11, the player has one chance to enter one room number.
            // the computer will tell the players if the room number is right or wrong.
            if(randomClue.get(remain) != 9 || randomClue.get(remain) != 11) {
                awardFrame.dispose();
                // set another frame to show that.
                checkFrame.setSize(600, 400);
                checkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                checkFrame.setLocationRelativeTo(null);
                // set a label to contain all the components.
                verifyLabel.setBounds(0, 0, 600, 400);
                verifyLabel.setIcon(background);
                verifyLabel.setLayout(new FlowLayout());
                //set a textField to ask the player to enter.
                number.setFont(new Font("Ink free", Font.PLAIN, 25));
                check.setFocusable(false);
                // add the components to the relative containers.
                verifyLabel.add(number);
                verifyLabel.add(check);
                check.addActionListener(this);
                checkFrame.add(verifyLabel);
                checkFrame.setVisible(true);
                verifyLabel.setLayout(null);

            }
            // Otherwise, ask the player to make the guess or not.
            else{
                // make sure that the player can read all the text.
                frameDelay.start();
            }
        }

        // This class is used to judge if the entered room number is equal to the right room number.
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == check){
                int ans = 0;
                // get the text in the textField.
                try{
                    ans = Integer.parseInt(number.getText());

                }
                // do nothing if the entered room number does not fit the requirements.
                catch (NumberFormatException exception){
                    System.out.println("");
                }
                // If they are equal, show True in green.
                if(Objects.equals(String.format("%02d", ans), StaminaSystemClues.two_digit_number)){
                    new TrueOrFalse("True", Color.green, operatorIndex, levelIndex);
                }
                // Else, show False in red.
                else{
                    new TrueOrFalse("False", Color.red, operatorIndex, levelIndex);
                }
                number.setEditable(false);
                check.setEnabled(false);
            }

            // make sure that the player can read all the text clearly.
            frameDelay.start();
        }

        // This class is used to make sure that the player can read all the text, after that
        // ask the player to make the guess based on different situations.
        Timer frameDelay = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // having rooms have not entered without enough energies, the player must make the guess.
                if (StaminaSystemClues.realEnergyGiven < 10 && StaminaSystemClues.chance > 0) {
                    new makeTheGuess(operatorIndex, levelIndex,
                            "You do not have enough energies to enter another room," +
                                    "have to make the guess now", 25);
                }

                // have rooms have nto entered with enough energies, based on player's choice.
                else if (StaminaSystemClues.chance > 0) {
                    new makeTheGuess(operatorIndex, levelIndex,
                            "Do you want to make the guess now", 35);
                }

                // entering all the rooms without enough energies, must make the guess now.
                else if (StaminaSystemClues.chance == 0) {
                    new makeTheGuess(operatorIndex, levelIndex,
                            "You already entered all the rooms, you have to make the guess now", 30);
                }
                // rerun the frames so when the game is reloading, everything restarted.
                // stop the timer.
                awardFrame.dispose();
                checkFrame.dispose();
                frameDelay.stop();
                awardFrame.getContentPane().removeAll();
                awardFrame.invalidate();
                awardFrame.validate();
                awardFrame.repaint();
                checkFrame.getContentPane().removeAll();
                checkFrame.invalidate();
                checkFrame.validate();
                checkFrame.getContentPane().repaint();
            }
        });
    }

    // This class is used to print the letters in the clue text one by one.
    Timer clueSlowDown = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            char[] character = clue.toCharArray();
            // as long as the letters are not the whole text, keep printing.
            if(clueIndex < character.length){
                addCharacter = String.valueOf(character[clueIndex]);
                clueText.append(addCharacter);
                totalCharacter += addCharacter;
                clueIndex++;
            }

            // if all the clue context being printed, stop the timer and
            // start to check if the room is one of the surprising room.
            if(clueIndex >= character.length){
                clueSlowDown.stop();
                surprise.start();
            }
        }
    });

    // This class is used to check if the entered room is one of the surprising room.
    Timer surprise = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // check the surprise.
            // stop the timer.
            new verify(StaminaSystemClues.chance);
            surprise.stop();
        }
    });

    @Override
    public void actionPerformed(ActionEvent e) {
        // if the player chooses one clue, show the clue to the player.
        if(e.getSource() == allButton[0]){
            // print the letter in the clue text one by one.
            clueSlowDown.start();
            getClue(StaminaSystemClues.chance);


        }
        // if the player chooses to add 20 energies, reload the game.
        if(e.getSource() == allButton[1]){
            StaminaSystemClues.realEnergyGiven += 20;
            // dispose the frame, reset the frame and ask the player to make the guess.
            awardFrame.dispose();
            awardFrame.getContentPane().removeAll();
            awardFrame.validate();
            awardFrame.revalidate();
            awardFrame.repaint();
            // no room can be entered, have to make the guess now.
            if(StaminaSystemClues.chance == 0){
                new makeTheGuess(operatorIndex, levelIndex, "You have entered all the rooms," +
                        "have to make the guess now", 25);
            }
            // else, asking as normal.
            else{
                new makeTheGuess(operatorIndex, levelIndex, "Do you want to make the guess now", 35);
            }

        }

    }
}
