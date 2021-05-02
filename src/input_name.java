import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class input_name {
    public static void writeTxt(String txtname, String name) throws IOException {//将数组写入txt文件中
        FileWriter file = new FileWriter("C:\\Users\\TNB\\Desktop\\"+txtname,true);
        file.write(name + "\n");
        file.close();
    }

    public static boolean isCHChar(String string) {//判断是否为纯中文字符
        boolean flag = true;
        Pattern p = Pattern.compile("[^\u4e00-\u9fa5]");
        if(p.matcher(string).find()) {
            flag = false;
        }
        return flag;
    }

    public static boolean isENChar(String string) {//判断是否为纯英文字符
        boolean flag = true;
        Pattern p = Pattern.compile("[^a-zA-z]");
        if(p.matcher(string).find()) {
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        System.out.println("输入姓名");
        while(true){
            String name=in.nextLine();
            if(name.equals("")){
                break;
            }
            if(isENChar(name)){
                writeTxt("en.txt",name);
            }
            else if(isCHChar(name)){
                writeTxt("ch.txt",name);
            }
            else
                System.out.println("请输入中文或英文名");
        }
    }
}
