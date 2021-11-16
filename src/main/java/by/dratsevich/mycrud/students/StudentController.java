package by.dratsevich.mycrud.students;

import by.dratsevich.mycrud.courses.Course;
import by.dratsevich.mycrud.courses.CourseNotFoundExeption;
import by.dratsevich.mycrud.courses.CourseService;
import by.dratsevich.mycrud.users.User;
import by.dratsevich.mycrud.users.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class StudentController {

  @Autowired
  private StudentService studentService;
  @Autowired
  private CourseService courseService;
  @Autowired
  private UserService userService;

  @GetMapping("/students")
  public String showStudentsList(Model studentModel) {
    List<Student> listStudents = studentService.listAllStudents();
    studentModel.addAttribute("listStudents", listStudents);
    return "students";
  }


  @GetMapping("/students/new")
  public String showNewForm(Model studentModel, Model courseModel) {
    studentModel.addAttribute("student", new Student());
    studentModel.addAttribute("pageTitle", "Add new student");
    List<Course> listCourses = courseService.listAllCourses();
    courseModel.addAttribute("listCourses", listCourses);
    return "student_form";
  }

  @PostMapping("students/save")
  public String saveStudent(Student student, RedirectAttributes ra) {
    studentService.save(student);
    return "redirect:/students";
  }

  @GetMapping("students/delete/{id}")
  public String deleteStudent(Student student) throws StudentNotFoundExeption {
    studentService.delete(student.getId());
    return "redirect:/students";
  }

  @GetMapping("students/edit/{id}")
  public String showEditForm(@PathVariable("id") Integer id, Model studentModel,
      Model courseModel, Model model,RedirectAttributes ra) {
    try {
      Student student = studentService.get(id);
      studentModel.addAttribute("student", student);
      studentModel.addAttribute("pageTitle", "Edit student");
      List<Course> listCourses = courseService.listAllCourses();
      courseModel.addAttribute("listCourses", listCourses);
      return "student_form";
    } catch (StudentNotFoundExeption e) {
      ra.addFlashAttribute("message", "The student has been edit successfully!");
    }
    return "redirect:/students";
  }

}
