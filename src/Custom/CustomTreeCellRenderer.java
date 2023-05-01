package Custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

	private JLabel label;
	private Color selectedBackgroundColor = new Color(135, 206, 250);
	private Color selectedForegroundColor = new Color(107, 112, 92);

	public CustomTreeCellRenderer() {
		label = new JLabel();
		label.setOpaque(true);
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		this.setFocusable(false);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setForeground(new Color(107, 112, 92));
		setBorder(null);
		Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
		label.setText(userObject.toString());
		label.setBackground(selected ? selectedBackgroundColor : getBackground());
		label.setForeground(selected ? selectedForegroundColor : getForeground());
		return label;
	}
}

