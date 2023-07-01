package lab1.runner;

// RunnerFactory 运行器工厂，单例。工厂模式。
public class RunnerFactory {
    private static final RunnerFactory instance = new RunnerFactory();

    private RunnerFactory() {
    }

    public static RunnerFactory getInstance() {
        return instance;
    }

    // getRunner 根据type返回不同Runner。默认为Main类型。
    public Runner getRunner(String type) {
        if ("test".equals(type)) {
            return RunnerTestImpl.getInstance();
        } else if ("main".equals(type)) {
            return RunnerMainImpl.getInstance();
        }
        return RunnerMainImpl.getInstance();
    }
}
