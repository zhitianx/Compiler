package Compiler.FrontEnd.AST.Expression.VariableExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.ArrayType;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class SubscriptExpression extends Expression{
    public Expression sup,inf;
    private SubscriptExpression(Expression sup, Expression inf, boolean isleftvalue, Type type){
        super(type,isleftvalue);
        this.sup = sup;
        this.inf = inf;
    }
    public static Expression getExpression(Expression sup,Expression inf){
        if (!(sup.type instanceof ArrayType)){
            throw new CompilationError("subscript error");
        }
        if (!(inf.type instanceof IntType)){
            throw new CompilationError("subscript error");
        }
        return new SubscriptExpression(sup,inf,sup.isleftvalue,((ArrayType)(sup.type)).reduce());
    }
}