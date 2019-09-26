package Compiler.FrontEnd.AST.Expression.VariableExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.ArrayType;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.BasicType.StringType;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Member;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Memberfunction;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Membervariable;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import Compiler.Utility.Error.InternalError;
//import Compiler.Utility.Utility;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
public class FieldExpression extends Expression{
    public Expression expression;
    public String name;
    private FieldExpression(Expression expression, String name, Type type, boolean isleftvalue){
        super(type,isleftvalue);
        this.expression = expression;
        this.name = name;
    }
    public static Expression getExpression(Expression expression,String name){
        if (expression.type instanceof ClassType){
            ClassType ct = (ClassType)(expression.type);
            Member mb = ct.getMember(name);
            if (mb instanceof Membervariable){
                return new FieldExpression(expression,name,((Membervariable)(mb)).type,expression.isleftvalue);
            }
            if (mb instanceof Memberfunction){
                return new FieldExpression(expression,name,((Memberfunction)(mb)).function,expression.isleftvalue);
            }
            throw new CompilationError("fieldexpression error");
        }else if (expression.type instanceof ArrayType) {
            if (name.equals("size")){
                return new FieldExpression(expression,name,IntType.getType(),false);
            }
        }else if (expression.type instanceof StringType) {
            if (name.equals("length")) {
                return new FieldExpression(expression,name,IntType.getType(),false);
            }else if (name.equals("substring")){
                return new FieldExpression(expression,name,StringType.getType(),false);
            }else if (name.equals("ord")){
                return new FieldExpression(expression,name,IntType.getType(),false);
            }else if (name.equals("parseInt")){
                return new FieldExpression(expression,name,IntType.getType(),false);
            }
        }
        throw new CompilationError("fieldexpression error");
    }
}