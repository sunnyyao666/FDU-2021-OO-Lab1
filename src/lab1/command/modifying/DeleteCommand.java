package lab1.command.modifying;

// DeleteCommand 删除命令。
public class DeleteCommand extends ModifyingCommand {
    private final boolean head; // 是否在头部删除
    private final int num; // 删除的字符数

    public DeleteCommand(boolean head, int num) {
        this.head = head;
        this.num = num;
    }

    // execute 删除操作。若origin字符总数小于num，则返回空字符串。
    @Override
    public String execute(String origin) {
        String result = origin;
        int len = result.length();
        if (len <= num) {
            result = "";
        } else {
            if (head) {
                result = result.substring(num);
            } else {
                result = result.substring(0, len - num);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result;
        if (head) {
            result = "d ";
        } else {
            result = "D ";
        }
        return result + num;
    }
}
