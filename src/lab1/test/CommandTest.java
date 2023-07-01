package lab1.test;

import lab1.command.Command;
import lab1.command.CommandStack;
import lab1.command.modifying.ModifyingCommand;

// CommandTest 测试元素，包括测试名，目标命令及期望值。
public class CommandTest {
    private final String name;
    private final Command command;
    private final String expected;

    public CommandTest(String name, Command command, String expected) {
        this.name = name;
        this.command = command;
        this.expected = expected;
    }

    public boolean test(String origin) {
        boolean f =true;
        String output = command.execute(origin);
        if (expected.equals(output)) {
            System.out.println(name + " succeed!");
        } else {
            System.out.println(name + " failed! Expected: " + expected + ", got: " + output);
            f = false;
        }
        if (command.isModifying()) {
            CommandStack commandStack = CommandStack.getInstance();
            commandStack.newModifyingCommand((ModifyingCommand) command, origin);
        }
        return f;
    }

    public String getExpected() {
        return expected;
    }
}
