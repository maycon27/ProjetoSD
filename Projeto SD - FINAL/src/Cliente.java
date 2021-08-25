import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Cliente extends JFrame{
	static String nomeArq = null;
	static int contador;
	static Socket clientSocket = null;
	static String ip = "127.0.0.1";
	
	
	public static void SolicitarServidores() {

		try {
			
			clientSocket = new Socket("127.0.0.1", 8000);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			Scanner sc = new Scanner(System.in);
			String file = null;
			System.out.print("Informe o nome do arquivo: ");
			file = sc.nextLine();
			nomeArq = file;
			// envia o nome do arquivo para o servidor principal
			out.println(file);
			out.flush();

			DataInputStream sdis = new DataInputStream(clientSocket.getInputStream());
			contador = sdis.readInt();
			int cont_aux;
			System.out.println("Servidores de arquivos");

			// lista o nome dos servidores de arquivos que tem o arquivo
			for (int i = 0; i < contador; i++) {
				cont_aux = contador - i;
				System.out.println(cont_aux + "-" + in.readLine());
			}

			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

	public static void main(String[] args) throws IOException {

		try {
			
			/*//chamada da Tela;
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TELA1 window = new TELA1();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});*/
			
			SolicitarServidores();
			//SolicitarServidores();
			int porta_f = 0;
			int q;
			Scanner sc = new Scanner(System.in);

			System.out.println("Informe o numero do qual servidor vc deseja baixar: ");
			q = sc.nextInt();

			if (q <= contador) {
				porta_f = 7000 + (q - 1);
			} else {
				System.out.println("Opção indisponivel");
			}

			Socket fileSocket = new Socket(ip, porta_f);
			DataOutputStream outToServer = new DataOutputStream(fileSocket.getOutputStream());
			outToServer.writeBytes(nomeArq + '\n');

			// Criando arquivo que sera recebido pelo servidor
			FileOutputStream fileOut = new FileOutputStream(nomeArq);

			// Criando canal de transferencia
			InputStream socketIn = fileSocket.getInputStream();

			// Lendo o arquivo recebido pelo socket e gravando no
			// arquivo local

			byte[] cbuffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = socketIn.read(cbuffer)) != -1) {
				fileOut.write(cbuffer, 0, bytesRead);
			}
			fileOut.close();

			fileSocket.close();
			System.out.println("Arquivo Recebido: " + nomeArq);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}

	}

}
