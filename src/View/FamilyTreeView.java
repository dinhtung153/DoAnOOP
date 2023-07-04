package View;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Custom.*;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import Controller.FamilyTreeController;
import Model.Person;
import Model.FamilyTreeModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FamilyTreeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public FamilyTreeModel model;
	public JTree tree;
	public Input input;
	public Notice notice;
	private JTextField textField_Search;
	private JLabel motherLabel;
	private JLabel spouseLabel;
	private JLabel childrenLabel;
	private JLabel nameLabel;
	private JLabel birthYearLabel;
	private JPanel photoPanel;
	private JLabel fatherLabel;
	private JLabel photoLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FamilyTreeView frame = new FamilyTreeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FamilyTreeView() {
		this.model = new FamilyTreeModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 498);
		setResizable(false);
		setTitle("Family Tree");
		setLocationRelativeTo(null);
		GlassPanePopup.install(this);
		FamilyTreeController action = new FamilyTreeController(this);
		input = new Input();
		notice = new Notice();
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 237, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.model.addPerson("Adam", "Male1.jpg", 1850, true, null, null);
		this.model.addPerson("Liam", "Male2.jpg", 1870, true, "Adam", "Eva");
		this.model.addPerson("Martha", "Female1.jpg", 1874, false, "Adam", "Eva");
		this.model.addPerson("William", "Male3.jpg", 1875, true, "Adam", "Eva");
		this.model.addPerson("James", "Male4.jpg", 1890, true, "Liam", "Serena");
		this.model.addPerson("Gemma", "Female2.jpg", 1891, false, "Liam", "Serena");
		this.model.addPerson("Charles", "Male5.jpg", 1900, true, "Jack", "Martha");

		this.model.setRoot("Adam");
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(this.model.getRoot().getName());
		createTreeNodes(rootNode, this.model.getRoot());

		tree = new JTree(rootNode);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(true);

		tree.setCellRenderer(new CustomTreeCellRenderer());

		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setVerticalScrollBar(new ScrollBarCustom());
		ScrollBarCustom sp1 = new ScrollBarCustom();
		sp1.setOrientation(JScrollBar.HORIZONTAL);
		scrollPane.setHorizontalScrollBar(sp1);
		scrollPane.setBounds(5, 57, 252, 325);
		contentPane.add(scrollPane);

		Button btnAdd = new Button("Add");
		btnAdd.addActionListener(action);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(208, 404, 81, 45);
		contentPane.add(btnAdd);

		Button btnDelete = new Button("Delete");
		btnDelete.addActionListener(action);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(390, 404, 81, 45);
		contentPane.add(btnDelete);

		Button btnEdit = new Button("Update");
		btnEdit.setText("Edit");
		btnEdit.addActionListener(action);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEdit.setBounds(299, 404, 81, 45);
		contentPane.add(btnEdit);


		JSeparator separator = new JSeparator();
		separator.setBounds(10, 393, 475, 8);
		contentPane.add(separator);

		JPanel detailPanel = new JPanel();
		detailPanel.setBounds(260, 57, 225, 325);
		detailPanel.setBackground(new Color(254, 250, 224));
		detailPanel.setLayout(null);
		contentPane.add(detailPanel);

		photoPanel = new JPanel();
		photoPanel.setBounds(18, 36, 184, 181);
		photoPanel.setBackground(new Color(254, 250, 224));
		detailPanel.add(photoPanel);
		photoLabel = new JLabel();
		photoPanel.add(photoLabel);

		fatherLabel = new JLabel();
		fatherLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherLabel.setForeground(new Color(107, 112, 92));
		fatherLabel.setBounds(10, 246, 194, 19);
		detailPanel.add(fatherLabel);

		motherLabel = new JLabel();
		motherLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherLabel.setForeground(new Color(107, 112, 92));
		motherLabel.setBounds(10, 265, 194, 19);
		detailPanel.add(motherLabel);

		spouseLabel = new JLabel();
		spouseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spouseLabel.setForeground(new Color(107, 112, 92));
		spouseLabel.setBounds(10, 284, 194, 19);
		detailPanel.add(spouseLabel);

		childrenLabel = new JLabel();
		childrenLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		childrenLabel.setForeground(new Color(107, 112, 92));
		childrenLabel.setBounds(10, 303, 194, 19);
		detailPanel.add(childrenLabel);

		nameLabel = new JLabel();
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		nameLabel.setBounds(10, 9, 110, 30);
		nameLabel.setForeground(new Color(107, 112, 92));
		detailPanel.add(nameLabel);

		birthYearLabel = new JLabel();
		birthYearLabel.setBounds(10, 228, 194, 19);
		detailPanel.add(birthYearLabel);
		birthYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		birthYearLabel.setForeground(new Color(107, 112, 92));

		JLabel Name = new JLabel("Enter the name:");
		Name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Name.setBounds(22, 11, 128, 35);
		contentPane.add(Name);

		textField_Search = new TextFieldCustom();
		textField_Search.setBounds(160, 7, 175, 42);
		contentPane.add(textField_Search);
		textField_Search.setColumns(10);

		Button btnSearchButton = new Button("Search");
		btnSearchButton.addActionListener(action);
		btnSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearchButton.setBounds(356, 7, 107, 45);
		contentPane.add(btnSearchButton);

		input.btnSave.addActionListener(action);
		input.btnCancel.addActionListener(action);
		notice.cmdYes.addActionListener(action);
		
		tree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
				Person person = FamilyTreeModel.people.get(selectedNode.getUserObject());
				nameLabel.setText(person.getName());
				String photoString = "";
				if (person.getPhoto() == "") {
					if (person.isMale()) {
						photoString = "MaleDefault.png";
					}
					else {
						photoString = "FemaleDefault.png";
					}
					ImageIcon icon = new ImageIcon(getClass().getResource("/Images/" + photoString));
					BufferedImage bufferedImage = new BufferedImage(181, 181, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = bufferedImage.createGraphics();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g2d.drawImage(icon.getImage(), 0, 0, 181, 181, null);
					g2d.dispose();
					ImageIcon newIcon = new ImageIcon(bufferedImage);
					photoLabel.setIcon(newIcon);
					
				} else {
					ImageIcon icon = new ImageIcon(getClass().getResource("/Images/" + person.getPhoto()));
					BufferedImage bufferedImage = new BufferedImage(181, 181, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = bufferedImage.createGraphics();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g2d.drawImage(icon.getImage(), 0, 0, 181, 181, null);
					g2d.dispose();
					ImageIcon newIcon = new ImageIcon(bufferedImage);
					photoLabel.setIcon(newIcon);
				}
				
				

				birthYearLabel.setText("Birth Year: " + person.getBirthYear());

				Person mother = person.getMother();
				if (mother != null) {
					motherLabel.setText("Mother: " + mother.getName());
				} else {
					motherLabel.setText("Mother: Unknown");
				}

				Person father = person.getFather();
				if (father != null) {
					fatherLabel.setText("Father: " + father.getName());
				} else {
					fatherLabel.setText("Father: Unknown");
				}

				Person spouse = person.getSpouse();
				if (spouse != null) {
					spouseLabel.setText("Spouse: " + person.getSpouse().getName());
				} else {
					spouseLabel.setText("Spouse: null");
				}

				ArrayList<Person> children = person.getChildren();

				if (children.size() > 0) {
					String string = "Children: ";
					for (int i = 0; i < children.size(); i++) {
						if (i == children.size() - 1) {
							string = string + children.get(i).getName();
						} else {
							string = string + children.get(i).getName() + ", ";
						}
					}
					childrenLabel.setText(string);
				} else {
					childrenLabel.setText("Children: null");
				}
			}
		});
	}

	public static void createTreeNodes(DefaultMutableTreeNode rootNode, Person person) {
		if (person == null) {
			return;
		}
		for (Person child : person.getChildren()) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(child.getName());
			rootNode.add(node);
			createTreeNodes(node, child);
		}
	}

	public void showForm() {
		GlassPanePopup.showPopup(input);
	}

	public void deleteForm() {
		input.textField_Name.setText("");
		input.textField_BirthYear.setText("");
		input.textField_Photo.setText("");
		input.textField_Spouse.setText("");
		input.textField_Children.setText("");
		input.buttonGroupSex.clearSelection();
	}

	public void giveNotice( ) {
		GlassPanePopup.showPopup(notice);
	}
	public void deletePerson() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
		if (tree.getModel().isLeaf(selectedNode)) {
			if (parentNode != null) {
				parentNode.remove(selectedNode);
				tree.updateUI();
			}

		} else {
			if (parentNode != null) {
				while (selectedNode.getChildCount() > 0) {
					DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) selectedNode.getFirstChild();
					selectedNode.remove(childNode);
				}
				parentNode.remove(selectedNode);
				tree.updateUI();
			}
		}
		Person parent = FamilyTreeModel.people.get(parentNode.getUserObject());
		Person person = FamilyTreeModel.people.get(selectedNode.getUserObject());
		parent.getChildren().remove(person);
		nameLabel.setText("");
		photoLabel.setIcon(null);
		birthYearLabel.setText("");
		fatherLabel.setText("");
		motherLabel.setText("");
		spouseLabel.setText("");
		childrenLabel.setText("");
	}

	public void showSelectedPerson() {
		GlassPanePopup.showPopup(input);
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		Person person = FamilyTreeModel.people.get(selectedNode.getUserObject());
		input.textField_Name.setText(person.getName());
		input.textField_Name.setEditable(false);
		input.textField_Children.setEditable(false);
		input.textField_BirthYear.setText(person.getBirthYear() + "");
		input.textField_Photo.setText(person.getPhoto());
		ArrayList<Person> children = person.getChildren();
		if (!children.isEmpty()) {
			String string = "";
			for (int i = 0; i < children.size(); i++) {
				if (i == children.size() - 1) {
					string = string + children.get(i).getName();
				} else {
					string = string + children.get(i).getName() + ", ";
				}
			}
			input.textField_Children.setText(string);
		} else {
			input.textField_Children.setText("null");
		}

		Person spouse = person.getSpouse();
		if (spouse != null) {
			input.textField_Spouse.setText("" + spouse.getName());
		} else {
			input.textField_Spouse.setText("null");
		}

		if (person.isMale()) {
			input.jRadioButtonMale.setSelected(true);
		} else {
			input.jRadioButtonFemale.setSelected(true);
		}
	}

	public void addOrUpdatePerson() {
		String name = input.textField_Name.getText().trim();
		int birthYear = Integer.parseInt(input.textField_BirthYear.getText());
		if (birthYear > 2023 || birthYear < 1800) {
			Message obj = new Message();
			obj.txt.setText("Invalid birth year!");
			obj.eventOK(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					System.out.println("Click OK");
					GlassPanePopup.closePopupLast();
				}
			});
			GlassPanePopup.showPopup(obj);
		}
		String photo = input.textField_Photo.getText().trim();
		boolean sex = false;
		if (input.jRadioButtonMale.isSelected()) {
			sex = true;
		} else if (input.jRadioButtonFemale.isSelected()) {
			sex = false;
		}
		String spouseName = input.textField_Spouse.getText().trim();
		String childrensName = input.textField_Children.getText().trim();
		Person p = new Person(name, photo, birthYear, sex);
		ArrayList<Person> listChildren = new ArrayList<>();
		StringTokenizer stk = new StringTokenizer(childrensName, ",");
		Person spouse = new Person(spouseName);
		while (stk.hasMoreElements()) {
			String childName = stk.nextToken();
			childName = childName.trim();
			Person child = new Person(childName);
			listChildren.add(child);
			if (sex) {
				child.setFather(p);
				child.setMother(spouse);
			} else {
				child.setMother(p);
				child.setFather(spouse);
			}
		}
		p.setChildren(listChildren);
		if (!this.model.isExist(p)) {
			FamilyTreeModel.people.put(p.getName(), p);
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			Person parent = FamilyTreeModel.people.get(selectedNode.getUserObject());

			if (parent.isMale()) {
				parent.addChild(p);
				p.setFather(parent);
				p.setMother(parent.getSpouse());
			} else {
				parent.addChild(p);
				p.setMother(parent);
				p.setFather(parent.getSpouse());
			}
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(p.getName());
			selectedNode.add(node);
			tree.updateUI();
		} else {
			DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null && selectedNode.getLevel() > 0) {
				Person selectedPerson = FamilyTreeModel.people.get(selectedNode.getUserObject().toString());
				if (selectedPerson != null) {
					selectedPerson.setPhoto(photo);
					selectedPerson.setBirthYear(birthYear);
					selectedPerson.setSex(sex);
					selectedPerson.setChildren(listChildren);
					for (int i = 0; i < listChildren.size(); i++) {
						if (selectedPerson.isMale()) {
							listChildren.get(i).setFather(selectedPerson);
							listChildren.get(i).setMother(spouse);
						} else {
							listChildren.get(i).setFather(spouse);
							listChildren.get(i).setMother(selectedPerson);
						}
					}
					Enumeration<TreeNode> children = selectedNode.children();
					while (children.hasMoreElements()) {
						DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) children.nextElement();
						Person childPerson = FamilyTreeModel.people.get(childNode.getUserObject().toString());
						if (selectedPerson.isMale()) {
							childPerson.setFather(selectedPerson);
							childPerson.setMother(spouse);
						} else {
							childPerson.setFather(spouse);
							childPerson.setMother(selectedPerson);
						}
						childNode.setUserObject(childPerson.getName());
						treeModel.reload(childNode);
					}

					selectedNode.setUserObject(name);
					treeModel.reload(selectedNode);
					tree.revalidate();
				}
			}

		}
		input.textField_Name.setEditable(true);
		input.textField_Children.setEditable(true);
	}

	public static DefaultMutableTreeNode findNode(DefaultTreeModel model, DefaultMutableTreeNode node, String search) {
		if (node.toString().equals(search)) {
			return node;
		}
		for (int i = 0; i < node.getChildCount(); i++) {
			DefaultMutableTreeNode foundNode = findNode(model, (DefaultMutableTreeNode) node.getChildAt(i), search);
			if (foundNode != null) {
				return foundNode;
			}
		}
		return null;
	}

	public void findPerson() {
		String name = this.textField_Search.getText();
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) treeModel.getRoot();
		DefaultMutableTreeNode foundNode = findNode(treeModel, rootNode, name);
		if (foundNode != null) {
			TreePath path = new TreePath(foundNode.getPath());
			tree.setSelectionPath(path);
			tree.scrollPathToVisible(path);
		} else {
			Message obj = new Message();
			obj.txt.setText("Person not found!");
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
