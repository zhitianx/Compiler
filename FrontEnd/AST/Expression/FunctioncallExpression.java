package Compiler.FrontEnd.AST.Expression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.VariableExpression.FieldExpression;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.BasicType.VoidType;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import Compiler.Utility.Error.InternalError;
//import Compiler.Utility.Utility;
import java.util.ArrayList;
import java.util.List;
public class FunctioncallExpression extends Expression{
    public Function func;
    public List<Expression> parameters;
    private FunctioncallExpression(Function func, List<Expression> parameters, Type type, boolean isleftvalue){
        super(type,isleftvalue);
        this.func = func;
        this.parameters = parameters;
    }
    public static Expression getExpression(Function func,List<Expression>parameters){
        return new FunctioncallExpression(func,parameters,func.type,false);
    }
    public static Expression getExpression(Expression expression,List<Expression>parameters){
        if (expression.type instanceof Function){
            Function function = (Function)expression.type;
            if (expression instanceof FieldExpression){
                //	member function call : add "this" to the parameter list
                parameters.add(0, ((FieldExpression)expression).expression);
            }
            if (parameters.size() != function.parameters.size()){
                throw new CompilationError("the number of parameters in the function-call expression is wrong");
            }
            for (int i = 0; i < parameters.size();i ++){
                if (i == 0 && expression instanceof FieldExpression){
                    //	no need to compare the type of "this"
                    continue;
                }
                if (!parameters.get(i).type.compatiblewith(function.parameters.get(i).type)){
                    throw new CompilationError("CE");
                }
            }
            return new FunctioncallExpression(function,parameters,function.type,false);
        }
        throw new CompilationError("function call error");
    }
}