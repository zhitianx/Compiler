package Compiler.FrontEnd.AST.Expression.BinaryExpression;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.Type;
//import Compiler.Utility.Utility;
public abstract class BinaryExpression extends Expression{
    Expression left,right;
    protected BinaryExpression(Expression left, Expression right, Type type, boolean isleftvalue){
        super(type,isleftvalue);
        this.left = left;
        this.right = right;
    }
}