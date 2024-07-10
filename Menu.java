package DTC;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu implements ActionListener{
    JFrame frame = new JFrame("Where is Irene Indiana");
    JLabel label;
    ImageIcon image;
    JTextArea operator = new JTextArea();
    String operatorText;
    String levelText;
    int levelLength;
    int operatorLength;
    int operatorChosen;
    int levelChosen;
    int operatorIndex;
    int levelIndex;
    String addedCharacter = "";
    String totalOperatorCharacter = "";
    String totalLevelCharacter = "";
    JButton addition;
    JButton subtraction;
    JButton times;
    JButton division;
    JButton[] allOperator;
    JPanel operatorPanel;
    JButton level1;
    JButton level2;
    JButton level3;
    JButton[] allLevel;
    JPanel levelPanel;
    JTextArea level = new JTextArea();
    int width = 800;
    int height = 550;


    Menu() {
        // set the frame
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the background image
        image = new ImageIcon(new ImageIcon("menu.png")
                .getImage().getScaledInstance(800, 550, Image.SCALE_DEFAULT));

        // set the label
        label = new JLabel();
        label.setBounds(0, 0, 800, 550);
        label.setIcon(image);
        label.setOpaque(true);

        // call the function which ask the player to choose the operator and the level they prefer
        operatorChosen();

        // add the label to the frame
        frame.add(label);


        // set the frame to be visible and the location of it to be in the middle
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // This function is used to show all the operators and the levels to the players
    // and ask them to choose what they prefer
    public void operatorChosen() {
        // set the textArea of the operator
        operator.setBounds(50, 50, 560, 50);
        operator.setFont(new Font("MV Boli", Font.BOLD, 25));
        operator.setForeground(Color.WHITE);
        operator.setLineWrap(false);
        operator.setWrapStyleWord(false);
        operator.setOpaque(false);
        operator.setBorder(BorderFactory.createEmptyBorder());
        label.add(operator);
        operatorText = "Choosing an operator you want to do below";

        // this Timer is used to print the letter one by one.
        operatorTimer.start();

        // create the four buttons
        addition = new JButton("4-digit addition");
        subtraction = new JButton("4-digit subtraction");
        times = new JButton("3-digit by 2-digit");
        division = new JButton("3-digits divided by 2-digits division");
        allOperator  = new JButton[]{addition, subtraction, times, division};

        // set the panel which contains all the operators.
        operatorPanel = new JPanel();
        operatorPanel.setBounds(50, 100, 550, 100);
        operatorPanel.setLayout(new GridLayout(2, 2, 5, 5));

        // set the buttons hence add all the buttons to the panel.
        for(int i = 0; i < 4; i++){
            allOperator[i].setBackground(Color.BLACK);
            allOperator[i].setFocusable(false);
            allOperator[i].setForeground(Color.white);
            allOperator[i].setBorder(BorderFactory.createEtchedBorder());
            allOperator[i].setFont(new Font("MV Boli", Font.PLAIN, 15));
            allOperator[i].addActionListener(this);
            operatorPanel.add(allOperator[i]);
        }

    }


    // This class is used to make the letters of the text being printed one by one
    Timer operatorTimer = new Timer(40, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // change the text to the characters.
            char[] character = operatorText.toCharArray();

            // as long as the length at the moment is less than the total length of the text,
            // keep adding the current letter to the textArea.
            // Otherwise, stop the timer and add the button panel to the frame to ask the player to choose.
            if(operatorLength < character.length){
                addedCharacter = String.valueOf(character[operatorLength]);
                operator.append(addedCharacter);
                totalOperatorCharacter += addedCharacter;
                operator.setEditable(false);
                operatorLength++;
            }
            if(totalOperatorCharacter.equals(operatorText)){
                operatorTimer.stop();
                frame.add(operatorPanel);
            }
        }
    });


    @Override
    public void actionPerformed(ActionEvent e) {
        // If the player chooses the operator, ser the colour to green and cannot edit anymore.
        for(operatorChosen = 0; operatorChosen < 4; operatorChosen++){
            if(e.getSource() == allOperator[operatorChosen]){
                operatorIndex = operatorChosen;
                allOperator[operatorChosen].setBackground(Color.GREEN);
                for(JButton element: allOperator){
                    element.setEnabled(false);
                }
                // keep printing out the level chosen part.
                new levelChosen();


            }
        }

    }

    // This class is used to ask the player to choose the level that they prefer to do.
    public class levelChosen implements ActionListener{
        levelChosen(){
            // set the text that will be printed out.
            levelText = "Choosing an level you want to do below";

            // set the textArea of it so the text can be shown on the frame.
            level.setBounds(50, 300, 560, 50);
            level.setFont(new Font("MV Boli", Font.BOLD, 25));
            level.setForeground(Color.WHITE);
            level.setLineWrap(false);
            level.setWrapStyleWord(false);
            level.setOpaque(false);
            level.setBorder(BorderFactory.createEmptyBorder());
            label.add(level);

            // print the letter of the text one by one.
            levelTimer.start();

            // If the operator chosen is addition or subtraction, times given is 25, 35, 45
            if (operatorIndex == 0 || operatorIndex == 1) {
                level1 = new JButton("25s");
                level2 = new JButton("35s");
                level3 = new JButton("45s");
            }

            // If the operator chosen is times or division, times given is 35, 45, 55.
            if (operatorIndex == 2 || operatorIndex == 3) {
                level1 = new JButton("35s");
                level2 = new JButton("45s");
                level3 = new JButton("55s");
            }
            allLevel = new JButton[]{level1, level2, level3};

            // set the panel of the level buttons
            levelPanel = new JPanel();
            levelPanel.setBounds(50, 350, 600, 100);
            levelPanel.setLayout(new GridLayout(2, 2, 5, 5));

            // add all the buttons to the panel.
            for (int i = 0; i < 3; i++) {
                allLevel[i].setBackground(Color.BLACK);
                allLevel[i].setFocusable(false);
                allLevel[i].setForeground(Color.white);
                allLevel[i].setBorder(BorderFactory.createEtchedBorder());
                allLevel[i].setFont(new Font("MV Boli", Font.PLAIN, 15));
                allLevel[i].addActionListener(this);
                levelPanel.add(allLevel[i]);
            }
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            // After choosing the level, set it to green and no longer editable.
            for(levelChosen = 0; levelChosen < 3; levelChosen++) {
                if (e.getSource() == allLevel[levelChosen]) {
                    allLevel[levelChosen].setBackground(Color.GREEN);
                    levelIndex = levelChosen;
                    for (JButton element : allLevel) {
                        element.setEnabled(false);
                    }
                }
            }
            // After finishing all the choices, start the introduction and welcome the players.
            new Welcome(operatorIndex, levelIndex);

            // dispose the old frame.
            frame.dispose();
        }
    }

    // This class is used to print the letters in the text one by one.
    Timer levelTimer = new Timer(40, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            char[] character = levelText.toCharArray();
            // as long as the current length is shorter than the text length, keep printing the next word
            if(levelLength < character.length){
                addedCharacter = String.valueOf(character[levelLength]);
                level.append(addedCharacter);
                totalLevelCharacter += addedCharacter;
                level.setEditable(false);
                levelLength++;
            }
            // Otherwise, stop the timer, add the panel to the frame to ask the player to make the choice.
            if(totalLevelCharacter.equals(levelText)){
                levelTimer.stop();
                label.add(levelPanel);
                frame.add(levelPanel);

            }
        }
    });


}
