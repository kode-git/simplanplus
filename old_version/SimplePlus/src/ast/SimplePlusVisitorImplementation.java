package ast;

import java.util.List;
import java.util.LinkedList;

import parser.SimplePlusBaseVisitor;
import parser.SimplePlusParser;
import utils.TypeConverter;

public class SimplePlusVisitorImplementation extends SimplePlusBaseVisitor<SimplePlusBaseElement> {
	
	// Constructor
	
	public SimplePlusVisitorImplementation() {
		
	}
	
	
	
	// Visit methods
	
	@Override
	public SimplePlusBaseElement visitBlock(SimplePlusParser.BlockContext ctx) {
		List<SimplePlusStatement> statements = new LinkedList<SimplePlusStatement>();
		
		for(SimplePlusParser.StatementContext statementCtx : ctx.statement()) {
			statements.add((SimplePlusStatement) visitStatement(statementCtx));
		}
		
		return new SimplePlusBlock(statements);
	}
	
	@Override
	public SimplePlusBaseElement visitStatement(SimplePlusParser.StatementContext ctx) {
		return visit(ctx.getChild(0));
	}
	
	@Override
	public SimplePlusBaseElement visitDeclaration(SimplePlusParser.DeclarationContext ctx) {
		return visit(ctx.getChild(0));
	}
	
	@Override
	public SimplePlusBaseElement visitDecFun(SimplePlusParser.DecFunContext ctx) {
		SimplePlusType type = (SimplePlusType) visitType(ctx.type());
		
		String id = ctx.ID().getText();
		
		List<SimplePlusArg> args = new LinkedList<SimplePlusArg>();
		if(ctx.children.size() > 5) {
			for(SimplePlusParser.ArgContext argCtx : ctx.arg()) {
				args.add((SimplePlusArg) visitArg(argCtx));
			}
		}
		
		SimplePlusBlock block = (SimplePlusBlock) visitBlock(ctx.block());
		
		return new SimplePlusDecFun(type, id, args, block);
	}
	
	@Override
	public SimplePlusBaseElement visitDecVar(SimplePlusParser.DecVarContext ctx) {
		SimplePlusType type = (SimplePlusType) visitType(ctx.type());
		
		String id = ctx.ID().getText();
		
		SimplePlusExp exp = null;
		if(ctx.children.size() > 3) {
			exp = (SimplePlusExp) visit(ctx.exp());
		}
		
		return new SimplePlusDecVar(type, id, exp);
	}
	
	@Override
	public SimplePlusBaseElement visitType(SimplePlusParser.TypeContext ctx) {
		return new SimplePlusType(TypeConverter.stringToType(ctx.getText()));
	}
	
	@Override
	public SimplePlusBaseElement visitArg(SimplePlusParser.ArgContext ctx) {
		SimplePlusType type = (SimplePlusType) visitType(ctx.type());
		
		SimplePlusRef ref = null;
		if(ctx.children.size() > 2) {
			ref = (SimplePlusRef) visitRef(ctx.ref());
		}
		
		String id = ctx.ID().getText();
		
		return new SimplePlusArg(type, ref, id);
	}
	
	@Override
	public SimplePlusBaseElement visitRef(SimplePlusParser.RefContext ctx) {
		return new SimplePlusRef();
	}
	
	@Override
	public SimplePlusBaseElement visitAssignment(SimplePlusParser.AssignmentContext ctx) {
		String id = ctx.ID().getText();
		
		SimplePlusExp exp = (SimplePlusExp) visit(ctx.exp());
		
		return new SimplePlusAssignment(id, exp);
	}
	
	
	@Override
	public SimplePlusBaseElement visitDeletion(SimplePlusParser.DeletionContext ctx) {
		return new SimplePlusDeletion(ctx.ID().getText());
	}
	
	@Override
	public SimplePlusBaseElement visitPrint(SimplePlusParser.PrintContext ctx) {
		return new SimplePlusPrint((SimplePlusExp) visit(ctx.exp()));
	}
	
