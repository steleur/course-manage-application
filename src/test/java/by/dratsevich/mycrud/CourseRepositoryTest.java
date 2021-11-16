package by.dratsevich.mycrud;

import by.dratsevich.mycrud.courses.Course;
import by.dratsevich.mycrud.courses.CourseRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class CourseRepositoryTest {
  @Autowired
  private CourseRepository courseRepo;

  @Test
  public void testAddNew () {
    Course course = new Course();
    course.setCourseName("Course 1");
    Course savedCourse = courseRepo.save(course);
    Assertions.assertThat(savedCourse).isNotNull();
    Assertions.assertThat(savedCourse.getCourseId()).isGreaterThan(0);
  }

  @Test
  public void testDelete() {
    Integer courseId = 6;
    courseRepo.deleteById(courseId);
    Optional<Course> optionalCourse = courseRepo.findById(courseId);
    Assertions.assertThat(optionalCourse).isNotPresent();
  }
}
