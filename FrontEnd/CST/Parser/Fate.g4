grammar Fate;

program:    (classDeclaration | functionDeclaration | variableDeclarationStatement)+;

classDeclaration:   'class' IDENTIFIER '{' (functionDeclaration | variableDeclarationStatement)* '}';

functionDeclaration:    type IDENTIFIER? '(' (type IDENTIFIER(','type IDENTIFIER)*)? ')' blockStatement;

variableDeclarationStatement:   type IDENTIFIER ('=' expression)? ';' ;

statement:  blockStatement
        |   expressionStatement
        |   selectionStatement
        |   iterationStatement
        |   jumpStatement
        |   variableDeclarationStatement
        ;

blockStatement: '{' statement* '}';

expressionStatement:    expression? ';' ;

selectionStatement: 'if' '(' expression ')' statement ('else' statement)?;

iterationStatement: 'while' '(' expression ')' statement    #whileStatement
                |   'for' '(' expression?';'expression?';'expression?')'statement   #forStatement
                ;

jumpStatement:  'continue'';'   #continueStatement
            |   'break'';'      #breakStatement
            |   'return' expression? ';' #returnStatement
            ;

expression:     constant        #constantExpression
        |       IDENTIFIER  #variableExpression
        |       '(' expression ')'  #subExpression
        |       expression operator=('++'|'--')     #suffixExpression
        |       expression '(' (expression(','expression)*)? ')' #functioncallExpression
        |       expression'['expression']'      #subscriptExpression
        |       expression '.' IDENTIFIER         #fieldExpression
        |       operator=('+'|'-'|'!'|'~'|'++'|'--') expression #unaryExpression
        |       'new' type ('['expression']' | '[]')*     #newExpression
        |       expression operator=('*'|'/'|'%') expression     #mulExpression
        |       expression operator=('+'|'-') expression    #addExpression
        |       expression operator=('<<'|'>>') expression  #shiftExpression
        |       expression operator=('<'|'>'|'<='|'>=') expression  #relExpression
        |       expression operator=('=='|'!=') expression      #equalityExpression
        |       expression '&' expression   #bitandExpression
        |       expression '^' expression   #bitxorExpression
        |       expression '|' expression   #bitorExpression
        |       expression '&&' expression  #logicandExpression
        |       expression '||' expression  #logicorExpression
        |       <assoc=right> expression '=' expression #assignExpression
        ;

type:   'void'  #voidType
    |   'int'   #intType
    |   'bool'  #boolType
    |   'string'    #stringType
    |   IDENTIFIER  #classType
    |   type '[]'   #arrayType
    ;

constant:   ('true'|'false')    #boolConstant
        |   INTEGER             #intConstant
        |   STRING              #stringConstant
        |   'null'              #nullConstant
        ;
IDENTIFIER: [a-zA-Z_][a-zA-Z_0-9]*;

INTEGER:    [0-9]+;

STRING	:	'"' CHAR* '"';

fragment
CHAR	:	~["\\\r\n]
		|	'\\' ['"?abfnrtv\\]
;

LINECOMMENT:    '//'    ~[\r\n]*    ->skip;

WHITESPACE:     [ \t\n\r]+ -> skip;

