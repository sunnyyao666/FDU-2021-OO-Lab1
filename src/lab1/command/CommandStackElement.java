package lab1.command;

import lab1.command.modifying.ModifyingCommand;

// CommandStackElement 修改类命令栈的元素，包括命令本身及初始值。
public class CommandStackElement {
    private final ModifyingCommand modifyingCommand;
    private final String origin;

    public CommandStackElement(ModifyingCommand modifyingCommand, String origin) {
        this.modifyingCommand = modifyingCommand;
        this.origin = origin;
    }

    public ModifyingCommand getModifyingCommand() {
        return modifyingCommand;
    }

    public String getOrigin() {
        return this.origin;
    }
}
