import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;

public class TELA2 {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TELA2 window = new TELA2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TELA2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ESCOLHA O SERVIDOR PARA DOWNLOAD");
		lblNewLabel.setBounds(111, 84, 210, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane CABECARIO = new JTextPane();
		CABECARIO.setEditable(false);
		CABECARIO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CABECARIO.setBackground(new Color(0, 128, 128));
		CABECARIO.setForeground(new Color(255, 255, 255));
		CABECARIO.setText("###TRABALHO FINAL | SISTEMAS DISTRIBUIDOS | GILTON, JEOVANE & MAYCON###");
		CABECARIO.setBounds(0, 0, 434, 20);
		frame.getContentPane().add(CABECARIO);
		
		JButton btnIr = new JButton("IR");
		btnIr.setBackground(new Color(0, 128, 128));
		btnIr.setForeground(new Color(255, 255, 255));
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ola mundo");
			}
			
		});
		btnIr.setBounds(172, 159, 89, 23);
		frame.getContentPane().add(btnIr);
		
		JLabel lblNewLabel_1 = new JLabel("Arquivo Recebeido!");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setBounds(172, 236, 100, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblProntoArquivoEncontrado = new JLabel("PRONTO! ARQUIVO ENCONTRADO!");
		lblProntoArquivoEncontrado.setBounds(128, 48, 173, 14);
		frame.getContentPane().add(lblProntoArquivoEncontrado);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Server 1");
		rdbtnNewRadioButton.setBounds(34, 118, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Server 2");
		rdbtnNewRadioButton_1.setBounds(178, 118, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Sever 3");
		rdbtnNewRadioButton_2.setBounds(319, 118, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(Color.GREEN);
		progressBar.setBounds(142, 206, 146, 14);
		frame.getContentPane().add(progressBar);
	}
}
