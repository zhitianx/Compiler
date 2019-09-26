package Compiler.FrontEnd.AST.Expression.ConstantExpression;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import java.util.List;
public class IntConstant extends Constant{
    public int value;
    IntConstant(int value){
        super(IntType.getType());
        this.value = value;
    }
    public static Constant getConstant(int value){
        return new IntConstant(value);
    }
}