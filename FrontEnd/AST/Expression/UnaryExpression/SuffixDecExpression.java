package Compiler.FrontEnd.AST.Expression.UnaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.List;
public class SuffixDecExpression extends UnaryExpression{
    public SuffixDecExpression(Expression expression,Type type,boolean isleftvalue){
        super(expression,isleftvalue,type);
    }
    public static Expression getExpression(Expression expression){
        if (!expression.isleftvalue){
            throw new CompilationError("suffixdec error");
        }
        if (expression.type instanceof IntType){
            return new SuffixDecExpression(expression,IntType.getType(),false);
        }
        throw new CompilationError("suffixdec error");
    }
}