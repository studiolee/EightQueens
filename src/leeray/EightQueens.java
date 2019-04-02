package leeray;

/**
 * @author leeray
 * @version 创建时间：2019年3月29日 下午7:43:49 <br>
 *          描述：八皇后问题<br>
 *          在8X8的棋盘上，摆放8个皇后，是指不能相互攻击，及任意两个皇后不能处于同一行，同一列，或同一对角线上，一种有多少种可能？<br>
 *          当一个皇后占据一个位置(x,y),在其水平方向（直线方程 y = C），垂直方向(x = C) 两个对角线方向(x + y = C ,
 *          x - y = C)上都不能放其他皇后<br>
 *          如果每行只放一个皇后，那么在水平方向上，永远不会冲突，因此只考虑记录其他三个方向上的占用情况<br>
 *          <br>
 */
public class EightQueens {
	public static void main(String[] args) {
		Queen(0);
	}

	/**
	 * 用三个一维数组A，B，C记录棋盘的占用情况，称为状态数组，状态数组的初始值为0，表示棋盘上还没有皇后<br>
	 * 如果有一个皇后占据了某一个位置(x,y),令A[x] = B[x + y] = C[x - y] =
	 * 1,那么下一个皇后(p,q)能够占据某一个位置的条件就是；<br>
	 * A[p] = B[p + q] = C[p - q] = 0;<br>
	 * 现在考虑数组下标的问题，棋盘大小8 X 8, x,y取值0 ~ 7, 数组大小应能够完全覆盖x+y,x-y,x的值<br>
	 * x + y 的范围:(0~14),x - y 的范围:(-7 ~ 7)，综合来看，范围-7 ~ 14，数组大小应为22<br>
	 * 那么现在A[x], B[x + y], C[x - y]分别等价于A[x+7], B[x + y + 7], C[x - y + 7]<br>
	 * 用数组Y[y] = x来表示皇后占据了(x,y)的位置
	 * 
	 * @param y
	 */
	static int A[] = new int[22];
	static int B[] = new int[22];
	static int C[] = new int[22];
	static int Y[] = new int[8];

	static void Queen(int y) {

		if (y < 0 || y > 8) {
			System.out.println("y is illeagal!");
			return;
		}
		if (y == 8) {
			Display(Y, 8);
			return;
		}
		for (int x = 0; x < 8; x++) {
			if (A[x + 7] == 0 && B[x + y + 7] == 0 && C[x - y + 7] == 0) {
				Y[y] = x;// 条件符合，占据(x,y)位置。
				A[x + 7] = 1;
				B[x + y + 7] = 1;
				C[x - y + 7] = 1;//当前皇后占用的路线
				Queen(y + 1);//递归调用，向下一行查找合适的皇后的位置
				A[x + 7] = 0;
				B[x + y + 7] = 0;
				C[x - y + 7] = 0;// 还原，以便下一次递归使用
			}
		}
	}

	static int count = 0;//计数

	static void Display(int[] arr, int n) {
		for (int y = 0; y < n; y++) {
			if (y%8 == 0)
				System.out.println(++count);
			for (int x = 0; x < 8; x++) {
				if (x != arr[y])
					System.out.print("#");
				else
					System.out.print("@");
			}
			System.out.println();
		}
	}
}
