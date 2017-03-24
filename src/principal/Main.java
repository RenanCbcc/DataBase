package principal;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Explorer().setVisible(true);
	}	

}
