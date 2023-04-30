package Model;

import java.util.ArrayList;

public class Person {
    private String name;
    private String photo;
    private int birthYear;
    private boolean sex;
    private Person mother;
    private Person father;
    private ArrayList<Person> children;

    public Person() {
        this.name = "";
        this.photo = "";
        this.birthYear = 0;
        this.mother = null;
        this.father = null;
        this.children = new ArrayList<>();
    }

    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public Person(String name, String photo, int birthYear, boolean sex) {
        this.name = name;
        this.photo = photo;
        this.birthYear = birthYear;
        this.sex = sex;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public void setBirthYear(int year) {
        this.birthYear = year;
    }

    public String getPhoto() {
        return this.photo;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public boolean isMale() {
    	return this.sex;
    }
    
    public Person getMother() {
        return this.mother;
    }

    public Person getFather() {
        return this.father;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }
    

    public void setChildren(ArrayList<Person> children) {
        this.children = children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public Person getSpouse() {
        for (Person child : children) {
            if (child.getMother() != null && child.getMother() != this) {
                return child.getMother();
            } else if (child.getFather() != null && child.getFather() != this) {
                return child.getFather();
            }
        }
        return null;
    }
    
    public Person getChildrenByIndex(int index) {
        return children.get(index);
    }
}
