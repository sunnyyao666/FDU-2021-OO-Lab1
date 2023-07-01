package lab1.filter;

// Filter 过滤器基类。Strategy模式。
public abstract class Filter {
    // filter 所有过滤器同一执行格式，入参为初始文本，返回值将所有不需要处理的部分打上大括号。
    public abstract String filter(String origin);
}
