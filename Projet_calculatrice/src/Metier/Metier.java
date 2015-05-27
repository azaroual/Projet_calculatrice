package Metier;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Metier {

	private static final String n = null;
	public String remplacer(String exp)
	{
		/*if (exp.contains("cosh")&& !exp.contains("cos"))
			exp = exp.replace("cosh", "Math.cosh");
		if (exp.contains("sinh"))
			exp = exp.replace("sinh", "Math.sinh");
		if (exp.contains("tangh"))
			exp = exp.replace("tangh", "Math.tanh");*/	
		
		if (exp.contains("cos"))
			exp = exp.replace("cos", "Math.cos");
		if (exp.contains("sin"))
			exp = exp.replace("sin", "Math.sin");
		if (exp.contains("tan"))
			exp = exp.replace("tan", "Math.tan");
			
		if (exp.contains("√"))
			exp = exp.replace("√", "Math.sqrt");
		if (exp.contains("ln"))
			exp = exp.replace("ln", "Math.log");
		/*if (exp.contains("log"))
			exp = exp.replace("log", "Math.log10");*/
		if (exp.contains("e"))
			exp = exp.replace("e", "Math.exp");
		if (exp.contains("^"))
		{
			int indice=0;
			int indiceAp=0,indiceAv=0;
			for (int i = 0; i < exp.length(); i++) {
				if(exp.charAt(i)=='^'){
					indice=i;
					System.out.println(indice);
					for (int j = indice; j < exp.length()-1; j++) {
						if(exp.charAt(j)=='+' ||exp.charAt(j)=='-'||exp.charAt(j)=='/'||exp.charAt(j)=='*'){
							indiceAp=j;
							break;
						}			
						else indiceAp=exp.length();
							
					}
					for (int k = indice; k >= 0; k--) {
						if(exp.charAt(k)=='+' ||exp.charAt(k)=='-'||exp.charAt(k)=='/'||exp.charAt(k)=='*'){
							indiceAv=k+1;
							System.out.println(indiceAv);
							break;
						}	
						else indiceAv=0;
					}
					
				}
			}
			System.out.println(indice);
			String powAp=exp.substring(indice+1, indiceAp);
			System.out.println(powAp);
			String powAv=exp.substring(indiceAv, indice );		
			exp=exp.substring(0, indiceAv)+"Math.pow("+powAv+","+powAp+")"+exp.substring(indiceAp, exp.length());
		}
		if(exp.contains("!"))
		{
			
			int indice=0, indiceAv = 0;
			for (int i = 0; i < exp.length(); i++) {
				if(exp.charAt(i)=='!')
				{
					System.out.println(i);
					indice=i;
					for (int j = indice; j >0; j--) {
						System.out.println(indice);
						if(exp.charAt(j)=='+' ||exp.charAt(j)=='-'||exp.charAt(j)=='/'||exp.charAt(j)=='*'){
							indiceAv=j;
							break;
						}
						else
							indiceAv=0;
					}
				}
				
			}
			String ex="";
			ex=exp.substring(indiceAv,indice);
			System.out.println(exp);
			int n=Integer.parseInt(ex);
			ex=Integer.toString(Metier.factorielle(n));
			System.out.println("--------"+ex);
			exp=ex+exp.substring(indice+1);
			System.out.println(exp);
			
		}
		try {
			return calculSimple(exp);
		} catch (ScriptException e) {
			return "Syntax ERROR";
			
		}
	}
	public static int factorielle(int n) {
		  return n <= 1
		       ? 1
		       : n * factorielle(n - 1);
		}
	public String calculSimple(String exp) throws ScriptException {

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		//System.out.println(exp);
		return engine.eval(exp).toString();
	}
}
