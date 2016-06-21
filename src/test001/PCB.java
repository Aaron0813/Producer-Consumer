package test001;

public class PCB {
	String No;//进程编号
	int state;//进程的状态
	int time;//所需时间
	double waitTime;//等待时间
	int needSlide;//所需时间片数
	int finshSlide;//已完成的时间片数
	int priority;//优先级
	
	
	public PCB(String no, int state, int time, int needSlide, int finshSlide, int priority) {
		super();
		No = no;
		this.state = state;
		this.time = time;
		this.needSlide = needSlide;
		this.finshSlide = finshSlide;
		this.priority = priority;
	}
	
	public PCB() {
		
	}
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getNeedSlide() {
		return needSlide;
	}
	public void setNeedSlide(int needSlide) {
		this.needSlide = needSlide;
	}
	public int getFinshSlide() {
		return finshSlide;
	}
	public void setFinshSlide(int finshSlide) {
		this.finshSlide = finshSlide;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}
	public double getWaitTime() {
		return waitTime;
	}
	@Override
	public String toString() {
		return "PCB [No=" + No + ", state=" + state + ", time=" + time + ", needSlide=" + needSlide + ", finshSlide="
				+ finshSlide + ", priority=" + priority + "]";
	}
	
}
