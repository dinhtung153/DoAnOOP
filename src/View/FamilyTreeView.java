package View;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FamilyTreeView extends JFrame {

	private JPanel contentPane;
	public FamilyTreeModel model;
	public JTree tree;
	public TextFieldCustom textField_Name;
	public TextFieldCustom textField_BirthYear;
	public TextFieldCustom textField_Photo;
	public TextFieldCustom textField_Children;
	public TextFieldCustom textField_Spouse;
	public RadioButtonCustom radioButtonMale;
	public RadioButtonCustom radioButtonFemale;
	public ButtonGroup buttonGroupSex;
	private JTextField textField_Search;

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
		setBounds(100, 100, 505, 655);
		setResizable(false);
		setTitle("Family Tree");
		setLocationRelativeTo(null);
		GlassPanePopup.install(this);
		FamilyTreeController action = new FamilyTreeController(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 237, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.model.addPerson("Adam", "photo1.png", 1850, true, null, null);
		this.model.addPerson("Liam", "photo2.png", 1870, true, "Adam", "Eva");
		this.model.addPerson("Martha", "photo4.png", 1874, false, "Adam", "Eva");
		this.model.addPerson("William", "photo3.png", 1875, true, "Adam", "Eva");
		this.model.addPerson("Kim", "photo23.png", 18777, false, "Adam", "Eva");
		this.model.addPerson("James", "photo5.png", 1890, true, "Liam", "Serena");
		this.model.addPerson("Gemma", "photo6.png", 1891, false, "Liam", "Serena");
		this.model.addPerson("Alexander", "photo7.png", 1893, true, "Liam", "Serena");
		this.model.addPerson("Aurelia", "photo8.png", 1895, false, "William", "Fiona");
		this.model.addPerson("Miranda", "photo9.png", 1895, false, "Paul", "Martha");
		this.model.addPerson("Ben", "photo10.png", 1896, true, "Paul", "Martha");
		this.model.addPerson("Ethan", "photo11.png", 1897, true, "Paul", "Martha");
		this.model.addPerson("Ladonna", "photo12.png", 1898, false, "Paul", "Martha");
		this.model.addPerson("Logan", "photo13.png", 1920, true, "James", "Mirabel");
		this.model.addPerson("Charles", "photo14.png", 1920, true, "Jack", "Aurelia");
		this.model.addPerson("Melanie", "photo15.png", 1921, false, "Jack", "Aurelia");
		this.model.addPerson("Bertha", "photo16.png", 1920, false, "Ben", "Xavia");
		this.model.addPerson("Evan", "photo17.png", 1921, true, "Ben", "Xavia");
		this.model.addPerson("Clara", "photo18.png", 1922, false, "Ben", "Xavia");
		this.model.addPerson("Luke", "photo19.png", 1944, true, "David", "Clara");
		this.model.addPerson("Daniel", "photo20.png", 1970, true, "Luke", "Zelda");
		this.model.addPerson("Leo", "photo21.png", 1971, true, "Luke", "Zelda");
		this.model.addPerson("Jade", "photo22.png", 1995, false, "Leo", "Giselle");

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

		JLabel Label_Name = new JLabel("Name:");
		Label_Name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Label_Name.setBounds(25, 416, 75, 25);
		contentPane.add(Label_Name);

		textField_Name = new TextFieldCustom();
		textField_Name.setColumns(10);
		textField_Name.setBounds(99, 412, 100, 42);
		contentPane.add(textField_Name);

		textField_BirthYear = new TextFieldCustom();
		textField_BirthYear.setColumns(10);
		textField_BirthYear.setBounds(99, 462, 100, 42);
		contentPane.add(textField_BirthYear);

		JLabel Label_BirthYear = new JLabel("BirthYear:");
		Label_BirthYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Label_BirthYear.setBounds(25, 466, 75, 25);
		contentPane.add(Label_BirthYear);

		JLabel Label_Photo = new JLabel("Photo:");
		Label_Photo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Label_Photo.setBounds(25, 515, 75, 25);
		contentPane.add(Label_Photo);

		textField_Photo = new TextFieldCustom();
		textField_Photo.setColumns(10);
		textField_Photo.setBounds(99, 512, 100, 42);
		contentPane.add(textField_Photo);

		Button btnAdd = new Button("Add");
		btnAdd.addActionListener(action);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(15, 562, 81, 45);
		contentPane.add(btnAdd);

		Button btnDelete = new Button("Delete");
		btnDelete.addActionListener(action);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(109, 562, 81, 45);
		contentPane.add(btnDelete);

		Button btnUpdate = new Button("Update");
		btnUpdate.addActionListener(action);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(200, 562, 81, 45);
		contentPane.add(btnUpdate);

		radioButtonMale = new RadioButtonCustom("Male");
		radioButtonMale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonMale.setBounds(224, 517, 62, 23);
		contentPane.add(radioButtonMale);

		radioButtonFemale = new RadioButtonCustom("Female");
		radioButtonFemale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonFemale.setBounds(300, 517, 75, 23);
		contentPane.add(radioButtonFemale);

		buttonGroupSex = new ButtonGroup();
		buttonGroupSex.add(radioButtonMale);
		buttonGroupSex.add(radioButtonFemale);

		Button btnCancel = new Button("Cancel");
		btnCancel.addActionListener(action);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(291, 562, 81, 45);
		contentPane.add(btnCancel);

		Button btnSave = new Button("Save");
		btnSave.addActionListener(action);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBounds(382, 562, 81, 45);
		contentPane.add(btnSave);

		textField_Spouse = new TextFieldCustom();
		textField_Spouse.setColumns(10);
		textField_Spouse.setBounds(300, 412, 100, 42);
		contentPane.add(textField_Spouse);

		JLabel Label_Spouse = new JLabel("Spouse:");
		Label_Spouse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Label_Spouse.setBounds(224, 416, 75, 25);
		contentPane.add(Label_Spouse);

		JLabel Label_Children = new JLabel("Children:");
		Label_Children.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Label_Children.setBounds(224, 466, 75, 25);
		contentPane.add(Label_Children);

		textField_Children = new TextFieldCustom();
		textField_Children.setColumns(10);
		textField_Children.setBounds(300, 463, 100, 42);
		contentPane.add(textField_Children);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 393, 475, 8);
		contentPane.add(separator);

		JPanel detailPanel = new JPanel();
		detailPanel.setBounds(260, 57, 225, 325);
		detailPanel.setBackground(new Color(254, 250, 224));
		detailPanel.setLayout(null);
		contentPane.add(detailPanel);

		JPanel photoPanel = new JPanel();
		photoPanel.setBounds(23, 36, 181, 181);
		photoPanel.setBackground(new Color(254, 250, 224));
		detailPanel.add(photoPanel);
		JLabel photoLabel = new JLabel();
		photoPanel.add(photoLabel);

		JLabel birthYearLabel = new JLabel();
		birthYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		birthYearLabel.setForeground(new Color(107, 112, 92));
		birthYearLabel.setBounds(10, 227, 194, 19);
		detailPanel.add(birthYearLabel);

		JLabel fatherLabel = new JLabel();
		fatherLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherLabel.setForeground(new Color(107, 112, 92));
		fatherLabel.setBounds(10, 246, 194, 19);
		detailPanel.add(fatherLabel);

		JLabel motherLabel = new JLabel();
		motherLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherLabel.setForeground(new Color(107, 112, 92));
		motherLabel.setBounds(10, 265, 194, 19);
		detailPanel.add(motherLabel);

		JLabel spouseLabel = new JLabel();
		spouseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spouseLabel.setForeground(new Color(107, 112, 92));
		spouseLabel.setBounds(10, 284, 194, 19);
		detailPanel.add(spouseLabel);

		JLabel childrenLabel = new JLabel();
		childrenLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		childrenLabel.setForeground(new Color(107, 112, 92));
		childrenLabel.setBounds(10, 303, 194, 19);
		detailPanel.add(childrenLabel);

		JLabel nameLabel = new JLabel();
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		nameLabel.setBounds(10, 9, 110, 30);
		nameLabel.setForeground(new Color(107, 112, 92));
		detailPanel.add(nameLabel);

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
		tree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
				Person person = FamilyTreeModel.people.get(selectedNode.getUserObject());
				nameLabel.setText(person.getName());

				if (person.isMale()) {
					ImageIcon icon = new ImageIcon(getClass().getResource("/Images/boy.png"));
					BufferedImage bufferedImage = new BufferedImage(181, 181, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = bufferedImage.createGraphics();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g2d.drawImage(icon.getImage(), 0, 0, 181, 181, null);
					g2d.dispose();
					ImageIcon newIcon = new ImageIcon(bufferedImage);
					photoLabel.setIcon(newIcon);
				} else {
					ImageIcon icon = new ImageIcon(getClass().getResource("/Images/girl.png"));
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

	public void deleteForm() {
		textField_Name.setText("");
		textField_BirthYear.setText("");
		textField_Photo.setText("");
		textField_Spouse.setText("");
		textField_Children.setText("");
		buttonGroupSex.clearSelection();
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
	}

	public void showSelectedPerson() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		Person person = FamilyTreeModel.people.get(selectedNode.getUserObject());
		this.textField_Name.setText(person.getName());
		this.textField_Name.setEditable(false);
		this.textField_Children.setEditable(false);
		this.textField_BirthYear.setText(person.getBirthYear() + "");
		this.textField_Photo.setText(person.getPhoto());
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
			this.textField_Children.setText(string);
		} else {
			this.textField_Children.setText("null");
		}

		Person spouse = person.getSpouse();
		if (spouse != null) {
			this.textField_Spouse.setText("" + spouse.getName());
		} else {
			this.textField_Spouse.setText("null");
		}

		if (person.isMale()) {
			radioButtonMale.setSelected(true);
		} else {
			radioButtonFemale.setSelected(true);
		}
	}

	public void addOrUpdatePerson() {
		String name = this.textField_Name.getText();
		int birthYear = Integer.parseInt(this.textField_BirthYear.getText());
		String photo = this.textField_Photo.getText();
		boolean sex = false;
		if (this.radioButtonMale.isSelected()) {
			sex = true;
		} else if (this.radioButtonFemale.isSelected()) {
			sex = false;
		}
		String spouseName = this.textField_Spouse.getText();
		String childrensName = this.textField_Children.getText();
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
		this.textField_Name.setEditable(true);
		this.textField_Children.setEditable(true);
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
		return null; // Không tìm thấy nút
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
