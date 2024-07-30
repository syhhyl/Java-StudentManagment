import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作\n1:登录 \n2:注册 \n3:忘记密码 \n0:退出\nenter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (UserManager.login()) {
                        manageStudents();
                    }
                    break;
                case 2:
                    UserManager.register();
                    break;
                case 3:
                    UserManager.forgotPassword();
                    break;
                case 0:
                    System.out.println("退出系统。");
                    return;
                default:
                    System.out.println("无效的选择，请重新选择。");
            }
        }
    }

    public static void manageStudents() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-------------欢迎来到学生管理系统----------------");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：退出");
            System.out.println("请输入您的选择:");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    StudentManager.addStudent();
                    break;
                case 2:
                    StudentManager.deleteStudent();
                    break;
                case 3:
                    StudentManager.updateStudent();
                    break;
                case 4:
                    StudentManager.viewStudents();
                    break;
                case 5:
                    System.out.println("退出学生管理系统。");
                    return;
                default:
                    System.out.println("无效的选择，请重新选择。");
            }
        }
    }
}
