package Compiler.FrontEnd.AST.Type.BasicType;

import Compiler.FrontEnd.AST.Type.Type;

public class StringType extends Type{
    private static StringType ret = new StringType();

    public static Type getType(){
        return ret;
    }

    @Override
    public boolean compatiblewith(Type other) {
        return other instanceof StringType;
    }
}