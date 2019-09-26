package Compiler.FrontEnd.AST.Expression.BinaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.IntConstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.List;
public class BitorExpression extends BinaryExpression{
    BitorExpression(Expression left,Expression right,Type type,boolean isleftvalue){
        super(left,right,type,isleftvalue);
    }
    public static Expression getExpression(Expression left,Expression right){
        if (left.type instanceof IntType && right.type instanceof IntType){
            if (left instanceof IntConstant && right instanceof IntConstant){
                int leftvalue = ((IntConstant)(left)).value;
                int rightvalue = ((IntConstant)(right)).value;
                return IntConstant.getConstant(leftvalue | rightvalue);
            }
            return new BitorExpression(left,right,IntType.getType(),false);
        }
        throw new CompilationError("illegal bitorexpression");
    }
}