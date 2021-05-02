import java.util.Scanner;

public class triangle_area {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.println("请输入三角形的三边");
        double a=in.nextDouble();
        double b=in.nextDouble();
        double c=in.nextDouble();
        if( a+b>c && b+c>a && a+c>b ){
            double s=(a+b+c)/2;
            double area=Math.sqrt(s*(s-a)*(s-b)*(s-c));
            System.out.println("此三角形的面积为："+area);
        }
        else{
            System.out.println("此三边不能构成三角形");
        }
    }
}
