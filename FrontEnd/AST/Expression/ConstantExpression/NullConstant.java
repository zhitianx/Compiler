package Compiler.FrontEnd.AST.Expression.ConstantExpression;
import Compiler.FrontEnd.AST.Type.BasicType.NullType;
import java.util.List;
public class NullConstant extends Constant{
    NullConstant(){
        super(NullType.getType());
    }
    public static Constant getConstant(){
        return new NullConstant();
    }
}