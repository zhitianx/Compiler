package Compiler.FrontEnd.AST.Expression.ConstantExpression;
import Compiler.FrontEnd.AST.Expression.Expression;
import Compiler.FrontEnd.AST.Type.Type;
//import Compiler.Utility.Utility;
public abstract class Constant extends Expression{
    Constant(Type type){
        super(type,false);
    }
}