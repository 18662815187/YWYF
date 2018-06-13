import java.util.ArrayList;
import java.util.List;

public class ToolsA {
	// 传入任务、每组数据条数，每次处理条数
	public Integer perform(List<String> array, int N, int M) {
		System.out.println("任务总数:" + array.size());
		/*
		 * 用于分组最终存放
		 */
		List<List<String>> a = new ArrayList<>();
		/**
		 * 临时分组存放
		 */
		List<String> tempList = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			tempList.add(array.get(i));
			/**
			 * 每N条数据一组即对N求余为0,将第一组加入到a中,避开下标为0的那组
			 */
			if (i % N == 0 && i != 0) {
				a.add(tempList);
				// 加完初始化否则会出现重复计算的情况
				tempList = new ArrayList<>();
			}
		}
		// 下标为0是只添加到了tempList中并没有add到a中，这里另外add下,前提是这个数组不为空
		if (array.size() > 0) {
			a.add(tempList);
		}
		// 开辟新临时分组空间
		tempList = new ArrayList<>();
		// 成功次数
		int s = 0;
		// 执行次数
		int r = 0;
		// 失败次数
		int f = 0;
		// 开始执行，每次执行M组数据，i<=表示处理到最后一组为止,i为当前组数据位置
		for (int i = 0; i <= a.size(); i++) {
			// 每次执行M组数据对M求余等于0则为执行完M组数据，且下标不等于0
			if (i % M == 0 && i != 0) {
				// 执行循环M次
				for (int j = 0; j < M; j++) {
					// 捕捉失败
					try {
						// 判断数组是否越界，只有在未越界的情况下才操作
						if (i - j < a.size()) {
							// 取数据
							tempList = a.get(i - j);
							// 累加成功次数，需对每组数据的每个数据进行处理
							for (@SuppressWarnings("unused")
							String string : tempList) {
								s++;
							}
							r++;
						}
					} catch (Exception e) {
						f++;
						r++;
						new ToolsA().perform(tempList, N, M);
					}
				}
				// 单独取下标等于0的情况
			} else if (i == 0) {
				try {
					tempList = a.get(i);
					for (@SuppressWarnings("unused")
					String string : tempList) {
						s++;
					}
					r++;
				} catch (Exception e) {
					f++;
					r++;
					new ToolsA().perform(tempList, N, M);
				}

			}

		}
		System.out.println("有" + a.size() + "组数据");
		System.out.println("总共执行了：" + r + "次");
		System.out.println("失败：" + f + "次");
		return s;
	}

}
