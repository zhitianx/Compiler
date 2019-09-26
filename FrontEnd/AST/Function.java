package Compiler.FrontEnd.AST;
import Compiler.Environment.Environment;
import Compiler.Environment.ScopeTable.Scope;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.FrontEnd.AST.Statement.BlockStatement;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.ArrayList;
import java.util.List;
public class Function extends Type implements Node,Scope{
    public String name;
    public Type type;
    public List<Symbol> parameters;
    public BlockStatement statements;
    public Function(String name,Type type,List<Symbol>parameters){
        this.name = name;
        this.type = type;
        this.parameters = parameters;
    }
    public static Function getFunction(String name,Type type,List<Symbol> parameters){
        if (Environment.scopetable.getClassScope() == null) {
            if (Environment.symboltable.include(name)){
                throw new CompilationError("two same functions");
            }
        }
        if (name.equals("main")) {
            if (!(type instanceof IntType)){
                throw new CompilationError("main not IntType");
            }
            if (parameters.size() != 0){
                throw new CompilationError("main shouldn't have parameters");
            }
        }
        for (int i = 0; i < parameters.size(); ++i){
            for (int j = i + 1; j < parameters.size(); ++j){
                if (parameters.get(i).name.equals(parameters.get(j).name)){
                    throw new CompilationError("two parameter same name");
                }
            }
        }
        return new Function(name,type,parameters);
    }
    public void addStatements(BlockStatement statements){
        this.statements = statements;
    }
    public List<Type> getParameterTypes() {
        List<Type> pT = new ArrayList<>();
        parameters.forEach(parameter -> pT.add(parameter.type));
        return pT;
    }
    @Override
    public boolean compatiblewith(Type other){
        return false;
    }
}