package Metier;

import java.util.ArrayList;




import javax.script.ScriptException;

public class Derivee2 {
	static boolean isNeg=false;
	static String expression ;
	static Metier m = new Metier();
	static ArrayList<String> resultat = new ArrayList<String>();
	
	public static String mainDerivee(String expression){
		
		//expression = "x-x+x";
		//expression ="4*x/x^4";
		expression=Derivee2.simpSymb(expression);
		System.out.println(expression);
		if(expression.charAt(0)=='-')
		{
			expression=expression.substring(1, expression.length());
			isNeg=true;
			
		}
		else if(expression.charAt(0)=='+')
		{
			expression=expression.substring(1, expression.length());
			isNeg=false;
			
		}
		String derivee = Derivee2.derivee(expression.replaceAll("\\-", "+-"));
		
		System.out.println(derivee);
		
		if(isNeg==true)
		{
			isNeg=false;
			return "-("+derivee+")";
		}
		else{
			
			return derivee;
		}
	}
	private static String simpSymb(String expression) {
		String exp = expression;
		// TODO Auto-generated method stub
		if(exp.contains("+-"))
		{
			
			return simpSymb(exp.replaceAll("\\+-", "-"));
		}
		if(exp.contains("-+"))
		{
			
			return simpSymb(exp.replace("-+", "-"));
		}if(exp.contains("++"))
		{
			
			return simpSymb(exp.replaceAll("\\++", "+"));
		}if(exp.contains("--"))
		{
			
			return simpSymb(exp.replaceAll("\\--", "+"));
		}
		return exp;
	}


	/*public static void main(String [] args){
		
		expression = "cos(x+e(x))";
		//expression ="4*x/x^4";
		
		String derivee = Derivee2.derivee(expression.replaceAll("\\-", "+-"));
		
			System.out.println(derivee);
	}*/

	public static String derivee(String expression) {
		String resultat = "";
		String exp = Derivee2.simM(expression);
		//exp = exp.replaceAll("\\-", "+-");
		
		// tester les parentheses
		String exepr =exp;
		if(!Derivee2.testeParenthesesDiv(exepr)){//si le split sur * est faux
			exepr = Derivee2.simP(exepr); //retirer la première et la dernière parenthese
			if(Derivee2.testeParenthesesDiv(exepr)){//si le split sur * est vrai
				exp = exepr;
				Derivee2.expressionsDiv(exp, 2);
			}
		}	
		String[] splitM=Derivee2.expressionsDiv(exp, 2);//stoker les deux expressions dans le tableau splitM
		if(splitM != null){
			System.out.println("DIV  :  "+splitM[0]+","+splitM[1]);// 
			for (int i = 0; i < splitM.length-1; i++) {
				if (splitM[i+1] != null && i<splitM.length-1){
					return "((("+derivee(splitM[i])+"*"+splitM[i+1]+")-("+derivee(splitM[i+1])+"*"+splitM[i]+"))/("+splitM[i+1]+"^2))";	
				}
										
			}
		}
		else{
			// tester les parentheses
			exepr =exp;
			if(!Derivee2.testeParenthesesMulti(exepr)){
				exepr = Derivee2.simP(exepr);
				if(Derivee2.testeParenthesesMulti(exepr)){
					exp = exepr;
					Derivee2.expressionsMulti(exp, 2);
				}
			}
			splitM = Derivee2.expressionsMulti(exp, 2); 
			if(splitM != null){
				System.out.println("MULTI  :  "+splitM[0]+","+splitM[1]);
				for (int i = 0; i < splitM.length-1; i++) {
					if (splitM[i+1] != null && i<splitM.length-1){
						return "(("+derivee(splitM[i])+")*("+splitM[i+1]+"))+(("+derivee(splitM[i+1])+")*("+splitM[i]+"))";	
					}
											
				}
			}
			else {
				// tester les parentheses
				exepr =exp;
				if(!Derivee2.testeParentheses(exepr)){
					exepr = Derivee2.simP(exepr);
					
					if(Derivee2.testeParentheses(exepr)){
						exp=exepr;
						Derivee2.expressionsPlus(exp, 2);
					}
				}	
				splitM = Derivee2.expressionsPlus(exp, 2); 
				if(splitM != null){
					System.out.println("Somme  :  "+splitM[0]+","+splitM[1]);
					for (int i = 0; i < splitM.length-1; i++) {
						if (splitM[i+1] != null && i<splitM.length-1){
							return "("+derivee(splitM[i])+")+("+derivee(splitM[i+1])+")";	
						}
												
					}
				}
			}
			
		}
		
		return Derivee2.deriveSimple(exp);
	}
	
