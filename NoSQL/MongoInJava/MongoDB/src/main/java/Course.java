import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
    private final List<String> courseList = new ArrayList<>();

    public List<String> createCourseList (String line){
        String[] lines = line.split(",");
        courseList.addAll(Arrays.asList(lines));
        return courseList;
    }
}
