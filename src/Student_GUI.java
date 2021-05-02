import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class Student_GUI{
    JTextField stuCollegeJTF;
    JTextField stuMajorJTF;
    JTextField stuClassJTF;
    JTextField stuIDJTF;
    JTextField stuNameJTF;
    JTextField stuAgeJTF;

    public static void writeTxt(String name) throws IOException {//将字符串写入txt文件中
        FileWriter file = new FileWriter("C:\\Users\\TNB\\Desktop\\stuInf.txt",true);
        file.write(name + "\n");
        file.close();
    }

    public Student_GUI(){//绘制GUI界面
        JFrame jf=new JFrame("学生信息");
        jf.setBounds(100,100,300,230);

        Container c=jf.getContentPane();

        JLabel stuCollege=new JLabel("院系：");
        JLabel stuMajor=new JLabel("专业：");
        JLabel stuClass=new JLabel("班级：");
        JLabel stuID=new JLabel("学号：");
        JLabel stuName=new JLabel("姓名：");
        JLabel stuAge=new JLabel("年龄：");

        stuCollegeJTF=new JTextField(20);
        stuMajorJTF=new JTextField(20);
        stuClassJTF=new JTextField(20);
        stuIDJTF=new JTextField(20);
        stuNameJTF=new JTextField(20);
        stuAgeJTF=new JTextField(20);

        JButton jb=new JButton("提交");

        c.add(stuCollege);c.add(stuCollegeJTF);
        c.add(stuMajor);c.add(stuMajorJTF);
        c.add(stuClass);c.add(stuClassJTF);
        c.add(stuID);c.add(stuIDJTF);
        c.add(stuName);c.add(stuNameJTF);
        c.add(stuAge);c.add(stuAgeJTF);
        c.add(jb);

        FlowLayout fl=new FlowLayout();
        c.setLayout(fl);

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((stuCollegeJTF.getText().equals("")) || (stuMajorJTF.getText().equals("")) ||
                        (stuClassJTF.getText().equals("")) ||(stuIDJTF.getText().equals("")) ||
                        (stuNameJTF.getText().equals("")) ||(stuAgeJTF.getText().equals("")))
                {
                    System.out.println("请输入完整内容");
                }
                else{
                    try {
                        writeTxt(stuCollege.getText()+stuCollegeJTF.getText());
                        writeTxt(stuMajor.getText()+stuMajorJTF.getText());
                        writeTxt(stuClass.getText()+stuClassJTF.getText());
                        writeTxt(stuID.getText()+stuIDJTF.getText());
                        writeTxt(stuName.getText()+stuNameJTF.getText());
                        writeTxt(stuAge.getText()+stuAgeJTF.getText());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.out.println("您的信息已提交");
                }
                stuCollegeJTF.setText("");
                stuMajorJTF.setText("");
                stuClassJTF.setText("");
                stuIDJTF.setText("");
                stuNameJTF.setText("");
                stuAgeJTF.setText("");
            }
        });
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Student_GUI();
    }
}