package Compiler.FrontEnd.AST.Type.BasicType;

import Compiler.FrontEnd.AST.Type.Type;

public class BoolType extends Type{
    private static BoolType ret = new BoolType();

    public static Type getType(){
        return ret;
    }

    @Override
    public boolean compatiblewith(Type other) {
        return other instanceof BoolType;
    }
}