package ast;
import parser.SimpLanPlusBaseVisitor;
import parser.SimpLanPlusParser;
import parser.SimpLanPlusVisitor;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node>{

    
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

    @Override
    public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) {
        return super.visitDeclaration(ctx);
    }

    @Override
    public Node visitStatement(SimpLanPlusParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

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
    public Node visitDecFun(SimpLanPlusParser.DecFunContext ctx) {
        Node type;
        if(ctx.type() == null){
            // void case
            type = null;
        } else {
            // type case
            type = visit(ctx.type());
        }
        String id = ctx.ID().getText();
        ArrayList<ArgNode> args = new ArrayList<ArgNode>();
        for(SimpLanPlusParser.ArgContext arg : ctx.arg()){
            args.add((ArgNode) visit(arg));
        }

        BlockNode block = (BlockNode) visitBlock(ctx.block());
        return new DecFunNode(type ,id ,args ,block);

    }

    @Override
    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        if(ctx.getText().equals("int"))
            return new IntTypeNode();
        else if(ctx.getText().equals("bool"))
            return new BoolTypeNode();
        if(ctx.type()!= null){
            GenericTypeNode typeNode = (GenericTypeNode) visit(ctx.type());
            return new PointerTypeNode( typeNode);
        }
        //this will never happen thanks to the parser
        return null;
    }

    @Override
    public Node visitArg(SimpLanPlusParser.ArgContext ctx) {
        Node type = visit(ctx.type());
        String id = ctx.ID().getText();
        return new ArgNode(type, id);
    }

    @Override
    public Node visitAssignment(SimpLanPlusParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
    }

    @Override
    public Node visitIte(SimpLanPlusParser.IteContext ctx) {
        return super.visitIte(ctx);
    }

    @Override
    public Node visitLhs(SimpLanPlusParser.LhsContext ctx) {
        return super.visitLhs(ctx);
    }

    @Override
    public Node visitCall(SimpLanPlusParser.CallContext ctx) {
        return super.visitCall(ctx);
    }

    @Override
    public Node visitDeletion(SimpLanPlusParser.DeletionContext ctx) {
        return super.visitDeletion(ctx);
    }

    @Override
    public Node visitPrint(SimpLanPlusParser.PrintContext ctx) {
        return super.visitPrint(ctx);
    }

    @Override
    public Node visitRet(SimpLanPlusParser.RetContext ctx) {
        return super.visitRet(ctx);
    }

    @Override
    public Node visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public Node visitBinExp(SimpLanPlusParser.BinExpContext ctx) {
        ExpNode left = (ExpNode) visit(ctx.left);

        ExpNode right = (ExpNode) visit(ctx.right);

        switch(ctx.op.getText()) {
            case "*":
                return new BinExpMultNode(left, right);
            case "/":
                return new BinExpDivNode(left, right);
            case "+":
                return new BinExpSumNode(left, right);
            case "-":
                return new BinExpSubNode(left, right);
            case "<":
                return new BinExpLtNode(left, right);
            case "<=":
                return new BinExpLeqNode(left, right);
            case ">":
                return new BinExpGtNode(left, right);
            case ">=":
                return new BinExpGeqNode(left, right);
            case "==":
                return new BinExpEqNode(left, right);
            case "!=":
                return new BinExpNeqNode(left, right);
            case "&&":
                return new BinExpAndNode(left, right);
            case "||":
                return new BinExpOrNode(left, right);
            default:
                throw new IllegalArgumentException("Operator `" + ctx.op.getText() + "` undefined");
        }
    }

    @Override
    public Node visitDerExp(SimpLanPlusParser.DerExpContext ctx) {
        return new DerExpNode((ExpNode) visit(ctx.lhs()));
    }

    @Override
    public Node visitNewExp(SimpLanPlusParser.NewExpContext ctx) {
        // TODO: Complete it
        return null;
    }

    @Override
    public Node visitValExp(SimpLanPlusParser.ValExpContext ctx) {
        return new IntNode(Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Node visitNegExp(SimpLanPlusParser.NegExpContext ctx) {
        return new NegExpNode((ExpNode) visit(ctx.exp()));
    }

    @Override
    public Node visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) {
        return new BoolNode(Boolean.parseBoolean(ctx.BOOL().getText()));
    }

    @Override
    public Node visitCallExp(SimpLanPlusParser.CallExpContext ctx) {
        return new CallExpNode((ExpNode) visitCall(ctx.call()));
    }

    @Override
    public Node visitNotExp(SimpLanPlusParser.NotExpContext ctx) {
        return new NotExpNode((ExpNode) visit(ctx.exp()));
    }

}
