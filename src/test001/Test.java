package test001;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
	int timeSlide=3;//一个时间片的时间长度
	ArrayList<PCB> unFinishedQueue=new ArrayList<>();	//存储未完成的队列
	ArrayList<PCB> finishedQueue=new ArrayList<>();		//存储已完成的队列
	double waitTime=0;
	double totalWaitTime=0;
	public static void main(String[] args) {
		Test test=new Test();
		test.loadProcess();
//		System.out.println("请输入进程数");
//		int processNumber=(new Scanner(System.in)).nextInt();
		System.out.println("使用哪种调度算法?");
		System.out.println("0->轮转法   1->优先权法 2->FIFO法");
		int choose= (new Scanner(System.in)).nextInt();
			switch (choose) {
			case 0:
				test.RR(9);
				break;
			case 1:
				test.Priority(9);
				break;
			case 2:
				test.FIFO(9);
				break;
			}
		
		
	}
	public void FIFO(int processNumber) {
//		/*初始化进程*/
//		double tempTime=0;
//		int tempPriority=0;
//		int tempNeedSlide;
//		//int tempFinshSlide;
//		int timeSlide=3;//一个时间片的时间长度
//		for (int i = 0; i < processNumber; i++) //随机生成processNumber个进程
//		{
//			tempTime=(Math.random()*7)+1;//避免产生所需时间数为0的进程
//			tempPriority=(int)(Math.random()*10);
//			tempNeedSlide=(int) Math.ceil(tempTime/timeSlide);//所需时间片数（向上取整）
//			unFinishedQueue.add(new PCB("进程"+i,1,(int)tempTime,tempNeedSlide,0,tempPriority));
//		}
//		
//		System.out.println("初始各进程状态为:");
//		for (PCB pcb : unFinishedQueue) {
//			System.out.println(pcb);
//		}
		
		//进行进程调度
		int clock=1;//时钟
		PCB currentPCB;//当前正在运行的PCB
		PCB previewPCB=null;//前一个运行的PCB
		while(!unFinishedQueue.isEmpty())
		{
			currentPCB=unFinishedQueue.get(0);
			currentPCB.setNeedSlide(currentPCB.getNeedSlide()-1);
			currentPCB.setFinshSlide(currentPCB.getFinshSlide()+1);
//			currentPCB.setPriority(currentPCB.getPriority()-3);
			currentPCB.setState(3);
			if (!currentPCB.equals(previewPCB)) {
				currentPCB.setWaitTime(waitTime);
			}
			waitTime+=timeSlide;
			unFinishedQueue.set(0, currentPCB);
			previewPCB=currentPCB;
			System.out.println();
			System.out.println("第"+clock+"次运行,CPU时间片大小为"+timeSlide);
			System.out.println(currentPCB);
			//waitTime+=timeSlide;
			if (currentPCB.getNeedSlide()==0){
//				previewPCB=currentPCB;
				finishedQueue.add(currentPCB);
				unFinishedQueue.remove(currentPCB);
			}
//			else
//			{
//				unFinishedQueue.remove(maxPriorityPCB);
//				unFinishedQueue.add(maxPriorityPCB);
//			}
			clock++;
//			if (clock>50) {
//				break;
//			}
		}
		System.out.println("所有进程运行结束");
		System.out.println("进程名      等待时间");
		for (PCB pcb : finishedQueue) {
			System.out.println(pcb.getNo()+"    "+pcb.getWaitTime());
			totalWaitTime+=pcb.getWaitTime();
		}
		System.out.println("平均等待时间为"+(totalWaitTime/finishedQueue.size()));
	}
	public void Priority(int processNumber) {
//		/*初始化进程*/
//		double tempTime=0;
//		int tempPriority=0;
//		int tempNeedSlide;
//		//int tempFinshSlide;
//		int timeSlide=3;//一个时间片的时间长度
//		for (int i = 0; i < processNumber; i++) //随机生成processNumber个进程
//		{
//			tempTime=(Math.random()*7)+1;//避免产生所需时间数为0的进程
//			tempPriority=(int)(Math.random()*10);
//			tempNeedSlide=(int) Math.ceil(tempTime/timeSlide);//所需时间片数（向上取整）
//			unFinishedQueue.add(new PCB("进程"+i,1,(int)tempTime,tempNeedSlide,0,tempPriority));
//		}
//		
//		System.out.println("初始各进程状态为:");
//		for (PCB pcb : unFinishedQueue) {
//			System.out.println(pcb);
//		}
//		
		
		//进行进程调度
		int clock=1;//时钟
		PCB maxPriorityPCB;//当前正在运行的PCB
		//优先级的数据越少，优先级越高
		while(!unFinishedQueue.isEmpty())
		{
			maxPriorityPCB=unFinishedQueue.get(0);
			for (PCB pcb : unFinishedQueue) {
				if (maxPriorityPCB.getPriority()>pcb.getPriority()) {
					maxPriorityPCB=pcb;
				}
			}
			maxPriorityPCB.setNeedSlide(maxPriorityPCB.getNeedSlide()-1);
			maxPriorityPCB.setFinshSlide(maxPriorityPCB.getFinshSlide()+1);
			maxPriorityPCB.setPriority(maxPriorityPCB.getPriority()+3);
			maxPriorityPCB.setState(3);
			maxPriorityPCB.setWaitTime(waitTime);
			waitTime+=timeSlide;
			unFinishedQueue.set(unFinishedQueue.indexOf(maxPriorityPCB), maxPriorityPCB);
//			unFinishedQueue.set
//			System.out.println(currentPCB);
			System.out.println();
			System.out.println("第"+clock+"次运行,CPU时间片大小为"+timeSlide);
//			for (PCB pcb : unFinishedQueue) 
//			{
//				System.out.println(pcb);
//			}
			System.out.println(maxPriorityPCB);
			if (maxPriorityPCB.getNeedSlide()==0){
				finishedQueue.add(maxPriorityPCB);
				unFinishedQueue.remove(maxPriorityPCB);
			}
//			else
//			{
//				unFinishedQueue.remove(maxPriorityPCB);
//				unFinishedQueue.add(maxPriorityPCB);
//			}
			clock++;
//			if (clock>50) {
//				break;
//			}
		}
		System.out.println("所有进程运行结束");
//		System.out.println("进程名      等待时间");
//		for (PCB pcb : finishedQueue) {
//			System.out.println(pcb.getNo()+"    "+pcb.getWaitTime());
//			totalWaitTime+=pcb.getWaitTime();
//		}
//		System.out.println("平均等待时间为"+(totalWaitTime/finishedQueue.size()));
	}
	
	
	public void RR(int processNumber) {
//		double tempTime=0;
//		int tempPriority=0;
//		int tempNeedSlide;
//		//int tempFinshSlide;
//		
//		for (int i = 0; i < processNumber; i++) //随机生成processNumber个进程
//		{
//			tempTime=(Math.random()*7)+1;//避免产生所需时间数为0的进程
//			tempPriority=(int)(Math.random()*10);
//			tempNeedSlide=(int) Math.ceil(tempTime/timeSlide);//所需时间片数（向上取整）
//			unFinishedQueue.add(new PCB("进程"+i,1,(int)tempTime,tempNeedSlide,0,tempPriority));
//		}
//		
//		System.out.println("初始各进程状态为:");
//		for (PCB pcb : unFinishedQueue) {
//			System.out.println(pcb);
//		}
		ArrayList<PCB> prePCBList=unFinishedQueue;
		int totalWaitTimes[]=new int[9];
		int clock=1;//时钟
		int currentIndex=0;
		PCB currentPCB;//当前正在运行的PCB
		PCB previewPCB=null;//前一个正在运行的PCB
		while(!unFinishedQueue.isEmpty())
		{
			currentPCB=unFinishedQueue.get(0);
			currentPCB.setNeedSlide(currentPCB.getNeedSlide()-1);
			currentPCB.setFinshSlide(currentPCB.getFinshSlide()+1);
			if (!currentPCB.equals(previewPCB)) {
				currentPCB.setWaitTime(waitTime);
			}
			waitTime+=timeSlide;
			currentPCB.setState(3);
			unFinishedQueue.set(0, currentPCB);
			previewPCB=currentPCB;
//			System.out.println(currentPCB);
			System.out.println();
			System.out.println("第"+clock+"次运行,CPU时间片大小为"+timeSlide);
//			for (PCB pcb : unFinishedQueue) 
//			{
//				System.out.println(pcb);
//			}
			System.out.println(currentPCB);
			if (currentPCB.getNeedSlide()==0){
				finishedQueue.add(currentPCB);
				unFinishedQueue.remove(currentPCB);
			}
			else
			{
				unFinishedQueue.remove(currentPCB);
				unFinishedQueue.add(currentPCB);
			}
			clock++;
//			if (clock>50) {
//				break;
//			}
		}
		System.out.println("所有进程运行结束");
//		System.out.println("进程名      等待时间");
//		for (PCB pcb : finishedQueue) {
//			System.out.println(pcb.getNo()+"    "+pcb.getWaitTime());
//			totalWaitTime+=pcb.getWaitTime();
//		}
//		System.out.println("平均等待时间为"+(totalWaitTime/finishedQueue.size()));
	}
	
	public void loadProcess() {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("data.dat"));
//			int timeSlide=3;//一个时间片的时间长度
			String line = reader.readLine();
			
			//逐行读取产品信息
			while (line!=null) {
				
				StringTokenizer tokenizer=new StringTokenizer(line,",");
				//String string=tokenizer.nextToken();
				PCB tempPCB=
						new PCB(tokenizer.nextToken(),
								Integer.parseInt(tokenizer.nextToken()),
								Integer.parseInt(tokenizer.nextToken()),
								0,
								0,
								Integer.parseInt(tokenizer.nextToken()));
				tempPCB.setNeedSlide((int) Math.ceil(((double)tempPCB.getTime())/timeSlide));
				unFinishedQueue.add(tempPCB);
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
		System.out.println("初始各进程状态为:");
		for (PCB pcb : unFinishedQueue) {
			System.out.println(pcb);
		}
	}

}
