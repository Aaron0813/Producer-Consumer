//package test002;
//
//import java.util.concurrent.Semaphore;
//
///** 
//* @author Sky
//* @version 创建时间：2016年6月21日 上午9:44:10 
//* 类说明 
//*/
//public class ProducerConsumer {
//	//信号量
//	int mutex=1;
//	//存放数据的空间
//	int[] space=new int[6];
//	
//	
//	void Wait(int s){
//		while(s<=0)
//			;
//		s--;
//	}
//	void Signal(int s){
//		s++;
//	}
//
//}
//class Repertory{
//	Semaphore mutex=new Semaphore(1);//保证只会有一个进程在进行运行
//	int items[]=new int[10];
//	int count=0,front=0,rear=0;
//	void put(){
//		try {
//			if (condition) {
//				
//			}
//			mutex.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//}
//class Producer implements Runnable{
//	
//
//	@Override
//	public void run() {
//		
//		
//	}
//}
// 