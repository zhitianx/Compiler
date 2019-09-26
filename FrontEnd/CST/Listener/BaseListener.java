package Compiler.FrontEnd.CST.Listener;

import Compiler.FrontEnd.AST.Node;
import Compiler.FrontEnd.CST.Parser.FateBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public abstract class BaseListener extends FateBaseListener{
    public static int row,column;
    public static ParseTreeProperty<Node> retnode = new ParseTreeProperty<>();

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        row = ctx.getStart().getLine();
        column = ctx.getStart().getCharPositionInLine();
    }
}


