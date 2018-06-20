/*import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import com.mysql.jdbc.Statement;*/

import java.sql.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;
import java.lang.System.*;

// Type convert:

// 低  ------------------------------------>  高
// byte,short,char—> int —> long—> float —> double

// 变量在栈上声明
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        int i = 129;
        byte b = (byte) i;
        System.out.println(b);
        char c = 'a';
        int in = c + 1;
        System.out.println(in);
        float num = 3.14f;
        double _num = num;
        System.out.println(_num);
        byte d = 0x4b;
        System.out.println(d);
        Integer in2 = 10;
        Integer _in = 10;
        System.out.println(in2.equals(_in));
// 在Java中，自带了一个java.util.logging.Logger，也可以使用log4j打印日志
        testRecycle();
        printDate();
        Exceptions except = new Exceptions();
        except.printException();
//        Object str = except.getInput();
//        System.out.println("Get input from console ：" + str);
        except.sumFromScanner();

        // 内置类型转换
        int i2 = (int) _num;
        System.out.println("=====" + i2);

        MysqlOpreater mOpt = new MysqlOpreater();
        mOpt.opreateMysql();
        try {
            mOpt.testThrowException();
//   可以使用父类异常进行捕获
        } catch (Exception e) {
            System.out.println("Catch the exception from function throws : " + e);
        }
    }

    private static void testRecycle() {
        int i = 0;
        do {
            System.out.printf("i is %d\n", i);
            i++;
        } while (i < 10);
    }

    protected static void printDate() {
        Date date = new Date();
        // 使用 toString() 函数显示日期时间
        System.out.println(date.getTime());
        System.out.println(date.compareTo(new Date(date.getTime() - 10)));
    }
// java的装箱与拆箱
// 在实际开发过程中，我们经常会遇到需要使用对象，而不是内置数据类型的情形。为了解决这个问题，Java 语言为每一个内置数据类型提供了对应的包装类，
// 所有的包装类（Integer、Long、Byte、Double、Float、Short）都是抽象类 Number 的子类。
// 这种由编译器特别支持的包装称为装箱，所以当内置数据类型被当作对象使用的时候，编译器会把内置类型装箱为包装类。
// 相似的，编译器也可以把一个对象拆箱为内置类型。
// Number 类属于 java.lang 包。
}

class Exceptions {
//    所有的异常类是从 java.lang.Exception 类继承的子类。
//    异常类有两个主要的子类：IOException 类和 RuntimeException 类。

    //    Exception 和 Error 类是 Throwable 类的子类。
//    Error 用来指示运行时环境发生的错误。

//    public void Exceptions() {
//
//    }

    protected void printException() {
// try 代码后不能既没 catch 块也没 finally 块。
        try {
            int a[] = new int[2];
            System.out.println("Access element three :" + a[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception thrown  :" + e);
            try {
                int b = 10 / 0;
            } catch (ArithmeticException e2) {
                System.out.println("Exception2 thrown2 :" + e2);
            }
        } catch (IllegalStateException i) {
            System.out.println("Exception thrown  :" + i);
        } finally {
            System.out.println("run here aways !");
        }
        System.out.println("Out of the block");
    }

//    next():
//
//            1、一定要读取到有效字符后才可以结束输入。
//            2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
//            3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
//            4、next() 不能得到带有空格的字符串。

//    nextLine()：
//
//            1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
//            2、可以获得空白。

    //  从console获取用户输入
    protected Object getInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入：");
        String inputStr = "";
        if (s.hasNext()) {
            inputStr = s.next();
            System.out.println("The input msg : " + inputStr);
        }
        s.close();
        return inputStr;
    }

    protected void sumFromScanner() {
        Scanner scan = new Scanner(System.in);
        double sum = 0;
        int m = 0;
        System.out.println("请输入数字：");
        while (scan.hasNextDouble()) {
            double x = scan.nextDouble();
            m = m + 1;
            sum = sum + x;
        }
        System.out.println(m + "个数的和为" + sum);
        System.out.println(m + "个数的平均值是" + (sum / m));
        scan.close();
    }
}

// mysql数据库操作
class MysqlOpreater {
    // 常量使用final声明，静态使用static声明
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sdk";
    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "baiying";

    public void MysqlOpreater(String opreaterName) {

    }

    protected void opreateMysql() {
        // java中的空值使用null，类似于GO的nil
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
//           Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, platform FROM device";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String plat = rs.getString("platform");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("\n");
                System.out.print("平台: " + plat);
                System.out.print("\n");
            }
            System.out.println("Read mysql ：" + rs);
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception sqlExc) {
            // 处理 JDBC 错误
            sqlExc.printStackTrace();
        }
    }

    // java方法后直接throws异常
    protected void testThrowException() throws ArithmeticException {
        int a = 10 / 0;
        return;
    }
}


// 自定义异常

//@Test和Override