import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class TELA1 {

	private JTextField inserirArquivo;
	private JButton btnIr;
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TELA1 window = new TELA1();
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
	public TELA1() {
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
		
		inserirArquivo = new JTextField();
		inserirArquivo.setForeground(new Color(0, 128, 128));
		inserirArquivo.setBounds(47, 135, 232, 22);
		frame.getContentPane().add(inserirArquivo);
		inserirArquivo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DIGITE O NOME DO ARQUIVO");
		lblNewLabel.setBounds(47, 119, 177, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane CABECARIO = new JTextPane();
		CABECARIO.setEditable(false);
		CABECARIO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CABECARIO.setBackground(new Color(0, 128, 128));
		CABECARIO.setForeground(new Color(255, 255, 255));
		CABECARIO.setText("###TRABALHO FINAL | SISTEMAS DISTRIBUIDOS | GILTON, JEOVANE & MAYCON###");
		CABECARIO.setBounds(0, 0, 434, 20);
		frame.getContentPane().add(CABECARIO);
		
		JTextPane txtpnAreaCliente = new JTextPane();
		txtpnAreaCliente.setForeground(new Color(0, 128, 128));
		txtpnAreaCliente.setBackground(new Color(230, 230, 250));
		txtpnAreaCliente.setEditable(false);
		txtpnAreaCliente.setText("AREA_CLIENTE");
		txtpnAreaCliente.setBounds(170, 62, 78, 20);
		frame.getContentPane().add(txtpnAreaCliente);
		
		JButton btnIr = new JButton("IR");
		btnIr.setBackground(new Color(0, 128, 128));
		btnIr.setForeground(new Color(255, 255, 255));
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			
		});
		btnIr.setBounds(302, 135, 89, 23);
		frame.getContentPane().add(btnIr);
		
		JLabel lblNewLabel_1 = new JLabel("Digite corretamente o nome do arquivo. Sem espacos e com a extensao correta.");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setBounds(20, 168, 414, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
