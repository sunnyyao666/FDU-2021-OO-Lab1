package lab1.command;

// ListCommand 列出最近num个修改类命令。
public class ListCommand extends Command {
    private final int num;

    public ListCommand(int num) {
        this.num = num;
    }

    @Override
    public String execute(String origin) {
        CommandStack commandStack = CommandStack.getInstance();
        commandStack.list(num);
        return origin;
    }

    @Override
    public String toString() {
        return "l " + num;
    }
}
