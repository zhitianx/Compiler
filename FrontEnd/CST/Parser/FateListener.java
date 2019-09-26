// Generated from Fate.g4 by ANTLR 4.7
package Compiler.FrontEnd.CST.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FateParser}.
 */
public interface FateListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FateParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FateParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FateParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(FateParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(FateParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(FateParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(FateParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(FateParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(FateParser.VariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FateParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FateParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(FateParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(FateParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(FateParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(FateParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FateParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(FateParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FateParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(FateParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link FateParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(FateParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link FateParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(FateParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link FateParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(FateParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link FateParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(FateParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link FateParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(FateParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link FateParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(FateParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link FateParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(FateParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link FateParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(FateParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link FateParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(FateParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link FateParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(FateParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(FateParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(FateParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(FateParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(FateParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpression(FateParser.SubscriptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpression(FateParser.SubscriptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelExpression(FateParser.RelExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelExpression(FateParser.RelExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicorExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicorExpression(FateParser.LogicorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicorExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicorExpression(FateParser.LogicorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(FateParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(FateParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(FateParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(FateParser.AssignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitorExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitorExpression(FateParser.BitorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitorExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitorExpression(FateParser.BitorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitxorExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitxorExpression(FateParser.BitxorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitxorExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitxorExpression(FateParser.BitxorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(FateParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(FateParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitandExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitandExpression(FateParser.BitandExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitandExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitandExpression(FateParser.BitandExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(FateParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(FateParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicandExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicandExpression(FateParser.LogicandExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicandExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicandExpression(FateParser.LogicandExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpression(FateParser.SuffixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpression(FateParser.SuffixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(FateParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(FateParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldExpression(FateParser.FieldExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldExpression(FateParser.FieldExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(FateParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(FateParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(FateParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(FateParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functioncallExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctioncallExpression(FateParser.FunctioncallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functioncallExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctioncallExpression(FateParser.FunctioncallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(FateParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link FateParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(FateParser.MulExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(FateParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(FateParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(FateParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(FateParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(FateParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(FateParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(FateParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(FateParser.VoidTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(FateParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(FateParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void enterClassType(FateParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classType}
	 * labeled alternative in {@link FateParser#type}.
	 * @param ctx the parse tree
	 */
	void exitClassType(FateParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolConstant(FateParser.BoolConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolConstant(FateParser.BoolConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntConstant(FateParser.IntConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntConstant(FateParser.IntConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(FateParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(FateParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterNullConstant(FateParser.NullConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link FateParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitNullConstant(FateParser.NullConstantContext ctx);
}