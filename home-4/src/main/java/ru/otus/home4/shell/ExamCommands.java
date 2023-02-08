package ru.otus.home4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;
import ru.otus.home4.config.LocalConfig;
import ru.otus.home4.dao.StudentDao;
import ru.otus.home4.domains.Student;
import ru.otus.home4.enums.LocalMessages;
import ru.otus.home4.services.ExamService;


@ShellComponent
@RequiredArgsConstructor
public class ExamCommands {

   final private  ExamService examService;
   final private StudentDao studentDao;
   final private LocalConfig localConfig;
   final private MessageSource messageSource;
   private String name;
   private String lastName;
    @ShellMethodAvailability(value = "checkRegisterData" )
    @ShellMethod(  value = "Start exam", key = {"start"})
    public String startExam() {
        Student student = examService.runExam(name, lastName);
        studentDao.save(student);
        return "\n<><><><><><><><> FINISH <><><><><><><><>";

    }

    @ShellMethod(  value = "Registration", key = {"reg", "register"} )
    public String register(@ShellOption() String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
            return "REGISTERED SUCCESSFULLY";
    }
    @ShellMethod(  value = "Logout", key = {"logout"} )
    public String logout() {
            this.name = null;
            this.lastName = null;
            return "YOU ARE LOGGED OUT";
    }

    private Availability checkRegisterData(){
       if (!(StringUtils.hasText(name) || StringUtils.hasText(lastName))){
           return Availability.unavailable(messageSource.getMessage(
                   LocalMessages.NOT_HAVE_REGISTER_ERROR.getValue(), new String[0], localConfig.getLocale()));
       }else {
           return Availability.available();
       }

    }
}