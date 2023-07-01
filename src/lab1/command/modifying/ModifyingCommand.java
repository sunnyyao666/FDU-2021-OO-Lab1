package lab1.command.modifying;

import lab1.command.Command;

// ModifyingCommand 继承Command，并为所有修改类命令的基类。
public abstract class ModifyingCommand extends Command {
    @Override
    public boolean isModifying() {
        return true;
    }
}
