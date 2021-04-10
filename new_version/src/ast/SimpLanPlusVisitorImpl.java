package ast;
import parser.SimpLanPlusBaseVisitor;
import parser.SimpLanPlusParser;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    
    @Override
    public Node visitBlock(SimpLanPlusParser.BlockContext ctx) {


        BlockNode res;

        //list of declarations in @res
        ArrayList<Node> declarations = new ArrayList<Node>();
        ArrayList<Node> statements = new ArrayList<Node>();

        //visit all nodes corresponding to declarations inside the let context and store them in @declarations
        //notice that the ctx.let().dec() method returns a list, this is because of the use of * or + in the grammar
        //antlr detects this is a group and therefore returns a list
        for (SimpLanPlusParser.DeclarationContext dc : ctx.declaration()){
            declarations.add( visit(dc) );
        }

        for (SimpLanPlusParser.StatementContext st : ctx.statement()){
            statements.add( visit(st) );
        }

        //build @res accordingly with the result of the visits to its content
        res = new BlockNode(declarations,statements);

        return res;

    }
    /*
    @Override
    public Node visitStatement(SimpLanPlusParser.StatementContext ctx) {

        return null;
    } */
/*
    @Override
    public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) {

        return null;
    }
*/


    @Override
    public Node visitDecVar(SimpLanPlusParser.DecVarContext ctx) {
        Node typeNode = visit(ctx.type());
        String id = ctx.ID().getText();
        Node expNode;
        if (ctx.exp()!=null){
            expNode = visit(ctx.exp());
            return new DecVarNode(typeNode,id,expNode);
        }
        return new DecVarNode(typeNode,id);

    }

    @Override
    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        if(ctx.getText().equals("int"))
            return new IntTypeNode();
        else if(ctx.getText().equals("bool"))
            return new BoolTypeNode();
        if(ctx.type()!= null){
            //TODO pointer type
        }
        //this will never happen thanks to the parser
        return null;
    }
}
