package subway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {

	static int Over=404;
	static int i;
	static Map<String, Integer> stationflag=new HashMap<String, Integer>();//存放站点和站点的标记
	static Map<String, String> stationline=new HashMap<String,String>();//存放站点和地铁线名称

public static void main(String[] args) {
		int n=400;
		double Map[]=new double[n*n];
		double Ma[]=Map;
		for (int i=0;i<n*n;i++)
			Map[i] = Over;
		String name[]=new String[n];
		String nam[]=name;
		int path[]=new int[400];
		int pa[] = path;
		List list =Arrays.asList(name);
		int x=0;
		try {
			//读取文件
			String filePath="C:\\Users\\18418\\Desktop\\地铁线路信息.txt";
			File file=new File(filePath);
			InputStreamReader read=new InputStreamReader(new FileInputStream(file),"UTF-8"); 
            BufferedReader br=new BufferedReader(read);
            String lineTxt=null;
		while ((lineTxt=br.readLine())!=null) {
			String na=null;
			String[] arr=lineTxt.split(" ");//取出语句的空格后存放到'arr'数组
			//取出地铁线名字并且删除
			String linename=arr[0];
			for(int i=0;i<arr.length-1;i++)
				arr[i]=arr[i+1];
			for (String namer : arr){						
				if (stationflag.keySet().contains(namer)) { //map包含的键的set视图中是否包含已经包含了该站点
					int temp=0;
					for(int i=0;i<arr.length;i++) {
						if(namer.equals(arr[i])) 
							temp++;
						}
					if(temp==1){
						stationflag.put(namer,(stationflag.get(namer)+1));
						stationline.put(namer,stationline.get(namer)+","+linename);
					}
				}
				else {//否则新存入一个站点信息
					name[x]=namer;
					x++;
					stationflag.put(namer,1);
					stationline.put(namer,linename);
				}
				if(na!=null) {
					Map[n*list.indexOf(namer)+list.indexOf(na)]=2;
					Map[n*list.indexOf(na)+list.indexOf(namer)]=2;	
				}
				na=namer;
			}
		}
		br.close();
		}catch (Exception e) {
			System.out.println("找不到指定的文件");
		}
		
		for (i=0;i<n;i++)
			Map[n*i+i] = 0;
		String start,dest;
		method e=new method();
		Scanner scanf=new Scanner(System.in);
		
		System.out.print("请输入起点站名:");
		start = scanf.nextLine();
		while (!e.exist(name, start, n)) {
			System.out.println("站点输入错误！");
			System.out.print("请重新输入起点站名:");
			start = scanf.nextLine();
		}
		System.out.print("请输入终点站名:");
		dest = scanf.nextLine();
		while (!e.exist(name, dest, n)) {
			System.out.println("站点输入错误！");
			System.out.print("请重新输入终点站名:");
			dest = scanf.nextLine();
		}
		
		if(e.exist(name,dest,n)) {
			method f=new method();
			float distan=(float)f.Dijkstra(Ma,pa,n,f.exam(nam,start,n), f.exam(nam,dest,n));//调用迪杰斯特拉算法查出此结点到其他点最短路径
			int s, d;
			//调用方法检测
			s=f.exam(nam,start,n);
			d=f.exam(nam,dest,n);
			System.out.print("最佳乘地铁路线：");
			//调用方法输出
			f.print(pa,nam,s,d);
			System.out.print("——>"+dest);
		}
		return;
	}

}
