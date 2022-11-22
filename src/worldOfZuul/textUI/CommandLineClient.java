/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfZuul.textUI;

import worldOfZuul.Command;
import worldOfZuul.Commands;
import worldOfZuul.Game;
import worldOfZuul.Characters.*;
import worldOfZuul.Misc.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;

    public GuideNPC guide = new GuideNPC("Boss", 0);
    public CitizenNPC sune = new CitizenNPC("Sunny Simon", "Sune", 3, new ArrayList<>(List.of(
            "Solar energy has always been my favorite!",
            "I DON'T LIKE COAL! It's bad for everyone!",
            "I used to like coal like you, until i discovered the joys of solar energy!",
            "Have you heard they still only wanna use coal at dirty hills!? You should go change that!"
    )));

    public ObstacleNPC kurt = new ObstacleNPC("Kurt the Stubborn", 1, false);
    public CitizenNPC wendy = new CitizenNPC("Wendy Vindfang", "Wendy", 2, new ArrayList<>(List.of(
            "Nice to meet you.",
            "It sure is windy today!",
            "Perfect wind for some clean green energy!",
            "Perfect day to use a kite!",
            "You should go teach them a windy lesson at Dirty Hills!x"

    )));
    public MainCharacter mainCharacter = new MainCharacter("Andreas");
    public PointShop pointshop_Hub = new PointShop();
    public PointShop pointshop_Dirt = new PointShop();

    public CommandLineClient() {
        game = new Game();
        parser = new Parser(game);
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the world of Green Watts!");
        System.out.println("Green Watts is a new, incredibly boring energy game.");
        System.out.println();
        System.out.println("Type '" + Commands.HELP + "' if you need help.");
        System.out.println("Type '" + Commands.GO + " +  a direction' to move around");
        System.out.println("Type '" + Commands.TALK + "' to interact with the NPCs that you encounter on your journey.");
        System.out.println();
        System.out.println(game.getRoomDescription());
    }

    private void printHelp() {
        for (String str : game.getCommandDescriptions()) {
            System.out.println(str + " ");
        }
    }

    public void cleanRun(){
        for (int i = 0; i < 300; i++) {
            System.out.println();
        }
    }

    //Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Commands commandWord = command.getCommandName();

        switch (commandWord) {
            case GO:
                if (game.goRoom(command)) {
                    cleanRun();
                    game.printMap();
                    System.out.println(game.getRoomDescription());
                } else {
                    System.out.println("Can't walk in that direction.");
                }
                break;
            case MAP:
                game.printMap();
                break;
            case HELP:
                System.out.println("Your command words are:");
                printHelp();
                break;
            case QUIT:
                if (game.quit(command)) {
                    wantToQuit = true;
                } else {
                    System.out.println("Quit what?");
                }
                return wantToQuit;
            case INV:
                mainCharacter.showInventory();
                break;
            case BUY:
                if (game.getRoomId() == 0) {
                    pointshop_Hub.buyItem(command, mainCharacter, game.getRoomId());
                }
                else if (game.getRoomId() == 1) {
                    pointshop_Dirt.buyItem(command,mainCharacter, game.getRoomId());
                }
                else {
                    System.out.println("You can't buy anything from here.");
                }
                break;
            case SHOP:
                if (game.getRoomId()==0) {
                    pointshop_Hub.openShop(game.getRoomId());
                }
                else if (game.getRoomId()==1) {
                    pointshop_Dirt.openShop(game.getRoomId());
                }
                else {
                    System.out.println("There is no shop nearby.");
                }
                break;
            case TALK:
                ArrayList<CitizenNPC> citizensInDistrict= new ArrayList<>();

                switch (game.getRoomId()) {
                    case 0:
                        guide.talk();
                        break;
                    case 1:
                        kurt.talk();
                        break;
                    case 2:
                        citizensInDistrict.add(wendy);
                        multipleTalkableNpcs(citizensInDistrict, command);
                        break;
                    case 3:
                        citizensInDistrict.add(sune);
                        multipleTalkableNpcs(citizensInDistrict, command);
                        break;
                    default:
                        System.out.println("There is no one here to talk with?!");
                }
                break;
            case POINTS:
                System.out.println(Money.getMoney());
                break;
//            case USE:
//                mainCharacter.useItem(command, game.getRoom());
//                break;
            default:
                System.out.println("I do not know that command?!");
        }
        return wantToQuit;
    }
    public void multipleTalkableNpcs(ArrayList<CitizenNPC> citizens, Command command){
        if(command.hasCommandValue()){
            String requested_npc_name = command.getCommandValue();
            for (CitizenNPC npc : citizens) {
                String npc_true_name = npc.getTrue_name();

                if(requested_npc_name.equalsIgnoreCase(npc_true_name)){
                    npc.talk();
                    if(!npc.getFirst_talk()) {
                        npc.setFirst_talk(true);
                        Integer money_reward = 500;
                        Money.addMoney(money_reward);
                        System.out.println("(You received " + money_reward + " points for talking with " + npc_true_name + " for the first time)");
                    }
                    return;
                }
            }
            System.out.println("There is no one here called '" + requested_npc_name + "'.");
        } else {
            System.out.print("Who do you want to talk to? (People nearby: ");
            for (CitizenNPC npc : citizens) {
                String npc_true_name = npc.getTrue_name();

                if(citizens.indexOf(npc)+1 == citizens.size()){
                    System.out.print(npc_true_name);
                    System.out.println(")");
                    return;
                }
                System.out.print(npc_true_name + ", ");
            }
        }
    }
}

