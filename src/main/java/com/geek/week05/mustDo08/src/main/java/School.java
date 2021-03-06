import javax.annotation.Resource;

/**
 * Created by xiangrui.xue on 2021/10/28.
 */
@Data
public class School implements ISchool{

    // Resource
    @Autowired(required = true) //primary
    Klass class1;

    @Resource(name = "student100")
    Student student100;

    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
    }

}
