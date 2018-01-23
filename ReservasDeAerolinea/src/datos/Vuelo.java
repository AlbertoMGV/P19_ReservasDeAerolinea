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

import gestores.GestorDB;

public class Vuelo {
	
	private String nVuelo;
	private Airport origen;
	private Airport destino;
	private Aircraft avion;
	private String aerolinea;


	public Vuelo(String nVuelo, Airport origen, Airport destino, Aircraft avion) {
		this.nVuelo = nVuelo;
		this.origen = origen;
		this.destino = destino;
		this.avion = avion;
		this.aerolinea = nVuelo.trim().substring(0, 2);		
	}

	

	@Override
	public String toString() {
		return "Vuelo [nVuelo=" + nVuelo + ", origen=" + origen + ", destino=" + destino + ", avion=" + avion + "]";
	}
	
	
	public String getAerolinea() {
		return aerolinea;
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
		
		JLabel lbltnVuelo = new JLabel("Nº de Vuelo:");
		lbltnVuelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lbltnVuelo);
		flightPanel.add(Box.createVerticalStrut(10));
		
		JLabel lblnVuelo = new JLabel(this.nVuelo);
		lblnVuelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblnVuelo);
		flightPanel.add(Box.createVerticalStrut(10));
		
		JLabel lbltAerolinea= new JLabel("Aerolinea:");
		lbltAerolinea.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lbltAerolinea);
		flightPanel.add(Box.createVerticalStrut(10));
		
		JLabel lblAerolinea= new JLabel(GestorDB.getAirlineName(aerolinea) + " (" + aerolinea + "/"+GestorDB.getAirlineICAO(aerolinea) +")");
		lblAerolinea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblAerolinea);
		flightPanel.add(Box.createVerticalStrut(10));
		
		JLabel lblAeropuertoDeOrigen = new JLabel("Aeropuerto de Origen:");
		lblAeropuertoDeOrigen.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lblAeropuertoDeOrigen);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblOrigen = new JLabel(origen.getCity() + " (" + origen.getIATA() + "/" + origen.getICAO() + ")");
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblOrigen);
		flightPanel.add(Box.createVerticalStrut(10));

		JLabel lblAeropuertoDeDestino = new JLabel("Aeropuerto de Destino:");
		lblAeropuertoDeDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lblAeropuertoDeDestino);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblDestino = new JLabel(destino.getCity() + " (" + destino.getIATA() + "/" + destino.getICAO() + ")");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblDestino);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblAcTitle = new JLabel("Informaci\u00F3n sobre la aeronave:");
		lblAcTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		flightPanel.add(lblAcTitle);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblAcName = new JLabel(avion.getName() + " ("+ avion.getIATA() + ")" + ", velocidad máxima: " + avion.getSpeed() + "km/h.");
		lblAcName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightPanel.add(lblAcName);
		flightPanel.add(Box.createVerticalStrut(10));

		
		JLabel lblImagenDelAvion = new JLabel();
		lblImagenDelAvion.setIcon(avion.getImage());
		flightPanel.add(lblImagenDelAvion);
		
		return flightPanel;
	}
	


}
