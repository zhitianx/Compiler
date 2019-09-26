package Compiler.FrontEnd.AST.Expression.UnaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.IntConstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.List;
public class BitNotExpression extends UnaryExpression{
    BitNotExpression(Expression expression,boolean isleftvalue,Type type){
        super(expression,isleftvalue,type);
    }
    public static Expression getExpression(Expression expression){
        if (expression.type instanceof IntType){
            if (expression instanceof IntConstant){
                int value = ((IntConstant)(expression)).value;
                return IntConstant.getConstant(~value);
            }
            return new BitNotExpression(expression,false,IntType.getType());
        }
        throw new CompilationError("bitnotexpression error");
    }
}