package Model;
import java.util.HashMap;


public class FamilyTreeModel {
    public static HashMap<String, Person> people = new HashMap<>();
    private Person root;

    public void addPerson(String name, String photo, int birthYear, boolean sex, String fatherName, String motherName) {
        Person person = new Person(name, photo, birthYear, sex);

        if (fatherName != null) {
            Person father = people.get(fatherName);
            if (father == null) {
                father = new Person(fatherName);
                people.put(fatherName, father);
            }
            father.addChild(person);
            person.setFather(father);
        }
        if (motherName != null) {
            Person mother = people.get(motherName);
            if (mother == null) {
                mother = new Person(motherName);
                people.put(motherName, mother);
            }
            mother.addChild(person);
            person.setMother(mother);
        }

        people.put(name, person);
    }

    public void setRoot(String name) {
        root = people.get(name);
    }

    public Person getRoot() {
        return root;
    }
    
    public boolean isExist(Person p) {
    	for (Person person : people.values()) {
    		if (p.getName().equals(person.getName())) {
    			return true;
    		}
    	}
    	return false;
    }
}
