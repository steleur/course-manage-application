package by.dratsevich.mycrud.courses;

import by.dratsevich.mycrud.users.User;
import by.dratsevich.mycrud.users.UserNotFoundExeption;
import by.dratsevich.mycrud.users.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

  @Autowired
  private CourseService courseService;
  @Autowired
  private UserService service;

  @GetMapping("/courses")
  public String showCoursesList(Model courseModel) {
    List<Course> listCourses = courseService.listAllCourses();
    courseModel.addAttribute("listCourses", listCourses);
    return "courses";
  }

  @GetMapping("/courses/new")
  public String showNewForm(Model courseModel, Model model) {
    courseModel.addAttribute("course", new Course());
    courseModel.addAttribute("pageTitle", "Add new course");
    List<User> listUsers = service.listAll();
    model.addAttribute("listUsers", listUsers);
    return "course_form";
  }

  @PostMapping("/courses/save")
  public String saveUser(@ModelAttribute Course course, RedirectAttributes ra) {
    courseService.save(course);
    ra.addFlashAttribute("message", "The user has been saved successfully!");
    return "redirect:/courses";

  }

  @GetMapping("courses/delete/{courseId}")
  public String deleteCourse(@PathVariable("courseId") Integer courseId, RedirectAttributes ra) {
    try {
      courseService.delete(courseId);
      ra.addFlashAttribute("message", "The user has been deleted successfully!");
    } catch (CourseNotFoundExeption e) {
      e.printStackTrace();
    }
    return "redirect:/courses";
  }

  @GetMapping("courses/edit/{courseId}")
  public String showEditForm(@PathVariable("courseId") Integer courseId, Model courseModel,
      Model model, RedirectAttributes ra) {
    try {
      Course course = courseService.get(courseId);
      courseModel.addAttribute("course", course);
      courseModel.addAttribute("pageTitle", "Edit course");
      List<User> listUsers = service.listAll();
      model.addAttribute("listUsers", listUsers);
      return "course_form";
    } catch (CourseNotFoundExeption e) {
      ra.addFlashAttribute("message", "The course has been edit successfully!");
    }
    return "redirect:/courses";
  }

}
