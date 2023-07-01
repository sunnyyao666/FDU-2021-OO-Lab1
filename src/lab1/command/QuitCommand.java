package lab1.command;

// QuitCommand 退出编辑器命令。
public class QuitCommand extends Command {
    @Override
    public String execute(String origin) {
        System.exit(0);
        return origin;
    }

    @Override
    public String toString() {
        return "q";
    }
}

