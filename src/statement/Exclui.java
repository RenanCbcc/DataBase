package statement;
import static javax.swing.JOptionPane.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import connection.Vinculo;

import java.sql.PreparedStatement;

public class Exclui extends JFrame implements ActionListener {
	private JPanel pnSul;
	private JFormattedTextField tfCodigo;
	private JButton btDelete;
	
	public Exclui()
	{
		setTitle("Excluir Categoria");
		setSize(200,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.pnSul = new JPanel();
		this.tfCodigo = new JFormattedTextField(new Integer(0));
		this.btDelete = new JButton("Delete");
		this.btDelete.setMnemonic('D');
		this.pnSul.add(this.btDelete);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(this.tfCodigo, BorderLayout.CENTER);
		getContentPane().add(this.pnSul, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//Implemento depois
	}
	
}
