package statement;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.Vinculo;

public class Consulta extends JFrame implements ActionListener{
	private JPanel pnSul;
	private JTable tbGrade;
	private JButton btQuery;
	private Vinculo vinculo;
	
	public Consulta(Vinculo BDcomerce)
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
		
		this.vinculo = BDcomerce;
		this.btQuery.addActionListener(this);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			
			Statement statement = vinculo.getConexao().createStatement();
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
			showMessageDialog(this,ex.getMessage(),"Querry Error",ERROR_MESSAGE);
			
		}
		
	}
	
	
}
