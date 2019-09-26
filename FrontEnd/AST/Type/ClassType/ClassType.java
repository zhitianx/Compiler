package Compiler.FrontEnd.AST.Type.ClassType;
import Compiler.Environment.Environment;
import Compiler.Environment.ScopeTable.Scope;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Type.BasicType.NullType;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Member;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Memberfunction;
import Compiler.FrontEnd.AST.Type.ClassType.Member.Membervariable;
import Compiler.FrontEnd.AST.Type.Type;
import Compiler.Utility.Error.CompilationError;
//import Compiler.Utility.Utility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ClassType extends Type implements Scope{
    public String name;
    public Map<String,Membervariable> membervariables;
    public Map<String,Memberfunction> memberfunctions;
    public Function defaultconstructor;
    private ClassType(String name){
        this.name = name;
        this.membervariables = new HashMap<>();
        this.memberfunctions = new HashMap<>();
        this.defaultconstructor = null;
    }
    public static Type getType(String name){
        return new ClassType(name);
    }
    public void addMember(String name,Type type){
        if (include(name)) {
            throw new CompilationError("one class can't have two members with same name");
        }
        if (type instanceof Function){
            Function function = (Function)type;
            function.name = this.name + "." + function.name;
            Memberfunction member = new Memberfunction(name,function);
            memberfunctions.put(name,member);
        }else{
            Membervariable member = new Membervariable(name,type);
            membervariables.put(name,member);
        }
    }
    public void addconstructor(Function function) {
        if (function.getParameterTypes().size() > 0) {
            throw new CompilationError("construtor of class should't have parameters");
        }
        if (defaultconstructor != null) {
            throw new CompilationError("one class only one constructor");
        }
        function.name = name + ".constructor";
        defaultconstructor = function;
    }
    public Member getMember(String name){
        Member member = null;
        if (membervariables.containsKey(name)){
            member = membervariables.get(name);
        }
        if (memberfunctions.containsKey(name)){
            member = memberfunctions.get(name);
        }
        if (member == null) throw new CompilationError("CE");
        return member;
    }
    public boolean include(String name){
        return memberfunctions.containsKey(name) || membervariables.containsKey(name);
    }
    @Override
    public boolean compatiblewith(Type other){
        return other instanceof NullType || other == this;
    }
}