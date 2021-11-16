package by.dratsevich.mycrud.students;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
  long countById(Integer id);

  List<Student> findAllByCourse_CourseId (Integer Id);

}
