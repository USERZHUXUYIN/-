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
	static Map<String, Integer> stationflag=new HashMap<String, Integer>();//���վ���վ��ı��
	static Map<String, String> stationline=new HashMap<String,String>();//���վ��͵���������

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
			//��ȡ�ļ�
			String filePath="C:\\Users\\18418\\Desktop\\������·��Ϣ.txt";
			File file=new File(filePath);
			InputStreamReader read=new InputStreamReader(new FileInputStream(file),"UTF-8"); 
            BufferedReader br=new BufferedReader(read);
            String lineTxt=null;
		while ((lineTxt=br.readLine())!=null) {
			String na=null;
			String[] arr=lineTxt.split(" ");//ȡ�����Ŀո���ŵ�'arr'����
			//ȡ�����������ֲ���ɾ��
			String linename=arr[0];
			for(int i=0;i<arr.length-1;i++)
				arr[i]=arr[i+1];
			for (String namer : arr){						
				if (stationflag.keySet().contains(namer)) { //map�����ļ���set��ͼ���Ƿ�����Ѿ������˸�վ��
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
				else {//�����´���һ��վ����Ϣ
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
			System.out.println("�Ҳ���ָ�����ļ�");
		}
		
		for (i=0;i<n;i++)
			Map[n*i+i] = 0;
		String start,dest;
		method e=new method();
		Scanner scanf=new Scanner(System.in);
		
		System.out.print("���������վ��:");
		start = scanf.nextLine();
		while (!e.exist(name, start, n)) {
			System.out.println("վ���������");
			System.out.print("�������������վ��:");
			start = scanf.nextLine();
		}
		System.out.print("�������յ�վ��:");
		dest = scanf.nextLine();
		while (!e.exist(name, dest, n)) {
			System.out.println("վ���������");
			System.out.print("�����������յ�վ��:");
			dest = scanf.nextLine();
		}
		
		if(e.exist(name,dest,n)) {
			method f=new method();
			float distan=(float)f.Dijkstra(Ma,pa,n,f.exam(nam,start,n), f.exam(nam,dest,n));//���õϽ�˹�����㷨����˽�㵽���������·��
			int s, d;
			//���÷������
			s=f.exam(nam,start,n);
			d=f.exam(nam,dest,n);
			System.out.print("��ѳ˵���·�ߣ�");
			//���÷������
			f.print(pa,nam,s,d);
			System.out.print("����>"+dest);
		}
		return;
	}

}
