package Compiler.FrontEnd.AST.Expression.ConstantExpression;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import java.util.List;
public class Boolconstant extends Constant{
    public boolean value;
    Boolconstant(boolean value){
        super(BoolType.getType());
        this.value = value;
    }
    public static Constant getConstant(boolean value){
        return new Boolconstant(value);
    }
}