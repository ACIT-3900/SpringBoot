package cstOptions.Service;

import cstOptions.Dao.StudentDao;
import cstOptions.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public void getAllStudents(){
        studentDao.getAllStudents();
    }
}
