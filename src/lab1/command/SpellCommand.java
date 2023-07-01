package lab1.command;

import lab1.Env;
import lab1.filter.Filter;

import java.util.Arrays;

// SpellCommand 拼写检查命令。
public class SpellCommand extends Command {
    // execute 拼写检查执行。按顺序逐行输出所有不在词表中的单词。不去重。
    @Override
    public String execute(String origin) {
        Env env = Env.getInstance();
        Filter filter = env.getCurFilter();
        String[] dic = env.getCurDic();

        char[] chars = filter.filter(origin).toCharArray();
        boolean f = false;
        StringBuilder word = new StringBuilder();
        for (char c : chars) {
            if (f) { // 在大括号中，直接跳过。
                if (c == '}') {
                    f = false;
                }
                continue;
            }
            if (c == '{') { // 遇到左大括号。截断并分析词，然后将标识符f设为true。
                f = true;
                testWord(word, dic);
                word = new StringBuilder();
                continue;
            }
            if (c == ' ' || c == ',' || c == '.') { // 遇到分割符。截断并分析词。
                testWord(word, dic);
                word = new StringBuilder();
                continue;
            }
            word.append(c); // 普通字母，加入当前单词。
        }
        testWord(word, dic);
        return origin;
    }

    // testWord 在词表中二分查找目标词。未找到则输出。
    private void testWord(StringBuilder word, String[] dic) {
        String s = word.toString();
        if (s.length() == 0) {
            return;
        }
        if (Arrays.binarySearch(dic, s) < 0) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "spell";
    }
}
