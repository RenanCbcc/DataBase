package statement;
import static javax.swing.JOptionPane.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import connection.Vinculo;
import java.sql.PreparedStatement;


public class Altera extends JFrame implements ActionListener {
	private JPanel pnSul;
	private JPanel pnOeste;
	private JPanel pnCentro;
	private JFormattedTextField tfCodigo;
	private JTextField tfDetail;
	private JButton btAlter;
	private Vinculo vinculo;
	
	public Altera(Vinculo BDComerce)
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
		this.pnOeste.add(new JLabel("Código: "));
		this.pnOeste.add(new JLabel("Descrição: "));
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
		this.vinculo = BDComerce;
		this.btAlter.setVisible(true);		
		this.btAlter.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			
			PreparedStatement statement = vinculo.getConexao().prepareStatement(
					"UPDATE CATEGORIA SET DESCRICAO = ? WHERE CODIGO = ?");
			
			statement.setString(1,this.tfDetail.getText().toString());
			statement.setInt(2, Integer.parseInt(this.tfDetail.getText()));
			statement.executeUpdate();
			vinculo.confirmarTransacao();
			
			if(statement.getUpdateCount() == 0)
			{
				showMessageDialog(this,"Registro nao existe!","Erro",ERROR_MESSAGE);
				return;
			}
			
			statement.close();
			showMessageDialog(this,"Registro alterado!","Sucesso",INFORMATION_MESSAGE);
			this.tfCodigo.setValue(null);
			this.tfDetail.setText("");
			this.tfCodigo.requestFocus();
			
		}
		
		catch(Exception ex)
		{
			showMessageDialog(this,ex.getMessage(),"Altera Error",ERROR_MESSAGE);
			
		}
		
	}

}
