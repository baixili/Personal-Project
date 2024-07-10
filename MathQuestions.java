package DTC;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MathQuestions extends Menu implements KeyListener {

    JLabel showQuestion = new JLabel();
    String question;
    int answer;
    JTextField textField = new JTextField();
    JTextArea basedAns = new JTextArea();
    int countdown;
    JTextField countdownLabel = new JTextField();
    int operatorIndex;
    int levelIndex;
    Map<Integer, Integer> add_sub = new HashMap<>();
    Map<Integer, Integer> mul_div = new HashMap<>();


    MathQuestions(int operatorIndex, int levelIndex) {
        this.operatorIndex = operatorIndex;
        this.levelIndex = levelIndex;
        Random random = new Random();
        // set the random numbers of addition and subtraction.
        int add_sub_num1 = random.nextInt(1000, 9999);
        int add_sub_num2 = random.nextInt(1000, 9999);

        // set the random numbers of multiplication.
        int mul_num1 = random.nextInt(100, 999);
        int mul_num2 = random.nextInt(10, 99);

        // set the random numbers of division.
        int div_number1 = random.nextInt(10, 99);
        int div_answer = random.nextInt(10, 99);
        int div_total_num = div_number1 * div_answer;

        // set the questions map which contain all the operators.
        Map<Integer, String> questionMap = new HashMap<>();
        questionMap.put(0, add_sub_num1 + " + " + add_sub_num2);
        questionMap.put(1, add_sub_num1 + " - " + add_sub_num2);
        questionMap.put(2, mul_num1 + " X " + mul_num2);
        questionMap.put(3, div_total_num + " / " + div_number1);
        // set the question of which is based on the player's choice.
        question = questionMap.get(operatorIndex);

        // set the answers map which contain all the answers.
        Map<Integer, Integer> answerMap = new HashMap<>();
        answerMap.put(0, add_sub_num1 + add_sub_num2);
        answerMap.put(1, add_sub_num1 - add_sub_num2);
        answerMap.put(2, mul_num1 * mul_num2);
        answerMap.put(3, div_answer);
        // se the answer which is based on the player's choice.
        answer = answerMap.get(operatorIndex);

        // set the time given to the player for addition and the subtraction.
        add_sub.put(0, 25);
        add_sub.put(1, 35);
        add_sub.put(2, 45);

        // set the time given to the player for multiplication and the division.
        mul_div.put(0, 35);
        mul_div.put(1, 45);
        mul_div.put(2, 55);

        // remove the components in the frame from the "Menu" to reuse it.
        frame.getContentPane().removeAll();
        frame.repaint();

        //reset the size of the frame.
        frame.setSize(300, 300);

        //set the label which used to show the questions.
        showQuestion.setBounds(50, 50, 300, 100);
        showQuestion.setFont(new Font("Ink free", Font.PLAIN, 35));
        showQuestion.setText(question);
        showQuestion.setOpaque(true);

        // The textField is used to ask the player to enter their answer.
        textField.setBounds(50, 200, 200, 30);
        textField.setCaretColor(Color.BLACK);
        // add the components to the frame.
        frame.add(textField);
        frame.add(showQuestion);
        textField.addKeyListener(this);

        // used to contain the countdown.
        countdownLabel.setBounds(230, 20, 50, 30);
        countdownLabel.setOpaque(false);
        countdownLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        countdownLabel.setBorder(BorderFactory.createEmptyBorder());
        countdownLabel.setEditable(false);

        // used to get the different countdown based on the level and the operators.
        new countdownSet();

        // start the countdown
        countdownTimer.start();

        // add the components to the frame, set the frame to be visible and the location to be in the center.
        frame.add(countdownLabel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    // This class is used to get the countdown.
    class countdownSet{
        countdownSet() {
            // in addition and subtraction, the timers are 25, 35, 45
            if(operatorIndex == 0 || operatorIndex == 1){
                countdown = add_sub.get(levelIndex);
            }
            // in multiplication and division, the timers are 35, 45, 55
            if(operatorIndex == 2 || operatorIndex == 3){
                countdown = mul_div.get(levelIndex);
            }


        }
    }

    // This class is used to delay the time that the frame being closed.
    Timer disposeTimer = new Timer(3500, e -> frame.dispose());

    // This class is used to start the countdown.
    Timer countdownTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // If the countdown is not equal to 0, continuous the countdown.
            // Otherwise, the user does not answer within the time. Reduce one life and stop the countdown.
            if(countdown >= 0){
                countdownLabel.setText(Integer.toString(countdown));
                countdown --;
            }
            else{
                countdownTimer.stop();
                // reset the countdown for next round.
                new countdownSet();
                // Tell the user that he loses.
                rightOrWrong("You did not answer within the time! Being reduced one life!");
                StaminaSystemClues.life--;
                // Give the time for the users to read the text.
                delay.start();
            }



        }
    });

    // This function is used to tell the user that they either do not answer the question correctly or within the time.
    void rightOrWrong(String word){
        frame.getContentPane().removeAll();
        frame.repaint();
        basedAns.setBounds(0, 50, 300, 300);
        basedAns.append(word);
        basedAns.setForeground(Color.red);
        basedAns.setFont(new Font("MV Boli", Font.BOLD, 30));
        basedAns.setLineWrap(true);
        basedAns.setWrapStyleWord(true);
        basedAns.setOpaque(false);
        basedAns.setEditable(false);
        frame.add(basedAns);
        frame.setVisible(true);
        // give the time for users to read the text.
        disposeTimer.start();
        disposeTimer.setRepeats(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // If the countdown is not 0, and the length of the answer is not 0, check the answer.
        // Otherwise, do nothing.
        if(countdown > 0) {
            if (textField.getText().length() > 0) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // If the answer is correct, give the award to the users and close the frame. Reset the countdown.
                    try {
                        if (Integer.parseInt(textField.getText()) == answer) {
                            frame.dispose();
                            countdownTimer.stop();
                            // reset the timer
                            new countdownSet();
                            // ask the user to choose an award.
                            new award(operatorIndex, levelIndex);
                        }
                        // Otherwise, tell the users that they are wrong.
                        else {
                            rightOrWrong("You are wrong! Being reduced one life!");
                            countdownTimer.stop();
                            // reset the timer.
                            new countdownSet();
                            StaminaSystemClues.life--;
                            // give the time to the users to read.
                            delay.start();

                        }
                    }
                    // If the answer entered does not fit the requirement, do nothing.
                    catch (Exception exception){

                    }

                }

            }
        }

    }

    // This class is used to check if the player have to make the guess now because of the lack of lives or energies.
    Timer delay = new Timer(2000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            delay.stop();
            frame.dispose();
            // enter all the rooms, have to make the guess.
            if(StaminaSystemClues.chance == 0 && StaminaSystemClues.life > 0){
                new makeTheGuess(operatorIndex, levelIndex, "You already entered all the rooms, have" +
                        " to make the guess now", 30);
            }
            // no enough energies, have to make the guess now.
            if(StaminaSystemClues.realEnergyGiven < 10 && StaminaSystemClues.life > 0){
                new makeTheGuess(operatorIndex, levelIndex, "You do not have enough energies to " +
                        "enter another room, have to make the guess", 25);
            }
            // everything is fine, ask the user to decide own.
            if(StaminaSystemClues.life > 0 && StaminaSystemClues.chance > 0
                    && StaminaSystemClues.realEnergyGiven > 0){
                new makeTheGuess(operatorIndex, levelIndex, "Do you want to make the guess now", 30);
            }
            // no lives, have to make the guess now.
            if(StaminaSystemClues.life == 0){
                new makeTheGuess(operatorIndex, levelIndex, "You do not have any life now," +
                        "but we give you one chance to guess", 25);
            }



        }
    });


    @Override
    public void keyReleased(KeyEvent e) {

    }
}