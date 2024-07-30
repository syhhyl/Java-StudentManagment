import java.util.*;

public class UserManager {
    private static Map<String, User> userMap = new HashMap<>();

    public static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        //判断用户名是否符合要求
        if (!validateUsername(username)) {
            System.out.println("用户名不符合要求或已存在。");
            //System.out.println("test");
            return;
        }

        if (userMap.containsKey(username)) {
            System.out.println("用户存在 请登陆");
            login();
        }

        System.out.println("请输入密码：");
        String password1 = sc.nextLine();
        System.out.println("请再次输入密码：");
        String password2 = sc.nextLine();

        if (!password1.equals(password2)) {
            System.out.println("两次输入的密码不一致。");
            return;
        }

        System.out.println("请输入身份证号码：");
        String idNumber = sc.nextLine();
        if (!validateIdNumber(idNumber)) {
            System.out.println("身份证号码不符合要求。");
            return;
        }

        System.out.println("请输入手机号码：");
        String phoneNumber = sc.nextLine();
        if (!validatePhoneNumber(phoneNumber)) {
            System.out.println("手机号码不符合要求。");
            return;
        }

        userMap.put(username, new User(username, password1, idNumber, phoneNumber));
        System.out.println("注册成功！");
    }

    public static boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        //检查在userMap中
        if (!userMap.containsKey(username)) {
            System.out.println("用户名未注册，请先注册。");
            return false;
        }


        for (int i = 0; i < 3; i++) {
            System.out.println("请输入密码：");
            String password = sc.nextLine();
            //System.out.println("debug");
            String genCaptcha = generateCaptcha();
            System.out.println("请输入验证码：" + genCaptcha);
            //System.out.println("debug");
            String inputCaptcha = sc.nextLine();

            if (!(inputCaptcha.equals(genCaptcha))) {
                System.out.println("验证码错误");
                continue;
            }

            if (userMap.get(username).getPassword().equals(password)) {
                System.out.println("登录成功！");
                return true;
            } else {
                System.out.println("用户名或密码错误。");
            }
        }
        System.out.println("登录失败，请稍后再试。");
        return false;
    }

    public static void forgotPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();

        if (!userMap.containsKey(username)) {
            System.out.println("用户名未注册。");
            return;
        }

        System.out.println("请输入身份证号码：");
        String idNumber = sc.nextLine();
        System.out.println("请输入手机号码：");
        String phoneNumber = sc.nextLine();

        User user = userMap.get(username);
        if (user.getIdNumber().equals(idNumber) && user.getPhoneNumber().equals(phoneNumber)) {
            System.out.println("请输入新密码：");
            String newPassword = sc.nextLine();
            user.setPassword(newPassword);
            System.out.println("密码修改成功！");
        } else {
            System.out.println("账号信息不匹配，修改失败。");
        }
    }

    //用户名验证
    private static boolean validateUsername(String username) {
        if (!username.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{3,15}$")) {
            return false;
        }

        return !userMap.containsKey(username);
    }

    //id验证
    private static boolean validateIdNumber(String idNumber) {
        if (idNumber.length() != 18 || idNumber.charAt(0) == '0') {
            return false;
        }
        if (!idNumber.substring(0, 17).matches("\\d+")) {
            return false;
        }

        return idNumber.charAt(17) == 'X' || idNumber.charAt(17) == 'x' || Character.isDigit(idNumber.charAt(17));
    }

    //手机号验证
    private static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 11 && phoneNumber.charAt(0) != '0' && phoneNumber.matches("\\d+");
    }

    //生成验证码
    private static String generateCaptcha() {
        StringBuilder captcha = new StringBuilder();
        Random rand = new Random();
        //随机4个大小写字母
        for (int i = 0; i < 4; i++) {
            char ch = (char) (rand.nextBoolean() ? rand.nextInt(26) + 'A' : rand.nextInt(26) + 'a');
            captcha.append(ch);
        }
        //0-9的一个随机数
        int digit = rand.nextInt(10);
        //随机位置插入
        int position = rand.nextInt(4);
        //插入
        captcha.insert(position, digit);
        //返回验证码字符串
        return captcha.toString();
    }
}
