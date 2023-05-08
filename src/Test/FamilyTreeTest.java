package Test;

import javax.swing.UIManager;
import View.FamilyTreeView;

public class FamilyTreeTest {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			FamilyTreeView frame = new FamilyTreeView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
