package datos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Vuelo {
	
	private String nVuelo;
	private Airport origen;
	private Airport destino;
	private Aircraft avion;
	
	public Vuelo(String nVuelo, Airport origen, Airport destino, Aircraft avion) {
		this.nVuelo = nVuelo;
		this.origen = origen;
		this.destino = destino;
		this.avion = avion;
	}

	@Override
	public String toString() {
		return "Vuelo [nVuelo=" + nVuelo + ", origen=" + origen + ", destino=" + destino + ", avion=" + avion + "]";
	}

	public String getnVuelo() {
		return nVuelo;
	}

	public Airport getOrigen() {
		return origen;
	}

	public Airport getDestino() {
		return destino;
	}

	public Aircraft getAvion() {
		return avion;
	}
	
	public JPanel getJPanel() {
		JPanel flightPanel = new JPanel();
		flightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		flightPanel.setBackground(Color.WHITE);
		flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.Y_AXIS));
		
		JLabel lblnVuelo = new JLabel(this.nVuelo);
		lblnVuelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblnVuelo);
		flightPanel.add(Box.createVerticalStrut(10));
		
		JLabel lblAeropuertoDeOrigen = new JLabel("Aeropuerto de Origen:");
		lblAeropuertoDeOrigen.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lblAeropuertoDeOrigen);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblOrigen = new JLabel(origen.getCity());
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblOrigen);
		flightPanel.add(Box.createVerticalStrut(10));

		JLabel lblAeropuertoDeDestino = new JLabel("Aeropuerto de Destino:");
		lblAeropuertoDeDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lblAeropuertoDeDestino);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblDestino = new JLabel(destino.getCity());
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblDestino);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblAcTitle = new JLabel("Informaci\u00F3n sobre la aeronave:");
		lblAcTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lblAcTitle);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblAcName = new JLabel(avion.getName() + " ("+ avion.getIATA() + ")");
		lblAcName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblAcName);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblImagenDelAvion = new JLabel();
		lblImagenDelAvion.setIcon(avion.getImage());
		flightPanel.add(lblImagenDelAvion);
		
		return flightPanel;
	}
	


}
