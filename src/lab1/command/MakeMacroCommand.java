package lab1.command;

// MakeMacroCommand 宏命令制作。
public class MakeMacroCommand extends Command {
    private final int num;
    private final String name;

    public MakeMacroCommand(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public String execute(String origin) {
        CommandStack commandStack = CommandStack.getInstance();
        commandStack.NewMacroCommand(num, name);
        return origin;
    }

    @Override
    public String toString() {
        return "m " + num + " " + name;
    }
}
