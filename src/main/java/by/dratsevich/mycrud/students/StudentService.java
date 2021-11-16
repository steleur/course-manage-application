package by.dratsevich.mycrud.students;

import by.dratsevich.mycrud.courses.Course;
import by.dratsevich.mycrud.courses.CourseNotFoundExeption;
import by.dratsevich.mycrud.courses.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepo;

  public List<Student> listAllStudents() {
    return (List<Student>) studentRepo.findAll();
  }

  public void save(Student student) {
    studentRepo.save(student);
  }

  public Student get (Integer id) throws CourseNotFoundExeption {
    Optional<Student> result = studentRepo.findById(id);
    if (result.isPresent()) {
      return result.get();
    }
    throw new CourseNotFoundExeption("Could not find any course with ID " + id);
  }

  public void delete(Integer id) throws CourseNotFoundExeption {
    Long count = studentRepo.countById(id);
    if (count == null || count == (long) 0) {
      throw new CourseNotFoundExeption("Could not find any course with id " + id);
    }
    studentRepo.deleteById(id);

  }

}
