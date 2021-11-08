import lombok.Data;

import java.util.List;

/**
 * Created by xiangrui.xue on 2021/10/28.
 */
@Data
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }
}
