package Compiler.FrontEnd.AST.Expression.VariableExpression;
import Compiler.Environment.Environment;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.List;
public class IdentifierExpression extends Expression{
    public Symbol symbol;
    private IdentifierExpression(Symbol symbol,Type type,boolean isleftvalue){
        super(type,isleftvalue);
        this.symbol = symbol;
    }
    public static Expression getExpression(String name){
        if (!Environment.symboltable.include(name)){
            throw new CompilationError("identifier expression error");
        }
        Symbol symbol = Environment.symboltable.getSymbol(name);
        if (symbol.scope instanceof ClassType){
            //	symbol -> this.symbol
            return FieldExpression.getExpression(IdentifierExpression.getExpression("this"), name);
        }else{
            if (symbol.type instanceof Function){
                return new IdentifierExpression(symbol,symbol.type,false);
            }else{
                return new IdentifierExpression(symbol,symbol.type,true);
            }
        }
    }
}