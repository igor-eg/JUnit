import java.util.*;
import java.time.*;

class  Main {

    public static void main(String[] args) throws Exception {
        Contact contact = new Contact(null, null, null, null);
        MissedCalls missedCalls = new MissedCalls(null, null);
        fillMissedCall(missedCalls, contact);


    }

    private static void fillMissedCall(MissedCalls missedCalls, Contact contact) throws Exception {


        try (Scanner scanner = new Scanner(System.in)) {
            String input;


            String[] action = new String[]{"1. Добавить контакт", "2. Добавить пропущенный вызов", "3. Вывести все пропущенные вызовы", "4. Очистить пропущенные вызовы", "5. Выход", "6. удаления контактов из телефонной книги"};

            while (true) {
                System.out.println("Меню:");
                for (int i = 0; i < action.length; i++) {
                    System.out.println(action[i]);
                }
                System.out.println("Выберите пункт из меню (1-4):");
                input = scanner.nextLine();
                if (input.equals("5")) {

                    break;
                }

                switch (Integer.parseInt(input)) {
                    case 1: // добавляем
                        while (true) {
                            System.out.println("1. Добавить контакт(имя, фамилия, номер телефона, группа контакта: работа, друзья, семья)," +
                                    " для завершения работы программы введите \"end\"");
                            input = scanner.nextLine();
                            if (input.equals("end")) {
                                break;
                            }

                            String[] contactParameters = input.split(", ");

                            Group groups = null;

                            switch (contactParameters[3]) {
                                case "работа":
                                    groups = Group.WORK;
                                    break;
                                case "друзья":
                                    groups = Group.FRIENDS;
                                    break;
                                case "семья":
                                    groups = Group.FAMILY;
                                    break;
                            }



                            if (!(missedCalls.addContact(contactParameters[2], new Contact(contactParameters[0],
                                    contactParameters[1], contactParameters[2], groups)))) {
                                System.out.println("Не может быть дубля телефонного номера ");
                            }


                        }

                        break;

                    case 2: // Добавить пропущенный вызов;
                        while (true) {
                            System.out.println("Добавить пропущенный вызов:");


                            System.out.println("1. Добавить пропущенный вызов (время пропущенного вызова в формате: 2018-11-03T12:45:30,  номер телефона), для завершения работы программы введите \"end\"");
                            input = scanner.nextLine();
                            if (input.equals("end")) {
                                break;
                            }
                            String[] missedCallParameters = input.split(", ");

                            missedCalls.addMissedCalls(LocalDateTime.parse(missedCallParameters[0]),
                                    new MissedCalls(LocalDateTime.parse(missedCallParameters[0]), missedCallParameters[1]));

                        }


                        break;

                    case 3: // Выводим список задач;
                        System.out.println("Пропущенные вызовы:");
                        System.out.println(missedCalls);


                        break;
                    case 4: // Очистить пропущенные вызовы;
                        System.out.println(" Очистить пропущенные вызовы? Для подтверждения наберите - да.");
                        input = scanner.nextLine();
                        if (input.equals("да")) {
                            missedCalls.clearMissedCalls();
                            System.out.println("Список пропущенных вызовов очищен");
                        }
                        break;
                    case 6: // Удаляем контакт;

                        while (true) {
                            System.out.println("Введите номер телефонов контактов для удаления (для завершения введите end):");
                            input = scanner.nextLine();
                            if (input.equals("end")) {
                                missedCalls.printContact();
                                break;
                            }

                            missedCalls.removeContact(input);

                        }


                        break;


                }
            }

        } catch (RuntimeException exception) {
            System.out.println("Не верно ведена информация (необходимо строго вводить разделитель: \", \" или дата введена не в нужном формате)");
        }

    }
}
