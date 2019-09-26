package Compiler.FrontEnd.AST.Expression.BinaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.IntConstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.NullConstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.StringConstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Expression.FunctioncallExpression;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.FrontEnd.AST.Type.BasicType.StringType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.ArrayList;
import java.util.List;
public class EqualExpression extends BinaryExpression{
    EqualExpression(Expression left,Expression right,Type type,boolean isleftvalue){
        super(left,right,type,isleftvalue);
    }
    public static Expression getExpression(Expression left,Expression right){
        if (!left.type.compatiblewith(right.type)) {
            throw new CompilationError("different type over equalexpression");
        }
        if (left instanceof NullConstant && right instanceof NullConstant){
            return Boolconstant.getConstant(true);
        }
        if (left instanceof IntConstant && right instanceof IntConstant){
            int x = ((IntConstant)(left)).value;
            int y = ((IntConstant)(right)).value;
            return Boolconstant.getConstant(x == y);
        }
        if (left instanceof Boolconstant && right instanceof Boolconstant){
            boolean x = ((Boolconstant)(left)).value;
            boolean y = ((Boolconstant)(right)).value;
            return Boolconstant.getConstant(x == y);
        }
        if (left instanceof StringConstant && right instanceof StringConstant){
            String x = ((StringConstant)(left)).value;
            String y = ((StringConstant)(right)).value;
            return Boolconstant.getConstant(x.equals(y));
        }
        if (left.type instanceof StringType && right.type instanceof StringType){
            return FunctioncallExpression.getExpression(
                    (Function)Environment.symboltable.getSymbol("_builtin_string_equal").type,
                    new ArrayList<Expression>() {{
                        add(left);
                        add(right);
                    }}
            );
        }
        return new EqualExpression(left,right,BoolType.getType(),false);
    }
}