package Compiler.FrontEnd.AST.Statement.JumpStatement;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Statement.Statement;
import Compiler.FrontEnd.AST.Statement.LoopStatement.LoopStatement;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class BreakStatement extends Statement{
    public LoopStatement to;
    private BreakStatement(LoopStatement to){
        this.to = to;
    }
    public static Statement getStatement(){
        if (Environment.scopetable.getLoopScope() == null){
            throw new CompilationError("CE");
        }
        return new BreakStatement(Environment.scopetable.getLoopScope());
    }
}

