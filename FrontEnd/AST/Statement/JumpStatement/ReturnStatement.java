package Compiler.FrontEnd.AST.Statement.JumpStatement;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.BasicType.VoidType;
import Compiler.FrontEnd.AST.Statement.Statement;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class ReturnStatement extends Statement{
    public Function to;
    public Expression expression;
    private ReturnStatement(Expression expression,Function to){
        this.expression = expression;
        this.to = to;
    }
    public static Statement getStatement(Expression expression){
        Function function = Environment.scopetable.getFunctionScope();
        if (function == null) {
            throw new CompilationError("CE");
        }
        if (expression == null) {
            if (function.type instanceof VoidType) {
                return new ReturnStatement(expression, function);
            }
        } else {
            if (expression.type.compatiblewith(function.type)) {
                return new ReturnStatement(expression, function);
            }
        }
        throw new CompilationError("CE");
    }
}