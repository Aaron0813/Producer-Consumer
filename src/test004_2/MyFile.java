package test004_2; 
/** 
* @author Sky
* @version 创建时间：2016年7月6日 下午7:40:33 
* 类说明 	模拟一个文件类，进行文件管理
*/
public class MyFile {
	String fileName;
	int length;
	String protectedCode;
	boolean opened;
	
	public MyFile() {
		
	}
	public MyFile(String fileName) {
		this.fileName=fileName;
		length=(int) (Math.random()*100);
		protectedCode="111";
		opened=false;
	}
	public String getfileName() {
		return fileName;
	}
	public void setfileName(String fileName) {
		this.fileName = fileName;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	public boolean isOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public String getProtectedCode() {
		return protectedCode;
	}
	public void setProtectedCode(String protectedCode) {
		this.protectedCode = protectedCode;
	}
	
	
	@Override
	public String toString() {
		return "MyFile [fileName=" + fileName + ", length=" + length + ", protectedCode=" + protectedCode + ", opened="
				+ opened + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyFile otherFile = (MyFile) obj;
		if (fileName == null) {
			if (otherFile.fileName != null)
				return false;
		} else if (!fileName.equals(otherFile.fileName))
			return false;
		return true;
	}
}
 