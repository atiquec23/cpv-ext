package co.com.bbog.cpv.util.ext;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PgpDecrypt {

	private JFrame frmPeriferia;
	private JTextField box1;
	private JTextField box2;
	private JTextField box3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PgpDecrypt window = new PgpDecrypt();
					window.frmPeriferia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PgpDecrypt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPeriferia = new JFrame();
		frmPeriferia.setTitle("Periferia");
		frmPeriferia.setBounds(100, 100, 450, 250);
		frmPeriferia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPeriferia.getContentPane().setLayout(null);

		JLabel lblCifrarPgp = new JLabel("DESCIFRAR PGP");
		lblCifrarPgp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCifrarPgp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCifrarPgp.setBounds(131, 11, 157, 23);
		frmPeriferia.getContentPane().add(lblCifrarPgp);

		JLabel lblNewLabel = new JLabel("Clave privada: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(23, 62, 84, 14);
		frmPeriferia.getContentPane().add(lblNewLabel);

		box1 = new JTextField();
		box1.setBounds(114, 59, 250, 20);
		frmPeriferia.getContentPane().add(box1);
		box1.setColumns(10);

		box2 = new JTextField();
		box2.setColumns(10);
		box2.setBounds(114, 87, 119, 20);
		frmPeriferia.getContentPane().add(box2);

		box3 = new JTextField();
		box3.setColumns(10);
		box3.setBounds(114, 116, 250, 20);
		frmPeriferia.getContentPane().add(box3);

		JLabel lblFraseSecreta = new JLabel("Frase Secreta: ");
		lblFraseSecreta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFraseSecreta.setBounds(23, 90, 84, 14);
		frmPeriferia.getContentPane().add(lblFraseSecreta);

		JLabel lblNewLabel_1 = new JLabel("Archivo en pgp: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(23, 119, 84, 14);
		frmPeriferia.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(frmPeriferia);
				File fl = jf.getSelectedFile();
				if (fl != null) {
					box1.setText(fl.getAbsolutePath());
				}
			}
		});
		btnNewButton_1.setBounds(366, 58, 45, 23);
		frmPeriferia.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf2 = new JFileChooser();
				jf2.showOpenDialog(frmPeriferia);

				File fl2 = jf2.getSelectedFile();
				if (fl2 != null) {
					box3.setText(fl2.getAbsolutePath());
				}

			}
		});
		btnNewButton_2.setBounds(366, 115, 45, 23);
		frmPeriferia.getContentPane().add(btnNewButton_2);

		JButton btnNewButton = new JButton("Descifrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream keyIn = new FileInputStream(box1.getText());
					FileInputStream in = new FileInputStream(box3.getText());
					FileOutputStream out = new FileOutputStream(box3.getText().replace(".pgp", ".txt"));
					PGPUtil.decryptFile(in, out, keyIn, box2.getText().toCharArray());
					in.close();
					out.close();
					keyIn.close();
					JOptionPane.showMessageDialog(frmPeriferia,
							"Archivo descifrado con exito en la ruta: \n" + box3.getText().replace(".pgp", ".txt"),
							"Ok", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmPeriferia, "Error al descifrar el archivo: " + e2, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(300, 153, 113, 30);
		frmPeriferia.getContentPane().add(btnNewButton);
	}
}
