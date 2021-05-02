package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Function.Calculate;
import GUI.View;

public class MainController extends View
{
	private Double M = 0d;// 存储器
	double accuracy = 0.0001;
	double range1=0,range2=0,range3=0;
	boolean isAngle=false;
	double idResult=0;
	String temp;

	static String calIn = " ";
	Calculate calcuate;
	Calculate dical;

	CalController calController;
	diController diController;

	public static void main(String[] args)
	{
		new MainController();
	}

	public static String transAngle(String inStr,String findStr){

		boolean isStart=false;
		int[] data = new int[20];
		int m =0;

		int n = findStr.length();

		StringBuilder x = new StringBuilder();
		x.append(inStr);
		for (int i = 0; i < inStr.length()-n+1; i++) {
			String str = inStr.substring(i,i+n);
			if(str.equals(findStr)){
				data[m] = i;
				m++;
			}
		}

		if(data[0]==0){
			isStart = true;
		}

		for(int j=1;j<=data.length;j++)
		{
			for(int index = 0;index<data.length-j;index++)
			{
				if(data[index]<data[index+1])
				{
					int temp = data[index];
					data[index] = data[index+1];
					data[index+1] = temp;
				}
			}
		}

		for (int datum : data) {
			if (datum != 0) {
				if (x.charAt(datum - 1) == 'a') {
					x.insert(datum - 1, "180/pi*");
				} else if (x.charAt(datum) != 'l') {
					x.insert(datum + n, "pi/180*");
				} else if (x.charAt(datum + 1) != 'n') {
					x.insert(datum, "1/log(10)*");
				} else {
					x.replace(datum + 1, datum + 2, "og");
				}
			}

		}

		if(isStart){
			if(inStr.startsWith("a")){
				x.insert(0,"180/pi*");
			}
			else if(!inStr.startsWith("l")){
				x.insert(n,"pi/180*");
			}else if(inStr.charAt(1)!='n'){
				x.insert(0,"1/log(10)*");
			}else {
				x.replace(1,2,"og");
			}
		}

		return String.valueOf(x);
	}

	public MainController()
	{
		super();

		// calulatepanel
		calController = new CalController();
		for (int i = 0; i < 54; i++)
		{
			key[i].addActionListener(calController);
		}

		//dipanel
		diController = new diController();
		acc1.addActionListener(diController);
		acc2.addActionListener(diController);
		acc3.addActionListener(diController);
		dikey1.addActionListener(diController);
		dikey2.addActionListener(diController);
		dikey3.addActionListener(diController);
	}

	class diController implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dical = new Calculate();
			String get = e.getActionCommand();

