package cstOptions;

import cstOptions.Dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/getStudentName", method = RequestMethod.GET)
    public ArrayList<String> getAllStudents(){
        return studentDao.getAllStudents();
    }

}
