package lab1;

import lab1.runner.Runner;
import lab1.runner.RunnerFactory;

// Main 文本编辑器主类。
public class Main {
    public static void main(String[] args) {
        String s = "";
        if (args.length >= 2 && "-t".equals(args[0])) { // 获取命令行参数
            s = args[1];
        }
        RunnerFactory runnerFactory = RunnerFactory.getInstance();
        Runner runner = runnerFactory.getRunner("main");
        runner.setString(s);
        runner.run();
    }
}
