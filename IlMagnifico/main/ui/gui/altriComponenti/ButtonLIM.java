package main.ui.gui.altriComponenti;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonLIM extends JButton implements MouseListener {
	
	public ButtonLIM(){
		setBackground(Color.WHITE);
		setFont(new Font("ALGERIAN", 5, 15));
		addMouseListener(this);
	}
	
	public ButtonLIM(String testo){
		setBackground(Color.WHITE);
		setFont(new Font("ALGERIAN", 5, 15));
		setText(testo);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setBackground(Color.RED);
		setForeground(Color.YELLOW);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
