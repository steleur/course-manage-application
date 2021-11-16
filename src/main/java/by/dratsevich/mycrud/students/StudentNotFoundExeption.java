package by.dratsevich.mycrud.students;

public class StudentNotFoundExeption extends Throwable {
  public StudentNotFoundExeption(String message) {
    super(message);
  }
}
