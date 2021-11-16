package by.dratsevich.mycrud.students;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
  long countById(Integer id);
}
