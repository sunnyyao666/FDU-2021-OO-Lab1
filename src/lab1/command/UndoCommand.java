package lab1.command;

// UndoCommand 撤销命令。
public class UndoCommand extends Command {
    @Override
    public String execute(String origin) {
        CommandStack commandStack = CommandStack.getInstance();
        String result = commandStack.undo();
        return result == null ? origin : result;
    }

    @Override
    public String toString() {
        return "u";
    }
}
