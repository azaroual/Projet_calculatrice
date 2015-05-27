import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatriceView extends JFrame implements ActionListener{
	
	private JPanel container;
	JMenuBar menu_bar1 = new JMenuBar();
	JMenu menu1 = new JMenu("Affichage");
	JMenuItem std = new JMenuItem("Standard");
	JMenuItem sc = new JMenuItem("Scientifique");
	private Dimension dimCAL;
	Ecran ec;
	
	public CalculatriceView()
	{
		
		ec =new Ecran(new Dimension(220, 60),new Dimension(220, 20));
		
		menu1.add(std);
	    menu1.add(sc);
	    menu_bar1.add(menu1);
	       
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		this.setTitle("Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);    
		CalculatriceSimple cSimple= new CalculatriceSimple(ec);
		container.add(ec);
		container.add(cSimple);
		this.setContentPane(container);
		this.setJMenuBar(menu_bar1);
		this.setVisible(true);
		this.pack();
		
		std.addActionListener(this);
		sc.addActionListener(this);
				
	}
	
	 public void actionPerformed(ActionEvent e){
		 if((JMenuItem)e.getSource()==std){
			 container.removeAll();
			this.setTitle("Calculatrice");
			CalculatriceSimple cSimple= new CalculatriceSimple(ec);
			container.add(ec);
			container.add(cSimple);
			container.validate();
			container.repaint();
			this.pack();
		 }
		 
		 if((JMenuItem)e.getSource()==sc){
			 	container.removeAll();
				this.setTitle("Calculatrice Scientifique");
				Ecran EcSc=new Ecran(new Dimension(550, 60),new Dimension(550, 20));
				CalculatriceScientifique cScientif= new CalculatriceScientifique(EcSc);
				container.add(EcSc);
				container.add(cScientif);
				container.validate();
				container.repaint();
				this.setSize(cScientif.getSize());
				this.pack();
		}
	 }
	
	
	public static void main(String[] args) {
		CalculatriceView c=new CalculatriceView();

	}

}
