package lab1.runner;

import lab1.command.*;
import lab1.command.modifying.*;
import lab1.command.modifying.MacroCommand;
import lab1.command.MakeMacroCommand;

import java.util.Scanner;

// RunnerMainImpl 正常文本编辑器运行器，单例。
public class RunnerMainImpl implements Runner {
    private static final RunnerMainImpl instance = new RunnerMainImpl();
    private static final CommandStack commandStack = CommandStack.getInstance();
    private String s = "";

    private RunnerMainImpl() {
    }

    public static RunnerMainImpl getInstance() {
        return instance;
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        while (true) {
            Command command = createCommand(input.nextLine());
            String origin = s;
            s = command.execute(s);
            if (command.isModifying()) {
                System.out.println(s);
                commandStack.newModifyingCommand((ModifyingCommand) command, origin);
            }
        }
    }

    @Override
    public void setString(String s) {
        this.s = s;
    }

    // createCommand 根据输入返回需要执行的命令。默认为NullCommand。
    private Command createCommand(String line) {
        line = line.trim();
        Command command = new NullCommand();
        int len = line.length();
        if (len == 0) {
            return command;
        }
        if (len == 1 || line.charAt(1) == ' ') {
            char c = line.charAt(0);
            if (c == 'q') {
                command = new QuitCommand();
            } else if (c == 's') {
                command = new ShowCommand();
            } else if (c == 'A') {
                command = new AppendCommand(false, line.substring(3, len - 1));
            } else if (c == 'a') {
                command = new AppendCommand(true, line.substring(3, len - 1));
            } else if (c == 'D') {
                command = new DeleteCommand(false, Integer.parseInt(line.substring(2)));
            } else if (c == 'd') {
                command = new DeleteCommand(true, Integer.parseInt(line.substring(2)));
            } else if (c == 'u') {
                command = new UndoCommand();
            } else if (c == 'r') {
                command = new RedoCommand();
            } else if (c == 'l') {
                command = new ListCommand(Integer.parseInt(line.substring(2)));
            } else if (c == 'm') {
                String t = line.substring(2);
                int num = Integer.parseInt(t.substring(0, t.indexOf(' ')));
                String name = t.substring(t.indexOf(' ') + 1);
                command = new MakeMacroCommand(num, name);
            }
        } else {
            if (line.charAt(0) == '$') {
                MacroCommand macroCommand = commandStack.findMacroCommand(line.substring(1));
                if (macroCommand != null) {
                    command = macroCommand;
                }
            } else if (line.indexOf("lang") == 0) {
                command = new LangCommand(line.substring(5));
            } else if (line.indexOf("content") == 0) {
                command = new ContentCommand(line.substring(8));
            } else if ("spell".equals(line)) {
                command = new SpellCommand();
            } else if ("spell-a".equals(line)) {
                command = new SpellACommand();
            } else if ("spell-m".equals(line)) {
                command = new SpellMCommand();
            }
        }
        return command;
    }
}
