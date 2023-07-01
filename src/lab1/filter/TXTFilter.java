package lab1.filter;

// TXTFilter txt格式过滤器，单例。
public class TXTFilter extends Filter {
    private final static TXTFilter instance = new TXTFilter();

    private TXTFilter() {
    }

    public static TXTFilter getInstance() {
        return instance;
    }

    @Override
    public String filter(String origin) {
        return origin;
    }
}
