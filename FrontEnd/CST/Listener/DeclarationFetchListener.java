package Compiler.FrontEnd.CST.Listener;

import Compiler.Environment.Environment;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.ArrayType;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.BasicType.StringType;
import Compiler.FrontEnd.AST.Type.BasicType.VoidType;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.FrontEnd.CST.Parser.FateParser;
import Compiler.Utility.Error.CompilationError;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class DeclarationFetchListener extends BaseListener{
    @Override
    public void exitFunctionDeclaration(FateParser.FunctionDeclarationContext ctx){
        String name;
        Type type = VoidType.getType();
        ClassType classtype = Environment.scopetable.getClassScope();
        boolean isconstructor = ctx.IDENTIFIER().size() != ctx.type().size();
        if (!isconstructor){
            name = ctx.IDENTIFIER(0).getText();
            type = (Type)retnode.get(ctx.type(0));
        }else{
            // default constructor
            if (classtype == null){
                throw new CompilationError("CE");
            }
            name = ctx.type(0).getText();
            if (!name.equals(classtype.name)){
                throw new CompilationError("CE");
            }
        }
        List<Symbol> parameters = new ArrayList<Symbol>(){{
            if (classtype != null) {
                add(new Symbol("this", classtype));
            }
            for (int i = 1; i < ctx.type().size();i ++){
                String parameterName = ctx.IDENTIFIER(i - (isconstructor ? 1 : 0)).getText();
                Type parameterType = (Type)retnode.get(ctx.type(i));
                add(new Symbol(parameterName, parameterType));
            }
        }};
        Function function = Function.getFunction(name,type,parameters);
        if (classtype != null){
            if (!isconstructor){
                //	member function
                classtype.addMember(name,function);
            }else{
                //	class constructor
                classtype.addconstructor(function);
            }
        } else {
            //	global function
            Environment.symboltable.add(name, function);
        }
        Environment.program.addfunction(function);
        retnode.put(ctx, function);
    }
    @Override
    public void enterClassDeclaration(FateParser.ClassDeclarationContext ctx){
        ClassType classType = (ClassType)retnode.get(ctx);
        Environment.enterscope(classType);
    }
    @Override
    public void exitClassDeclaration(FateParser.ClassDeclarationContext ctx){
        ClassType classtype = (ClassType)retnode.get(ctx);
        ctx.variableDeclarationStatement().forEach(statementContext->{
            String name = statementContext.IDENTIFIER().getText();
            Type type = (Type)retnode.get(statementContext.type());
            classtype.addMember(name,type);
        });
        Environment.exitscope();
    }
    @Override
    public void exitArrayType(FateParser.ArrayTypeContext ctx){
        Type baseType = (Type)retnode.get(ctx.type());
        retnode.put(ctx,ArrayType.getType(baseType));
    }
    @Override
    public void exitIntType(FateParser.IntTypeContext ctx){
        retnode.put(ctx,IntType.getType());
    }
    @Override
    public void exitStringType(FateParser.StringTypeContext ctx){
        retnode.put(ctx,StringType.getType());
    }
    @Override
    public void exitVoidType(FateParser.VoidTypeContext ctx){
        retnode.put(ctx,VoidType.getType());
    }
    @Override
    public void exitBoolType(FateParser.BoolTypeContext ctx){
        retnode.put(ctx,BoolType.getType());
    }
    @Override
    public void exitClassType(FateParser.ClassTypeContext ctx){
        String name = ctx.getText();
        if (!Environment.classtable.include(name)) {
            throw new CompilationError("CE");
        }
        retnode.put(ctx, Environment.classtable.getClassType(name));
    }
}