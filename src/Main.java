import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("=== STUDENT MANAGEMENT SYSTEM ===\n" +
                    "1. Показать всех студентов\n" +
                    "2. Найти студента по ID\n" +
                    "3. Добавить нового студента\n" +
                    "4. Обновить данные студента\n" +
                    "5. Удалить студента\n" +
                    "6. Поиск по имени/фамилии\n" +
                    "7. Поиск по email\n" +
                    "8. Фильтр по возрасту\n" +
                    "0. Выход");

            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Введите пункт меню: ");
            int numberMenu = scanner.nextInt();
            scanner.nextLine();

            switch (numberMenu) {
                case 1:
                    System.out.println("Список студентов: ");
                    List<Student> students1 = Repository.reedAll();
                    if (students1.size() == 0) {
                        System.out.println("Список пуст");
                    } else {
                        System.out.println(students1);
                    }
                    break;
                case 2:
                    System.out.println("Поиск студента по ID");
                    System.out.println("Введите ID номер студента: ");
                    int numberId = scanner.nextInt();
                    Student student2 = Repository.searchById(numberId);
                    if (student2 == null) {
                        System.out.println("Студента с таким ID не существует!");
                    } else {
                        System.out.println(student2);
                    }
                    break;
                case 3:
                    System.out.println("Добавить нового студента");
                    System.out.println("Введите имя: ");
                    String name = scanner.nextLine();
//                        if(name.isEmpty())
                    System.out.println("Введите фамилию: ");
                    String surname = scanner.nextLine();
                    System.out.println("Введите возраст: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    if (age < 0 || age > 100) {
                        System.out.println("Некорректный возраст");
                        break;
                    }
                    System.out.println("Введите телефон: ");
                    String phone = scanner.nextLine();
                    System.out.println("Введите email: ");
                    String email = scanner.nextLine();
                    if (Repository.validationEmail(email)) {
                        System.out.println("Данный email существует!");
                        break;
                    }

                    Student student = new Student(name, surname, age, phone, email);
                    Repository.createStudent(student);
                    break;
                case 4:
                    System.out.println("Обновение данных студента");
                    System.out.println("Введите ID студента: ");
                    int updateId = scanner.nextInt();
                    if (!Repository.validationId(updateId)) {
                        System.out.println("Студента с таким ID не существует!");
                        break;
                    }
                    scanner.nextLine();
                    System.out.println("Введите новое имя: ");
                    String updateName = scanner.nextLine();
                    System.out.println("Введите новую фамилию: ");
                    String updateSurname = scanner.nextLine();
                    System.out.println("Введите новый возраст: ");
                    int updateAge = scanner.nextInt();
                    System.out.println("Введите новый телефон: ");
                    String updatePhone = scanner.next();
                    System.out.println("Введите новый email: ");
                    String updateEmail = scanner.next();
                    if (Repository.validationEmail(updateEmail)) {
                        System.out.println("Данный email существует!");
                        break;
                    }
                    Repository.updateStudent(updateId, updateName, updateSurname, updateAge, updatePhone, updateEmail);
                    break;
                case 5:
                    System.out.println("Удаление записей\n" +
                            "1. Удалить студента по ID\n" +
                            "2. Удалить всех студентов");
                    System.out.println("Введите пункт меню:");
                    int number5 = scanner.nextInt();
                    if (number5 == 1) {
                        System.out.println("Введите ID студента для удаления: ");
                        int idDelete = scanner.nextInt();
                        if (!Repository.validationId(idDelete)) {
                            System.out.println("Студента с таким ID не существует!");
                            break;
                        }
                        Repository.deleteById(idDelete);
                    } else if (number5 == 2) {
                        Repository.deleteAll();
                    } else {
                        System.out.println("Неккоректный ввод");
                    }
                    break;
                case 6:
                    System.out.println("Поиск по имени/фамилии");
                    System.out.println("Введите имя: ");
                    String nameSearch = scanner.nextLine();
                    System.out.println("Введите фамилию: ");
                    String surnameSearch = scanner.nextLine();
                    List<Student> students6 = Repository.searchByNameSurname(nameSearch, surnameSearch);
                    if (students6.size() == 0) {
                        System.out.println("Студента с таким именем и фамилией не существует");
                    } else {
                        System.out.println(students6);
                    }
                    break;
                case 7:
                    System.out.println("Поиск по email");
                    System.out.println("Введите email: ");
                    String emailSearch = scanner.nextLine();
                    Student student7 = Repository.searchByEmail(emailSearch);
                    if (student7 == null) {
                        System.out.println("Студента с таким Email не существует!");
                    } else {
                        System.out.println(student7);
                    }
                    break;
                case 8:
                    System.out.println("Фильтр по возрасту");
                    System.out.println("Введите возраст: ");
                    int ageSearch = scanner.nextInt();
                    List<Student> students8 = Repository.searchByAge(ageSearch);
                    if (students8.size() == 0) {
                        System.out.println("Студентов с таким возрастом не существует!");
                    } else {
                        System.out.println(students8);
                    }
                    break;
                case 0:
                    System.out.println("Выход");
                    scanner.close();
                    flag = false;
                    break;
            }
        }
    }
}