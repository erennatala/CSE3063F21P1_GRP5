import java.util.List;

public abstract class Person {
    private int id;//id attribute takes the ID of the person
    private String name;//name attribute takes the name of the person
    private String surname;// surname attribute takes the surname of the person
    private List<String> emails;//emails attribute is a list that takes emails

    protected Person() {
    }

    protected Person(int id, String name, String surname, List<String> emails) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.emails = emails;
    }

    protected Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public List<String> getEmails() {
        return emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (!getName().equals(person.getName())) return false;
        return getSurname().equals(person.getSurname());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + '}';
    }
}