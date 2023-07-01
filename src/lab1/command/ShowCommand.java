package lab1.command;

// ShowCommand 展示当前字符串命令。
public class ShowCommand extends Command {
    @Override
    public String execute(String origin) {
        System.out.println(origin);
        return origin;
    }

    @Override
    public String toString() {
        return "s";
    }
}
