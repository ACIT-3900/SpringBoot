package cstOptions;

import cstOptions.Dao.StudentDao;
import cstOptions.Entity.Options;
import cstOptions.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentDao studentDao = new StudentDao();

    @RequestMapping(value = "/getStudentName", method = RequestMethod.GET)
    public ArrayList<HashMap<String, String>> getAllStudents(){
        return studentDao.getAllStudents();
    }

    @RequestMapping(value = "/searchById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Student searchById(@PathVariable(name = "id") String id){
        return studentDao.searchById(id);
    }

    @RequestMapping(value = "/optionsDetail", method = RequestMethod.GET)
    public ArrayList<Options> ViewOptions(){
        return studentDao.ViewOptions();
    }

}
