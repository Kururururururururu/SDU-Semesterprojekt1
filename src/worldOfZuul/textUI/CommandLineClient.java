/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfZuul.textUI;

import com.sun.tools.javac.Main;
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
    public CitizenNPC sune = new CitizenNPC("Solcelle Sune", 3, new ArrayList<>(List.of(
            "Test 1",
            "Test 2"
    )));

    public ObstacleNPC kurt = new ObstacleNPC("Kurt", 1, false);
    public CitizenNPC wendy = new CitizenNPC("Wendy Vindfang", 2, new ArrayList<>(List.of(
            "Nice to meet you.",
            "It sure is windy today!"
    )));
    public MainCharacter mainCharacter = new MainCharacter("Andreas");
    public PointShop pointshop = new PointShop();
    public Points points = new Points(1000);
    public CommandLineClient() {
        game = new Game();
        parser = new Parser(game);
        //npc_boss = new NPC("The Boss", "Merchant");
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

    //Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Commands commandWord = command.getCommandName();

        switch (commandWord) {
            case GO:
                if (game.goRoom(command)) {
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
                    pointshop.buyItem(command, points, mainCharacter);
                } else {
                    System.out.println("You can't buy anything from here.");
                }
                break;
            case SHOP:
                if (game.getRoomId().equals(0)) {
                    pointshop.openShop();
                } else {
                    System.out.println("There is no shop nearby.");
                }
                break;
            case TALK:
                switch (game.getRoomId()) {
                    case 0:
                        guide.talk();
                        break;
                    case 1:
                        kurt.talk();
                        break;
                    case 2:
                        wendy.talk();
                        break;
                    case 3:
                        sune.talk();
                        break;
                    default:
                        System.out.println("There is no one here to talk with?!");
                }
                break;
            case POINTS:
                System.out.println(points.getPoints());
                break;
            case USE:
                mainCharacter.useItem(command, game.getRoom());
                break;
            default:
                System.out.println("I do not know that command?!");
        }
        return wantToQuit;
    }
}

