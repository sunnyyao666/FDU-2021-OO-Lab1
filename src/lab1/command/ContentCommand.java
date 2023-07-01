package lab1.command;

import lab1.Env;

// ContentCommand 设置文本格式命令。
public class ContentCommand extends Command {
    private final String content;

    public ContentCommand(String content) {
        this.content = content;
    }

    @Override
    public String execute(String origin) {
        Env env = Env.getInstance();
        env.setCurFilter(content);
        return origin;
    }

    @Override
    public String toString() {
        return "content " + content;
    }
}
