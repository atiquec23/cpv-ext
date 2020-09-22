package co.com.bbog.cpv.util.ext;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class PgpCrypt {

	private JFrame frmOk;
	private JTextField box1;
	private JTextField box2;

	private boolean asciiArmored = false;
	private boolean integrityCheck = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PgpCrypt window = new PgpCrypt();
					window.frmOk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PgpCrypt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOk = new JFrame();
		frmOk.setTitle("Periferia");
		frmOk.setBounds(100, 100, 450, 220);
		frmOk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOk.getContentPane().setLayout(null);

		JLabel lblCifrarPgp = new JLabel("CIFRAR PGP");
		lblCifrarPgp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCifrarPgp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCifrarPgp.setBounds(132, 11, 157, 23);
		frmOk.getContentPane().add(lblCifrarPgp);

		JLabel lblNewLabel = new JLabel("Clave publica: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 62, 83, 14);
		frmOk.getContentPane().add(lblNewLabel);

		box1 = new JTextField();
		box1.setBounds(103, 59, 250, 20);
		frmOk.getContentPane().add(box1);
		box1.setColumns(10);

		box2 = new JTextField();
		box2.setColumns(10);
		box2.setBounds(103, 91, 250, 20);
		frmOk.getContentPane().add(box2);

		JLabel lblNewLabel_1 = new JLabel("Archivo: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 94, 70, 14);
		frmOk.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(frmOk);
				File fl = jf.getSelectedFile();
				if (fl != null) {
					box1.setText(fl.getAbsolutePath());
				}
			}
		});
		btnNewButton_1.setBounds(361, 58, 45, 23);
		frmOk.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf2 = new JFileChooser();
				jf2.showOpenDialog(frmOk);

				File fl2 = jf2.getSelectedFile();
				if (fl2 != null) {
					box2.setText(fl2.getAbsolutePath());
				}

			}
		});
		btnNewButton_2.setBounds(361, 90, 45, 23);
		frmOk.getContentPane().add(btnNewButton_2);

		JButton btnNewButton = new JButton("Cifrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] cad = box2.getText().split("\\.");
					FileInputStream keyIn = new FileInputStream(box1.getText());
					FileOutputStream out = new FileOutputStream(box2.getText().replace(cad[1], "pgp"));
					PGPUtil.encryptFile(out, box2.getText(), PGPUtil.readPublicKey(keyIn), asciiArmored,
							integrityCheck);
					keyIn.close();
					out.close();
					JOptionPane.showMessageDialog(frmOk,
							"Archivo cifrado con exito en la ruta: \n" + box2.getText().replace(cad[1], "pgp"), "Ok",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmOk, "Error al cifrar el archivo: " + e2, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(161, 128, 113, 30);
		frmOk.getContentPane().add(btnNewButton);
	}
}
