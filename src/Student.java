
public class Student {
    int id;
    String name;
    String surname;
    int age;
    String phone;
    String email;

    public Student(String name, String surname, int age, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public Student(int id, String name, String surname, int age, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Студент: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + "\n";
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
