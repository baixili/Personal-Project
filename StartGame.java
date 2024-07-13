package DTC;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartGame implements ActionListener {
    static ArrayList<Integer> clickedButton = new ArrayList<>();
    int buttonIndex;
    JFrame frame = new JFrame("Game start");
    JLabel label = new JLabel();
    JButton[] gridButton = new JButton[25];
    JPanel buttonPanel = new JPanel();
    int operatorIndex;
    int levelIndex;
    static JTextArea stamina = new JTextArea();





    StartGame(int operatorIndex, int levelIndex){

        StaminaSystemClues.chance --;
        this.operatorIndex = operatorIndex;
        this.levelIndex = levelIndex;

        // set the frame
        frame.setSize(650, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the label which contain the all the components inside.
        label.setBounds(0, 0, 650, 750);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);

        // set the panel which contains all the buttons.
        buttonPanel.setBounds(0, 89, 650, 560);
        buttonPanel.setLayout(new GridLayout(5, 5));
        buttonPanel.setBackground(Color.BLACK);

        // set all the rooms can be entered as buttons
        int buttonText = 1;
        // add the room numbers to the buttons
        for(buttonIndex = 0; buttonIndex < 25; buttonIndex++){
            String buttonTextString = String.format("%02d", buttonText);
            gridButton[buttonIndex] = new JButton();
            gridButton[buttonIndex].setFocusable(false);
            // the 12th button is the starting place.
            if(buttonIndex == 12){
                gridButton[buttonIndex].setText("start Place");
            }
            // these buttons below represent the room numbers from 1 to 12.
            else if(buttonIndex == 1 || buttonIndex == 2 || buttonIndex == 3 || buttonIndex == 5 || buttonIndex == 9 ||
                    buttonIndex == 10 || buttonIndex == 14 || buttonIndex == 15 || buttonIndex == 19 || buttonIndex == 21
                    || buttonIndex == 22 || buttonIndex == 23){
                gridButton[buttonIndex].setText(buttonTextString);
                buttonText++;
                gridButton[buttonIndex].addActionListener(this);
            }
            // The other buttons represent nothing so the players cannot do anything to them.
            else{
                gridButton[buttonIndex].setBackground(new Color(204, 204, 204));
            }
            gridButton[buttonIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // add the buttons to the panel.
            buttonPanel.add(gridButton[buttonIndex]);
        }

        // If the button being clicked, no longer to press again, so, the colour will be red and not editable.
        for(int element: clickedButton){
            gridButton[element].setBackground(Color.RED);
            gridButton[element].setForeground(Color.BLACK);
            gridButton[element].setEnabled(false);
        }
        // Add the component to the label.
        label.add(buttonPanel);
        System.out.println("energies" + StaminaSystemClues.realEnergyGiven);

        // show the lives and the energies that the players have.
        new information(StaminaSystemClues.realEnergyGiven, StaminaSystemClues.life, stamina, label);

        // add the label to the frame.
        frame.add(label);

        // set the frame to be visible and the location of the frame in the middle.
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    // This class is used to show the present energies and the lives that the player have.
    static class information{
        information(int energy, int lives, JTextArea textArea, JLabel textLabel){
            // set the textArea that is used to contain the energies and the lives.
            // add the lives and the energies to the textArea.
            textArea.setBounds(10, 15, 130, 60);
            textArea.setText("Energy: " + energy + "\nLives: " + lives);
            textArea.setEditable(false);
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.white);
            textArea.setFont(new Font("Ink Free", Font.BOLD, 25));
            textLabel.add(textArea);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the right button is being clicked,
        // reduces the energies and show the math questions to the player.
        for(buttonIndex = 0; buttonIndex < 25; buttonIndex++){
            if(e.getSource() == gridButton[buttonIndex]){
                StaminaSystemClues.realEnergyGiven -= StaminaSystemClues.energyCost;
                // add the clicked buttons to the arrayList to change
                // the colour and the editable of the buttons.
                clickedButton.add(buttonIndex);
                // show the math question to the player and dispose the frame.
                new MathQuestions(operatorIndex, levelIndex);
                frame.dispose();

            }
        }


    }
}
