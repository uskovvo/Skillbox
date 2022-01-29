import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.List;

public class Student {
    @Getter
    private String name;
    @Getter
    private int age;
    private List<String> courses;
    @Getter
    private final Hashtable<Student, List<String>> student = new Hashtable<>();

    public Student (String fileCvs){
        loadStudentsFromFile(fileCvs);
    }

    public Student (String name, int age){
        this.name = name;
        this.age = age;
    }

    public void loadStudentsFromFile(String fileCvs){
        try {
            List<String> lines = Files.readAllLines(Path.of(fileCvs));
            lines.forEach(line -> {
                String[] fragments = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for(int a = 0; a < fragments.length; a++){
                    if(fragments[a].contains("\"")){
                        fragments[a] = fragments[a].replaceAll("\"", "");
                    }
                }
                courses = new Course().createCourseList(fragments[2]);
                student.put(new Student(fragments[0], Integer.parseInt(fragments[1])), courses);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
