package lab1.command;

// Command 所有命令的基类。Command模式。
public abstract class Command {
    // execute 所有命令的统一执行格式，入参为初始文本，返回值为命令执行后的文本。
    public abstract String execute(String origin);

    // isModifying 返回该命令是否为修改类命令。直接继承Command的都为非修改类命令。
    public boolean isModifying() {
        return false;
    }

    // toString 获取每个命令的名字，用于list。
    public abstract String toString();
}
