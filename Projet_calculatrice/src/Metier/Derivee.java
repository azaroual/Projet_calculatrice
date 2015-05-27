package Metier;

import java.util.Arrays;

import javax.script.ScriptException;

public class Derivee {
	Metier m;
	public boolean BP(String exp) {
		int t = 0;
		if(exp.contains("(") || exp.contains(")")){
			for (int i = 0; i < exp.length(); i++) {
				if (exp.charAt(i) == '(') {
					t++;
				} else if (exp.charAt(i) == ')') {
					t--;
				}
			}
			if (t == 0)
				return true;
		}
		return false;
	}

	public String simP(String exp) {
		if (BP(exp) == true) 
		{
			if (exp.charAt(0) == '(' && exp.charAt(1) == '(') 
			{
				exp=exp.substring(0,exp.length()-1);
				exp=exp.substring(1,exp.length());
			}
		}
		else
			System.out.println("Syntax error");
		return exp;
	}
	public String simM(String exp)
	{
		char[] tabExp=exp.toCharArray();
		for(int i = 0;i<tabExp.length; i++)
		{
			if(i<tabExp.length-2 && Character.isDigit(tabExp[i])&& tabExp[i+1]=='*'&&tabExp[i+2]=='x'&& i>0 &&tabExp[i-1]!='^')
			{
				tabExp[i+1]='.';
			}
			else if(Character.isDigit(tabExp.length-2)&& tabExp[tabExp.length-1]=='*' && tabExp[tabExp.length]=='x')
			{
				tabExp[i+1]='.';
			}
		}
		exp=String.valueOf(tabExp);
		return exp;
	}
	public String[] splitMult(String exp)
	{
		String[]  tabExp=exp.split("\\*",2);
				
		return tabExp;
	}
	public String[] splitPlus(String exp)
	{
		String[]  tabExp=exp.split("\\+");
				
		return tabExp;
	}
	public String[] splitMoin(String exp)
	{
		String[]  tabExp=exp.split("\\-");
				
		return tabExp;
	}
	public String[] splitDivis(String exp)
	{
		String[]  tabExp=exp.split("\\/",2);
				
		return tabExp;
	}

