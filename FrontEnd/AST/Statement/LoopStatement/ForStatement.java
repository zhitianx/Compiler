package Compiler.FrontEnd.AST.Statement.LoopStatement;
import Compiler.FrontEnd.AST.Expression.ConstantExpression.Boolconstant;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Statement.Statement;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class ForStatement extends LoopStatement{
    public Expression initial,cond,inc;
    public Statement statement;
    public static Statement getStatement(){
        return new ForStatement();
    }
    public void addinitial(Expression initial){
        this.initial = initial;
    }
    public void addinc(Expression inc){
        this.inc = inc;
    }
    public void addstatement(Statement statement){
        this.statement = statement;
    }
    public void addcond(Expression cond){
        if (cond == null){
            this.cond = Boolconstant.getConstant(true);
        }
        if (!(cond.type instanceof BoolType)){
            throw new CompilationError("CE");
        }
        this.cond = cond;
    }
}