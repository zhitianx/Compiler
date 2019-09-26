package Compiler.FrontEnd.AST.Expression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.ArrayType;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import Compiler.Utility.Error.InternalError;
//import Compiler.Utility.Utility;
import java.util.ArrayList;
import java.util.List;
public class NewExpression extends Expression{
    public List<Expression> expressions;
    public Function defaultconstructor;
    private NewExpression(List<Expression> expressions, Type type, boolean isleftvalue){
        super(type,isleftvalue);
        this.expressions = expressions;
        this.defaultconstructor = null;
    }
    public static Expression getExpression(Type type,List<Expression> expressions){
        if (expressions.isEmpty()){
            if (type instanceof ClassType){
                return new NewExpression(expressions,type,false);
            }
            throw new CompilationError("newexpression error");
        }
        Type x = ArrayType.getType(type,expressions.size());
        return new NewExpression(expressions,x,false);
    }
}