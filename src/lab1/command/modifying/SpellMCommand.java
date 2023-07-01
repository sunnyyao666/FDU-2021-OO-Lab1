package lab1.command.modifying;

import lab1.Env;
import lab1.filter.Filter;

import java.util.Arrays;

// SpellMCommand 删除错误单词命令。
public class SpellMCommand extends ModifyingCommand {
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
            if (f) { // 在大括号中，不用处理，直接附加在result上。
                if (c == '}') {
                    f = false;
                } else {
                    result.append(c);
                }
                continue;
            }
            if (c == '{') { // 遇到左大括号。截断并分析词，然后将标识符f设为true。
                f = true;
                testWord(result, word, dic);
                word = new StringBuilder();
                continue;
            }
            if (c == ' ' || c == ',' || c == '.') { // 遇到分割符。截断并分析词，然后将分割符加入result。
                testWord(result, word, dic);
                word = new StringBuilder();
                result.append(c);
                continue;
            }
            word.append(c); // 普通字母，加入当前单词。
        }
        testWord(result, word, dic);
        return result.toString();
    }

    // testWord 在词表中二分查找目标词。找到则将其加入result，否则忽略。
    private void testWord(StringBuilder result, StringBuilder word, String[] dic) {
        String s = word.toString();
        if (s.length() == 0) {
            return;
        }
        if (Arrays.binarySearch(dic, s) >= 0) {
            result.append(s);
        }
    }

    @Override
    public String toString() {
        return "spell-m";
    }
}
