package lab1.command;

// RedoCommand 重做命令。
public class RedoCommand extends Command {
    @Override
    public String execute(String origin) {
        CommandStack commandStack = CommandStack.getInstance();
        String result = commandStack.redo();
        return result == null ? origin : result;
    }

    @Override
    public String toString() {
        return "r";
    }
}
