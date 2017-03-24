package statement;
import static javax.swing.JOptionPane.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import connection.Vinculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Incluir extends JFrame implements ActionListener{
	private JPanel pnSul;
	private JTextField tfDetail;
	private JButton btRecord;
	
	public Incluir()
	{
		setTitle("Incluir Categoria");
		setSize(300,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pnSul = new JPanel();
		this.tfDetail = new JTextField();
		this.btRecord = new JButton("Record");
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(this.tfDetail, BorderLayout.CENTER);
		getContentPane().add(this.pnSul, BorderLayout.SOUTH);
		
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dm.width - dm.getWidth()/2),(int)(dm.height -  dm.getHeight())/2);
		
		this.btRecord.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Vinculo BDComerce = new Vinculo();//Estabele uma conexão
			PreparedStatement statement = BDComerce.getConexao().prepareStatement(
					"INSERT INTO Categoria (Descricao) Values(?)",Statement.RETURN_GENERATED_KEYS);
			//O método estático prepareStatement() cria um obj usado para remeter o INSERT  
			
			statement.setString(1, this.tfDetail.getText());
			//O metodo acima especifica o que Values(?) receberá
			
			statement.executeUpdate();
			//O método acima ordena o envio e execução da instrução
			
			BDComerce.confirmarTransacao(); //Commit
			
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			
			showMessageDialog(this,"Registro Gravado: " + rs.getInt(1)
				+ "-" + this.tfDetail.getText());
			
			rs.close();
			statement.close();
						
			this.tfDetail.setText("");
			this.tfDetail.requestFocus();
			
		}
		
		catch(Exception ex)
		{
			showMessageDialog(this,ex.getMessage(),"Error",ERROR_MESSAGE);
		}
		
	}
	
	
	
}
