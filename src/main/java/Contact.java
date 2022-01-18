import java.util.*;

class Contact implements Comparable<Contact> {

    private String name;
    private String surname;
    private String phone;
    private Group group;

    public Contact(String name, String surname, String phone, Group group) {

        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.group = group;

    }



    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {

        return String.format(name + " " + phone);

    }


    @Override
    public boolean equals(Object obj) {
        // Сравним с собой
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(Contact.class)) {
            return false;
        }

        Contact altContact = (Contact) obj;

        return this.phone.equals(altContact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }


    @Override
//реализуем метод compareTo интерфейса Comparable
    public int compareTo(Contact o) {

        int result = this.name.compareTo(o.name);

        return result;
    }

}