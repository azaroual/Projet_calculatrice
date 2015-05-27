import java.awt.*;

import javax.swing.*;

public class Ecran extends JPanel {

	private JPanel panEcran;
	private JLabel ecran;
	private JLabel resultat;
	

	public Ecran(Dimension dimPan,Dimension dimLab) {
		initEcran(dimPan,dimLab);

	}
	

	public String getEcran() {
		return this.ecran.getText();
	}


	public void setEcran(String ecran) {
		this.ecran.setText(ecran);
	}


	public String getResultat() {
		return this.resultat.getText();
	}


	public void setResultat(String resultat) {
		this.resultat.setText(resultat);
	}


	public void initEcran(Dimension dimPan,Dimension dimLab) 
	{
		
		panEcran=new JPanel();
		panEcran.setPreferredSize(dimPan);
		panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		
		resultat = new JLabel();

		ecran = new JLabel("0");
		resultat = new JLabel("");
		Font police = new Font("Arial", Font.BOLD, 20);
		 ecran.setFont(police);
		resultat.setFont(police);
		// On aligne les informations à droite dans le JLabel
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(dimLab);
		resultat.setFont(police);
		// On aligne les informations à droite dans le JLabel
		resultat.setHorizontalAlignment(JLabel.RIGHT);
		resultat.setPreferredSize(dimLab);
		panEcran.add(ecran);
		panEcran.add(resultat);
		this.add(panEcran);
		this.setVisible(true);

	}
	
}
