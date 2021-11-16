package by.dratsevich.mycrud.courses;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
  long countByCourseId(Integer courseId);
}