			if("重置".compareTo(get)==0){
				diField.setText("");
				finalField.setText("");
				integral1.setText("");
				integral2.setText("");
				differ.setText("");
				accuracy = 0.0001;
				range1 = 0;
				range2 = 0;
				range3 = 0;
				diArea.setText("");
			}
			else if("积分".compareTo(get)==0){
				if(integral1.getText().equals("") || integral2.getText().equals("")){
					finalField.setText("请输入积分上下限数值");
				}
				else{
					range1 = Double.parseDouble(integral1.getText());
					range2 = Double.parseDouble(integral2.getText());
					int epoch = (int) ((range2-range1)/accuracy);
					double x = range1;
					for(int i=0;i<epoch;i++){
						x += accuracy;
						idResult += Double.parseDouble(dical.getResult(diField.getText().
								replaceAll("x",String.valueOf(x))));
					}
					finalField.setText(String.valueOf(String.format("%.10f",idResult*accuracy)));
				}
				diArea.append("∫"+range1+"-->"+range2+"("+diField.getText()+")dx"+"\n"+"="+finalField.getText()+"\n");

			}
			else if("微分".compareTo(get)==0){
				if(differ.getText().equals("")){
					finalField.setText("请输入微分数值");
				}
				else{
					range3 = Double.parseDouble(differ.getText());
					double y = range3;
					double y1 = Double.parseDouble(dical.getResult(diField.getText().
							replaceAll("x",String.valueOf(y+accuracy))));
					double y2 = Double.parseDouble(dical.getResult(diField.getText().
							replaceAll("x",String.valueOf(y-accuracy))));
					finalField.setText(String.valueOf(String.format("%.10f",(y1-y2)/(2*accuracy))));
				}
				diArea.append("d(" + diField.getText() + ")/dx(x="+range3+")" + "\n"+"="+finalField.getText() + "\n");
			}
			else if("1e-2".compareTo(get)==0){
				accuracy = 0.01;
				diArea.append("accuracy" + "=" + accuracy + "\n");
			}
			else if("1e-4".compareTo(get)==0){
				accuracy = 0.0001;
				diArea.append("accuracy" + "=" + accuracy + "\n");
			}
			else if("1e-6".compareTo(get)==0){
				accuracy = 0.000001;
				diArea.append("accuracy" + "=" + accuracy + "\n");
			}
			idResult = 0;
		}
	}

	class CalController implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			calcuate = new Calculate();
			String get = e.getActionCommand();



			if (("0123456789.+-*/^()eπ'sin''cos''tan''^2''^3''^(1/2)''mod'" +
					"'acos''asin''atan''e^''exp''10^''ln''log'").contains(get)) {
				if("mod".contains(get)){
					calIn = calField.getText() + "%";
					calField.setText(calIn);
				}
				else if("exp".contains(get) && !"e".contains(get)){
					calIn = calField.getText() + "*10^";
					calField.setText(calIn);
				}
				else if(("'sin''cos''tan''asin''acos''atan''ln''log'").contains(get)){
					calIn = calField.getText() + get + "(";
					calField.setText(calIn);
				}
				else {
					calIn = calField.getText() + get;
					calField.setText(calIn);
				}
			}
			else if ("=".contains(get) && calField.getText().trim().length() != 0 && !calIn.contains("=")) {
				calIn = calField.getText();
				temp = calIn;

				if(isAngle){
					if(calIn.contains("sin(") || calIn.contains("cos(") || calIn.contains("tan(")
							|| calIn.contains("log(") || calIn.contains("ln(")){
						if(calIn.contains("log(")){
							temp = transAngle(temp,"log(");
						}
						if(calIn.contains("ln(")){
							temp = transAngle(temp,"ln(");
						}
						if(calIn.contains("sin(")){
							temp = transAngle(temp,"sin(");
						}
						if(calIn.contains("cos(")){
							temp = transAngle(temp,"cos(");
						}
						if(calIn.contains("tan(")){
							temp = transAngle(temp,"tan(");
						}
						resultField.setText(calcuate.getResult(temp));
					}
					else{
						resultField.setText(calcuate.getResult(calIn));
					}
				}
				if(!isAngle){
					if(calIn.contains("log(") || calIn.contains("ln(")){
						if(calIn.contains("log")){
							temp = transAngle(temp,"log(");
						}
						if(calIn.contains("ln")){
							temp = transAngle(temp,"ln(");
						}
						resultField.setText(calcuate.getResult(temp));
					}
					else{
						resultField.setText(calcuate.getResult(calIn));
					}
				}
				calField.setText(calIn);
				if (!resultField.getText().contains("error"))
				{
					calArea.append(calIn + "=" + resultField.getText() + "\n");
				}
			}
			else if ("CE".compareTo(get) == 0) {
				int L = calIn.length();
				if (L != 0)
				{
					calIn = " ";
				}
				calField.setText(calIn);
				resultField.setText("0");
			}
			else if ("<--".compareTo(get) == 0) {
				int L = calIn.length();
				if (L != 0)
				{
					calIn = calIn.substring(0, L - 1);
				}
				calField.setText(calIn);
			}
			else if ("CA".compareTo(get) == 0) {
				calIn = "";
				calField.setText(calIn);
				resultField.setText("0");
				calArea.setText("");
			}
			else if ("rand".compareTo(get) == 0) {
				calIn = "";
				String num=String.valueOf(Math.random());
				calField.setText(num);
				resultField.setText(num);
			}
			else if("%".compareTo(get) == 0) {
				String num=String.valueOf(Double.parseDouble(calIn)/100);
				calField.setText(num);
				resultField.setText(num);
				calArea.append(calIn + "%=" + resultField.getText() + "\n");
			}
			else if("+/-".compareTo(get) == 0) {
				if (calIn.startsWith("-")) {
					calIn = calIn.substring(1);
					if(calIn.startsWith("(") &&calIn.endsWith(")")){
						calIn = calIn.substring(1);
						calIn = calIn.substring(0,calIn.length()-1);
					}
				} else {
					if(calIn.contains("+")||calIn.contains("-")||calIn.contains("*")||calIn.contains("/")){
						calIn = "-" + "(" + calIn + ")";
					}
					else{
						calIn = "-" + calIn;
					}
				}
				calField.setText(calIn);
			}
			else if("n!".compareTo(get) == 0) {
				if (Integer.parseInt(calIn) == 0) {
					resultField.setText("1");
				}
				long sum = 1;
				for (int i = 1; i <= Integer.parseInt(calIn); i++) {
					sum *= i;
				}
				calField.setText(calIn+"!");
				resultField.setText(String.valueOf(sum));
				if(Integer.parseInt(calIn)>20) {
					resultField.setText("OVER THE RANGE");
				}
				calArea.append(calIn + "!=" + resultField.getText() + "\n");
				calIn="";
			}
			else if("MS".compareTo(get) == 0){
				if(calIn.equals(" ")){
					calArea.append("当前值为空白，无法存入存储器" + "\n");
				}else{
					M = Double.parseDouble(calField.getText());
					calArea.append("数值" + calField.getText() + "已存入存储器" + "\n");
				}
			}
			else if("MC".compareTo(get) == 0){
				M = 0d;
				calArea.append("存储器内数据已清空" + "\n");
			}
			else if("M+".compareTo(get) == 0){
				M += Double.parseDouble(resultField.getText());
				calArea.append("存储器+"+resultField.getText()+"=>" + M + "\n");
			}
			else if("M-".compareTo(get) == 0){
				M -= Double.parseDouble(resultField.getText());
				calArea.append("存储器-"+resultField.getText()+"=>" + M + "\n");
			}
			else if("MR".compareTo(get) == 0){
				calField.setText(String.valueOf(M));
				calArea.append("已显示寄存器数值" + M + "\n");
			}
			else if("dms".compareTo(get) == 0){
				double x = Double.parseDouble(calField.getText())*3600;
				int d = (int) (x /3600);
				int m = (int) (x / 60 -d*60);
				int s = (int) (x-d*3600-m*60);
				calField.setText(String.valueOf(d+m*0.01+s*0.0001));
				calArea.append(calIn + "度=" + calField.getText() + "时分秒"+ "\n");
			}
			else if("deg".compareTo(get) == 0){
				double x = Double.parseDouble(calField.getText())*10000;
				int s = (int) (x % 100);
				int m = (int) ((x % 10000 - s)/100);
				int d = (int) (x / 10000);
				calField.setText(String.valueOf(String.format("%.2f",(d*3600+m*60+s)/3600.0)));
				calArea.append(calIn + "时分秒=" + calField.getText() +"度"+ "\n");
			}
			else if("angle".compareTo(get) == 0){
				isAngle = true;
				calArea.append("当前三角函数单位（角度）"+ "\n");
			}
			else if("rad".compareTo(get) == 0){
				isAngle = false;
				calArea.append("当前三角函数单位（弧度）"+ "\n");
			}
			calField.requestFocusInWindow();
		}
	}
}
