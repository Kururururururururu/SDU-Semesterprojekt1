/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfZuul.textUI;

import worldOfZuul.Misc.Money;

/**
 *
 * @author ancla
 */
public class WorldOfZuulApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Money.addMoney(1000);
        CommandLineClient client = new CommandLineClient();
        client.play();
    }
    
}
