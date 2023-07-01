package lab1.command;

import lab1.Env;

// LangCommand 设置文本语言命令。
public class LangCommand extends Command {
    private final String lang;

    public LangCommand(String lang) {
        this.lang = lang;
    }

    @Override
    public String execute(String origin) {
        Env env = Env.getInstance();
        env.setCurDic(lang);
        return origin;
    }

    @Override
    public String toString() {
        return "lang " + lang;
    }
}
