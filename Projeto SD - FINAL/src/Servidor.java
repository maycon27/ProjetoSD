import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

class Servidor {

	ServerSocket server = null;

	public static void main(String[] args) {
		ServerSocket server = null;

		try {
			server = new ServerSocket(8000);
			server.setReuseAddress(true);

			while (true) {

				// Aceita as solicitações do cliente
				Socket client = server.accept();
				// valores do endereço e a porta do cliente
				System.out.println("Cliente conectado");
				System.out.println(client.getInetAddress().getHostAddress());
				System.out.println(client.getPort());

				SolicitarServerFile serverSock = new SolicitarServerFile(client);

				new Thread(serverSock).start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class SolicitarServerFile implements Runnable {

		private final Socket fileServerSocket;

		public SolicitarServerFile(Socket socket) {
			this.fileServerSocket = socket;
		}

		@Override
		public void run() {
			BufferedReader in = null;
			try {

				// Pega os dados passado pelo cliente
				in = new BufferedReader(new InputStreamReader(fileServerSocket.getInputStream()));
				String file;
				file = in.readLine();
				int timeout = 10 * 1000;
				DatagramSocket requestSocket = new DatagramSocket();
				requestSocket.setSoTimeout(timeout);
				InetAddress IPAddress = InetAddress.getByName("239.0.0.1");

				// Contém o nome do arquivo
				byte[] sendData = file.getBytes();
				// Contém o nome e o ip do servidor de arquivos
				byte[] receiveData = new byte[1024];

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9000);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				// envia broadcast para os servidores de arquivo da rede
				requestSocket.send(sendPacket);

				String[] nomeServidorDeArquivo = new String[1000];
				int QuantidadeDeServidores = 0;

				try {
					while (true) {
						// Recebe as solicitaçõs dos servidores de arquivos que tem o arquivo pedido
						requestSocket.receive(receivePacket);
						// requestSocket.setSoTimeout(timeout); // reinicia o tempo
						String receive = new String(receivePacket.getData(), 0, receivePacket.getLength());
						nomeServidorDeArquivo[QuantidadeDeServidores] = receive; // salva o nome do servidor de arquivos
						QuantidadeDeServidores++;
					}

				} catch (SocketTimeoutException e) { // Ao final de 10 segundos
					System.out.println("Servidores encontrados: " + QuantidadeDeServidores);
					// Envia a quantidade de servidores de arquivos encontrados
					DataOutputStream respostaCliente = new DataOutputStream(fileServerSocket.getOutputStream());
					respostaCliente.writeInt(QuantidadeDeServidores);

					// Envia os nomes dos servidores encontrados
					for (int i = 0; i < QuantidadeDeServidores; i++) {
						respostaCliente.write(nomeServidorDeArquivo[i].getBytes());
					}

					requestSocket.close();
					fileServerSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
