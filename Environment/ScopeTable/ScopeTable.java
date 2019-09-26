package Compiler.Environment.ScopeTable;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.AST.Statement.LoopStatement.LoopStatement;
import Compiler.FrontEnd.AST.Type.ClassType.ClassType;
import Compiler.Utility.Error.InternalError;
import org.antlr.v4.codegen.model.Loop;

import java.util.Stack;

public class ScopeTable{
    private Stack<Scope> scopes;
    private Stack<ClassType> classscopes;
    private Stack<Function> functionscopes;
    private Stack<LoopStatement> loopscopes;

    public ScopeTable(){
        scopes = new Stack<>();
        classscopes = new Stack<>();
        functionscopes = new Stack<>();
        loopscopes = new Stack<>();
    }

    public void enterscope(Scope scope){
        scopes.push(scope);
        if (scope instanceof ClassType){
            classscopes.push((ClassType)(scope));
        }
        if (scope instanceof Function){
            functionscopes.push((Function)(scope));
        }
        if (scope instanceof LoopStatement){
            loopscopes.push((LoopStatement)(scope));
        }
    }
    public void exitscope(){
        if (scopes.empty()) throw new InternalError();
        Scope scope = scopes.pop();
        if (scope instanceof ClassType){
            if (classscopes.empty()) throw new InternalError();
            classscopes.pop();
        }
        if (scope instanceof Function){
            if (functionscopes.empty()) throw new InternalError();
            functionscopes.pop();
        }
        if (scope instanceof LoopStatement){
            if (loopscopes.empty()) throw new InternalError();
        }
    }
    public Scope getScope(){
        if (scopes.empty()) return null;
        return scopes.peek();
    }
    public ClassType getClassScope(){
        if (classscopes.empty()) return null;
        return classscopes.peek();
    }
    public Function getFunctionScope(){
        if (functionscopes.empty()) return null;
        return functionscopes.peek();
    }
    public LoopStatement getLoopScope(){
        if (loopscopes.empty()) return null;
        return loopscopes.peek();
    }
}