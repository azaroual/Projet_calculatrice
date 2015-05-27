import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JPanel;

import Metier.Derivee2;
import Metier.Metier;

public class CalculatriceScientifique extends JPanel implements ActionListener {

	// panneaux
	private JPanel container;
	private JPanel operateurScientifique;
	// boutons
	private JButton sin, cos, tan, ln, log, e, n, pO, pF, sqrt, sinh, cosh,
			tanh, pow,ans,sup,derivee,x;

	private Dimension dimCAL;
	private Dimension dimOperSc;
	CalculatriceSimple cSimple;
	Ecran ec;
	Metier m;

	public CalculatriceScientifique(Ecran ec) {
		this.ec = ec;
		m = new Metier();
		init();
	}

	private void init() {
		operateurScientifique = new JPanel();
		operateurScientifique.setPreferredSize(new Dimension(165, 225));
		dimCAL = new Dimension(510, 190);
		cSimple = new CalculatriceSimple(ec);
		dimOperSc = new Dimension(65, 40);

		sin = new JButton("sin");
		sin.addActionListener(this);
		sin.setPreferredSize(dimOperSc);
		operateurScientifique.add(sin);
		ln = new JButton("ln");
		ln.addActionListener(this);
		ln.setPreferredSize(dimOperSc);
		operateurScientifique.add(ln);
		
		pO = new JButton("(");
		pO.addActionListener(this);
		pO.setPreferredSize(dimOperSc);
		operateurScientifique.add(pO);
		pF = new JButton(")");
		pF.addActionListener(this);
		pF.setPreferredSize(dimOperSc);
		operateurScientifique.add(pF);
		sinh = new JButton("sinh");
		sinh.addActionListener(this);
		sinh.setPreferredSize(dimOperSc);
		//operateurScientifique.add(sinh);
		cos = new JButton("cos");
		cos.addActionListener(this);
		cos.setPreferredSize(dimOperSc);
		operateurScientifique.add(cos);
		log = new JButton("log");
		log.addActionListener(this);
		log.setPreferredSize(dimOperSc);
		operateurScientifique.add(log);
		sqrt = new JButton("√");
		sqrt.addActionListener(this);
		sqrt.setPreferredSize(dimOperSc);
		operateurScientifique.add(sqrt);
		pow = new JButton("^");
		pow.addActionListener(this);
		pow.setPreferredSize(dimOperSc);
		operateurScientifique.add(pow);
		cosh = new JButton("cosh");
		cosh.addActionListener(this);
		cosh.setPreferredSize(dimOperSc);
	//	operateurScientifique.add(cosh);
		tan = new JButton("tan");
		tan.addActionListener(this);
		tan.setPreferredSize(dimOperSc);
		operateurScientifique.add(tan);
		e = new JButton("e");
		e.addActionListener(this);
		e.setPreferredSize(dimOperSc);
		operateurScientifique.add(e);
		n = new JButton("n!");
		n.addActionListener(this);
		n.setPreferredSize(dimOperSc);
		operateurScientifique.add(n);
		tanh = new JButton("tanh");
		tanh.addActionListener(this);
		tanh.setPreferredSize(dimOperSc);
		//operateurScientifique.add(tanh);
		
		sup = new JButton("←");
		sup.addActionListener(this);
		sup.setPreferredSize(dimOperSc);
		operateurScientifique.add(sup);
		
		x = new JButton("x");
		x.addActionListener(this);
		x.setPreferredSize(dimOperSc);
		operateurScientifique.add(x);
		
		derivee = new JButton("Dérivée");
		derivee.addActionListener(this);
		derivee.setPreferredSize(new Dimension(124,40));
		operateurScientifique.add(derivee);
		
		
		ans = new JButton("Ans");
		ans.addActionListener(this);
		ans.setPreferredSize(dimOperSc);
		operateurScientifique.add(ans);

		container = new JPanel(new BorderLayout());
		container.setPreferredSize(dimCAL);
		container.add(cSimple, BorderLayout.EAST);
		container.add(operateurScientifique, BorderLayout.CENTER);
		this.add(container);

	}
	public String remplacer(String str)
	{
		if (str == ("sin")) {
			str = str.replace("sin", "sin(");
		}
		if (str == ("cos")) {
			str = str.replace("cos", "cos(");
		}
		if (str == ("tan")) {
			str = str.replace("tan", "tan(");
		}
		if (str == ("ln")) {
			str = str.replace("ln", "ln(");
		}
		if (str == ("log")) {
			str = str.replace("log", "log(");
		}
		if (str == ("sinh")) {
			str = str.replace("sinh", "sinh(");
		}
		if (str == ("cosh")) {
			str = str.replace("cosh", "cosh(");
		}
		if (str == ("tanh")) {
			str = str.replace("tanh", "tanh(");
		}
		if (str == ("e")) {
			str = str.replace("e", "e(");
		}
		if (str == ("√")) {
			str = str.replace("√", "√(");
		}
		if (str == ("n!")) {
			str = str.replace("n!", "!");
		}
		return str;
	}
	public void actionPerformed(ActionEvent e) {

		String str = ((JButton) e.getSource()).getText();
		str=remplacer(str);
	
		
		if ((JButton) e.getSource()==sup)
		{
			if (!ec.getEcran().equals("0") && !ec.getEcran().equals("")) {
				str=ec.getEcran();
				if(str.length()!=0)
				{
				str=str.substring(0, str.length()-1);
				ec.setEcran(str);
				}
				else{
					ec.setEcran("0");
					ec.setResultat("");
				}
					
			}
		}
		else if ((JButton) e.getSource()==ans)
		{
			if(!ec.getResultat().equals(""))
			{
				str=ec.getResultat();
				ec.setResultat("");
			}
			else
				str="0";
		}
		else if((JButton)e.getSource()==derivee)
		{
			if(!ec.getEcran().equals("")){
				str=ec.getEcran();
				ec.setResultat(Derivee2.mainDerivee(str));
			}
		}
		else{
			if (!ec.getEcran().equals("0") && !ec.getEcran().equals(".")) {
				if (!ec.getResultat().equals("")) {
					str = ((JButton) e.getSource()).getText();
					str=remplacer(str);
					ec.setResultat("");
				}

				else
					str = ec.getEcran() + str;
			}
		}
		ec.setEcran(str);
	}
}
