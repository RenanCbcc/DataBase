package principal;
import statement.Incluir;

import static javax.swing.JOptionPane.*;

import connection.Vinculo;
public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
			Vinculo c = new Vinculo();
			showMessageDialog(null,"Conex�o Aberta!");
			c.fechar();
			showMessageDialog(null,"Conex�o Fechada!");
			
		}
		catch (Exception ex)
		{
			showMessageDialog(null,ex.getMessage(),"Erro",ERROR_MESSAGE);
			
		}
		
		new Incluir().setVisible(true);
		
		//System.exit(0);
	}

}
