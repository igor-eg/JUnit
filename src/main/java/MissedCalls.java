import java.util.*;
import java.time.*;

public class MissedCalls implements Comparable<MissedCalls> {

    private LocalDateTime dt;
    private String phoneNumber;

    private Map<String, Contact> contacts = new HashMap<>();
    private Map<LocalDateTime, MissedCalls> missedCall = new TreeMap<>();

    public MissedCalls(LocalDateTime dt, String phoneNumber) {

        this.dt = dt;
        this.phoneNumber = phoneNumber;


    }

    public MissedCalls() {

    }


    public boolean addContact(String phone, Contact contact) {
        if (contacts.containsKey(phone)) {
            return false;
        }
        contacts.put(phone, contact);
        return true;

    }

    public boolean addMissedCalls(LocalDateTime dt, MissedCalls missedCalls) {
        missedCall.put(dt, missedCalls);
        return true;
    }

    public boolean clearMissedCalls() {

        missedCall.clear();
        return true;
    }

    public boolean removeContact(String input) {

        contacts.remove(input);
        return true;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder listOfMissedCalls = new StringBuilder();
        for (Map.Entry<LocalDateTime, MissedCalls> entry : missedCall.entrySet()) {
            int i = 0;
            for (Map.Entry<String, Contact> some : contacts.entrySet()) {
                i++;
                if (entry.getValue().getPhoneNumber().equals(some.getValue().getPhone())) {

                    System.out.println(entry.getKey() + "       " + entry.getValue().getPhoneNumber() +
                            "       " + some.getValue().getName() + "      " + some.getValue().getSurname() + "      "
                            + some.getValue().getGroup());

                    break;
                }
                if (i == contacts.size() & !(entry.getValue().getPhoneNumber().equals(some.getValue().getPhone()))) {
                    System.out.println(entry.getKey() + "       " + entry.getValue().getPhoneNumber());
                }

            }
        }

        return listOfMissedCalls.toString();
    }


    public void printContact() {
        System.out.println("Список :");
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {

            System.out.println("-   " + entry.getValue().getName() + ",   " + entry.getValue().getSurname() + ",   "
                    + entry.getValue().getPhone() + ",   " + entry.getValue().getGroup());
        }


    }

    @Override
//реализуем метод compareTo интерфейса Comparable
    public int compareTo(MissedCalls o) {

        int result = this.dt.compareTo(o.dt);
        return result;
    }


}
