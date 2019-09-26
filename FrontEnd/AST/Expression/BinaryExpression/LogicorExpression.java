package Compiler.FrontEnd.AST.Expression.BinaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.List;
public class LogicorExpression extends BinaryExpression{
    LogicorExpression(Expression left,Expression right,Type type,boolean isleftvalue){
        super(left,right,type,isleftvalue);
    }
    public static Expression getExpression(Expression left,Expression right){
        if (left.type instanceof BoolType && right.type instanceof BoolType){
            if (left instanceof Boolconstant && right instanceof Boolconstant){
                boolean leftvalue = ((Boolconstant)(left)).value;
                boolean rightvalue = ((Boolconstant)(right)).value;
                return Boolconstant.getConstant(leftvalue || rightvalue);
            }
            return new LogicorExpression(left,right,BoolType.getType(),false);
        }
        throw new CompilationError("logicor illegal");
    }
}