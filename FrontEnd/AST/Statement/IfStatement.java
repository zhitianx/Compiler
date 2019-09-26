package Compiler.FrontEnd.AST.Statement;
import Compiler.Environment.ScopeTable.Scope;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class IfStatement extends Statement implements Scope{
    public Expression cond;
    public Statement truestatement,falsestatement;
    public IfStatement(Expression cond,Statement truestatement,Statement falsestatement){
        this.cond = cond;
        this.truestatement = truestatement;
        this.falsestatement = falsestatement;
    }
    public static Statement getStatement(Expression cond,Statement truestatement,Statement falsestatement){
        if (!(cond.type instanceof BoolType)){
            throw new CompilationError("CE");
        }
        if (cond instanceof Boolconstant){
            boolean value = ((Boolconstant)(cond)).value;
            if (value) return truestatement; else return falsestatement;
        }
        return new IfStatement(cond,truestatement,falsestatement);
    }
}