package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.FamilyTreeView;
import Custom.*;



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
			try {
				this.FamilyTreeView.deletePerson();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				Message obj = new Message();
		        obj.eventOK(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		                System.out.println("Click OK");
		                GlassPanePopup.closePopupLast();
		            }
		        });
		        GlassPanePopup.showPopup(obj);
			}
		} else if (command.equals("Update")) {
			try {
				this.FamilyTreeView.showSelectedPerson();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				Message obj = new Message();
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
			this.FamilyTreeView.deleteForm();
		} else if (command.equals("Save")) {
			try {
				this.FamilyTreeView.addOrUpdatePerson();
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				Message obj = new Message();
				obj.txt.setText("You need to take action first!");
		        obj.eventOK(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		                System.out.println("Click OK");
		                GlassPanePopup.closePopupLast();
		            }
		        });
		        GlassPanePopup.showPopup(obj);
			}
		}
	}
}
