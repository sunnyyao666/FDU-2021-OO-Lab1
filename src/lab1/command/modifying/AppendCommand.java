package lab1.command.modifying;

// AppendCommand 添加命令。
public class AppendCommand extends ModifyingCommand {
    private final boolean head; // 是否添加在头部
    private final String toAppend; // 新添加的字符串

    public AppendCommand(boolean head, String toAppend) {
        this.head = head;
        this.toAppend = toAppend;
    }

    @Override
    public String execute(String origin) {
        String result = origin;
        if (head) {
            result = toAppend + result;
        } else {
            result += toAppend;
        }
        return result;
    }

    @Override
    public String toString() {
        String result;
        if (head) {
            result = "a \"";
        } else {
            result = "A \"";
        }
        return result + toAppend + "\"";
    }
}