	public static String deriveeMultiplication(String exp) {
		int splitLimt =2;
		// tester les parentheses
		if(Derivee2.testeParentheses(exp)){
		}else{
			exp = Derivee2.simP(exp);
			if(Derivee2.testeParentheses(exp)){
				String[] expMulti = Derivee2.expressionsMulti(exp, splitLimt);
				
			}else
				return "Syntax Error";
		}
		return exp;
			
	}

	public static boolean testeParentheses(String exp) {
		if(Derivee2.expressionsPlus(exp, 2) == null && exp.contains("+")){
			return false;
		}
		return true;
	}
	
	public static boolean testeParenthesesMulti(String exp) {
		if(Derivee2.expressionsMulti(exp, 2) == null && exp.contains("*")){
			return false;
		}
		return true;
	}
	
	public static boolean testeParenthesesDiv(String exp) {
		if(Derivee2.expressionsDiv(exp, 2) == null && exp.contains("/")){
			return false;
		}
		return true;
	}

	
	public static String[] expressionsMulti(String expression, int splitLimit){
		
		String[] splitM = Derivee2.splitMult(expression, splitLimit);
		String remp ="(";
		String remp2 ="(";
		String[] concatSplitM = new String[2];
		concatSplitM[0] = "";
		if(splitM.length >1 && expression.contains("*")){
			for (int i = 0; i < splitM.length-1; i++) {
				
				if(i < splitM.length-2) 
					concatSplitM[0] += splitM[i]+"*";
				else
					concatSplitM[0] += splitM[i];
			}
			concatSplitM[1] = splitM[1];
			remp = concatSplitM[0];
			remp2 = concatSplitM[1];
		}
		else if(splitM.length<=1 && expression.contains("*")){
			concatSplitM[0] = expression;
			remp = expression;
		}
		
		remp=remp.replaceAll("\\+x", "+1");
		remp=remp.replaceAll("\\-x", "-1");
		remp=remp.replaceAll("\\.x", "*1");
		remp=remp.replaceAll("\\/x", "/1");
		remp=remp.replaceAll("x", "1");		
		try {
			System.out.println( m.calculSimple(m.remplacer(remp)));
			remp2=remp2.replaceAll("\\+x", "+1");
			remp2=remp2.replaceAll("\\-x", "-1");
			remp2=remp2.replaceAll("\\.x", "*1");
			remp2=remp2.replaceAll("\\/x", "/1");
			remp2=remp2.replaceAll("x", "1");
			System.out.println( m.calculSimple(m.remplacer(remp2)));
			
		} catch (ScriptException e) {
			
			int count = Derivee2.countOcurenceSym(expression, '*');
			if (splitLimit+1 <= count+1)
				return expressionsMulti(expression, splitLimit+1);
			else {
				return null;
			}
			
		}
		return concatSplitM;
		
	}
	public static String[] expressionsDiv(String expression, int splitLimit){
		String[] splitD = Derivee2.splitDiv(expression, splitLimit);
		String remp = "(";
		String remp2 = "(";
		String[] concatSplitD = new String[2];
		concatSplitD[0] = "";
		
		if(splitD.length >1 && expression.contains("/") ){
			for (int i = 0; i < splitD.length-1; i++) {
				
				if(i < splitD.length-2) 
					concatSplitD[0] += splitD[i]+"/";
				else
					concatSplitD[0] += splitD[i];
			}
			concatSplitD[1] = splitD[splitD.length-1];
			
			remp = concatSplitD[0];
			remp2 = concatSplitD[1];
		}
		else if(splitD.length<=1 && expression.contains("/")){
			concatSplitD[0] = expression;
			remp = expression;
		}
			
		
		remp=remp.replaceAll("\\+x", "+1");
		remp=remp.replaceAll("\\-x", "-1");
		remp=remp.replaceAll("\\.x", "*1");
		remp=remp.replaceAll("\\/x", "/1");
		remp=remp.replaceAll("x", "1");
		
		try {
			
			System.out.println( m.calculSimple(m.remplacer(remp)));
			
			remp2=remp2.replaceAll("\\+x", "+1");
			remp2=remp2.replaceAll("\\-x", "-1");
			remp2=remp2.replaceAll("\\.x", "*1");
			remp2=remp2.replaceAll("\\/x", "/1");
			remp2=remp2.replaceAll("x", "1");
			
			System.out.println( m.calculSimple(m.remplacer(remp2)));
		} catch (ScriptException e) {
			int count = Derivee2.countOcurenceSym(expression, '/');
			if (splitLimit+1 <= count+1)
				return expressionsDiv(expression, splitLimit+1);
			else 
				return null;
			
		}
			
		return concatSplitD;
		
	}
	
