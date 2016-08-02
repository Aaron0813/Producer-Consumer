package test004_2;

/**
 * @author Sky
 * @version 创建时间：2016年7月6日 下午7:53:38 类说明 对文件类进行相应操作的类
 */
public class MyFileHandler {
	// 用户创建文件
	public static Boolean create(UFD user, String filename) {
//		if (user.fileNumber < 5) {
			user.fileList.add(new MyFile(filename));
			user.fileNumber++;
			System.out.println(filename+"文件创建成功");
			return true;
//		} else{
//			System.out.println("所创建文件数量已达上限");
//			return false;
//		}
	}
	// 用户创建文件
		public static Boolean create(UFD user, MyFile file) {
			if (user.fileNumber < 5) {
				user.fileList.add(file);
				System.out.println(file.getfileName()+"文件创建成功");
				user.fileNumber++;
				return true;
			} else{
				System.out.println("所创建文件数量已达上限");
				return false;
			}
		}

	// 用户删除文件
	public static void deleteFile(UFD ufd, String fileName) {
		boolean flag = false;
		for (MyFile myFile : ufd.fileList) {
			if (myFile.getfileName().equals(fileName)) {
				ufd.fileList.remove(myFile);
				flag = true;
				ufd.fileNumber--;
				break;
			}
		}
		if (flag)
			System.out.println("文件删除完毕");
		else
			System.out.println("文件不存在");

	}

	// 用户打开一个文件
	public static boolean openFile(UFD ufd, String fileName) {
		for (MyFile myFile : ufd.fileList) {
			if (myFile.getfileName().equals(fileName)) {
				if (!myFile.isOpened()) {
					myFile.setOpened(true);
					ufd.fileList.remove(ufd.fileList.indexOf(myFile));
					ufd.fileList.add(myFile);
					System.out.println("文件成功打开");
					return true;
				}else{
					System.out.println("文件没有被打开过");
					return false;
				}
			}
		}
			System.out.println("文件不存在");
			return false;
	}

	// 用户关闭一个文件
	public static boolean closeFile(UFD ufd, String fileName) {
		
		for (MyFile myFile : ufd.fileList) {
			if (myFile.getfileName().equals(fileName)) {
				if (myFile.isOpened()) {
					myFile.setOpened(false);
					ufd.fileList.remove(ufd.fileList.indexOf(myFile));
					ufd.fileList.add(myFile);
					System.out.println(myFile.getfileName()+"成功关闭");
					return true;
				}else{
					System.out.println("文件没有被打开过");
					return false;
				}
			}
		}
			System.out.println("文件不存在");
			return false;
	}

	// 用户读取一个文件
	public static boolean readFile(UFD ufd, String fileName) {
		boolean flag = false;
		for (MyFile myFile : ufd.fileList) {
			if (myFile.getfileName().equals(fileName)) {
				byte[] protectedCode = (myFile.getProtectedCode()).getBytes();
				// preProtectedCode[1] = '1';
				// String proCode = preProtectedCode.toString();
				// myFile.setProtectedCode(proCode);
				// ufd.fileList.remove(ufd.fileList.indexOf(myFile));
				// ufd.fileList.add(myFile);
				if (protectedCode[0] == '1') {// 文件可以被读取
					flag = true;
					System.out.println("文件读取成功");
					break;
				} 
				else {
					System.out.println("文件不允许读取");
					break;
				}

			}
		}
		return flag;
//		if (flag)
//			System.out.println("文件成功读取");
//		else
//			System.out.println("文件读取失败");
	}

	// 用户对一个文件进行写入
	public static boolean writeFile(UFD ufd, String fileName) {
		boolean flag = false;
		for (MyFile myFile : ufd.fileList) {
			if (myFile.getfileName().equals(fileName)) {
				byte[] protectedCode = (myFile.getProtectedCode()).getBytes();
				if (protectedCode[1] == '1') {// 文件可以被写入
					flag = true;
					break;
				} 
					
				else {
					System.out.println("文件不允许读取");
					break;
				}
			}
		}
		return flag;
//		if (flag)
//			System.out.println("文件成功写入");
//		else
//			System.out.println("文件写入失败");
	}

	// 显示用户所有的文件信息
	public static void dir(UFD ufd,String filePath) {
		String fileDeatils="";
		int number=0;
		for (MyFile myFile : ufd.fileList) {
			fileDeatils+="文件名:"+myFile.getfileName()+"\n"
					+"文件长度:"+myFile.getLength()+"\n"
					+"文件保护码:"+myFile.getProtectedCode()+"\n"
					+"文件路径:"+filePath+"/"+ufd.getUserName()+"/"+myFile.getfileName()+"\n\n";
			number++;
		}
		System.out.println(fileDeatils);
		System.out.println("total:"+number);
	}

}
