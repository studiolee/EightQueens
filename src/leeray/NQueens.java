package leeray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leeray
 * @version 创建时间：2019年4月2日 下午4:00:48 <br>
 * 			描述：问题拓展到N皇后问题<br>
 */
public class NQueens {
	List<List<String>> list = new ArrayList<List<String>>();

	/**
	 * 输入一个n 代表求n皇后，最后输出的是list<list<>>形式的数据
	 * @param n
	 * @return
	 */
	public List<List<String>> solveNQueens(int n) {
		Queen(0, n);
		return list;
	}

	void write(int[] arr, int n) {
		List<String> temp = new ArrayList<String>();
		for (int y = 0; y < n; y++) {
			if (y % n == 0)
				list.add(temp);
			String str = "";
			for (int x = 0; x < n; x++) {
				if (x != arr[y])
					str += ".";
				else
					str += "Q";
			}
			temp.add(str);
		}
	}
	
	//由于要使用递归求解，没有办法，只能将标志数组定义成静态的，大小也确定了
	//根据Y的大小，可以看出此程序最多支持334皇后问题，之歌问题已经很大了，如果有需求可以再加
	//依据的规则是Y{n} - A[3n-2]-B[3n-2]-C[3n-2]
	static int A[] = new int[1000];
	static int B[] = new int[1000];
	static int C[] = new int[1000];
	static int Y[] = new int[334];

	void Queen(int y, int n) {
		if (y == n) {
			write(Y, n);
			return;
		}
		for (int x = 0; x < n; x++) {
			if (A[x + n - 1] == 0 && B[x + y + n - 1] == 0 && C[x - y + n - 1] == 0) {
				Y[y] = x;// 占据(x,y)的位置
				A[x + n - 1] = 1;
				B[x + y + n - 1] = 1;
				C[x - y + n - 1] = 1;
				Queen(y + 1, n);
				A[x + n - 1] = 0;
				B[x + y + n - 1] = 0;
				C[x - y + n - 1] = 0;
			}
		}
	}
}
