import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class TELA3ARQUIVONAOENCONTRADO {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TELA3ARQUIVONAOENCONTRADO window = new TELA3ARQUIVONAOENCONTRADO();
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
	public TELA3ARQUIVONAOENCONTRADO() {
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
		
		JLabel lblNewLabel = new JLabel("ORA BOLAS!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(190, 45, 61, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane CABECARIO = new JTextPane();
		CABECARIO.setEditable(false);
		CABECARIO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CABECARIO.setBackground(new Color(0, 128, 128));
		CABECARIO.setForeground(new Color(255, 255, 255));
		CABECARIO.setText("###TRABALHO FINAL | SISTEMAS DISTRIBUIDOS | GILTON, JEOVANE & MAYCON###");
		CABECARIO.setBounds(0, 0, 434, 20);
		frame.getContentPane().add(CABECARIO);
		
		JLabel lblProntoArquivoEncontrado = new JLabel("ARQUIVO N\u00C3O ENCONTRADO!");
		lblProntoArquivoEncontrado.setBounds(148, 59, 173, 14);
		frame.getContentPane().add(lblProntoArquivoEncontrado);
		
		JLabel lblNewLabel_1 = new JLabel("FECHE TODAS EXECU\u00C7\u00D5ES DO CONSOLE!");
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(118, 114, 203, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E TENTE COM OUTRO NOME DE ARQUIVO.");
		lblNewLabel_2.setBounds(118, 129, 214, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("VERIFIQUE O NOME DO ARQUIVO!");
		lblNewLabel_3.setBounds(134, 98, 173, 14);
		frame.getContentPane().add(lblNewLabel_3);
	}

}
