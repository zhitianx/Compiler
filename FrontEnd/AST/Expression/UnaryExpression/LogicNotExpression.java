package Compiler.FrontEnd.AST.Expression.UnaryExpression;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;

public class LogicNotExpression extends UnaryExpression{
    private LogicNotExpression(Expression expression, Type type, boolean isleftvalue){
        super(expression,isleftvalue,type);
    }
    public static Expression getExpression(Expression expression){
        if (expression.type instanceof BoolType){
            if (expression instanceof Boolconstant){
                boolean value = ((Boolconstant)(expression)).value;
                return Boolconstant.getConstant(!value);
            }
            return new LogicNotExpression(expression,BoolType.getType(),false);
        }
        throw new CompilationError("logicnot error");
    }
}