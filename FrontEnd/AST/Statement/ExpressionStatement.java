package Compiler.FrontEnd.AST.Statement;
import Compiler.FrontEnd.AST.Expression.Expression;
import java.util.List;
public class ExpressionStatement extends Statement{
    public Expression expression;
    private ExpressionStatement(Expression expression){
        this.expression = expression;
    }
    public static Statement getStatement(Expression expression){
        return new ExpressionStatement(expression);
    }
}