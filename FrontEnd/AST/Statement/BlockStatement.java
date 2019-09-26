package Compiler.FrontEnd.AST.Statement;
import Compiler.Environment.ScopeTable.Scope;
//import Compiler.Utility.Utility;
import java.util.ArrayList;
import java.util.List;
public class BlockStatement extends Statement implements Scope{
    public ArrayList<Statement> stmts;
    private BlockStatement(){
        stmts = new ArrayList<>();
    }
    public static Statement getStatement(){
        return new BlockStatement();
    }
    public void addStatement(Statement statement){
        stmts.add(statement);
    }
}