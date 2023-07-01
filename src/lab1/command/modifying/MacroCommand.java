package lab1.command.modifying;

import java.util.List;

// MacroCommand 宏命令。
public class MacroCommand extends ModifyingCommand {
    private final String name;
    private final List<ModifyingCommand> modifyingCommands; // 子命令链表

    public MacroCommand(String name, List<ModifyingCommand> modifyingCommands) {
        this.name = name;
        this.modifyingCommands = modifyingCommands;
    }

    @Override
    public String execute(String origin) {
        for (ModifyingCommand command : modifyingCommands) {
            origin = command.execute(origin);
        }
        return origin;
    }

    @Override
    public String toString() {
        return "$" + name;
    }
}
