package Compiler.FrontEnd.AST.Type.BasicType;

import Compiler.FrontEnd.AST.Type.Type;

public class IntType extends Type {
    private static IntType ret = new IntType();
    public static Type getType(){
        return ret;
    }
    @Override
    public boolean compatiblewith(Type other) {
        return other instanceof IntType;
    }
}