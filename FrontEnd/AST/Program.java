package Compiler.FrontEnd.AST;
import Compiler.Environment.ScopeTable.Scope;
import Compiler.FrontEnd.AST.Statement.VariableDeclarationStatement;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
//import Compiler.Utility.Utility;
import java.util.ArrayList;
import java.util.List;
public class Program implements Node,Scope{
    public List<ClassType> classes;
    public List<Function>  functions;
    public List<VariableDeclarationStatement> globalvariables;
    public Program(){
        classes = new ArrayList<>();
        functions = new ArrayList<>();
        globalvariables = new ArrayList<>();
    }
    public static Program getProgram(){
        return new Program();
    }
    public void addclass(ClassType classtype){
        classes.add(classtype);
    }
    public void addfunction(Function function){
        functions.add(function);
    }
    public void addglobalvariable(VariableDeclarationStatement globalvariable){
        globalvariables.add(globalvariable);
    }
}