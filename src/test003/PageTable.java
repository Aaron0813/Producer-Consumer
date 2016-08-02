package test003; 
/** 
* @author Sky
* @version 创建时间：2016年6月28日 上午8:45:09 
* 类说明 
*/
public class PageTable {
	int pageNumber;
	int state;
	int blockNumber;
	int isChanged;
	int position;
	
	public PageTable(int pageNumber, int state, int blockNumber,int isChanged, int position) {
//		super();
		this.pageNumber = pageNumber;
		this.state = state;
		this.blockNumber = blockNumber;
		this.isChanged=isChanged;
		this.position = position;
	}
	public PageTable(int pageNumber, int state,int isChanged, int position) {
//		super();
		this.pageNumber = pageNumber;
		this.state = state;
		this.isChanged=isChanged;
		this.position = position;
	}
	
	public int getIsChanged() {
		return isChanged;
	}
	public void setIsChanged(int isChanged) {
		this.isChanged = isChanged;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "PageTable [pageNumber=" + pageNumber + ", state=" + state + ", blockNumber=" + blockNumber + ", isChanged="
				+ isChanged + ", position=" + position + "]";
	}
	
	
}
 