import java.util.ArrayList;
import java.util.List;

public class TestDemo {
	public static void main(String[] args) {
		List<String> array = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			array.add(i + ",");
		}
		Integer s = new ToolsA().perform(array, 10, 5);
		System.out.println("成功：" + s + "次");
	}
}
