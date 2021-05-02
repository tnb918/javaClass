import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Sort{
    int[] arr=new int[100];

    public void loadNum() {//产生随机数并存入数组
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * 100 + 1);
            arr[i] = index;
        }
    }

    public void showNum(){//显示数组的值
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public void writeTxt(String txtname) throws IOException {//将数组写入txt文件中
        File file = new File("C:\\Users\\TNB\\Desktop\\"+txtname);
        FileWriter out = new FileWriter(file);
        for (int value : arr) {
            out.write(value + "   ");
        }
        out.close();
    }

    public void bubbleSort(){
        System.out.println("冒泡排序法（升幂）结果：");
        for(int x=1;x<=arr.length;x++)
        {
            for(int index = 0;index<arr.length-x;index++)
            {
                if(arr[index]>arr[index+1])
                {
                    int temp = arr[index];
                    arr[index] = arr[index+1];
                    arr[index+1] = temp;
                }
            }
        }
    }

    public void bubbleSort(int flag){
        System.out.println("冒泡排序法（降幂）结果：");
        for(int x=1;x<=arr.length;x++)
        {
            for(int index = 0;index<arr.length-x;index++)
            {
                if(arr[index]<arr[index+1])
                {
                    int temp = arr[index];
                    arr[index] = arr[index+1];
                    arr[index+1] = temp;
                }
            }
        }
    }

    public void sortFast(int low,int high){//快速排序
        if(low>=high) {   //递归结束条件
            return;
        }
        int i = low;
        int j = high;
        int key = arr[i]; //选择基准
        //第一次排序，比基准小的放到了基准左面，比基准大的放到了基准右面
        while(i<j) {
            while(arr[j]>=key && i<j) {
                j--;
            }
            if(i<j) {
                int t;
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
            while(arr[i]<=key && i<j) {
                i++;
            }
            if(i<j) {
                int t;
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //对基准左侧的集合重复操作
        sortFast(low,i-1);
        //对基准右侧的集合重复操作
        sortFast(i+1,high);
    }

}

public class Random_num {
    public static void main(String[] args) throws IOException {
        Sort example=new Sort();
        example.loadNum();
        System.out.println("原始随机数组：");
        example.showNum();

        example.writeTxt("num.txt");
        example.bubbleSort();
        example.showNum();

        example.bubbleSort(1);
        example.writeTxt("num2.txt");
        example.showNum();

        example.sortFast(0,99);
        System.out.println("快速排序法结果：");
        example.showNum();
    }
}