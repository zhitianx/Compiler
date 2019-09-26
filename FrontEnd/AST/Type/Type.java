package Compiler.FrontEnd.AST.Type;

import Compiler.FrontEnd.AST.Node;
//import Compiler.Utility.Utility;

public abstract class Type implements  Node{
    public abstract boolean compatiblewith(Type other);
}