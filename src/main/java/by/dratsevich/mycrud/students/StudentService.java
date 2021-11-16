package by.dratsevich.mycrud.students;

import by.dratsevich.mycrud.courses.Course;
import by.dratsevich.mycrud.courses.CourseNotFoundExeption;
import by.dratsevich.mycrud.courses.CourseRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.Id;
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

  public Student get (Integer id) throws StudentNotFoundExeption {
    Optional<Student> result = studentRepo.findById(id);
    if (result.isPresent()) {
      return result.get();
    }
    throw new StudentNotFoundExeption("Could not find any student with ID " + id);
  }

  public void delete(Integer id) throws StudentNotFoundExeption {
    Long count = studentRepo.countById(id);
    if (count == null || count == (long) 0) {
      throw new StudentNotFoundExeption("Could not find any student with id " + id);
    }
    studentRepo.deleteById(id);

  }

  public List<Student> listStudentsOnCourse(Integer Id) {
    return studentRepo.findAllByCourse_CourseId(Id);
  }


}
