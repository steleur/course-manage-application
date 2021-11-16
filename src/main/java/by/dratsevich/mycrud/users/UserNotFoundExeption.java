package by.dratsevich.mycrud.users;

public class UserNotFoundExeption extends Throwable {

  public UserNotFoundExeption(String message) {
    super(message);
  }
}
