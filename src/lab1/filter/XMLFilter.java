package lab1.filter;

// XMLFilter xml格式过滤器，单例。
public class XMLFilter extends Filter {
    private final static XMLFilter instance = new XMLFilter();

    private XMLFilter() {
    }

    public static XMLFilter getInstance() {
        return instance;
    }

    @Override
    public String filter(String origin) {
        return origin.replace("<", "{<").replace(">", ">}");
    }
}
