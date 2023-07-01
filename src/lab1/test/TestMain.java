package lab1.test;

import lab1.runner.Runner;
import lab1.runner.RunnerFactory;

// TestMain 测试主类。
public class TestMain {
    public static void main(String[] args) {
        RunnerFactory runnerFactory = RunnerFactory.getInstance();
        Runner runner = runnerFactory.getRunner("test");
        runner.run();
    }
}