	public static String[] expressionsPlus(String expression, int splitLimit){
		
		String[] splitP= Derivee2.splitPlus(expression, splitLimit);
		String remp = "(";
		String remp2 = "(";
		
		String[] concatSplitP = new String[2];
		concatSplitP[0] = "";
		if(splitP.length>1 && expression.contains("+")){
				
			for (int i = 0; i < splitP.length-1; i++) {
				
				if(i < splitP.length-2) 
					concatSplitP[0] += splitP[i]+"+";
				else
					concatSplitP[0] += splitP[i];
			}
			concatSplitP[1] = splitP[splitP.length-1];
			
			remp = concatSplitP[0];
			remp2 = concatSplitP[1];
		}
		else if(splitP.length<=1){
			concatSplitP[0] = expression;
			remp = expression;
		}
		
		remp=remp.replaceAll("\\+x", "+1");
		remp=remp.replaceAll("\\-x", "-1");
		remp=remp.replaceAll("\\.x", "*1");
		remp=remp.replaceAll("\\/x", "/1");
		remp=remp.replaceAll("x", "1");
		
		try {
			System.out.println("-----------------------------------");
			System.out.println( m.calculSimple(m.remplacer(remp)));
			remp2=remp2.replaceAll("\\+x", "+1");
			remp2=remp2.replaceAll("\\-x", "-1");
			remp2=remp2.replaceAll("\\.x", "*1");
			remp2=remp2.replaceAll("\\/x", "/1");
			remp2=remp2.replaceAll("x", "1");
			System.out.println( m.calculSimple(m.remplacer(remp2)));
			System.out.println("-----------------------------------");
		} catch (ScriptException e) {
			int count = Derivee2.countOcurenceSym(expression, '+');
			if (splitLimit+1 <= count+1){
				return expressionsPlus(expression, splitLimit+1);
			}
			else return null;
		}
		
		return concatSplitP;
		
	}
	
	private static String[] splitPlus(String exp, int limitSplit) {
		String[]  tabExp = exp.split("\\+", limitSplit);
		return tabExp;
	}

	public static String[] splitMult(String exp, int limitSplit)
	{
		String[]  tabExp = exp.split("\\*", limitSplit);
				
		return tabExp;
	}
	public static String[] splitDiv(String exp, int limitSplit)
	{
		String[]  tabExp = exp.split("\\/", limitSplit);
				
		return tabExp;
	}
	
	public static String simM(String exp)
	{
		char[] tabExp=exp.toCharArray();
		for(int i = 0;i<tabExp.length; i++)
		{
			if(i<tabExp.length-2 && Character.isDigit(tabExp[i])&& tabExp[i]=='*'&&tabExp[i+1]=='x'&& i>0 &&tabExp[i-2]!='^')
			{
				tabExp[i+1]='.';
			}
			else if(tabExp.length>1&&i==0 && tabExp[1]=='*' && tabExp[2]=='x')
			{
				tabExp[i+1]='.';
			}
		}
		exp=String.valueOf(tabExp);
		return exp;
	}
	
