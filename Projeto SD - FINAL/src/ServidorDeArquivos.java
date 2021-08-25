import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

class Conexao implements Runnable {

	private Socket socket;
	private String nomeArquivo;
	private String diretorio;

	public Conexao(Socket newSocket, String diretorio) {
		socket = newSocket;
		this.diretorio = diretorio;
	}

	@Override
	public void run() {
		try {
			BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			nomeArquivo = request.readLine();
			System.out.println("Solicitando arquivo \"" + nomeArquivo + "\"");

			File arquivo = new File(diretorio + "\\" + nomeArquivo);
			FileInputStream fis = new FileInputStream(arquivo);
			OutputStream chunk = socket.getOutputStream();
			final int bufferLength = 1024;
			byte[] arqBytes = new byte[bufferLength];

			// enviando o tamanho do arquivo
			DataOutputStream response = new DataOutputStream(socket.getOutputStream());
			response.writeLong(arquivo.length());

			// iniciando a leitura do arquivo
			int totalBytesRead = 0;
			while (true) { // lendo o arquivo
				int bytesRead = fis.read(arqBytes);

				if (bytesRead != -1) { // envia o perdaço do arquivo
					chunk.write(arqBytes, 0, bytesRead);
					chunk.flush();
					totalBytesRead += bytesRead;
				} else { // fim do arquivo
					System.out.println("Arquivo Enviado!");
					break;

				}
			}
			fis.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

public class ServidorDeArquivos {

	private static String diretorio = "C:\\Users\\GILTON_NOT\\Desktop\\TESTE";
	private static int porta = 7000;

	public static void main(String[] args) {

		while (true) {

			try (ServerSocket arqSocket = new ServerSocket(porta)) { // cria socket tcp
				Thread l = new Thread(new conexaoServer(9000, porta, diretorio)); // cria socket

				l.start();

				System.out.println("Aguardando conexões");
				Thread c = new Thread(new Conexao(arqSocket.accept(), diretorio));
				c.start();
			} catch (BindException e) { // endereços em uso > tenta uma nova porta
				porta++;
			} catch (FileNotFoundException e) {
				System.err.println("Arquivo não encontrado!");
				break;
			} catch (SocketTimeoutException e) {
				System.err.println("Tempo máximo de espera atingido!");
				System.err.println("Serviço encerrado");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

		}
	}

}

class conexaoServer implements Runnable {

	private int porta;
	private int portaDoArquivo;
	private String diretorio;

	public conexaoServer(int porta, int portaDoArquivo, String diretorio) {
		this.porta = porta;
		this.portaDoArquivo = portaDoArquivo;
		this.diretorio = diretorio;
	}

	@Override
	public void run() {

		try (MulticastSocket serverSocket = new MulticastSocket(porta)) {

			serverSocket.joinGroup(InetAddress.getByName("239.0.0.1"));

			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];

			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket); // recebe broadcast UDP do servidor principal
				String file = new String(receivePacket.getData(), 0, receivePacket.getLength());
				if (new File(diretorio + "\\" + file).isFile()) // se o ponteiro do arquivo apontar pra um arquivo
				// existente no disco
				{
					InetAddress IPAddress = receivePacket.getAddress(); // pega o ip do servidor principal
					int port = receivePacket.getPort(); // pega a porta do servidor principal
					String identification = InetAddress.getLocalHost().getHostName() + "-" + IPAddress.getHostName()
							+ ":" + portaDoArquivo + "\n"; // pega o nome do servidor de
					System.out.println("Arquivo encontrado!");
					sendData = identification.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket); // devolve para o servidor principal via UDP
				} else {
					System.out.println("Arquivo não encontrado!");
				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
