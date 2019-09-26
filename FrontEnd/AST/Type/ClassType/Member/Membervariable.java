package Compiler.FrontEnd.AST.Type.ClassType.Member;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.FrontEnd.AST.Type.Type;
//import Compiler.Utility.Utility;
public class Membervariable extends Member{
    public Type type;
    public Expression expression;
    public Membervariable(String name,Type type){
        super(name);
        this.type = type;
    }
}