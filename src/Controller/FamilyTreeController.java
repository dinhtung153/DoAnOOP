package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.FamilyTreeView;



public class FamilyTreeController implements ActionListener{
	public FamilyTreeView FamilyTreeView;
	public FamilyTreeController(FamilyTreeView FamilyTreeView) {
		this.FamilyTreeView = FamilyTreeView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Add")) {
			this.FamilyTreeView.deleteForm();
		} else if (command.equals("Delete")) {
			this.FamilyTreeView.deletePerson();
		} else if (command.equals("Update")) {
			this.FamilyTreeView.showSelectedPerson();
		} else if (command.equals("Cancel")) {
			this.FamilyTreeView.deleteForm();
		} else if (command.equals("Save")) {
			this.FamilyTreeView.addOrUpdatePerson();
		}
	}
}
