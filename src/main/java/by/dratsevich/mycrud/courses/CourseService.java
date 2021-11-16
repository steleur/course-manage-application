package by.dratsevich.mycrud.courses;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
  @Autowired private CourseRepository courseRepo;

  public List<Course> listAllCourses() {
    return (List<Course>) courseRepo.findAll();
  }

  public void save(Course course) {
    courseRepo.save(course);
  }

  public Course get (Integer courseId) throws CourseNotFoundExeption {
    Optional<Course> result = courseRepo.findById(courseId);
    if (result.isPresent()) {
      return result.get();
    }
    throw new CourseNotFoundExeption("Could not find any course with ID " + courseId);
  }

  public void delete(Integer courseId) throws CourseNotFoundExeption {
    Long count = courseRepo.countByCourseId(courseId);
    if (count == null || count == (long) 0) {
      throw new CourseNotFoundExeption("Could not find any course with id " + courseId);
    }
    courseRepo.deleteById(courseId);

  }

}
