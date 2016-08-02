package test003;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Sky
 * @version 创建时间：2016年6月28日 上午9:03:57 类说明
 */
public class Test {
	ArrayList<PageTable> pageList = new ArrayList<>();// 存放所有页表基本信息的List
	ArrayList<Operator> operatorList = new ArrayList<>();
	int[] P = new int[4];// 模拟FIFO进行页面调度的数据结构
	int index = 0;
	int Max = 4;

	public static void main(String[] args) {
		Test test003 = new Test();
//		test003.work1();
		test003.work2();
		// test003.loadOperators();
		// test003.loadPageTables();

	}

	public void loadPageTables() {
		BufferedReader reader;
		try {
			int i = 0;
			reader = new BufferedReader(new FileReader("pageTable.txt"));
			String line = reader.readLine();
			int pageNumber;
			int state;
			int change;
			int blockNumber;
			int position;
			PageTable tempPage;
			// 逐行读取加载到内存的页表信息
			while (line != null) {

				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				pageNumber = Integer.parseInt(tokenizer.nextToken());
				state = Integer.parseInt(tokenizer.nextToken());
				if (state == 1) {
					blockNumber = Integer.parseInt(tokenizer.nextToken());
					change = Integer.parseInt(tokenizer.nextToken());
					position = Integer.parseInt(tokenizer.nextToken());
					// System.out.println(pageNumber+" "+state+" "+blockNumber+"
					// "+position);
					tempPage = new PageTable(pageNumber, state, blockNumber, change, position);
					P[i] = tempPage.getPageNumber();// 将页表加载到主存中
					i = (i + 1) % Max;
				} else {
					// blockNumber;
					change = Integer.parseInt(tokenizer.nextToken());
					position = Integer.parseInt(tokenizer.nextToken());
					// System.out.println(pageNumber+" "+state+" "+position);
					tempPage = new PageTable(pageNumber, state, change, position);
				}

				// PageTable tempPage=
				// new PageTable(Integer.parseInt(tokenizer.nextToken()),
				// Integer.parseInt(tokenizer.nextToken()),
				// Integer.parseInt(tokenizer.nextToken()),
				// Integer.parseInt(tokenizer.nextToken()));
				pageList.add(tempPage);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("主存中已加载的页表:");
		 for (PageTable tempPage : pageList) {
		 System.out.println(tempPage);
		 }
	}

	public void loadOperators() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("instrustions.txt"));
			String line = reader.readLine();

			// 逐行读取加载到内存的页表信息
			while (line != null) {

				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				Operator tempOperator = new Operator(Integer.parseInt(tokenizer.nextToken()),
						Integer.parseInt(tokenizer.nextToken()));
				operatorList.add(tempOperator);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("指令信息为:");
		// for (Operator tempOperator : operatorList) {
		// System.out.println(tempOperator);
		// }
	}

	public void work1() {
		loadOperators();
		loadPageTables();
		PageTable tempPage;
		for (Operator operator : operatorList) {
			tempPage = pageList.get(operator.getPageNumber());
			if (tempPage.getState() == 1)
				System.out.println("输出是第" + operator.getPageNumber() + "的绝对地址"
						+ (tempPage.getBlockNumber() * 128 + operator.getUnitNumber()));
			else
				System.out.println("*" + operator.getPageNumber());
		}

	}

	public void work2() {
		loadOperators();
		loadPageTables();
		PageTable tempPage;
		for (Operator operator : operatorList) {
			tempPage = pageList.get(operator.getPageNumber());
			if (tempPage.getState() == 1) {
				System.out.println("输出的是第" + operator.getPageNumber() + "的绝对地址"
						+ (tempPage.getBlockNumber() * 128 + operator.getUnitNumber()));
				tempPage.setIsChanged(1);
				pageList.set(tempPage.getPageNumber(), tempPage);// 修改页表信息
			} else// 缺页调度
			{
				PageTable replacedPage = pageList.get(P[index]);
				replacedPage.setState(0);
				pageList.set(P[index], replacedPage);
				if (replacedPage.getIsChanged() == 0) {// 被替换的页表没有做修改
					P[index] = tempPage.getPageNumber();// 直接替换页表信息
					index = (index + 1) % Max; // 指针下移
					System.out.println("装入" + tempPage.getPageNumber());
				} else {
					System.out.println("调出" + P[index]);

					System.out.println("装入" + tempPage.getPageNumber());
					P[index] = tempPage.getPageNumber();
					index = (index + 1) % Max;
				}
				tempPage.setState(1);
				tempPage.setBlockNumber(replacedPage.getBlockNumber());
				System.out.println("输出的是第" + operator.getPageNumber() + "的绝对地址"
						+ (tempPage.getBlockNumber() * 128 + operator.getUnitNumber()));
				
				pageList.set(tempPage.getPageNumber(), tempPage);// 标志需要调入的页表已经被调入

			}
			System.out.println("主存中已加载的页表:");
			for (int i = 0; i < P.length; i++) {
				System.out.print(P[i] + "   ");
			}
			System.out.println();
		}
		// System.out.println("程序结束以后数组P的信息为");
		// for (int i = 0; i < P.length; i++) {
		// System.out.print(P[i]+" ");
		// }
//		for (PageTable tempPage1 : pageList) {
//			System.out.println(tempPage1);
//		}
	}

}
