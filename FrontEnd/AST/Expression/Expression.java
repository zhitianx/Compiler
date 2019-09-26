package Compiler.FrontEnd.AST.Expression;
import Compiler.FrontEnd.AST.Node;
import Compiler.FrontEnd.AST.Type.Type;
import java.util.List;
public abstract class Expression implements Node{
    public Type type;
    public boolean isleftvalue;
    public Expression(Type type,boolean isleftvalue){
        this.type = type;
        this.isleftvalue = isleftvalue;
    }
}