package test004_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Sky
 * @version 创建时间：2016年7月6日 下午8:55:37 类说明
 */
public class Test {
	MFD myMFD = new MFD();
	ArrayList<AFD> afdList = new ArrayList<>();

	public static void main(String[] args) {
		Test test004 = new Test();
		test004.init();
	}

	void init() {
		try {
			File df = new File("FileManagerDatas.txt");
			FileReader dread = new FileReader(df);
			BufferedReader bufr = new BufferedReader(dread);
			String bufc = bufr.readLine();
			System.out.println("信息初始化");
			while (bufc != null) {
				String[] cons = bufc.split(" ");
				String username = cons[0];
				String filename = cons[1];
				int length = Integer.parseInt(cons[2]);
				String procode = cons[3];

				MyFile file = new MyFile();
				file.setfileName(filename);
				file.setProtectedCode(procode);
				file.setLength(length);
				file.setOpened(false);

				UFD ufd = myMFD.findByName(username);
				if (ufd == null) {
					ufd = new UFD(username);
					MyFileHandler.create(ufd, file);
					myMFD.mfdlist.add(ufd);
				} else {
					MyFileHandler.create(ufd, file);
				}
				bufc = bufr.readLine();
			}
			bufr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n");
		run();
	}

	void run() {
		boolean flag = true;// 标志用户是否结束当前操作
		System.out.println("请输入用户名");
		Scanner s = new Scanner(System.in);
		String userName = s.nextLine();

		String command;
		for (UFD ufd : myMFD.mfdlist) {

			if (userName.equals(ufd.getUserName())) {// 存在該用戶
				ArrayList<MyFile> tempFileList = ufd.getFileList();
				if (!tempFileList.isEmpty()) {
					System.out.println(ufd.getUserName() + "所拥有的所有文件为：");
					for (MyFile file : tempFileList)
						System.out.println(file.getfileName());// 显示用户的所有文件
				}

				System.out.println("初始化AFD");
				while (flag) {
					System.out.println("请输入命令:");
					System.out.println("1  Create");
					System.out.println("2  Delete");
					System.out.println("3  Open");
					System.out.println("4  Close");
					System.out.println("5  Read");
					System.out.println("6  Write");
					System.out.println("7  Dir");
					System.out.println("8  Exit");
					command = s.nextLine();
					switch (command) {
					case "Create":
						create(ufd);
						break;
					case "Delete":
						delete(ufd);
						break;
					case "Open":
						open(ufd);
						break;
					case "Close":
						close(ufd);
						break;
					case "Read":
						read(ufd);
						break;
					case "Write":
						write(ufd);
						break;
					case "Dir":
						dir(ufd);
						break;
					case "Exit":
						exit(ufd);
						break;

					default:
						break;
					}
				}
			}
		}
		System.out.println("該用戶不存在");
	}

	void create(UFD ufd) {
		if (ufd.fileNumber < 5) {
			System.out.println("请输入要创建的文件名");
			Scanner s = new Scanner(System.in);
			String fileName = s.nextLine();
			if (MyFileHandler.create(ufd, fileName)) {
				MyFile tempFile = ufd.findByFileName(fileName);
				afdList.add(new AFD(fileName, tempFile.getProtectedCode(), 0));
			}
		} else
			System.out.println("所创建文件数量已达上限");

	}

	void delete(UFD ufd) {
		System.out.println("请输入要删除的文件名");
		Scanner s = new Scanner(System.in);
		String fileName = s.nextLine();
		MyFileHandler.deleteFile(ufd, fileName);
		afdList.remove(find_AFD_ByFileName(fileName));
	}

	void open(UFD ufd) {// open是干啥的啊？
		if (!ufd.isHasOpen()) {
			System.out.println("请输入要打开的文件名");
			Scanner s = new Scanner(System.in);
			String fileName = s.nextLine();

			if (MyFileHandler.openFile(ufd, fileName)) {
				MyFile tempFile = ufd.findByFileName(fileName);
				afdList.add(new AFD(fileName, tempFile.getProtectedCode(), 0));
				ufd.setHasOpen(true);
			}

		} else {
			System.out.println("已经打开了一个文件，请关闭后再进行操作");
		}

	}

	void close(UFD ufd) {
		if (ufd.isHasOpen()) {
			System.out.println("请输入要关闭的文件名");
			Scanner s = new Scanner(System.in);
			String fileName = s.nextLine();
			if (MyFileHandler.closeFile(ufd, fileName)) {
				afdList.remove(find_AFD_ByFileName(fileName));
				ufd.setHasOpen(false);
			}
		} else {
			System.out.println("当前没有打开任何文件，请打开文件后再进行操作");
		}

	}

	void read(UFD ufd) {
		System.out.println("请输入要读取的文件名");
		Scanner s = new Scanner(System.in);
		String fileName = s.nextLine();
		if (MyFileHandler.readFile(ufd, fileName)) {
			AFD tempAFD = find_AFD_ByFileName(fileName);
			afdList.remove(afdList.indexOf(tempAFD));
			tempAFD.setRead_write_index((int) (Math.random() * 100));
			afdList.add(tempAFD);
			// System.out.println("文件读取成功");
		} else {
			System.out.println("文件读取失败");
		}

	}

	void write(UFD ufd) {
		System.out.println("请输入要读取的文件名");
		Scanner s = new Scanner(System.in);
		String fileName = s.nextLine();
		if (MyFileHandler.writeFile(ufd, fileName)) {
			AFD tempAFD = find_AFD_ByFileName(fileName);
			afdList.remove(afdList.indexOf(tempAFD));
			tempAFD.setRead_write_index((int) (Math.random() * 100));
			afdList.add(tempAFD);
		} else {
			System.out.println("文件读取失败");
		}

	}

	void dir(UFD ufd) {
		MyFileHandler.dir(ufd, myMFD.getFilePath());
	}

	void exit(UFD ufd) {
		System.out.println("操作完成后MFD的信息为:");
		for (UFD afd : myMFD.mfdlist) {
			System.out.println(afd);
		}
		System.out.println();

		System.out.println("当前用户的UFD为:");
		System.out.println(ufd);
		System.out.println();

		System.out.println("当前用户的AFD列表为:");
		for (AFD afd : afdList) {
			System.out.println(afd);
		}
		System.exit(0);
	}

	AFD find_AFD_ByFileName(String fileName) {
		for (AFD afd : afdList)
			if (afd.getFileName().equals(fileName))
				return afd;
		return null;
	}
}
