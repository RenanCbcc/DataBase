package statement;
import static javax.swing.JOptionPane.*;
import conexao.ComercioConexao;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;


public class Altera extends JFrame implements ActionListener {
	private JPanel pnSul;
	private JPanel pnOeste;
	private JPanel pnCentro;
	private JFormattedTextField tfCodigo;
	private JTextField tfDetail;
	private JButton btAlter;
	
	public Altera()
	{
		setTitle("Alterar Categoria");
		setSize(300,150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.pnSul = new JPanel();
		this.pnOeste = new JPanel();
		this.pnCentro = new JPanel();
		this.tfCodigo = new JFormattedTextField(new Integer(0));
		this.tfDetail = new JTextField();
		this.btAlter = new JButton("Alter");
		this.btAlter.setMnemonic('A');
		
		this.pnOeste.setLayout(new GridLayout(2,1));
		this.pnOeste.add(new JLabel("Codigo: "));
		this.pnOeste.add(new JLabel("Descricao: "));
		this.pnCentro.setLayout(new GridLayout(2,1));
		this.pnCentro.add(tfCodigo);
		this.pnCentro.add(tfDetail);
		this.pnSul.add(btAlter);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(this.pnOeste, BorderLayout.WEST);
		getContentPane().add(this.pnCentro, BorderLayout.CENTER);
		getContentPane().add(this.pnSul, BorderLayout.SOUTH);
		
		
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dm.width - dm.getWidth()/2),(int)(dm.height -  dm.getHeight())/2);
		
		this.btAlter.addActionListener(this);
	
}
