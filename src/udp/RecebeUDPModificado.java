package udp;

import java.io.*;
import java.net.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class RecebeUDPModificado {
	public static void main(String[] args) throws Exception {
		byte[] buffer = new byte[1024];
		String s;
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		DatagramSocket socket_receive = new DatagramSocket(1235);
		DatagramSocket socket_send = new DatagramSocket();

		InetAddress end = InetAddress.getByName("127.0.0.1");
		boolean flag = false;
		String campo = null;
		String frase = null;
		String[] s2 = new String[10];
		int j = 0;

		for (;;) {

			if (flag == false) {
				try {
					socket_receive.setSoTimeout(10000);
					for (int i = 0; i < 5; i++) {
						try {
							socket_receive.receive(packet);
							s = new String(buffer, 0, packet.getLength());
							System.out.println("Districhando...");
							campo = s.substring(0, 1);
							frase = s.substring(1, packet.getLength());
							s2[j] = frase;
							System.out.println("Field Extracted:" + campo);
							System.out.println("From: " + packet.getAddress().getHostName() + "Port:" + packet.getPort()
									+ "Field:" + campo + "Msg:" + frase);
							if (Integer.parseInt(campo) > -1)
								break;

						} catch (InterruptedIOException e) {
							System.out.println("TimeOut: " + e);
						}
					}
				} catch (SocketException e) {
					System.out.println("Erro de Socket: " + e);
				}
			}

			if (Integer.parseInt(campo) == 9) {
				System.out.println("Send confirmation packet cancel!!!");
				flag = true;
				break;

			} else {
				byte[] msg = new byte[campo.length()];
				msg = campo.getBytes();
				DatagramPacket packet_send = new DatagramPacket(msg, msg.length, end, 1234);
				socket_send.send(packet_send);
				System.out.println("Confirmation send !!!");
				j++;
			}
		}

		JFrame f = new JFrame("Http-Like");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Tela e = new Tela(s2, s2.length);
		f.add(e);

		f.setSize(500, 500);
		f.setVisible(true);

	}

}

class Tela extends JPanel {

	/**
	* 
	*/
	ArrayList<String> line = new ArrayList<String>();
	static int i = 0;
	String estilo;
	String texto;

	public ArrayList<String> getLine() {
		return line;
	}

	Tela(String[] s, int tam) {

		while (i < tam) {
			this.line.add(s[i]);
			i++;
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);

		int estilo = -1;
		int tam = 12;
		String tipoFonte = null;
		int protocolo;

		int i = 0;
		while (i < this.line.size()) {
			protocolo = Integer.parseInt(this.line.get(i).substring(1, 2));
			switch (protocolo) {

			case 1:
				g.setColor(new Color(255, 0, 0));
				break;
			case 2:
				g.setColor(new Color(0, 255, 0));
				break;
			case 3:
				g.setColor(new Color(0, 0, 255));
				break;
			case 4:
				g.setColor(new Color(144, 255, 144));
				break;
			case 5:
				g.setColor(new Color(0, 255, 255));
				break;
			case 6:
				g.setColor(new Color(255, 0, 255));
				break;
			case 7:
				g.setColor(new Color(0, 255, 144));
				break;
			case 8:
				g.setColor(new Color(144, 255, 0));
				break;
			case 9:
				g.setColor(new Color(192, 192, 192));
				break;

			default:
				g.setColor(new Color(0, 0, 0));
				break;
			}

			protocolo = Integer.parseInt(this.line.get(i).substring(2, 3));
			switch (protocolo) {

			case 1:
				tipoFonte = "Arial";
				break;
			case 2:
				tipoFonte = "Calibri";
				break;
			case 3:
				tipoFonte = "Comic Sans MS";
				break;
			case 4:
				tipoFonte = "Times New Roman";
				break;
			case 5:
				tipoFonte = "Georgia";
				break;
			case 6:
				tipoFonte = "Lucida Sans";
				break;
			case 9:
				tipoFonte = "Serif";
				break;
			case 8:
				tipoFonte = "Tahoma";
				break;
			case 7:
				tipoFonte = "Verdana";
				break;
			default:
				tipoFonte = "Times New Roman";
				break;
			}

			protocolo = Integer.parseInt(this.line.get(i).substring(3, 4));
			switch (protocolo) {
			case 1:
				estilo = 0;
				break;
			case 2:
				estilo = 1;
				break;
			case 3:
				estilo = 2;
				break;

			default:
				estilo = -1;
				break;

			}

			protocolo = Integer.parseInt(this.line.get(i).substring(4, 5));

			switch (protocolo) {
			case 1:
				tam = 22;
				break;
			case 2:
				tam = 28;
				break;
			case 3:
				tam = 32;
				break;

			default:
				tam = 12;
				break;

			}

			Font f = new Font(tipoFonte, estilo, tam);
			FontMetrics fm = g.getFontMetrics();

			g.setFont(f);
			if (i > 0)
				g.drawString(this.line.get(i).substring(6, this.line.get(i).length()), 10, 30 * i + fm.getAscent());
			else
				g.drawString(this.line.get(i).substring(6, this.line.get(i).length()), 10, 30 * 1 / 2 + fm.getAscent());

			i++;

		}

	}
}
