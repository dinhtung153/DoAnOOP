package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import View.FamilyTreeView;
import Custom.*;

public class FamilyTreeController implements ActionListener {
	public FamilyTreeView FamilyTreeView;

	public FamilyTreeController(FamilyTreeView FamilyTreeView) {
		this.FamilyTreeView = FamilyTreeView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Add")) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) FamilyTreeView.tree.getLastSelectedPathComponent();
			if (selectedNode != null) {
				this.FamilyTreeView.deleteForm();
				this.FamilyTreeView.showForm();
			} else {
				Message obj = new Message();
				obj.txt.setText("You need to choose family member first!");
				obj.eventOK(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent ae) {
				        System.out.println("Click OK");
				        GlassPanePopup.closePopupLast();
				    }
				});
				GlassPanePopup.showPopup(obj);
			}

		} else if (command.equals("Delete")) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) FamilyTreeView.tree.getLastSelectedPathComponent();
			if (selectedNode != null) {
				this.FamilyTreeView.giveNotice();
			} else {
				Message obj = new Message();
				obj.txt.setText("You need to choose family member first!");
				obj.eventOK(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent ae) {
				        System.out.println("Click OK");
				        GlassPanePopup.closePopupLast();
				    }
				});
				GlassPanePopup.showPopup(obj);
			}
		} else if (command.equals("Edit")) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) FamilyTreeView.tree.getLastSelectedPathComponent();
			if (selectedNode != null) {
				this.FamilyTreeView.showSelectedPerson();
			} else {
				Message obj = new Message();
				obj.txt.setText("You need to choose family member first!");
				obj.eventOK(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent ae) {
				        System.out.println("Click OK");
				        GlassPanePopup.closePopupLast();
				    }
				});
				GlassPanePopup.showPopup(obj);
			}
		} else if (command.equals("Cancel")) {
			GlassPanePopup.closePopupLast();
		} else if (command.equals("Save")) {
			this.FamilyTreeView.addOrUpdatePerson();
			GlassPanePopup.closePopupLast();
		} else if (command.equals("Search")) {
			this.FamilyTreeView.findPerson();
		} else if (command.equals("Yes")) {
			this.FamilyTreeView.deletePerson();
			GlassPanePopup.closePopupLast();
		}
	}
}
