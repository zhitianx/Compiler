package Compiler.FrontEnd.AST.Statement;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.BasicType.VoidType;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class VariableDeclarationStatement extends Statement{
    public Symbol symbol;
    public Expression expression;
    private VariableDeclarationStatement(Symbol symbol,Expression expression){
        this.symbol = symbol;
        this.expression = expression;
    }
    public static Statement getStatement(Symbol symbol,Expression expression){
        if (symbol.type instanceof VoidType) {
            throw new CompilationError("CE");
        }
        if (expression == null || expression.type.compatiblewith(expression.type)){
            return new VariableDeclarationStatement(symbol,expression);
        }
        throw new CompilationError("CE");
    }
}