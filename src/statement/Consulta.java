package statement;

import static javax.swing.JOptionPane.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import connection.Vinculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consulta extends JFrame implements ActionListener{
	private JPanel pnSul;
	private JTable tbGrade;
	private JButton btQuery;
	
	
	public Consulta()
	{
		setTitle("Consultar Categoria");
		setSize(400,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.pnSul = new JPanel();
		this.tbGrade = new JTable(new DefaultTableModel());
		this.btQuery = new JButton("Query");
		this.btQuery.setMnemonic('Q');
		this.pnSul.add(this.btQuery);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new JScrollPane(this.tbGrade),BorderLayout.CENTER);
		getContentPane().add(this.pnSul, BorderLayout.SOUTH);
		
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dm.width - dm.getWidth()/2),(int)(dm.height -  dm.getHeight())/2);
		
		this.btQuery.addActionListener(this);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Vinculo BDComerce = new Vinculo();//Estabele uma conexão
			Statement statement = BDComerce.getConexao().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM CATEGORIA ORDER BY DESCRICAO");
			
			Vector<String> colunas = new Vector<String>();
			colunas.add("Codigo");
			colunas.add("Descricao");
			
			Vector<Object> registros = new Vector<Object>();
			
			while(rs.next())
				{
				Vector<Object> linha = new Vector<Object>();
				linha.add(rs.getInt(1));
				linha.add(rs.getString(2));
				registros.add(linha);
				}
			
			rs.close();
			statement.close();
			
			DefaultTableModel dtm = (DefaultTableModel)(this.tbGrade.getModel());
			dtm.setDataVector(registros, colunas);
			
		}
		
		catch(Exception ex)
		{
			showMessageDialog(this,ex.getMessage(),"Error",ERROR_MESSAGE);
			
		}
		
	}
	
	
}
