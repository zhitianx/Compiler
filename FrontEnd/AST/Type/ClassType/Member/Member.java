package Compiler.FrontEnd.AST.Type.ClassType.Member;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
//import Compiler.Utility.Error.InternalError;
public abstract class Member{
    public String name;
    public ClassType origin;
    Member(String name){
        this.name = name;
        this.origin = Environment.scopetable.getClassScope();
    }
}