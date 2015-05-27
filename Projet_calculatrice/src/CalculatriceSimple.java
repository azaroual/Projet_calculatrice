import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptException;
import javax.swing.*;

import Metier.Metier;

public class CalculatriceSimple extends JPanel implements ActionListener {
	// Creation des panneaux
	private JPanel container;
	//private JPanel panEcran;
	private JPanel chiffre;
	private JPanel operateur;

	//private JLabel ecran;
	//private JLabel resultat;

	private JButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, point;
	private JButton c, moins, plus, egal, divi, multi;

	private Dimension dimChif = new Dimension(50, 40);
	private Dimension dimCAL = new Dimension(220, 180);
	private Dimension dimOper = new Dimension(50, 31);
	private Metier m;
	private Ecran ec;

	public CalculatriceSimple(Ecran ec) {
		m = new Metier();
		this.ec=ec;
		
		init();
	}

	public void init() {
		container = new JPanel(new BorderLayout());
		container.setPreferredSize(dimCAL);
		// Dimension des panneux
		//panEcran = new JPanel();
		//panEcran.setPreferredSize(new Dimension(220, 60));
		chiffre = new JPanel();
		chiffre.setPreferredSize(new Dimension(165, 225));
		operateur = new JPanel();
		operateur.setPreferredSize(new Dimension(55, 225));

		//ecran = new JLabel("0");
		//resultat = new JLabel("");
		//Font police = new Font("Arial", Font.BOLD, 20);
		//ecran.setFont(police);
		//resultat.setFont(police);
		// On aligne les informations à droite dans le JLabel
		//ecran.setHorizontalAlignment(JLabel.RIGHT);
		//ecran.setPreferredSize(new Dimension(220, 20));
		//resultat.setFont(police);
		// On aligne les informations à droite dans le JLabel
		//resultat.setHorizontalAlignment(JLabel.RIGHT);
		//resultat.setPreferredSize(new Dimension(220, 20));
		//panEcran.add(ecran);
		//panEcran.add(resultat);

		n1 = new JButton("1");
		n1.addActionListener(this);
		n1.setPreferredSize(dimChif);
		chiffre.add(n1);
		n2 = new JButton("2");
		n2.addActionListener(this);
		n2.setPreferredSize(dimChif);
		chiffre.add(n2);
		n3 = new JButton("3");
		n3.addActionListener(this);
		n3.setPreferredSize(dimChif);
		chiffre.add(n3);
		n4 = new JButton("4");
		n4.addActionListener(this);
		n4.setPreferredSize(dimChif);
		chiffre.add(n4);
		n5 = new JButton("5");
		n5.addActionListener(this);
		n5.setPreferredSize(dimChif);
		chiffre.add(n5);
		n6 = new JButton("6");
		n6.addActionListener(this);
		n6.setPreferredSize(dimChif);
		chiffre.add(n6);
		n7 = new JButton("7");
		n7.addActionListener(this);
		n7.setPreferredSize(dimChif);
		chiffre.add(n7);
		n8 = new JButton("8");
		n8.addActionListener(this);
		n8.setPreferredSize(dimChif);
		chiffre.add(n8);
		n9 = new JButton("9");
		n9.addActionListener(this);
		n9.setPreferredSize(dimChif);
		chiffre.add(n9);
		n0 = new JButton("0");
		n0.addActionListener(this);
		n0.setPreferredSize(dimChif);
		chiffre.add(n0);
		point = new JButton(".");
		point.addActionListener(this);
		point.setPreferredSize(dimChif);
		chiffre.add(point);

		c = new JButton("C");
		c.addActionListener(this);
		c.setForeground(Color.red);
		c.setPreferredSize(dimOper);
		operateur.add(c);
		egal = new JButton("=");
		egal.addActionListener(this);
		egal.setPreferredSize(dimChif);
		chiffre.add(egal);
		plus = new JButton("+");
		plus.addActionListener(this);
		plus.setPreferredSize(dimOper);
		operateur.add(plus);
		moins = new JButton("-");
		moins.addActionListener(this);
		moins.setPreferredSize(dimOper);
		operateur.add(moins);
		multi = new JButton("*");
		multi.addActionListener(this);
		multi.setPreferredSize(dimOper);
		operateur.add(multi);
		divi = new JButton("/");
		divi.addActionListener(this);
		divi.setPreferredSize(dimOper);
		operateur.add(divi);

		//panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		//container.add(panEcran, BorderLayout.NORTH);
		
		container.add(chiffre, BorderLayout.CENTER);
		container.add(operateur, BorderLayout.EAST);
		this.add(container);

	}

	public void actionPerformed(ActionEvent e) {
		if ((JButton) e.getSource() == c) {
			ec.setEcran("0");
			ec.setResultat("");
			//ecran.setText("0");
			//resultat.setText("");
		} else if ((JButton) e.getSource() == egal) {
			try {
				String res=m.remplacer(ec.getEcran());//String res = m.calculSimple(ecran.getText());
				
				res = m.calculSimple(res);
				
				//resultat.setText(res);
				ec.setResultat(res);
			} catch (ScriptException e1) {
				//resultat.setText("Syntax Error");
				ec.setResultat("Syntax Error");
			}

		} else {
			String str = ((JButton) e.getSource()).getText();

			//if (!ecran.getText().equals("0") && !ecran.getText().equals(".")) {
			if (!ec.getEcran().equals("0") && !ec.getEcran().equals(".")) {
				//if (!resultat.getText().equals("")){
				if (!ec.getResultat().equals("")){
					str = ((JButton) e.getSource()).getText();
					//resultat.setText("");
					ec.setResultat("");
				}
					
				else
					//str = ecran.getText() + str;
					str = ec.getEcran() + str;
			}
			
			//ecran.setText(str);
			ec.setEcran(str);
		}

	}
}

