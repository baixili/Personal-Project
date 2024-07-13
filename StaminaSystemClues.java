package DTC;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StaminaSystemClues{
    int width = 200;
    int height = 265;
    static int chance = 12;
    static int energyCost = 10;
    static Random random = new Random();
    static final int room_number = random.nextInt(1, 52);
    static final String two_digit_number = String.format("%02d", room_number);

    ArrayList<Integer> energyGiven = new ArrayList<>();

    static int realEnergyGiven;

    ArrayList<Integer> goodSurprise = new ArrayList<>();
    ArrayList<Integer> badSurprse = new ArrayList<>();
    static int good_surprise;
    static int bad_surprise;
    static int life = 3;

    String is_even;
    String room_range;
    String multiple_of_4;
    int exclude;
    int exclude1;
    int exclude2;
    int exclude3;
    int exclude4;
    String more_room_range;
    int possible_room_number;
    ArrayList<Integer> possibility = new ArrayList<>();
    ArrayList<HashMap<Integer, String>> set_clues = new ArrayList<>();

    static ArrayList<Integer> randomClue = new ArrayList<>();
    static ArrayList<ImageIcon> totalClues = new ArrayList<>();
    static HashMap<Integer, String> initial_clue = new HashMap<>();

    // set the images of NPC
    ImageIcon clue1 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue1.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue2 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue2.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue3 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue3.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue4 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue4.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue5 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue5.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue6 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue6.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue7 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue7.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue8 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue8.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue9 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue9.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue10 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue10.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue11 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue11.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    ImageIcon clue12 = new ImageIcon(new ImageIcon("C:\\Users\\li796\\IdeaProjects\\Java exerise\\src" +
            "\\DTC\\clue12.png")
            .getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    StaminaSystemClues(){

        // add all the possible energies to the arrayList.
        energyGiven.add(60); energyGiven.add(70); energyGiven.add(80);

        // add all the possible surprise to the arrayList.
        goodSurprise.add(10); goodSurprise.add(20); goodSurprise.add(30);
        badSurprse.add(10); badSurprse.add(20);

        // add all the appeared numbers to the arrayList.
        for(int i = 0; i <= 9; i++){
            possibility.add(i);
        }

        // set the initial energy given only at the beginning of the game.
        if(chance == 12){
            System.out.println(energyGiven);
            realEnergyGiven = energyGiven.get(random.nextInt(0, 3));
        }

        System.out.println("The answer is " + two_digit_number);



        // set the possible index of the good_energies
        good_surprise = goodSurprise.get(random.nextInt(0, 3));

        // set the possible index of the bad energies
        bad_surprise = badSurprse.get(random.nextInt(0, 2));

        // clue one
        if(room_number % 2 == 0){
            is_even = "is";
        }
        else{
            is_even = "is not";
        }


        // clue 2
        if(room_number >= 25){
            room_range = "is greater or equal to 25";
        }
        else{
            room_range = "is less than 25";
        }

        // clue 3
        if(room_number % 4 == 0){
            multiple_of_4 = "is a multiple of 4";
        }
        else{
            multiple_of_4 = "is not a multiple of 4";
        }

        // remove the number in the list which appears in the room number
       for(int i = 0; i < 2; i++){
           if(possibility.contains(Character.getNumericValue(two_digit_number.charAt(i)))){
               possibility.remove((Integer) Character.getNumericValue(two_digit_number.charAt(i)));
           }
       }

        //clue 4
        possible_room_number = Character.getNumericValue(two_digit_number.charAt(0));
        exclude = possibility.get(random.nextInt(0, 7));
        possibility.remove((Integer) exclude);

        // clue 5
        exclude1 = possibility.get(random.nextInt(0, 6));
        possibility.remove((Integer) exclude1);
        exclude2 = possibility.get(random.nextInt(0, 5));
        possibility.remove((Integer) exclude2);

        // clue 6
        exclude3 = possibility.get(random.nextInt(0, 4));
        possibility.remove((Integer) exclude3);
        exclude4 = possibility.get(random.nextInt(0, 3));


        // clue 7
        if (room_number >= 35){
            more_room_range = "The room number is greater than or equal to 35";
        }

        if (room_number <= 15){
            more_room_range = "The room number is less than or equal to 15";
        }

        if (room_number <= 35 && room_number >= 15){
            more_room_range = "The room number is between 15 and 35 inclusive.";
        }


        // add all the clues and the surprises to the clue map.
        initial_clue.put(1, String.format("I saw the room number %s an even number", is_even));
        initial_clue.put(2, String.format("I saw the room has a number %s ", possible_room_number));
        initial_clue.put(3, String.format("I saw the room %s", room_range));
        initial_clue.put(4, String.format("I saw the room number %s", multiple_of_4));
        initial_clue.put(5, String.format("You've been added up %d energies.", good_surprise));
        initial_clue.put(6, String.format("The room does not have number %d and %d", exclude1, exclude2));
        initial_clue.put(7, String.format("You've been reduced %d energies", bad_surprise));
        initial_clue.put(8, String.format("The room does not have number %d and %d", exclude3, exclude4));
        initial_clue.put(9, "You've got a chance to verify");
        initial_clue.put(10, more_room_range);
        initial_clue.put(11, "You've got a chance to verify");
        initial_clue.put(12,
                String.format("I saw the room has a number %s or %d", two_digit_number.charAt(1), exclude));
        set_clues.add(initial_clue);


        // add the map to the arrayList and then shuffle them to make them random.
        for(int i = 1; i < 13; i++){
            randomClue.add(i);
        }
        Collections.shuffle(randomClue);

        // add all the NPC to the arrayList and shuffle them to make them random.
        totalClues.add(clue1);
        totalClues.add(clue2);
        totalClues.add(clue3);
        totalClues.add(clue4);
        totalClues.add(clue5);
        totalClues.add(clue6);
        totalClues.add(clue7);
        totalClues.add(clue8);
        totalClues.add(clue9);
        totalClues.add(clue10);
        totalClues.add(clue11);
        totalClues.add(clue12);
        Collections.shuffle(totalClues);
    }

}
