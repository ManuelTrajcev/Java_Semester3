package designPaterns.command;

public class Room {
    Command command;

    public Room() {

    }


    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
