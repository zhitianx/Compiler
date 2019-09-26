package Compiler.FrontEnd.AST.Expression.BinaryExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.List;
public class AssignmentExpression extends BinaryExpression{
    private AssignmentExpression(Type type,boolean isLeftValue,Expression left, Expression right){
        super(left,right,type,isLeftValue);
    }
    public static Expression getExpression(Expression left, Expression right) {
        if (!left.isleftvalue) {
            throw new CompilationError("assignmentexpression error");
        }
        if (left.type.compatiblewith(right.type)) {
            return new AssignmentExpression(left.type, true, left, right);
        }
        throw new CompilationError("assignmentexpression error");
    }
}