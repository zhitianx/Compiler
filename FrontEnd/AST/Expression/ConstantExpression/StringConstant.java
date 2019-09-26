package Compiler.FrontEnd.AST.Expression.ConstantExpression;
import Compiler.Environment.Environment;
import Compiler.FrontEnd.AST.Type.BasicType.StringType;
import java.util.List;
public class StringConstant extends Constant{
    public String value;
    StringConstant(String value){
        super(StringType.getType());
        this.value = value;
    }
    public static Constant getConstant(String value){
        return new StringConstant(value);
    }
}