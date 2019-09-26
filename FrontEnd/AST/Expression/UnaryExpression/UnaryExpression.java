package Compiler.FrontEnd.AST.Expression.UnaryExpression;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.Type;
//import Compiler.Utility.Utility;
public abstract class UnaryExpression extends Expression{
    public Expression expression;
    public UnaryExpression(Expression expression,boolean isleftvalue,Type type){
        super(type,isleftvalue);
        this.expression = expression;
    }
}