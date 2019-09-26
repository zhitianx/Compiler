package Compiler.FrontEnd.AST.Statement.LoopStatement;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Statement.Statement;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class WhileStatement extends LoopStatement{
    public Expression cond;
    public Statement statement;
    public static Statement getStatement(){
        return new WhileStatement();
    }
    public void addcond(Expression cond){
        if (!(cond.type instanceof BoolType)){
            throw new CompilationError("CE");
        }
        this.cond = cond;
    }
    public void addstatement(Statement statement){
        this.statement = statement;
    }
}