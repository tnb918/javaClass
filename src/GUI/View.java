package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class View extends JFrame
{
	private static final Font EQUATION_FONT = new Font("", Font.PLAIN, 36);     //算式文本字体
	private static final Font RESULT_FONT = new Font("", Font.BOLD, 32);        //结果文本字体
	private static final Font BUTTON_FONT_ENGLISH = new Font("", 3, 25);        //默认按钮字体
	private static final Font BUTTON_FONT_CHINESE = new Font("", 1, 20);        //中文字体
	private static final Font BUTTON_FONT_NUMBER = new Font("", 1, 36);         //数字字体

	protected JTabbedPane jtab;
	protected JPanel calulatepane, tempane,dipane;
	// calulatepane
	protected JButton[] key;
	protected JTextField calField, resultField;
	protected JTextArea calArea;
	//dipane
	protected JButton acc1, acc2, acc3, dikey1, dikey2, dikey3;
	protected JLabel diLabel1, diLabel2, diLabel3, diLabel4, diLabel5;
	protected JTextField diField, finalField, integral1, integral2, differ;
	protected JTextArea diArea;

	public View()
	{
		layoutcompents();
		launchframe();
	}

	public void layoutcompents()
	{
		calulatepane();
		dipane();

		// jtab
		jtab = new JTabbedPane();
		jtab.addTab("计算", calulatepane);
		jtab.addTab("微积分", dipane);

		// jfrme
		this.add(jtab);
	}

	public void launchframe()
	{
		setTitle("科学计算器");
		//setIconImage(new ImageIcon("image/math.png").getImage());
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		calField.requestFocusInWindow();
	}

	public void calulatepane()
	{
		// calulatepane
		calulatepane = new JPanel();

		key = new JButton[54];
		String[] keyname = {"MC","MR","MS","M+","M-","π","e","%","rand",
							"^2","^","sin","cos","tan","CA","CE","<--","/",
							"^3","^(1/","asin","acos","atan","7","8","9","*",
							"^(1/2)","10^","log","exp","mod","4","5","6","-",
							"1/","e^","ln","dms","deg","1","2","3","+",
							"angle","rad","n!","(",")","+/-","0",".","=",};
		JPanel keyPane1 = new JPanel();
		for (int i = 0; i < 54; i++)
		{
			key[i] = new JButton(keyname[i]);
			keyPane1.add(key[i]);
			if ("1234567890".contains(keyname[i]))
			{
				key[i].setForeground(Color.BLUE);
				key[i].setFont(BUTTON_FONT_NUMBER);
			}else{
				key[i].setFont(BUTTON_FONT_ENGLISH);
			}
			if ("'CE''=''CA'".contains(keyname[i]))
			{
				key[i].setForeground(Color.RED);
			}

		}
		keyPane1.setLayout(new GridLayout(6, 9, 5, 5));

		calArea = new JTextArea();
		//calArea.setEditable(false);
		JScrollPane caljsp = new JScrollPane(calArea);
		Border rightBorder = BorderFactory.createEtchedBorder();
		Border rightBorderTittle = BorderFactory.createTitledBorder(rightBorder, "历史记录");
		caljsp.setBorder(rightBorderTittle);
		caljsp.setFont(RESULT_FONT);

		resultField = new JTextField();
		resultField.setEditable(false);
		resultField.setBackground(Color.white);
		resultField.setHorizontalAlignment(JTextField.RIGHT);
		resultField.setText("0");
		resultField.setFont(EQUATION_FONT);

		calField = new JTextField();
		calField.setHorizontalAlignment(JTextField.LEFT);
		calField.setHorizontalAlignment(JTextField.RIGHT);
		calField.setFont(EQUATION_FONT);

		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(2, 1));
		displayPanel.add(calField);
		displayPanel.add(resultField);

		tempane = new JPanel();
		tempane.setLayout(new BorderLayout());
		tempane.add(keyPane1, BorderLayout.SOUTH);
		tempane.add(displayPanel);

		calulatepane.setLayout(new BorderLayout());
		calulatepane.add(tempane,BorderLayout.CENTER);
		calulatepane.add(caljsp,BorderLayout.EAST);
		caljsp.setPreferredSize(new Dimension(150,600));
	}

	public void dipane() {
		//dipane
		dipane = new JPanel();
		diLabel1 = new JLabel(" 函数 y(x) = ");
		diLabel2 = new JLabel("积/微分结果");
		diLabel3 = new JLabel("  积分下限   ");
		diLabel4 = new JLabel("  积分上限   ");
		diLabel5 = new JLabel("  微分数值   ");
		acc1 = new JButton("1e-2");
		acc2 = new JButton("1e-4");
		acc3 = new JButton("1e-6");
		dikey1 = new JButton("积分");
		dikey2 = new JButton("微分");
		dikey3 = new JButton("重置");
		diField = new JTextField();
		finalField = new JTextField();
		integral1 = new JTextField();
		integral2 = new JTextField();
		differ = new JTextField();

		JPanel P1 = new JPanel();
		P1.setLayout(new BorderLayout());
		P1.add(diLabel1,BorderLayout.WEST);
		P1.add(diField,BorderLayout.CENTER);

		JPanel P2 = new JPanel();
		P2.setLayout(new BorderLayout());
		P2.add(diLabel2,BorderLayout.WEST);
		P2.add(finalField,BorderLayout.CENTER);

		JPanel P3 = new JPanel();
		P3.setLayout(new BorderLayout());
		P3.add(diLabel3,BorderLayout.WEST);
		P3.add(integral1,BorderLayout.CENTER);

		JPanel P4 = new JPanel();
		P4.setLayout(new BorderLayout());
		P4.add(diLabel4,BorderLayout.WEST);
		P4.add(integral2,BorderLayout.CENTER);

		JPanel P5 = new JPanel();
		P5.setLayout(new BorderLayout());
		P5.add(diLabel5,BorderLayout.WEST);
		P5.add(differ,BorderLayout.CENTER);

		JPanel dikeyPanel = new JPanel();
		dikeyPanel.setLayout(new GridLayout(3, 3, 5, 5));
		dikeyPanel.add(P3);dikeyPanel.add(P4);dikeyPanel.add(P5);
		dikeyPanel.add(acc1);dikeyPanel.add(acc2);dikeyPanel.add(acc3);
		dikeyPanel.add(dikey1);dikeyPanel.add(dikey2);dikeyPanel.add(dikey3);

		JPanel allPanel = new JPanel();
		allPanel.setLayout(new GridLayout(3,1));
		allPanel.add(P1);allPanel.add(P2);allPanel.add(dikeyPanel);

		diArea = new JTextArea();
		diArea.setEditable(false);
		JScrollPane dijsp = new JScrollPane(diArea);
		Border rightBorder = BorderFactory.createEtchedBorder();
		Border rightBorderTittle = BorderFactory.createTitledBorder(rightBorder, "历史记录");
		dijsp.setBorder(rightBorderTittle);

		dijsp.setFont(RESULT_FONT);
		diField.setFont(EQUATION_FONT);
		finalField.setFont(EQUATION_FONT);
		diLabel1.setFont(BUTTON_FONT_CHINESE);
		diLabel2.setFont(BUTTON_FONT_CHINESE);
		diLabel3.setFont(BUTTON_FONT_CHINESE);
		diLabel4.setFont(BUTTON_FONT_CHINESE);
		diLabel5.setFont(BUTTON_FONT_CHINESE);
		acc1.setFont(BUTTON_FONT_ENGLISH);
		acc2.setFont(BUTTON_FONT_ENGLISH);
		acc3.setFont(BUTTON_FONT_ENGLISH);
		integral1.setFont(BUTTON_FONT_CHINESE);
		integral2.setFont(BUTTON_FONT_CHINESE);
		differ.setFont(BUTTON_FONT_CHINESE);
		dikey1.setFont(BUTTON_FONT_CHINESE);
		dikey2.setFont(BUTTON_FONT_CHINESE);
		dikey3.setFont(BUTTON_FONT_CHINESE);

		dipane.setLayout(new BorderLayout());
		dipane.add(allPanel,BorderLayout.CENTER);
		dipane.add(dijsp,BorderLayout.EAST);
		dijsp.setPreferredSize(new Dimension(150,600));
	}
}
