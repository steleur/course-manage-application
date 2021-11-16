package by.dratsevich.mycrud.users;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository repo;

  public List<User> listAll() {
    return (List<User>) repo.findAll();
  }

  public void save(User user) {
    repo.save(user);
  }

  public User get (Integer id) throws UserNotFoundExeption {
    Optional<User> result = repo.findById(id);
    if (result.isPresent()) {
      return result.get();
    }
    throw new UserNotFoundExeption("Could not find any user with ID " + id);
  }

  public void delete(Integer id) throws UserNotFoundExeption {
   Long count = repo.countById(id);
   if (count == null || count == (long) 0) {
     throw new UserNotFoundExeption("Could not find any user with id " + id);
   }
    repo.deleteById(id);

  }
}
