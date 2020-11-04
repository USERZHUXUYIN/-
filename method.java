package subway;

public class method {
	static int Over = 404;
	//迪杰斯特拉算法实现
	public double Dijkstra(double Map[],int path[],int n,int m,int d) {
		int u,t;
		double min,result;
		double dist[]=new double[n+1];
		int s[]=new int[n+1];
		for(int i=0;i<n;i++){
			dist[i]=Map[n*m+i];
			s[i]=0;
			if(i!=m&&dist[i]<Over){
				path[i]=m;
			}
			else
				path[i]=-1;
		}
		s[m]=1;
		for(int i=0;i<n-1;i++){
			min=Over;
			u=m;
			for(int j=0;j<n;j++){
				if (s[j]==0&&dist[j]<min){
					u=j;
					min=dist[j];
				}
			}
			s[u]=1;
			for(t=0;t<n;t++){
				if (s[t]==0&&dist[u]+Map[n*u+t]<dist[t]) {
					dist[t]=(float)(dist[u]+Map[n*u+t]);
					path[t]=u;
				}
			}
		}
		result=dist[d];
		return result;
	}
	//判断站点是否存在
	public int exam(String name[],String nam,int n) {
		int i=0;
		while (i<n&&!name[i].equals(nam)) {
			i++;
		}
		return i;
	}
	//输出站点
	public void print(int pa[],String nam[],int d,int i)
	{
		if (pa[i]>=0) { 
			print(pa,nam,d,pa[i]);
			System.out.print("——>" +nam[pa[i]]); //输出的格式
		}
	}
	//判断站点是否存在
	public static boolean exist(String name[],String nam,int n) {
		int i=0;
		while (i<n) {
			if (name[i]!=null&&name[i].equals(nam)) { //判断站点存在其中，则返回true
				return true;
			}
			i++;
		}
		return false;
	}
}
