package lab1.runner;

import lab1.command.*;
import lab1.command.modifying.AppendCommand;
import lab1.command.modifying.DeleteCommand;
import lab1.command.modifying.SpellMCommand;
import lab1.test.CommandTest;

// RunnerTestImpl 自动测试运行器，单例。需要使用指定的词表。
public class RunnerTestImpl implements Runner {
    private static final RunnerTestImpl instance = new RunnerTestImpl();
    private String s = "abc";

    private RunnerTestImpl() {
    }

    public static RunnerTestImpl getInstance() {
        return instance;
    }

    /**
     * 自动测试流程：（初始为abc）
     * Input: s
     * Output: abc
     * Input: A "de"
     * Output: abcde
     * Input: u
     * Input: u
     * Input: r
     * Input: r
     * Input: a "yz"
     * Output: yzabcde
     * Input: m 2 m1
     * Input: D 2
     * Output: yzabc
     * Input: d 2
     * Output: abc
     * Input: l 1
     * Output: 1 d 2
     * Input: l 10
     * Output: 1 d 2
     *         2 D 2
     *         3 a "yz"
     *         4 A "de"
     * Input: D 100
     * Output:
     * Input: lang fra
     * Input: content xml
     * Input: A "<a><b>Votre vue et wrong</b></a>"
     * Output: <a><b>Votre vue et wrong</b></a>
     * Input: spell
     * Output: wrong
     * Input: spell-a
     * Output: <a><b>Votre vue et *[wrong]</b></a>
     * Input: spell-m
     * Output: <a><b>Votre vue et </b></a>
     * Input: d 100
     * Output:
     * Input: $m1
     * Output: yzde
     */
    @Override
    public void run() {
        CommandTest[] tests = new CommandTest[]{
                new CommandTest("ShowTest", new ShowCommand(), "abc"),
                new CommandTest("BackAppendTest", new AppendCommand(false, "de"), "abcde"),
                new CommandTest("NormalUndoTest", new UndoCommand(), "abc"),
                new CommandTest("NoneUndoTest", new UndoCommand(), "abc"),
                new CommandTest("NormalRedoTest", new RedoCommand(), "abcde"),
                new CommandTest("NoneRedoTest", new RedoCommand(), "abcde"),
                new CommandTest("FrontAppendTest", new AppendCommand(true, "yz"), "yzabcde"),
                new CommandTest("MakeMacroTest", new MakeMacroCommand(2, "m1"), "yzabcde"),
                new CommandTest("BackDeleteTest", new DeleteCommand(false, 2), "yzabc"),
                new CommandTest("FrontDeleteTest", new DeleteCommand(true, 2), "abc"),
                new CommandTest("NormalListTest", new ListCommand(1), "abc"),
                new CommandTest("ExceedListTest", new ListCommand(10), "abc"),
                new CommandTest("ExceedBackDeleteTest", new DeleteCommand(false, 100), ""),
                new CommandTest("LangTest", new LangCommand("fra"), ""),
                new CommandTest("ContentText", new ContentCommand("xml"), ""),
                new CommandTest("XMLAppendTest", new AppendCommand(false, "<a><b>Votre vue et wrong</b></a>"), "<a><b>Votre vue et wrong</b></a>"),
                new CommandTest("SpellTest", new SpellCommand(), "<a><b>Votre vue et wrong</b></a>"),
                new CommandTest("SpellATest", new SpellACommand(), "<a><b>Votre vue et wrong</b></a>"),
                new CommandTest("SpellMTest", new SpellMCommand(), "<a><b>Votre vue et </b></a>"),
                new CommandTest("ExceedFrontDeleteTest", new DeleteCommand(true, 100), "")
        };
        int total = tests.length + 1;
        int correct = total;
        for (CommandTest t : tests) {
            if (!t.test(s)) {
                correct -= 1;
            }
            s = t.getExpected();
        }
        if (!new CommandTest("MacroTest", CommandStack.getInstance().findMacroCommand("m1"), "yzde").test(s)) {
            correct -= 1;
        }
        System.out.println("-----------------------------------");
        if (correct == total) {
            System.out.println("All " + total + " tests have passed!");
        } else {
            System.out.println("Total tests: " + total + ", passed: " + correct + ", failed: " + (total - correct));
        }
    }

    @Override
    public void setString(String s) {
    }
}