	public static int countOcurenceSym(String chn, char sym){
		int compteur =0;
		for (int i = 0; i < chn.length(); i++) {
			if(chn.charAt(i) == sym)
				compteur++;
		}
		return compteur;
	}
	public static String simP(String exp) {
		
		exp=exp.substring(0,exp.length()-1);
		exp=exp.substring(1,exp.length());

		return exp;
	}

	public static String deriveSimple(String exp) {
		String deriv = "";
		boolean est_negative = false;
		String trigo="";
		if(exp.contains(".")){
			String[] tab=new String[2];
			tab=exp.split("\\.",2);
			return tab[0]+"*"+Derivee2.derivee(tab[1]);
		}
		else if((exp.contains("cos")||exp.contains("sin")||exp.contains("tan")) && exp.length()>6)
		{
			//trigo=exp.substring(3, exp.length());
			
			
			if(exp.contains("cos")){
				trigo=exp.replaceAll("cos", "");
				trigo = Derivee2.simP(trigo);
				exp=exp.replaceAll("cos", "-sin");
				System.out.println(exp);
			}
			else if(exp.contains("sin")){
				trigo=exp.replaceAll("sin", "");
				trigo = Derivee2.simP(trigo);
				exp=exp.replaceAll("sin", "cos");
				
			}
			else if(exp.contains("tan")){
				trigo=exp.replaceAll("tan", "");
				trigo = Derivee2.simP(trigo);
				exp=exp.replaceAll("tan", "1+tan");
				exp="["+exp+"^2]";
			}
			System.out.println("res "+exp);
			return "["+Derivee2.derivee(trigo)+"]*"+exp+"";
		}
		else if((exp.contains("ln")||exp.contains("e")) && exp.length()>5)
		{
			if(exp.contains("ln"))
			{
				//trigo=exp.substring(2, exp.length());
				trigo=exp.replaceAll("ln", "");
				trigo = Derivee2.simP(trigo);
				System.out.println("ddddd"+trigo);
				return "("+Derivee2.derivee(trigo)+")/("+trigo+")";
			}
			
			else if(exp.contains("e"))
			{
				//trigo=exp.substring(1, exp.length());
				trigo=exp.replaceAll("e", "");
				trigo = Derivee2.simP(trigo);
				return "("+Derivee2.derivee(trigo)+")*("+exp+")";
			}
						
		}
		else if(exp.contains("^")){
			String []s=exp.split("\\^");
			s[1] = s[1].replaceAll("\\)", "");
			s[0] = s[0].replaceAll("\\(", "");
			int pow=Integer.parseInt(s[1])-1;
			if(pow>1)
				deriv=s[1]+"*"+s[0]+"^"+pow;
			else
				deriv=s[1]+"*"+s[0];
			
		}
		else{
			if(exp.contains("-"))
			{
				est_negative =true;
				exp=exp.substring(1, exp.length());
			}
			switch (exp) {
			case "sin(x)":
				deriv = "cos(x)";
				break;
			case "cos(x)":
				deriv = "-sin(x)";
				break;
			case "tan(x)":
				deriv = "1+tan(x)^2";
				break;
			case "√(x)":
				deriv = "1/2*√(x)";
				break;
			case "ln(x)":
				deriv = "1/x";
				break;
			case "e(x)":
				deriv = "e(x)";
				break;
			case "x":
				deriv = "1";
				break;
			default : 
				char[] tabExp=exp.toCharArray();
				for (int i = 0; i < tabExp.length; i++) {
					if(Character.isDigit(tabExp[i]))
						deriv = "0";
					else{
						deriv = "Syntax Error";
						break;
					}
				}
			}
		}
		return est_negative==true ?"-"+deriv: deriv;
	}

	public static String simOperateur(String exp)
	{
		if(exp.contains("+-"))
		{
			
			exp=exp.replaceAll("\\+-", "-");
		}
		else if(exp.contains("-+"))
		{
			exp=exp.replaceAll("\\-+", "-");
		}
		else if(exp.contains("++"))
		{
			exp=exp.replaceAll("\\++", "+");
		}
		else if(exp.contains("--"))
		{
			exp=exp.replaceAll("\\--", "+");
		}
		return exp;
	}
}
