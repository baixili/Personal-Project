package DTC;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome implements ActionListener {
    JFrame window = new JFrame("Where is Irene Indiana");
    JLabel background = new JLabel();
    ImageIcon image;
    JTextField textField;
    ImageIcon icon;
    JButton button = new JButton();
    JTextField notification;
    JTextArea inform = new JTextArea();
    JTextArea start = new JTextArea();
    String welcome;
    String basicRules;
    String name;
    int index = 0;
    String addCharacter = "";
    String totalCharacter = "";
    JTextArea introduction = new JTextArea();
    int introIndex = 0;

    JButton ruleButton = new JButton("detailed rules(for new player)");
    JButton startButton = new JButton("I am ready!");
    JPanel buttonPanel;

    int operatorIndex;
    int levelIndex;

    String startText;

    Welcome(int operatorIndex, int levelIndex) {
        this.operatorIndex = operatorIndex;
        this.levelIndex = levelIndex;


        // set a new frame to welcome to players and do the general introduction.
        window.setSize(800, 550);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the background of the frame.
        image = new ImageIcon(new ImageIcon("welcome.png")
                .getImage().getScaledInstance(800, 550, Image.SCALE_DEFAULT));
        background.setBounds(0, 0, 800, 550);
        background.setIcon(image);
        background.setOpaque(true);

        // set the textField which is used to ask the players to enter their username.
        textField = new JTextField(10);
        textField.setBounds(250, 430, 300, 50);
        textField.setText("Username");
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.red);
        textField.setCaretColor(Color.white);
        textField.setFont(new Font("Ink free", Font.BOLD, 35));
        textField.setEditable(true);

        // set the continuous button and make the button looks like an arrow
        button.setBounds(650, 430, 100, 50);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(new Color(20, 21, 26));
        icon = new ImageIcon(new ImageIcon("arrow.png")
                .getImage().getScaledInstance(70, 50, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.addActionListener(this);

        // add all the components to the frame
        window.add(button);
        window.add(textField);
        window.add(background);

        // set the frame to be visible and the location of it in the center.
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the user clicks the continuous button but the user does not enter
        // any word or the words exceed 10 letters, give the notification to the user.
        if (e.getSource() == button) {
            if (textField.getText().length() == 0) {
                // set the textField which is used to give the notification to the player.
                notification = new JTextField();
                notification.setBounds(250, 490, 400, 20);
                notification.setText("Username cannot be none!");
                notification.setForeground(Color.red);
                notification.setFocusable(true);
                notification.setBorder(BorderFactory.createEmptyBorder());
                notification.setBackground(new Color(7, 3, 11));
                window.add(notification);
            }
            else if (textField.getText().length() > 10) {
                notification = new JTextField();
                notification.setBounds(250, 490, 400, 20);
                notification.setText("Username cannot have more than 10 letters!");
                notification.setForeground(Color.red);
                notification.setFocusable(true);
                notification.setBorder(BorderFactory.createEmptyBorder());
                notification.setBackground(new Color(7, 3, 11));
                window.add(notification);
            }

            // If the username fits the requirement, give the general information to the user.
            else{
                name = textField.getText();
                new GeneralInform();
            }
        }

    }

    // This class is used to give the general information and welcome tht player.
    public class GeneralInform implements ActionListener {
        GeneralInform() {
            // set the welcome context.
            welcome = name + ", welcome to join our team. One task is waiting for you.";
            startText = "Good, " + name + ". Hope to see you back soon!      ";
            // make the continuous button and the information textField be visible again.
            button.setVisible(false);
            textField.setVisible(false);

            // Set the textArea used to contain all the informations.
            inform.setBounds(200, 370, 400, 200);
            inform.setWrapStyleWord(true);
            inform.setFont(new Font("Ink free", Font.BOLD, 32));
            button.addActionListener(this);
            inform.setLineWrap(true);

            // add the component to the frame.
            window.add(inform);
            informationTime.start();

        }
        Timer informationTime = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] character = welcome.toCharArray();
                // as long as the length at the moment is less than the total length of the text,
                // keep adding the current letter to the textArea.
                // Otherwise, stop the timer and make the button to be visible to make the user
                // to continue the game.
                if (index < character.length) {
                    addCharacter = String.valueOf(character[index]);
                    inform.append(addCharacter);
                    inform.setEditable(false);
                    totalCharacter += addCharacter;
                    index++;
                }
                else {
                    informationTime.stop();
                    button.setVisible(true);

                }
            }
        });

        @Override
        public void actionPerformed(ActionEvent e) {
            // If the continuous button being clicked, show the general information to the users.
            if (e.getSource() == button) {
                inform.setVisible(false);
                window.getContentPane().removeAll();
                window.repaint();
                addCharacter = "";
                totalCharacter = "";

                //show the basic rules, detailed rules and ask the player to start the game.
                new introduce();

            }
        }
    }

    // This class is used to show all, the basic information,
    // the detailed information and to ask
    // the player to start the game.
    public class introduce {
        introduce() {
            // reset the frame
            window.getContentPane().setBackground(new Color(51, 51, 51));
            basicRules = "Irene Indiana is hiding somewhere in" +
                    " Onslow College. After our investigation," +
                    " we found that she was hiding in a classroom " +
                    "in Onslow College which numbered " +
                    "from 01 to 50. We now need you to speculate " +
                    "which room she is in through " +
                    "the clues from ten informates who are " +
                    " now in 10 different rooms. Go to them for clues now." +
                    "Are you ready?";
            // set a textArea to contain the basic information.
            introduction.setBounds(80, 50, 650, 300);
            introduction.setEditable(false);
            introduction.setBackground(new Color(51, 51, 51));
            introduction.setFont(new Font("Serif", Font.ITALIC, 30));
            introduction.setForeground(Color.white);
            introduction.setLineWrap(true);
            introduction.setWrapStyleWord(true);
            introduction.setBorder(BorderFactory.createEmptyBorder());

            // add the component to the frame called window.
            window.add(introduction);

            // print the letter one by one.
            informationTime.start();

        }

        // This class is used to print the letter one by one in the text.
        Timer informationTime = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // as long as the length at the moment is less than the total length of the text,
                // keep adding the current letter to the textArea.
                // Otherwise, stop the timer and show the rules button and the start button.
                char[] textChar = basicRules.toCharArray();
                if (introIndex < textChar.length) {
                    addCharacter = String.valueOf(textChar[introIndex]);
                    introduction.append(addCharacter);
                    totalCharacter += addCharacter;
                    introIndex++;
                }
                if (introIndex >= textChar.length) {
                    informationTime.stop();
                    new Rules().RulesButton();

                }
            }
        });

    }

    // This class is used to set the button which is used to show the
    // detailed rules and the start button which directly start the game.
    public class Rules implements ActionListener {
        void RulesButton() {
            // set the button panels used to contain the two buttons.
            buttonPanel = new JPanel();
            buttonPanel.setBounds(120, 400, 500, 50);
            buttonPanel.setBackground(Color.green);
            buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));

            // set the rules button
            ruleButton.setBackground(Color.BLACK);
            ruleButton.setForeground(Color.white);
            ruleButton.setFocusable(false);
            ruleButton.addActionListener(this);

            // set the start button
            startButton.setBackground(Color.BLACK);
            startButton.setForeground(Color.white);
            startButton.setFocusable(false);
            startButton.addActionListener(this);

            // add the components to the panel and then to the frame.
            buttonPanel.add(ruleButton);
            buttonPanel.add(startButton);
            buttonPanel.setVisible(true);
            window.add(buttonPanel);
            window.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // If clicking the rule button, show the detailed rules to the users.
            if (e.getSource() == ruleButton) {
                new detailedRules();
            }

            // If clicking the start button, start the game and set the variable to be null to be reused.
            if (e.getSource() == startButton) {
                addCharacter = "";
                totalCharacter = "";
                new Start();
            }
        }

        // This class is used to note the player that the game is starting.
        public class Start {
            Start() {
                // remove all the component so the frame can be reused.
                // set the new background.
                window.getContentPane().removeAll();
                window.repaint();
                index = 0;
                window.getContentPane().setBackground(new Color(51, 51, 51));

                // set the textArea which is used to contain the words that used
                // to tell the player that the gane is starting
                start.setVisible(true);
                start.setBounds(120, 100, 500, 300);
                start.setFont(new Font("MV Boli", Font.PLAIN, 50));
                start.setOpaque(false);
                start.setWrapStyleWord(true);
                start.setLineWrap(true);
                start.setForeground(Color.white);

                // print the letter one by one
                startTime.start();

                // add the component to the frame and set the frame to be visible.
                window.add(start);
                window.setVisible(true);

            }

            //This class is used to print the letter in the word one by one.
            Timer startTime = new Timer(60, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // as long as the current length is shorter
                    // than the text length, keep printing the next word
                    // Otherwise, stop the timer and show the map, the energies and the life to the player
                    char[] charString = startText.toCharArray();
                    if (index < charString.length) {
                        addCharacter = String.valueOf(charString[index]);
                        start.append(addCharacter);
                        totalCharacter += addCharacter;
                        index++;
                    }
                    else {
                        startTime.stop();
                        new StartGame(operatorIndex, levelIndex);
                        window.dispose();
                    }

                }
            });
        }
    }

}

