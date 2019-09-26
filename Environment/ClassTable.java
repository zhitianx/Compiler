package Compiler.Environment;

import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.Utility.Error.CompilationError;

import java.util.*;

public class ClassTable{
    Map<String,ClassType> classes;
    public ClassTable(){
        classes = new HashMap<>();
    }
    public void put(String name,ClassType classtype){
        if (classes.containsKey(name)){
            throw new CompilationError("CE");
        }
        classes.put(name,classtype);
    }
    public ClassType getClassType(String name){
        return classes.get(name);
    }
    public boolean include(String name){
        return classes.containsKey(name);
    }
}