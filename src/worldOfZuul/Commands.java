package worldOfZuul;

public enum Commands
{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    TALK("talk"),
    INV("inv"),
    SHOP("shop"),
    BUY("buy"),
    POINTS("points");
    
    private String commandName;
    
    Commands(String commandString)
    {
        this.commandName = commandString;
    }
    
    public String toString()
    {
        return commandName;
    }
}
