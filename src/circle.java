import java.util.Scanner;

class Circle_cal{
    public double r;

    public Circle_cal(double r){
        this.r=r;
    }

    public void getArea(){
        System.out.printf("圆的面积为: %5.2f\n",Math.PI*Math.pow(r,2));
    }

    public void getPerimeter(){
        System.out.printf("圆的周长为: %5.2f\n",Math.PI*r*2);
    }
}

public class circle {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.print("请输入半径r的值: ");
        double r=in.nextDouble();
        Circle_cal example=new Circle_cal(r);
        example.getArea();
        example.getPerimeter();
    }
}
