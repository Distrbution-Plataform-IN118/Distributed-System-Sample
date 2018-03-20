package udp;
import java.io.*;
import java.net.*;

public class EnviaUDP {
	public static void main(String[] args) throws Exception {

		InetAddress end = InetAddress.getByName("127.0.0.1");

		String s[] = { "<1111 Hello Marcos...", "<2222 Estou fazendo...", "<3333 Atividade...",
				"<4433 Sistemas Distribuidos...", "<5522 Testando protocolo udp...",
				"<6633 Implemntando camada de confirmacao...", "<7711 Quando chegar no decimo...",
				"<8822 Pacote o destinario...", "<9933 Deixar de enviar...", "<0000 Confirmacao..." };

		int flag = 0;
		int i = 0;
		String temp = null;
		DatagramSocket socket = new DatagramSocket();
		DatagramSocket socket_receive = new DatagramSocket(1234);

		System.out.println("Carregado vetor de string!!!");

		while (i < s.length) {
			byte[] buffer = new byte[1024];
			String receive;
			DatagramPacket packet_receive = new DatagramPacket(buffer, buffer.length);

			byte[] msg = new byte[s[i].length()];
			temp = s[i];
			s[i] = Integer.toString(i) + s[i];
			// s[i].concat(temp);

			msg = s[i].getBytes();
			DatagramPacket packet = new DatagramPacket(msg, msg.length, end, 1235);
			socket.send(packet);

			flag = i;
			System.out.println("Mensagem " + i + " enviada esperando confirmacao");

			try {
				socket_receive.setSoTimeout(10000);
				for (int j = 0; j < 5; j++) {
					try {
						// Recebendo Datagrama
						byte[] buffer_receive = new byte[1024];
						socket_receive.receive(packet_receive);
						receive = new String(buffer_receive, 0, packet_receive.getLength());
						System.out.println("Confirmed Receive From: " + packet_receive.getAddress().getHostName() + ":"
								+ packet_receive.getPort() + ":" + receive);
						if (packet_receive.getLength() > 0) { 
							i++;
							break;
						} else {
							i = flag;
						}

					} catch (InterruptedIOException e) {
						System.out.println("TimeOut: " + e);
					}
				}
			} catch (SocketException e) {
				System.out.println("Erro de Socket: " + e);
			}
		}
		socket.close();
	}
}
