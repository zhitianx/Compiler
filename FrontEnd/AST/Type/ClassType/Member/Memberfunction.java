package Compiler.FrontEnd.AST.Type.ClassType.Member;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
public class Memberfunction extends Member{
    public Function function;
    public Memberfunction(String name,Function function){
        super(name);
        this.function = function;
    }
}