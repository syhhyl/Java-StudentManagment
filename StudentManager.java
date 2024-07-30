import java.util.*;

public class StudentManager {
    private static Map<String, Student> studentMap = new HashMap<>();

    public static void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生ID：");
        String id = sc.nextLine();

        if (studentMap.containsKey(id)) {
            System.out.println("学生ID已存在，无法添加。");
            return;
        }

        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.println("请输入学生家庭住址：");
        String address = sc.nextLine();

        Student student = new Student(id, name, age, address);
        studentMap.put(id, student);
        System.out.println("学生添加成功！");
    }

    public static void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生ID：");
        String id = sc.nextLine();

        if (studentMap.containsKey(id)) {
            studentMap.remove(id);
            System.out.println("学生删除成功！");
        } else {
            System.out.println("未找到该学生！");
        }
    }

    public static void updateStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生ID：");
        String id = sc.nextLine();

        if (studentMap.containsKey(id)) {
            Student student = studentMap.get(id);
            System.out.println("请输入新的学生姓名：");
            student.setName(sc.nextLine());
            System.out.println("请输入新的学生年龄：");
            student.setAge(sc.nextInt());
            sc.nextLine(); // consume newline
            System.out.println("请输入新的学生家庭住址：");
            student.setAddress(sc.nextLine());

            System.out.println("学生信息更新成功！");
        } else {
            System.out.println("未找到该学生！");
        }
    }

    public static void viewStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("当前无学生信息，请添加后再查询。");
        } else {
            System.out.println("id\t姓名\t年龄\t家庭住址");
            for (Student student : studentMap.values()) {
                System.out.println(student);
            }
        }
    }
}
