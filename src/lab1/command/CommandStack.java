package lab1.command;

import lab1.command.modifying.MacroCommand;
import lab1.command.modifying.ModifyingCommand;

import java.util.*;

// CommandStack 修改类命令栈，单例。同时维护宏表。
public class CommandStack {
    private final static CommandStack instance = new CommandStack();
    private final Stack<CommandStackElement> undoStack = new Stack<>();
    private final Stack<CommandStackElement> redoStack = new Stack<>();
    private final Map<String, MacroCommand> macroCommandMap = new HashMap<>();

    private CommandStack() {
    }

    public static CommandStack getInstance() {
        return instance;
    }

    // newModifyingCommand 将新的修改类命令压入undoStack，同时清空redoStack。
    public void newModifyingCommand(ModifyingCommand modifyingCommand, String origin) {
        undoStack.push(new CommandStackElement(modifyingCommand, origin));
        redoStack.clear();
    }

    // list 列出最近num个修改命令。如果num超过总数则仅列出总数个。
    public void list(int num) {
        if (num > undoStack.size()) {
            num = undoStack.size();
        }
        Stack<CommandStackElement> temp = new Stack<>();
        for (int i = 1; i <= num; i++) {
            CommandStackElement top = undoStack.pop();
            System.out.println(i + " " + top.getModifyingCommand().toString());
            temp.push(top);
        }
        for (int i = 0; i < num; i++) {
            undoStack.push(temp.pop());
        }
    }

    // undo 撤销一次最近的修改类命令，并返回原值。如果撤销失败（无命令）则返回空。
    public String undo() {
        if (undoStack.empty()) {
            return null;
        }
        CommandStackElement top = undoStack.pop();
        redoStack.push(top);
        return top.getOrigin();
    }

    // redo 重做一次最近的修改类命令，并返回新值。如果重做失败（无命令）则返回空。
    public String redo() {
        if (redoStack.empty()) {
            return null;
        }
        CommandStackElement top = redoStack.pop();
        undoStack.push(top);
        return top.getModifyingCommand().execute(top.getOrigin());
    }

    // NewMacroCommand 将最近num个修改类命令制作为一个名为name的宏，并加入宏表。
    // 如果有重名宏，则新宏顶替旧宏。
    // 如果num超过总数则使用总数个修改类命令。
    public void NewMacroCommand(int num, String name) {
        if (num > undoStack.size()) {
            num = undoStack.size();
        }
        if (num <= 0) {
            return;
        }
        Stack<CommandStackElement> temp = new Stack<>();
        List<ModifyingCommand> commands = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            temp.push(undoStack.pop());
        }
        for (int i = 0; i < num; i++) {
            CommandStackElement top = temp.pop();
            commands.add(top.getModifyingCommand());
            undoStack.push(top);
        }
        MacroCommand macroCommand = new MacroCommand(name, commands);
        macroCommandMap.put(name, macroCommand);
    }

    // findMacroCommand 在宏表中查找名为name的宏并返回。若不存在则返回空。
    public MacroCommand findMacroCommand(String name) {
        return macroCommandMap.get(name);
    }
}
