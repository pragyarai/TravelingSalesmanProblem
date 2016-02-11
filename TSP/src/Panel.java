
import javax.swing.*;


public class Panel extends JPanel{

	public Panel(JComponent top, JComponent bottom) {
		
		setLayout(new OverlayLayout(this));
		add(top);
		add(bottom);
		
	}
	
}
