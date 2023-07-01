package lab1.command;

import lab1.Env;
import lab1.filter.Filter;

import java.util.Arrays;

// SpellACommand 标记拼写检查命令。
public class SpellACommand extends Command {
    @Override
    public String execute(String origin) {
        Env env = Env.getInstance();
        Filter filter = env.getCurFilter();
        String[] dic = env.getCurDic();

        char[] chars = filter.filter(origin).toCharArray();
        boolean f = false;
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (char c : chars) {
            if (f) { // 在大括号中，不用处理，直接加入输出。
                if (c == '}') {
                    f = false;
                } else {
                    result.append(c);
                }
                continue;
            }
            if (c == '{') { // 遇到左大括号，截断并分析词，然后将标识符f设为true。
                f = true;
                testWord(result, word, dic);
                word = new StringBuilder();
                continue;
            }
            if (c == ' ' || c == ',' || c == '.') { // 遇到分割符。截断并分析词，然后将分割符加入输出。
                testWord(result, word, dic);
                word = new StringBuilder();
                result.append(c);
                continue;
            }
            word.append(c); // 普通字母，加入当前单词。
        }
        testWord(result, word, dic);
        System.out.println(result);
        return origin;
    }

    // testWord 在词表中二分查找目标词。找到则将其本身加入输出，否则打上标记再加入输出。
    private void testWord(StringBuilder result, StringBuilder word, String[] dic) {
        String s = word.toString();
        if (s.length() == 0) {
            return;
        }
        if (Arrays.binarySearch(dic, s) < 0) {
            result.append("*[").append(s).append("]");
        } else {
            result.append(s);
        }
    }

    @Override
    public String toString() {
        return "spell-a";
    }
}
