package DTC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class allTheRoom implements ActionListener {
    JFrame frame;
    JFrame buttonFrame = new JFrame();
    JButton[] allButton = new JButton[50];
    JPanel panel = new JPanel();
    JButton openButton = new JButton("1-50 rooms map");

    allTheRoom(){
        // make the frame.
        frame = new JFrame();
        frame.setLocation(1050, 500);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // set the button to open the frame which shows all the rooms.
        openButton.setFocusable(false);
        openButton.setFont(new Font("Ink free", Font.BOLD, 20));
        openButton.setForeground(Color.white);
        openButton.setBackground(Color.BLACK);
        openButton.addActionListener(this);

        // add the components, set the frame be visible and set the pack.
        frame.add(openButton);
        frame.setVisible(true);
        frame.pack();

        // set the panel as the container to contain all the rooms as the buttons.
        panel.setLayout(new GridLayout(5, 10, 5, 5));

        // set all the buttons and add them to the panel.
        for(int index = 1; index < 51; index++){
            allButton[index - 1] = new JButton();
            allButton[index - 1].setText(String.valueOf(index));
            allButton[index - 1].setFocusable(false);
            allButton[index - 1].setForeground(Color.white);
            allButton[index - 1].setBackground(new Color(0, 204, 0));
            allButton[index - 1].addActionListener(this);
            panel.add(allButton[index - 1]);
        }
        // add the components to the button frame.
        buttonFrame.add(panel);
        buttonFrame.setLocationRelativeTo(null);
        buttonFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        buttonFrame.pack();
    }
    // to make the frame which contains all the rooms as the buttons be visible after click the button.
    void roomShow(){
        buttonFrame.setVisible(true);
        }

        // This class is used to open the frame contains all the rooms after clicking the open button.
    @Override
    public void actionPerformed(ActionEvent e) {
        // if clicking the button, show the rooms.
        if(e.getSource() == openButton){
            roomShow();
        }

        // If the player click the room button, the colour will change from green to red if it is green.
        // If the room has colour of red, clicking will change the colour from red to green.
        for(int index = 0; index < 50; index++){
            if(e.getSource() == allButton[index]){
                if(allButton[index].getBackground() == Color.RED){
                    allButton[index].setBackground(new Color(0, 204, 0));
                }
                else{
                    allButton[index].setBackground(Color.RED);
                }
            }
        }
    }
}
