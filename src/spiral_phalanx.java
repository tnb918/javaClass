import java.util.Scanner;

public class spiral_phalanx {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.println("请输入螺旋方阵阶数：");
        int num=in.nextInt();
        int a[][]=new int[num][num];
        int max=num*num;
        int index=0;
        int epoch=0;
        int m=0;
        int n=-1;
        while(index<max){
            //从左到右
            for(int i=0;i<num-epoch;i++){
                a[m][++n]=++index;
            }
            //从上到下
            for(int i=0;i<num-epoch-1;i++){
                a[++m][n]=++index;
            }
            //从右到左
            for(int i=0;i<num-epoch-1;i++){
                a[m][--n]=++index;
            }
            //从下到上
            for(int i=0;i<num-epoch-2;i++){
                a[--m][n]=++index;
            }
            epoch+=2;
        }
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }

    }
}
