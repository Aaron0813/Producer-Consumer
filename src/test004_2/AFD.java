package test004_2; 
/** 
* @author Sky
* @version 创建时间：2016年7月6日 下午10:47:22 
* 类说明 
*/
public class AFD {
	String fileName;//打开文件的名字
	String protectedCode;//文件保护码
	int read_write_index;//读写指针
	
	public AFD(String fileName, String protectedCode, int read_write_index) {
		this.fileName = fileName;
		this.protectedCode = protectedCode;
		this.read_write_index = read_write_index;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getProtectedCode() {
		return protectedCode;
	}
	public void setProtectedCode(String protectedCode) {
		this.protectedCode = protectedCode;
	}
	public int getRead_write_index() {
		return read_write_index;
	}
	public void setRead_write_index(int read_write_index) {
		this.read_write_index = read_write_index;
	}
	@Override
	public String toString() {
		return "AFD [fileName=" + fileName + ", protectedCode=" + protectedCode + ", read_write_index="
				+ read_write_index + "]";
	}
	
}
 