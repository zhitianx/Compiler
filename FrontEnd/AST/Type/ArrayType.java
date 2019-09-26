package Compiler.FrontEnd.AST.Type;

import Compiler.FrontEnd.AST.Type.BasicType.NullType;
import Compiler.FrontEnd.AST.Type.BasicType.VoidType;
import Compiler.Utility.Error.CompilationError;
import Compiler.Utility.Error.InternalError;

public class ArrayType extends Type{
    public Type baseType;
    public int dimension;
    private ArrayType(Type baseType,int dimension){
        this.baseType = baseType;
        this.dimension = dimension;
    }
    public static Type getType(Type baseType,int dimension){
        if (baseType instanceof VoidType){
            throw new CompilationError("not allow void-arraytype");
        }
        if (dimension == 0){
            throw new InternalError();
        }
        return new ArrayType(baseType,dimension);
    }
    public static Type getType(Type baseType){
        if (baseType instanceof VoidType){
            throw new CompilationError("not allow void-array-type");
        }
        if (baseType instanceof ArrayType){
            ArrayType x = (ArrayType)baseType;
            return new ArrayType(x.baseType, x.dimension + 1);
        } else {
            return new ArrayType(baseType, 1);
        }
    }

    public Type reduce(){
        if (dimension == 1){
            return baseType;
        }else{
            return ArrayType.getType(baseType,dimension - 1);
        }
    }

    @Override
    public boolean compatiblewith(Type other){
        if (other instanceof NullType){
            return true;
        }else if (other instanceof ArrayType){
            ArrayType x = (ArrayType)other;
            return x.baseType.compatiblewith(baseType) && x.dimension == dimension;
        }
        return false;
    }
}