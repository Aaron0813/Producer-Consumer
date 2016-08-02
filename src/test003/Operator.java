package test003; 
/** 
* @author Sky
* @version 创建时间：2016年6月28日 上午9:01:46 
* 类说明 
*/
public class Operator {
	int pageNumber;
	int unitNumber;
	public Operator(int pageNumber, int unitNumber) {
		this.pageNumber=pageNumber;
		this.unitNumber=unitNumber;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}
	@Override
	public String toString() {
		return "Operator [pageNumber=" + pageNumber + ", unitNumber=" + unitNumber + "]";
	}
	
}
 