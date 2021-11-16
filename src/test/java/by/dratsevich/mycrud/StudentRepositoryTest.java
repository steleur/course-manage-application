package by.dratsevich.mycrud;

import by.dratsevich.mycrud.students.Student;
import by.dratsevich.mycrud.students.StudentRepository;
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

public class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void testAddNew () {
    Student student = new Student();
    student.setFirstName("Kirill");
    student.setLastName("Dratsevich");
    student.setPhoneNumber("+375297819512");
    Student savedStudent = studentRepository.save(student);
    Assertions.assertThat(savedStudent).isNotNull();
    Assertions.assertThat(savedStudent.getFirstName()).isEqualTo("Kirill");
  }

}
