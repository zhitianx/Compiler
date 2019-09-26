package Compiler.FrontEnd.AST.Expression.BinaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.IntConstant;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.StringConstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Expression.FunctioncallExpression;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.BasicType.StringType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.ArrayList;
import java.util.List;
public class NotlessExpression extends BinaryExpression{
    NotlessExpression(Expression left,Expression right,Type type,boolean isleftvalue){
        super(left,right,type,isleftvalue);
    }
    public static Expression getExpression(Expression left,Expression right){
        if (left.type instanceof IntType && right.type instanceof IntType){
            if (left instanceof IntConstant && right instanceof IntConstant){
                int leftvalue = ((IntConstant)(left)).value;
                int rightvalue = ((IntConstant)(right)).value;
                return Boolconstant.getConstant(leftvalue >= rightvalue);
            }
            return new NotlessExpression(left,right,BoolType.getType(),false);
        }else if (left.type instanceof StringType && right.type instanceof StringType){
            if (left instanceof StringConstant && right instanceof StringConstant){
                String v1 = ((StringConstant)(left)).value;
                String v2 = ((StringConstant)(right)).value;
                return Boolconstant.getConstant(v1.compareTo(v2) >= 0);
            }
            return FunctioncallExpression.getExpression(
                    (Function)Environment.symboltable.getSymbol("builtin_string_notless").type,
                    new ArrayList<Expression>() {{
                        add(left);
                        add(right);
                    }}
            );
        }
        throw new CompilationError("CE");
    }
}