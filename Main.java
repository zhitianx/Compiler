package Compiler;
import Compiler.Environment.Environment;
import Compiler.Environment.SymbolTable.Symbol;
import Compiler.FrontEnd.AST.Function;
import Compiler.FrontEnd.CST.Listener.ClassFetchListener;
import Compiler.FrontEnd.CST.Listener.DeclarationFetchListener;
import Compiler.FrontEnd.CST.Listener.SyntaxErrorListener;
import Compiler.FrontEnd.CST.Listener.TreeBuildListener;
import Compiler.FrontEnd.CST.Parser.FateLexer;
import Compiler.FrontEnd.CST.Parser.FateParser;
import Compiler.Utility.Error.CompilationError;
import Compiler.Utility.Error.InternalError;
//import Compiler.Utility.Utility;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

public class Main{
    public static void main(String[] args) throws Exception{
        InputStream file = new FileInputStream("/home/void/IdeaProjects/compiler/src/Compiler/a.in");
        ANTLRInputStream input = new ANTLRInputStream(file);
        FateLexer lexer = new FateLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FateParser parser = new FateParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener());
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        Environment.initialize();
        walker.walk(new ClassFetchListener(),tree);
        walker.walk(new DeclarationFetchListener(),tree);
        if (!(Environment.symboltable.include("main"))){
            throw new CompilationError("CE");
        }
        Symbol test = Environment.symboltable.getSymbol("main");
        if (!(test.type instanceof Function)){
            throw new CompilationError("CE");
        }
        walker.walk(new TreeBuildListener(),tree);
    }
}