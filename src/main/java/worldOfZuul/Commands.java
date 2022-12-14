package worldOfZuul;


public enum Commands {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    TALK("talk"),
    INV("inv"),
    SHOP("shop"),
    POINTS("points"),
    BUY("buy"),
    MAP("map"),
    USE("use");
    
    private String commandName;
    
    Commands(String commandString) {
        this.commandName = commandString;
    }
    
    public String toString() {
        return commandName;
    }
}
