package principal;
import statement.Incluir;
import conexao.ComercioConexao; 
import static javax.swing.JOptionPane.*;
public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
			ComercioConexao c = new ComercioConexao();
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
