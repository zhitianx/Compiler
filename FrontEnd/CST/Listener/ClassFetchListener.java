package Compiler.FrontEnd.CST.Listener;

import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.CST.Parser.FateParser;

public class ClassFetchListener extends BaseListener{
    @Override
    public void exitClassDeclaration(FateParser.ClassDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        ClassType classType = (ClassType)ClassType.getType(name);
        Environment.classtable.put(name, classType);
        Environment.program.addclass(classType);
        retnode.put(ctx, classType);
    }
}