package statement;

import static javax.swing.JOptionPane.*;
import conexao.ComercioConexao;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consulta extends JFrame implements ActionListener{
	private JPanel pnSul;
	private JTextField tfGrade;
	private JButton btQuery;
	
	
	public Consulta()
	{
		setTitle("Consultar Categoria");
		setSize(300,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pnSul = new JPanel();
		this.tfGrade = new JTextField();
		this.btQuery = new JButton("Record");
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(this.tfGrade, BorderLayout.CENTER);
		getContentPane().add(this.pnSul, BorderLayout.SOUTH);
		
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dm.width - dm.getWidth()/2),(int)(dm.height -  dm.getHeight())/2);
		
		this.btQuery.addActionListener(this);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
