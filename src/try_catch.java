import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class try_catch {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        while(true){
            String x=in.next();
            try{
                int num =Integer.parseInt(x);//键盘录入的结果转换成int类型的数据
                System.out.println(Integer.toBinaryString(num));//输出其二进制表现形式
                break;
            }
            catch(Exception e){
                try{
                    new BigInteger(x);//判断是否为整数
                    System.out.println("录入整数过大，请重新输入一个整数");
                }
                catch(Exception e1){
                    try{
                        new BigDecimal(x);//判断是否为小数
                        System.out.println("录入的是小数，请重新输入一个整数");
                    }
                    catch(Exception e2){
                        System.out.println("录入的是非法字符，请输入一个整数");
                    }
                }
            }
        }
    }
}