	@Override
	public SimplePlusBaseElement visitRet(SimplePlusParser.RetContext ctx) {
		SimplePlusExp exp = null;
		if(ctx.children.size() > 1) {
			exp = (SimplePlusExp) visit(ctx.exp());
		}
		
		return new SimplePlusRet(exp);
	}
	
	@Override
	public SimplePlusBaseElement visitIte(SimplePlusParser.IteContext ctx) {
		SimplePlusExp exp = (SimplePlusExp) visit(ctx.exp());
		
		List<SimplePlusStatement> statements = new LinkedList<SimplePlusStatement>();
		for(SimplePlusParser.StatementContext statementCtx : ctx.statement()) {
			statements.add((SimplePlusStatement) visitStatement(statementCtx));
		}
		
		return new SimplePlusIte(exp, statements);
	}
	
	@Override
	public SimplePlusBaseElement visitCall(SimplePlusParser.CallContext ctx) {
		String id = ctx.ID().getText();
		
		List<SimplePlusExp> exps = new LinkedList<SimplePlusExp>();
		if(ctx.children.size() > 3) {
			for(SimplePlusParser.ExpContext expCtx : ctx.exp()) {
				exps.add((SimplePlusExp) visit(expCtx));
			}
		}
		
		return new SimplePlusCall(id, exps);
	}
	
	@Override
	public SimplePlusBaseElement visitBaseExp(SimplePlusParser.BaseExpContext ctx) {
		return visit(ctx.exp());
	}
	
	
	@Override
	public SimplePlusBaseElement visitVarExp(SimplePlusParser.VarExpContext ctx) {
		return new SimplePlusVarExp(ctx.ID().getText());
	}
	
	@Override
	public SimplePlusBaseElement visitBinExp(SimplePlusParser.BinExpContext ctx) {
		SimplePlusExp left = (SimplePlusExp) visit(ctx.left);
		
		SimplePlusExp right = (SimplePlusExp) visit(ctx.right);
		
		switch(ctx.op.getText()) {
		case "*":
			return new SimplePlusBinExpMult(left, right);
		case "/":
			return new SimplePlusBinExpDiv(left, right);
		case "+":
			return new SimplePlusBinExpSum(left, right);
		case "-":
			return new SimplePlusBinExpSub(left, right);
		case "<":
			return new SimplePlusBinExpLt(left, right);
		case "<=":
			return new SimplePlusBinExpLeq(left, right);
		case ">":
			return new SimplePlusBinExpGt(left, right);
		case ">=":
			return new SimplePlusBinExpGeq(left, right);
		case "==":
			return new SimplePlusBinExpEq(left, right);
		case "!=":
			return new SimplePlusBinExpNeq(left, right);
		case "&&":
			return new SimplePlusBinExpAnd(left, right);
		case "||":
			return new SimplePlusBinExpOr(left, right);
		default:
			throw new IllegalArgumentException("Operator `" + ctx.op.getText() + "` undefined");
		}
	}
	
	@Override
	public SimplePlusBaseElement visitValExp(SimplePlusParser.ValExpContext ctx) {
		return new IntNode(Integer.parseInt(ctx.NUMBER().getText()));
	}
	
	@Override
	public SimplePlusBaseElement visitNegExp(SimplePlusParser.NegExpContext ctx) {
		return new SimplePlusNegExp((SimplePlusExp) visit(ctx.exp()));
	}

	@Override
	public SimplePlusBaseElement visitBoolExp(SimplePlusParser.BoolExpContext ctx) {
		return new BoolNode(Boolean.parseBoolean(ctx.BOOL().getText()));
	}
	
	@Override
	public SimplePlusBaseElement visitCallExp(SimplePlusParser.CallExpContext ctx) {
		return visitCall(ctx.call());
	}
	
	@Override
	public SimplePlusBaseElement visitNotExp(SimplePlusParser.NotExpContext ctx) {
		return new SimplePlusNotExp((SimplePlusExp) visit(ctx.exp()));
	}

}
