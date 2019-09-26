package Compiler.Environment.SymbolTable;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
public class SymbolTable{
    private Map<String,Stack<Symbol>> cursymbols;
    private Stack<Map<String,Symbol>> symboltable;
    public SymbolTable(){
        cursymbols = new HashMap<>();
        symboltable = new Stack<>();
    }
    public Symbol add(String name,Type type){
        if (symboltable.peek().containsKey(name)) {
            throw new CompilationError("one scope can't have two same symbols");
        }
        if (!cursymbols.containsKey(name)){
            cursymbols.put(name,new Stack<>());
        }
        Symbol ret = new Symbol(name,type);
        cursymbols.get(name).push(ret);
        symboltable.peek().put(name,ret);
        return ret;
    }
    public Symbol addglobalvariable(String name, Type type){
        Symbol symbol = add(name,type);
        return symbol;
    }
    public Symbol addtemporaryvariable(String name, Type type){
        Symbol symbol = add(name,type);
        return symbol;
    }
    public Symbol addparametervariable(String name, Type type){
        Symbol symbol = add(name,type);
        return symbol;
    }
    public Symbol getSymbol(String name){
        return cursymbols.get(name).peek();
    }
    public boolean include(String name){
        return cursymbols.containsKey(name) && !cursymbols.get(name).empty();
    }
    public void enterScope(){
        symboltable.push(new HashMap<>());
    }
    public void exitScope(){
        symboltable.pop().forEach((k,v)->cursymbols.get(k).pop());
    }
}