import java.util.Scanner;

class BMI_cal{
    public double height;
    public double weight;
    public double BMI_num;

    public BMI_cal(double height,double weight){
        this.height=height;
        this.weight=weight;
    }

    public void getBMI(){
        this.BMI_num =weight/(height*height);
        System.out.printf("BMI指标计算为：%5.2f\n",BMI_num);
        System.out.println("BMI正常范围为18.5~24");
        System.out.printf("您的体重正常范围为%5.2f~%5.2f\n",18.5*height*height,24*height*height);
        if(BMI_num<18.5){
            System.out.println("您的体重过轻");
        }
        else if(BMI_num<24){
            System.out.println("您的体重正常");
        }
        else
            System.out.println("您的体重超重");
    }
}
public class BMI {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.print("请输入身高（m）: ");
        double height=in.nextDouble();
        System.out.print("请输入体重（kg）: ");
        double weight=in.nextDouble();
        BMI_cal example=new BMI_cal(height,weight);
        example.getBMI();
    }
}
