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
	private Vinculo vinculo;
	
	public Exclui(Vinculo BDComerce)
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
		
		this.vinculo = BDComerce;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			
			PreparedStatement statement = vinculo.getConexao().prepareStatement(
					"DELETE FROM CATEGORIA WHERE CODIGO = ?");
			
			statement.setInt(1,Integer.parseInt(this.tfCodigo.getText()));
			statement.executeUpdate();
			vinculo.confirmarTransacao();
			
			if(statement.getUpdateCount() == 0)
			{
				showMessageDialog(this,"Registro nao existe!","Erro",ERROR_MESSAGE);
				return;
			}
			
			statement.close();
			
			showMessageDialog(this,"Registro concluido!","Sucesso",INFORMATION_MESSAGE);
			this.tfCodigo.setValue(null);
			this.tfCodigo.requestFocus();
			
		}
		
		catch(Exception ex)
		{
			showMessageDialog(this,ex.getMessage(),"Exclude Error",ERROR_MESSAGE);
			
		}

	}
	
}
