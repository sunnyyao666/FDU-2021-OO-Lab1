package lab1.command;

// NullCommand 空命令，用于未从输入中匹配到任何一种命令时。
public class NullCommand extends Command {
    @Override
    public String execute(String origin) {
        return origin;
    }

    @Override
    public String toString() {
        return "";
    }
}
