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
import Player.*;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;

    private NPC npc_starter;

    private MainPlayer player;

    public CommandLineClient() {
        game = new Game();
        parser = new Parser(game);
        npc_starter = new NPC("Professor Oak", "guide and boss");
        player = new MainPlayer();
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Hello player! My name is " + npc_starter.getName());
        System.out.println("I am your new " + npc_starter.getRole() + " on this wonderful journey towards a green future!");
        System.out.println("Type '" + Commands.HELP + "' if you need any help.");
        System.out.println("or simply type '" + Commands.GO + " direction' to move around the world.");
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
            case HELP:
                System.out.println("Your command words are:");
                printHelp();
                break;
            case INV:
                player.getInv();
                break;
            case QUIT:
                if (game.quit(command)) {
                    wantToQuit = true;
                } else {
                    System.out.println("Quit what?");
                }
                return wantToQuit;
            case TALK:
                System.out.println(npc_starter.talk());
                break;
            default:
                System.out.println("I do not know that command?!");
        }
        return wantToQuit;
    }
}

