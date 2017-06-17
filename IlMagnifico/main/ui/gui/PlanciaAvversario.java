package main.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.ui.gui.components.ButtonLIM;

public class PlanciaAvversario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9065298711726398042L;
	private ArrayList<Plancia> planceAvversari = new ArrayList<Plancia>();
	private ArrayList<ButtonLIM> scorriPlance = new ArrayList<ButtonLIM>();

	PlanciaAvversario(ArrayList<Plancia> plance) {
		this.setBounds(0, 50, 1362, 644);
		this.setVisible(true);
		this.setLayout(null);
		this.planceAvversari = plance;
		for (int i = 0; i < planceAvversari.size(); i++) {
			add(planceAvversari.get(i));
			planceAvversari.get(i).setBounds(0, 0, 1362, 644);
			if (i == 0)
				planceAvversari.get(i).setVisible(true);
			else
				planceAvversari.get(i).setVisible(false);

			if (planceAvversari.size() + 1 > 2) {
				scorriPlance.add(new ButtonLIM("NEXT"));
				scorriPlance.get(i).setBounds(0, 0, 120, 30);
				scorriPlance.get(i).setVisible(true);
				scorriPlance.get(i).addActionListener(new ScorriPlance());
				planceAvversari.get(i).add(scorriPlance.get(i));
			}

		}

	}

	public ArrayList<Plancia> getPlance() {
		return planceAvversari;
	}

	public void setPlance(ArrayList<Plancia> planceAvversari) {
		this.planceAvversari = planceAvversari;
	}

	private class ScorriPlance implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int planciaCorrente = 0;
			for (int i = 0; i < scorriPlance.size(); i++) {
				if (scorriPlance.get(i) == (ButtonLIM) (arg0.getSource())) {
					planciaCorrente = i;
					break;
				}
			}
			if (planciaCorrente == scorriPlance.size() - 1)
				planciaCorrente = 0;
			else
				planciaCorrente++;

			for (int i = 0; i < planceAvversari.size(); i++) {
				if (i != planciaCorrente)
					planceAvversari.get(i).setVisible(false);
				else
					planceAvversari.get(i).setVisible(true);
			}

		}

	}
}