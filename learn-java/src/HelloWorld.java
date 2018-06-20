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

// ��  ------------------------------------>  ��
// byte,short,char��> int ��> long��> float ��> double

// ������ջ������
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("�������ͣ�byte ������λ����" + Byte.SIZE);
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
// ��Java�У��Դ���һ��java.util.logging.Logger��Ҳ����ʹ��log4j��ӡ��־
        testRecycle();
        printDate();
        Exceptions except = new Exceptions();
        except.printException();
//        Object str = except.getInput();
//        System.out.println("Get input from console ��" + str);
        except.sumFromScanner();

        // ��������ת��
        int i2 = (int) _num;
        System.out.println("=====" + i2);

        MysqlOpreater mOpt = new MysqlOpreater();
        mOpt.opreateMysql();
        try {
            mOpt.testThrowException();
//   ����ʹ�ø����쳣���в���
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
        // ʹ�� toString() ������ʾ����ʱ��
        System.out.println(date.getTime());
        System.out.println(date.compareTo(new Date(date.getTime() - 10)));
    }
// java��װ�������
// ��ʵ�ʿ��������У����Ǿ�����������Ҫʹ�ö��󣬶����������������͵����Ρ�Ϊ�˽��������⣬Java ����Ϊÿһ���������������ṩ�˶�Ӧ�İ�װ�࣬
// ���еİ�װ�ࣨInteger��Long��Byte��Double��Float��Short�����ǳ����� Number �����ࡣ
// �����ɱ������ر�֧�ֵİ�װ��Ϊװ�䣬���Ե������������ͱ���������ʹ�õ�ʱ�򣬱����������������װ��Ϊ��װ�ࡣ
// ���Ƶģ�������Ҳ���԰�һ���������Ϊ�������͡�
// Number ������ java.lang ����
}

class Exceptions {
//    ���е��쳣���Ǵ� java.lang.Exception ��̳е����ࡣ
//    �쳣����������Ҫ�����ࣺIOException ��� RuntimeException �ࡣ

    //    Exception �� Error ���� Throwable ������ࡣ
//    Error ����ָʾ����ʱ���������Ĵ���

//    public void Exceptions() {
//
//    }

    protected void printException() {
// try ������ܼ�û catch ��Ҳû finally �顣
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
//            1��һ��Ҫ��ȡ����Ч�ַ���ſ��Խ������롣
//            2����������Ч�ַ�֮ǰ�����Ŀհף�next() �������Զ�����ȥ����
//            3��ֻ��������Ч�ַ���Ž����������Ŀհ���Ϊ�ָ������߽�������
//            4��next() ���ܵõ����пո���ַ�����

//    nextLine()��
//
//            1����EnterΪ������,Ҳ����˵ nextLine()�������ص�������س�֮ǰ�������ַ���
//            2�����Ի�ÿհס�

    //  ��console��ȡ�û�����
    protected Object getInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("�����룺");
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
        System.out.println("���������֣�");
        while (scan.hasNextDouble()) {
            double x = scan.nextDouble();
            m = m + 1;
            sum = sum + x;
        }
        System.out.println(m + "�����ĺ�Ϊ" + sum);
        System.out.println(m + "������ƽ��ֵ��" + (sum / m));
        scan.close();
    }
}

// mysql���ݿ����
class MysqlOpreater {
    // ����ʹ��final��������̬ʹ��static����
    // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sdk";
    // ���ݿ���û���������
    static final String USER = "root";
    static final String PASS = "baiying";

    public void MysqlOpreater(String opreaterName) {

    }

    protected void opreateMysql() {
        // java�еĿ�ֵʹ��null��������GO��nil
        Connection conn = null;
        Statement stmt = null;
        try {
            // ע�� JDBC ����
//           Class.forName("com.mysql.jdbc.Driver");

            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, platform FROM device";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // ͨ���ֶμ���
                int id = rs.getInt("id");
                String plat = rs.getString("platform");

                // �������
                System.out.print("ID: " + id);
                System.out.print("\n");
                System.out.print("ƽ̨: " + plat);
                System.out.print("\n");
            }
            System.out.println("Read mysql ��" + rs);
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception sqlExc) {
            // ���� JDBC ����
            sqlExc.printStackTrace();
        }
    }

    // java������ֱ��throws�쳣
    protected void testThrowException() throws ArithmeticException {
        int a = 10 / 0;
        return;
    }
}


// �Զ����쳣

//@Test��Override