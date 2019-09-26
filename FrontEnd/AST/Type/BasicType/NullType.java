package Compiler.FrontEnd.AST.Type.BasicType;

import Compiler.FrontEnd.AST.Type.ArrayType;
import Compiler.FrontEnd.AST.Type.Type;

public class NullType extends Type{
    private static NullType ret = new NullType();

    public static Type getType(){
        return ret;
    }

    @Override
    public boolean compatiblewith(Type other) {
        return other instanceof NullType || other instanceof ArrayType;
    }
}