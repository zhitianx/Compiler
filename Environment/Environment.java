package Compiler.Environment;
import Compiler.Environment.ScopeTable.Scope;
import Compiler.Environment.ScopeTable.ScopeTable;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.Environment.SymbolTable.SymbolTable;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Program;
import Compiler.FrontEnd.AST.Type.BasicType.BoolType;
import Compiler.FrontEnd.AST.Type.BasicType.IntType;
import Compiler.FrontEnd.AST.Type.BasicType.StringType;
import Compiler.FrontEnd.AST.Type.BasicType.VoidType;
import java.util.ArrayList;
public class Environment{
    public static Program program;
    public static ScopeTable scopetable;
    public static SymbolTable symboltable;
    public static ClassTable classtable;
    public static void initialize(){
        symboltable = new SymbolTable();
        scopetable = new ScopeTable();
        classtable = new ClassTable();
        program = Program.getProgram();
        enterscope(program);
        getBuiltinfunctions();
    }
    public static void enterscope(Scope scope){
        scopetable.enterscope(scope);
        symboltable.enterScope();
    }
    public static void exitscope(){
        scopetable.exitscope();
        symboltable.exitScope();
    }
    public static void getBuiltinfunctions(){
        Function func;
        func = Function.getFunction("_builtin_print",VoidType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("str", StringType.getType()));
                }}
        );
        symboltable.add("print",func);
        func = Function.getFunction("_builtin_println",VoidType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("str", StringType.getType()));
                }}
        );
        symboltable.add("println",func);
        func = Function.getFunction("_builtin_getInt",IntType.getType(),new ArrayList<Symbol>());
        symboltable.add("getInt",func);
        func = Function.getFunction("_builtin_getString",StringType.getType(),new ArrayList<Symbol>());
        symboltable.add("getString",func);
        func = Function.getFunction("_builtin_toString",StringType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("int", IntType.getType()));
                }}
        );
        symboltable.add("toString",func);
        func = Function.getFunction("_builtin_string_length",IntType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("Self", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_length",func);
        func = Function.getFunction("_builtin_string_substring",VoidType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("Self", StringType.getType()));
                    add(new Symbol("lhs", IntType.getType()));
                    add(new Symbol("rhs", IntType.getType()));
                }}
        );
        symboltable.add("builtin_string_subString",func);
        func = Function.getFunction("_builint_string_parseInt",IntType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("self", StringType.getType()));
                }}
        );
        symboltable.add("builtin_string_parseInt",func);
        func = Function.getFunction("_builtin_string_ord",IntType.getType(),new ArrayList<Symbol>(){{
                    add(new Symbol("self", StringType.getType()));
                    add(new Symbol("pos", IntType.getType()));
                }}
        );
        symboltable.add("_builtin_string_ord",func);
        func = Function.getFunction("_builtin_array_size", IntType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("this", VoidType.getType()));
                }}
        );
        symboltable.add("_builtin_array_size",func);
        func = Function.getFunction("_builtin_string_notequal", BoolType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_notequal",func);
        func = Function.getFunction("_builtin_string_equal", BoolType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_equal",func);
        func = Function.getFunction("_builtin_string_less", BoolType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_less",func);
        func = Function.getFunction("_builtin_string_notless", BoolType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_notless",func);
        func = Function.getFunction("_builtin_string_greater", BoolType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_greater",func);
        func = Function.getFunction("_builtin_string_notgreater", BoolType.getType(), new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        );
        symboltable.add("_builtin_string_notgreater",func);
    }
}