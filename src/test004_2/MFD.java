package test004_2;

import java.util.ArrayList;

/** 
* @author Sky
* @version 创建时间：2016年7月6日 下午8:58:33 
* 类说明 
*/
public class MFD {
	String filePath="/root";
	ArrayList<UFD> mfdlist=new ArrayList<>();

	public ArrayList<UFD> getMfdlist() {
		return mfdlist;
	}

	public void setMfdlist(ArrayList<UFD> mfdlist) {
		this.mfdlist = mfdlist;
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public UFD findByName(String name) {
		
		for (UFD ufd : mfdlist) {
			if (ufd.getUserName().equals(name)) {
				return ufd;
			}
		}
		return null;
		
	}
	
}
 