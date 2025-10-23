import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    static String url = "jdbc:mysql://localhost:3306/university";
    static String username = "root";
    static String password = "root";

    public static void createStudent(Student student) {
        try {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO Students (name, surname, age, phone, email) VALUES(?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setString(2, student.getSurname());
                    preparedStatement.setInt(3, student.getAge());
                    preparedStatement.setString(4, student.getPhone());
                    preparedStatement.setString(5, student.getEmail());
                    preparedStatement.executeUpdate();
//                    System.out.println("Good connection");
                    try (ResultSet key = preparedStatement.getGeneratedKeys()) {
                        if (key.next()) {
                            int id = key.getInt(1);
                            student.setId(id);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Bad connection");
        }
        System.out.println("Студент добавлен");
    }

    public static List<Student> reedAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Students";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Bad connection");
        }
        return students;
    }

    public static Student searchById(int numberId) {
              Student student = null;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Students WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numberId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                        student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"));
                    System.out.println("Студент найден!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Bad connection");
        }
        return student;
    }

    public static List<Student> searchByNameSurname(String nameSearch, String surnameSearch) {
                List<Student> students = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Students WHERE name = ? AND surname = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nameSearch);
                preparedStatement.setString(2, surnameSearch);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"));
                    System.out.println("Студент найден!");
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static Student searchByEmail(String emailSearch) {
               Student student = null;
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Students WHERE email = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, emailSearch);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"));
                         System.out.println("Студент найден!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public static List<Student> searchByAge(int ageSearch) {
               List<Student> students = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Students WHERE age = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ageSearch);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"));
                    System.out.println("Студент найден!");
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static void updateStudent(int updateId, String updateName, String updateSurname, int updateAge, String updatePhone, String updateEmail) {
           try(Connection connection = DriverManager.getConnection(url, username, password)) {
               String sql = "UPDATE Students SET name = ?, surname = ?, age = ?, phone = ?, email = ? WHERE id = ?";
               try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                   preparedStatement.setString(1, updateName);
                   preparedStatement.setString(2, updateSurname);
                   preparedStatement.setInt(3, updateAge);
                   preparedStatement.setString(4, updatePhone);
                   preparedStatement.setString(5, updateEmail);
                   preparedStatement.setInt(6, updateId);
                   preparedStatement.executeUpdate();
                   System.out.println("Данные студента обновлены");
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
    }

    public static void deleteById(int idDelete) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM Students WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idDelete);
                preparedStatement.executeUpdate();
                System.out.println("Студент удален");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAll() {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM Students";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
                System.out.println("Все записи удалены");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validationEmail(String email) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT COUNT(id) FROM Students WHERE email = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                  return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean validationId(int id) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT COUNT(id) FROM Students WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

