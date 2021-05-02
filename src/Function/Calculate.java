package Function;
import javax.swing.JOptionPane;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculate
{
	public String getResult(String expressioon)
	{
		Expression calc = null;
		try
		{
			ExpressionBuilder builder = new ExpressionBuilder(expressioon);
			builder.build();
			calc = builder.build();
			return ""+calc.evaluate();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "输入格式有误!");
			return "error";
		}
	}
}
