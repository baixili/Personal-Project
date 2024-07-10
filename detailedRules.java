package DTC;

import javax.swing.*;
import java.awt.*;

public class detailedRules{
    String text = "1. You need to answer a question first before going " +
            "into each room. If you answer correctly, you can " +
            "choose either to get the clue or replenish your energy.\n" +
            "\n2. After seeing all the informants, you have to tell which " +
            "room you think Irene is at.\n\n3. Stamina system\n" +
            "    -You will get random amount of energies in the beginning of the game\n" +
            "    -Entering each room costs 10 energies.\n" +
            "    -You have 3 lives.\n" +
            "    -Answer wrong loses 1 life.\n " +
            "\n4. Some rooms have \"surprises\"\n" +
            "\n6. After going to all the rooms, you must make your guess.\n" +
            "\n7. How to lose.\n    -Lose all the lives.\n    " +
            "-Wrong number guess(you only have one chance).\n    " +
            "-Do not have enough energy to enter another room\n    " +
            "\n8. How to win the game.\n    -The only way to win the game" +
            " is to guess the number correctly!";
    JTextArea rules = new JTextArea();
    JFrame frame = new JFrame("Detailed rules");

    detailedRules(){
        frame.setSize(1100, 800);
        frame.getContentPane().setBackground(Color.red);
        rules.setBounds(0, 0, 1100, 800);
        rules.setWrapStyleWord(true);
        rules.setLineWrap(true);
        rules.setFont(new Font("Consolas", Font.ITALIC, 22));
        rules.setForeground(Color.WHITE);
        rules.setBackground(new Color(51, 51, 51));
        rules.setText(text);
        rules.setEditable(false);
        frame.add(rules);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