	public String derive(String exp) {
		String deriv = "";
		if(exp.contains("^")){
			String []s=exp.split("\\^");
			int pow=Integer.parseInt(s[1])-1;
			if(pow>1)
				deriv=s[1]+"*"+s[0]+"^"+pow;
			else
				deriv=s[1]+"*"+s[0];
		}
		else{
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
				deriv = "0";
				break;
			
			}
		}
		return deriv;
	}
	public String deriveeSomme(String exp) throws ScriptException 
	{
		exp=exp.replace("-", "+-");
		System.out.println(exp);
		m=new Metier();
		String der="";
		String []splitP=splitPlus(exp);
		for(int i=0;i<splitP.length;i++)
		{
			if(!splitP[i].contains("cos")&&!splitP[i].contains("sin")&&!splitP[i].contains("ln")&&!splitP[i].contains("tan")){
				splitP[i]=splitP[i].replaceAll("\\(", "");
				splitP[i]=splitP[i].replaceAll("\\)", "");
			}
			System.out.println("split "+splitP[i]);
			if(splitP[i].contains("*")){
				der+=deriveeMultiplication(splitP[i]);
				if(i<splitP.length-1)
				 {
					 der+="+";
				 }
			}
			else if(splitP[i].contains("-")){
				der+=deriveeMultiplication(splitP[i]);
				if(i<splitP.length-1)
				 {
					 der+="+";
				 }
			}
			else{
			 der+=derive(splitP[i]);
			 if(i<splitP.length-1)
			 {
				 der+="+";
			 }
			}
		}
		exp=der.replace("+0", "");
		return exp;
	}
	public String deriveeSous(String exp) throws ScriptException 
	{
		m=new Metier();
		String der="";
		String []splitS=splitMoin(exp);
		for(int i=0;i<splitS.length;i++)
		{
			if(!splitS[i].contains("cos")&&!splitS[i].contains("sin")&&!splitS[i].contains("ln")&&!splitS[i].contains("tan")){
				splitS[i]=splitS[i].replaceAll("\\(", "");
				splitS[i]=splitS[i].replaceAll("\\)", "");
			}
			System.out.println("split "+splitS[i]);
			if(splitS[i].contains("*")){
				der+=deriveeMultiplication(splitS[i]);
				if(i<splitS.length-1)
				 {
					 der+="-";
				 }
			}
			else if(splitS[i].contains("-")){
				der+=deriveeMultiplication(splitS[i]);
				if(i<splitS.length-1)
				 {
					 der+="-";
				 }
			}
			else{
			 der+=derive(splitS[i]);
			 if(i<splitS.length-1)
			 {
				 der+="-";
			 }
			}
		}
		exp=der.replace("-0", "");
		return exp;
	}
	public String simOperateur(String exp)
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
	public String deriveeMultiplication(String exp) throws ScriptException
	{
		System.out.println(exp);
		String resultat=exp;
		System.out.println(resultat);
		m=new Metier();
		String []splitM=splitMult(resultat);
		for(int i=0;i<splitM.length;i++)
		{
			String remp=splitM[i].replaceAll("\\+x", "+1");
			remp=remp.replaceAll("\\.x", "*1");
			remp=remp.replaceAll("x", "1");
			
			m.calculSimple(remp);
			
			//System.out.println("split M "+splitM[i]);
			
			
			if(splitM[i].contains("+"))
			{
				if(i<splitM.length-1){
					resultat=deriveeSomme(splitM[i])+"*"+splitM[i+1]+"+"+splitM[i]+"*"+deriveeMultiplication(splitM[i+1]);
					System.out.println("R1"+resultat);
				}
					
				else {
					resultat=deriveeSomme(splitM[i]);
					System.out.println("R2"+resultat);
				}
					
				return resultat;
			}
			else if(splitM[i].contains("-"))
			{
				if(i<splitM.length-1)
					resultat=deriveeSous(splitM[i])+"*"+splitM[i+1]+"+"+splitM[i]+"*"+deriveeMultiplication(splitM[i+1]);
				else 
					resultat=deriveeSous(splitM[i]);
					return resultat;
			}
			
			else if(i<splitM.length-1){
				return "("+derive(splitM[0])+"*"+splitM[1]+")+"+"("+splitM[0]+"*"+deriveeMultiplication(splitM[1])+")";
			}
			
		}
		
		return derive(resultat);
	}
	public String deriveeDivision(String exp) throws ScriptException
	{
		
		String resultat=exp;
		m=new Metier();
		String []splitD=splitDivis(resultat);
		for(int i=0;i<splitD.length;i++)
		{
			String remp=splitD[i].replaceAll("\\+x", "+1");
			remp=remp.replaceAll("\\.x", "*1");
			remp=remp.replaceAll("x", "1");
			
			m.calculSimple(remp);
			
			System.out.println("split D "+splitD[i]);
			
			if(splitD[i].contains("*"))
			{
				if(i<splitD.length-1){
					resultat="(("+deriveeMultiplication(splitD[i])+")*("+splitD[i+1]+")-("+splitD[i]+")*("+deriveeDivision(splitD[i+1])+"))/("+splitD[1]+")^2";
					System.out.println("div"+resultat);
				}
					
				else {
					resultat=deriveeMultiplication(splitD[i]);
					System.out.println("div"+resultat);
				}
					
				return resultat;
			}
			
			else if(splitD[i].contains("+"))
			{
				if(i<splitD.length-1){
					resultat="(("+deriveeSomme(splitD[i])+")*("+splitD[i+1]+")-("+splitD[i]+")*("+deriveeDivision(splitD[i+1])+"))/("+splitD[1]+")^2";
					System.out.println("R1"+resultat);
				}
					
				else {
					resultat=deriveeSomme(splitD[i]);
					System.out.println("R2"+resultat);
				}
					
				return resultat;
			}
			
			else if(splitD[i].contains("-"))
			{
				if(i<splitD.length-1)
					resultat="(("+deriveeSous(splitD[i])+")*("+splitD[i+1]+")-("+splitD[i]+")*("+deriveeDivision(splitD[i+1])+"))/("+splitD[1]+")^2";
				else 
					resultat=deriveeSous(splitD[i]);
					return resultat;
			}
			
			else if(i<splitD.length-1){
				return "(("+derive(splitD[0])+"*"+splitD[1]+")-"+"("+splitD[0]+"*"+deriveeDivision(splitD[1])+"))"+"/("+splitD[1]+")";
			}
			
		}
		
		return derive(resultat);
	}

	public static void main(String[] args) {
		Derivee d = new Derivee();
		//String sss = "5+x*2*x+(33x)";
		String exp = "4*x*x";
		String b = d.simM(exp);//remplacer * par 
	//	b=d.deriveeSomme(exp);//calcule derivee polynome
		try{
		//b = d.simP(b);
	//b=d.deriveeMultiplication(b);
			b=d.deriveeDivision(b);
		System.out.println(b);
		}
		catch(ScriptException e)
		{
			
			try {
				b=d.deriveeSomme(b);
			} catch (ScriptException e1) {
				System.out.println("Syntaxe erreur");
			}
			//b=d.simOperateur(b);
			System.out.println(b);
		}
		
		/*System.out.println(b);
		//String sa = Arrays.toString(d.splitMult(b));
		//System.out.println(sa);
		String []splitM=d.splitMult(b);
		Metier m=new Metier();
		try{
			for(int i=0;i<splitM.length;i++)
			{
				String remp=splitM[i].replaceAll("\\+x", "+1");
				remp=remp.replaceAll("\\.x", "*1");
				m.calculSimple(remp);		
				System.out.println("split M "+splitM[i]);
			}
			String []splitP1=d.splitPlus(splitM[0]);
			for(int i=0;i<splitP1.length;i++)
			{
				String remp2=splitP1[i].replace('x', '1');
				System.out.println("split multi"+m.calculSimple(remp2));
			}
			String []splitP2=d.splitPlus(splitM[1]);
			for(int i=0;i<splitP2.length;i++)
			{
				String remp3=splitP2[i].replace('x', '1');
				System.out.println("split multi"+m.calculSimple(remp3));
			}
			String derive="";
			System.out.println("la deriver est : ");
			for(int i=0;i<splitP1.length;i++)
			{
				System.out.println("SPLIT P1 "+splitP1[i]);
				derive+=""+d.derive(splitP1[i]);
			}
			for(int i=0;i<splitP2.length;i++)
			{
				derive=derive+""+d.derive(splitP2[i]);
			}
			System.out.println(derive);
			
			
		}
		catch (ScriptException e1) {
			System.out.println("Syntax Error");
		}*/
		
	}

}
