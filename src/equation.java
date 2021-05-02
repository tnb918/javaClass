import java.util.Scanner;

public class equation {
    public static void main(String[] args){
        System.out.println("请输入方程ax^2+bx+c=0的参数a,b,c");
        Scanner in=new Scanner(System.in);
        double a,b,c;
        do{
            System.out.print("a = ");
            a=in.nextDouble();
            if(a==0){
                System.out.println("参数a取值不能为0");
            }
        }while(a==0);
        System.out.print("b = ");
        b=in.nextDouble();
        System.out.print("c = ");
        c=in.nextDouble();
        double flag=b*b-4*a*c;
        if(flag>0){
            double X1=(-b+Math.sqrt(flag))/(2*a);
            double X2=(-b-Math.sqrt(flag))/(2*a);
            System.out.println("方程有两个解，解为X1 = "+X1+"，X2 = "+X2);
        }
        if(flag==0){
            double X=(-b+Math.sqrt(flag))/(2*a);
            System.out.println("方程只有一个解，解为 X = "+X);
        }
        if(flag<0){
            System.out.println("此方程无解");
        }
    }
}
