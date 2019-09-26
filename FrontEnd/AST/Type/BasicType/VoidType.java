package Compiler.FrontEnd.AST.Type.BasicType;

import Compiler.FrontEnd.AST.Type.Type;

public class VoidType extends Type{
    private static VoidType ret = new VoidType();

    public static Type getType(){
        return ret;
    }

    @Override
    public boolean compatiblewith(Type other) {
        return false;
    }
}