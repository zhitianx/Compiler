package Compiler.FrontEnd.CST.Listener;
import Compiler.Environment.Environment;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.FrontEnd.AST.Expression.BinaryExpression.*;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.IntConstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.NullConstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.StringConstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Expression.FunctioncallExpression;
import Compiler.FrontEnd.AST.Expression.NewExpression;
import Compiler.FrontEnd.AST.Expression.UnaryExpression.*;
import Compiler.FrontEnd.AST.Expression.VariableExpression.FieldExpression;
import Compiler.FrontEnd.AST.Expression.VariableExpression.IdentifierExpression;
import Compiler.FrontEnd.AST.Expression.VariableExpression.SubscriptExpression;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Statement.*;
import Compiler.FrontEnd.AST.Statement.JumpStatement.*;
import Compiler.FrontEnd.AST.Statement.LoopStatement.ForStatement;
import Compiler.FrontEnd.AST.Statement.LoopStatement.WhileStatement;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Member;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Membervariable;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.FrontEnd.CST.Parser.FateParser;
import Compiler.Utility.Error.CompilationError;
import Compiler.Utility.Error.InternalError;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.List;

public class TreeBuildListener extends BaseListener{
    @Override
    public void exitProgram(FateParser.ProgramContext ctx){
        ctx.variableDeclarationStatement().forEach(stmt->{
            VariableDeclarationStatement x = (VariableDeclarationStatement)retnode.get(stmt);
            Environment.program.addglobalvariable(x);
        });
    }
    @Override
    public void enterFunctionDeclaration(FateParser.FunctionDeclarationContext ctx){
        Function function = (Function)retnode.get(ctx);
        Environment.enterscope(function);
    }
    @Override
    public void exitFunctionDeclaration(FateParser.FunctionDeclarationContext ctx){
        Function function = (Function)retnode.get(ctx);
        if (function.name.equals("this")){
            throw new CompilationError("declare a function called this");
        }
        function.addStatements((BlockStatement)retnode.get(ctx.blockStatement()));
        Environment.exitscope();
    }
    @Override
    public void enterClassDeclaration(FateParser.ClassDeclarationContext ctx){
        ClassType classtype = (ClassType)retnode.get(ctx);
        Environment.enterscope(classtype);
        classtype.membervariables.forEach((name,member) -> Environment.symboltable.add(name, member.type));
        classtype.memberfunctions.forEach((name,member) -> Environment.symboltable.add(name, member.function));
    }
    @Override
    public void exitClassDeclaration(FateParser.ClassDeclarationContext ctx){
        ClassType classtype = (ClassType)retnode.get(ctx);
        ctx.variableDeclarationStatement().forEach(stmt ->{
            String name = stmt.IDENTIFIER().getText();
            if (stmt.expression() != null) {
                Member member = classtype.getMember(name);
                if (member instanceof Membervariable) {
                    Membervariable memberVariable = (Membervariable)member;
                    memberVariable.expression = (Expression)retnode.get(stmt.expression());
                }
            }
        });
        Environment.exitscope();
    }
    @Override
    public void enterStatement(FateParser.StatementContext ctx){
        if (ctx.parent instanceof FateParser.SelectionStatementContext){
            Environment.enterscope(null);
        }
    }
    @Override
    public void exitStatement(FateParser.StatementContext ctx){
        if (ctx.parent instanceof FateParser.SelectionStatementContext){
            Environment.exitscope();
        }
        retnode.put(ctx,retnode.get(ctx.getChild(0)));
    }
    @Override
    public void enterBlockStatement(FateParser.BlockStatementContext ctx){
        BlockStatement blockStatement = (BlockStatement)BlockStatement.getStatement();
        Environment.enterscope(blockStatement);
        if (ctx.parent instanceof FateParser.FunctionDeclarationContext) {
            Function function = (Function)retnode.get(ctx.parent);
            for (int i = 0; i < function.parameters.size(); ++i) {
                Symbol parameter = function.parameters.get(i);
                function.parameters.set(i, Environment.symboltable.addparametervariable(parameter.name, parameter.type));
            }
        }
        retnode.put(ctx, blockStatement);
    }
    @Override
    public void exitBlockStatement(FateParser.BlockStatementContext ctx){
        ctx.statement().forEach(stmt ->{
            ((BlockStatement)retnode.get(ctx)).addStatement(
                    (Statement)retnode.get(stmt)
            );
        });
        Environment.exitscope();
    }
    @Override
    public void exitExpressionStatement(FateParser.ExpressionStatementContext ctx){
        retnode.put(ctx,ExpressionStatement.getStatement(
                (Expression)retnode.get(ctx.expression())
        ));
    }
    @Override
    public void exitSelectionStatement(FateParser.SelectionStatementContext ctx){
        retnode.put(ctx, IfStatement.getStatement(
                (Expression)retnode.get(ctx.expression()),
                (Statement)retnode.get(ctx.statement(0)),
                (Statement)retnode.get(ctx.statement(1))
        ));
    }
    @Override
    public void enterWhileStatement(FateParser.WhileStatementContext ctx){
        WhileStatement whileStatement = (WhileStatement)WhileStatement.getStatement();
        Environment.enterscope(whileStatement);
        retnode.put(ctx, whileStatement);
    }
    @Override
    public void exitWhileStatement(FateParser.WhileStatementContext ctx){
        WhileStatement whileStatement = (WhileStatement)retnode.get(ctx);
        whileStatement.addcond((Expression)retnode.get(ctx.expression()));
        whileStatement.addstatement((Statement)retnode.get(ctx.statement()));
        Environment.exitscope();
    }
    @Override
    public void enterForStatement(FateParser.ForStatementContext ctx){
        ForStatement forStatement = (ForStatement)ForStatement.getStatement();
        Environment.enterscope(forStatement);
        retnode.put(ctx, forStatement);
    }
    @Override
    public void exitForStatement(FateParser.ForStatementContext ctx){
        ForStatement forStatement = (ForStatement)retnode.get(ctx);
        forStatement.addstatement((Statement)retnode.get(ctx.statement()));
        int semicolons = 0;
        for (ParseTree parseTree : ctx.children) {
            if (parseTree.getText().equals(";")) {
                semicolons++;
            }
            if (parseTree instanceof FateParser.ExpressionContext) {
                Expression expression = (Expression)retnode.get(parseTree);
                if (semicolons == 0) {
                    forStatement.addinitial(expression);
                } else if (semicolons == 1) {
                    forStatement.addcond(expression);
                } else if (semicolons == 2) {
                    forStatement.addinc(expression);
                } else {
                    throw new InternalError();
                }
            }
        }
        Environment.exitscope();
    }
    @Override
    public void exitContinueStatement(FateParser.ContinueStatementContext ctx){
        retnode.put(ctx,ContinueStatement.getStatement());
    }
    @Override
    public void exitBreakStatement(FateParser.BreakStatementContext ctx){
        retnode.put(ctx,BreakStatement.getStatement());
    }
    @Override
    public void exitReturnStatement(FateParser.ReturnStatementContext ctx){
        Expression returnExpression = (Expression)retnode.get(ctx.expression());
        retnode.put(ctx, ReturnStatement.getStatement(returnExpression));
    }
    @Override
    public void exitVariableDeclarationStatement(FateParser.VariableDeclarationStatementContext ctx){
        if (!(ctx.parent instanceof FateParser.ClassDeclarationContext)){
            Type type = (Type)retnode.get(ctx.type());
            String name = ctx.IDENTIFIER().getText();
            if (name.equals("this")){
                throw new CompilationError("declare a variable called this");
            }
            Symbol symbol;
            if (Environment.scopetable.getScope() == Environment.program){
                symbol = Environment.symboltable.addglobalvariable(name,type);
            } else {
                symbol = Environment.symboltable.addtemporaryvariable(name,type);
            }
            Expression expression = (Expression)retnode.get(ctx.expression());
            retnode.put(ctx, VariableDeclarationStatement.getStatement(symbol,expression));
        }
    }
    @Override
    public void exitConstantExpression(FateParser.ConstantExpressionContext ctx){
        retnode.put(ctx,retnode.get(ctx.constant()));
    }
    @Override
    public void exitShiftExpression(FateParser.ShiftExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("<<")) {
            retnode.put(ctx,ShiftleftExpression.getExpression(lhs,rhs));
        } else if (ctx.operator.getText().equals(">>")) {
            retnode.put(ctx,ShiftleftExpression.getExpression(lhs,rhs));
        } else {
            throw new InternalError();
        }
    }
    @Override
    public void exitSubscriptExpression(FateParser.SubscriptExpressionContext ctx){
        retnode.put(ctx,SubscriptExpression.getExpression(
                (Expression)retnode.get(ctx.expression(0)),
                (Expression)retnode.get(ctx.expression(1))
        ));
    }
    @Override
    public void exitRelExpression(FateParser.RelExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("<")){
            retnode.put(ctx,LessExpression.getExpression(lhs,rhs));
        }else if (ctx.operator.getText().equals(">")){
            retnode.put(ctx,GreaterExpression.getExpression(lhs,rhs));
        }else if (ctx.operator.getText().equals("<=")){
            retnode.put(ctx,NotgreaterExpression.getExpression(lhs,rhs));
        }else if (ctx.operator.getText().equals(">=")){
            retnode.put(ctx,NotlessExpression.getExpression(lhs,rhs));
        }else{
            throw new InternalError();
        }
    }
    @Override
    public void exitLogicorExpression(FateParser.LogicorExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        retnode.put(ctx, LogicorExpression.getExpression(lhs,rhs));
    }
    @Override
    public void exitNewExpression(FateParser.NewExpressionContext ctx){
        List<Expression> dimensionExpressions = new ArrayList<>();
        ctx.expression().forEach(expressionContext -> {
            Expression dimensionExpression = (Expression)retnode.get(expressionContext);
            dimensionExpressions.add(dimensionExpression);
        });
        boolean change = false;
        for (ParseTree parseTree : ctx.children){
            if (parseTree instanceof TerminalNode){
                Token token = ((TerminalNode)parseTree).getSymbol();
                if (token.getText().equals("[]")) {
                    dimensionExpressions.add(null);
                    change = true;
                }else if (change){
                    throw new CompilationError("CE");
                }
            }else if (change){
                throw new CompilationError("CE");
            }
        }
        Type baseType = (Type)retnode.get(ctx.type());
        retnode.put(ctx, NewExpression.getExpression(baseType, dimensionExpressions));
    }
    @Override
    public void exitAssignExpression(FateParser.AssignExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        retnode.put(ctx, AssignmentExpression.getExpression(lhs,rhs));
    }
    @Override
    public void exitBitorExpression(FateParser.BitorExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        retnode.put(ctx, BitorExpression.getExpression(lhs,rhs));
    }
    @Override
    public void exitBitxorExpression(FateParser.BitxorExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        retnode.put(ctx,BitxorExpression.getExpression(lhs,rhs));
    }
    @Override
    public void exitVariableExpression(FateParser.VariableExpressionContext ctx){
        retnode.put(ctx, IdentifierExpression.getExpression(ctx.getText()));
    }
    @Override
    public void exitBitandExpression(FateParser.BitandExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        retnode.put(ctx, BitandExpression.getExpression(lhs, rhs));
    }
    @Override
    public void exitAddExpression(FateParser.AddExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("+")) {
            retnode.put(ctx,AddExpression.getExpression(lhs,rhs));
        } else if (ctx.operator.getText().equals("-")) {
            retnode.put(ctx,MinusExpression.getExpression(lhs,rhs));
        } else {
            throw new InternalError();
        }
    }
    @Override
    public void exitLogicandExpression(FateParser.LogicandExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        retnode.put(ctx, LogicandExpression.getExpression(lhs,rhs));
    }
    @Override
    public void exitSuffixExpression(FateParser.SuffixExpressionContext ctx){
        Expression expression = (Expression)retnode.get(ctx.expression());
        if (ctx.operator.getText().equals("++")) {
            retnode.put(ctx,SuffixIncExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("--")) {
            retnode.put(ctx,SuffixDecExpression.getExpression(expression));
        } else {
            throw new InternalError();
        }
    }
    @Override
    public void exitEqualityExpression(FateParser.EqualityExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("==")){
            retnode.put(ctx,EqualExpression.getExpression(lhs, rhs));
        }else if (ctx.operator.getText().equals("!=")){
            retnode.put(ctx,NotequalExpression.getExpression(lhs, rhs));
        }else{
            throw new InternalError();
        }
    }
    @Override
    public void exitFieldExpression(FateParser.FieldExpressionContext ctx){
        retnode.put(ctx, FieldExpression.getExpression(
                (Expression)retnode.get(ctx.expression()),
                ctx.IDENTIFIER().getText()
        ));
    }
    @Override
    public void exitUnaryExpression(FateParser.UnaryExpressionContext ctx){
        Expression expression = (Expression)retnode.get(ctx.expression());
        if (ctx.operator.getText().equals("+")) {
            retnode.put(ctx,PrefixPlusExpression.getExpression(expression));
        }else if (ctx.operator.getText().equals("-")){
            retnode.put(ctx,PrefixMinusExpression.getExpression(expression));
        }else if (ctx.operator.getText().equals("!")){
            retnode.put(ctx,LogicNotExpression.getExpression(expression));
        }else if (ctx.operator.getText().equals("~")){
            retnode.put(ctx,BitNotExpression.getExpression(expression));
        }else if (ctx.operator.getText().equals("++")){
            retnode.put(ctx,PrefixIncExpression.getExpression(expression));
        }else if (ctx.operator.getText().equals("--")){
            retnode.put(ctx,PrefixDecExpression.getExpression(expression));
        }else{
            throw new InternalError();
        }
    }
    @Override
    public void exitSubExpression(FateParser.SubExpressionContext ctx){
        retnode.put(ctx,retnode.get(ctx.expression()));
    }
    @Override
    public void exitFunctioncallExpression(FateParser.FunctioncallExpressionContext ctx){
        Expression function = (Expression)retnode.get(ctx.expression(0));
        List<Expression> parameters = new ArrayList<>();
        for (int i = 1; i < ctx.expression().size();++i){
            Expression parameter = (Expression)retnode.get(ctx.expression(i));
            parameters.add(parameter);
        }
        retnode.put(ctx,FunctioncallExpression.getExpression(function,parameters));
    }
    @Override
    public void exitMulExpression(FateParser.MulExpressionContext ctx){
        Expression lhs = (Expression)retnode.get(ctx.expression(0));
        Expression rhs = (Expression)retnode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("*")) {
            retnode.put(ctx,MultiplyExpression.getExpression(lhs,rhs));
        } else if (ctx.operator.getText().equals("/")) {
            retnode.put(ctx,DivideExpression.getExpression(lhs,rhs));
        } else if (ctx.operator.getText().equals("%")) {
            retnode.put(ctx,ModExpression.getExpression(lhs,rhs));
        } else {
            throw new InternalError();
        }
    }
    @Override
    public void exitBoolConstant(FateParser.BoolConstantContext ctx){
        retnode.put(ctx,Boolconstant.getConstant(Boolean.valueOf(ctx.getText())));
    }
    @Override
    public void exitIntConstant(FateParser.IntConstantContext ctx){
        try {
            retnode.put(ctx,IntConstant.getConstant(Integer.valueOf(ctx.getText())));
        } catch (NumberFormatException e) {
            throw new CompilationError("the number format is wrong");
        }
    }
    @Override
    public void exitStringConstant(FateParser.StringConstantContext ctx){
        retnode.put(ctx,StringConstant.getConstant(ctx.getText().substring(1,ctx.getText().length() - 1)));
    }
    @Override
    public void exitNullConstant(FateParser.NullConstantContext ctx){
        retnode.put(ctx,NullConstant.getConstant());
    }
}