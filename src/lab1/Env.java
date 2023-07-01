package lab1;

import lab1.filter.Filter;
import lab1.filter.TXTFilter;
import lab1.filter.XMLFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Env 环境变量类，包括过滤器Filter及词表Dic。
public class Env {
    private final static Env instance = new Env();

    private final Map<String, Filter> filterMap = new HashMap<>();
    private Filter curFilter;

    private final Map<String, String[]> dicMap = new HashMap<>();
    private String[] curDic;

    private Env() {
        registerFilter("txt", TXTFilter.getInstance());
        registerFilter("xml", XMLFilter.getInstance());
        setCurFilter("txt");
        setCurDic("eng");
    }

    public static Env getInstance() {
        return instance;
    }

    // registerFilter 注册过滤器，加入过滤器表中。
    public void registerFilter(String name, Filter filter) {
        filterMap.put(name, filter);
    }

    public Filter getCurFilter() {
        return curFilter;
    }

    // setCurFilter 获取当前过滤器，默认为txt格式。
    public void setCurFilter(String content) {
        curFilter = filterMap.get(content);
        if (curFilter == null) {
            curFilter = filterMap.get("txt");
        }
    }

    public String[] getCurDic() {
        return curDic;
    }

    public void setCurDic(String lang) {
        makeDic(lang);
        curDic = dicMap.get(lang);
    }

    // makeDic 首次会从本地文件中读取词表，并排序，便于之后的二分查找。
    private void makeDic(String lang) {
        if (dicMap.get(lang) != null) {
            return;
        }
        List<String> dicList = new LinkedList<>();
        String filePath = "./" + lang + ".txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                dicList.add(s);
            }

        } catch (IOException e) {
            System.out.println("Failed to load file '" + filePath + "'!");
            return;
        }
        String[] dic = new String[dicList.size()];
        dicList.toArray(dic);
        Arrays.sort(dic);
        dicMap.put(lang, dic);
    }
}
