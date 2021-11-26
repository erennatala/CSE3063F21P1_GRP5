import java.util.ArrayList;
import java.util.Date;

public abstract class Person {
    private int id;
    private String name;
    private String surname;
    private ArrayList<String> emails;

    public Person(int id, String name, String surname, ArrayList<String> emails) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.emails = emails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }


}