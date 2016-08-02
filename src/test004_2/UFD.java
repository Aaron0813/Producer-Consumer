package test004_2;

import java.util.ArrayList;

/**
 * @author Sky
 * @version 创建时间：2016年7月6日 下午8:39:22 类说明
 */
public class UFD {
	String userName;
	boolean hasOpen=false;
	int fileNumber = 0;
	ArrayList<MyFile> fileList;

	public UFD() {

	}

	public UFD(String userName) {
		this.userName = userName;
		fileList = new ArrayList<>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<MyFile> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<MyFile> fileList) {
		this.fileList = fileList;
	}
	

	public boolean isHasOpen() {
		return hasOpen;
	}

	public void setHasOpen(boolean hasOpen) {
		this.hasOpen = hasOpen;
	}

	public int getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public MyFile findByFileName(String fileName) {
		for (MyFile myFile : fileList)
			if (myFile.getfileName().equals(fileName))
				return myFile;
		return null;
	}

	@Override
	public String toString() {
		String details="";
		details+="UFD [userName=" + userName+"\n";
		for (MyFile myFile : fileList) 
			details+=myFile.toString()+"\n";
		return details;
	}

}
