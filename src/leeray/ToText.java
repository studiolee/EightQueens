package leeray;

import java.io.File;
import java.io.FileWriter;

/**
 * @author leeray
 * @version 创建时间：2019年3月29日 下午11:47:16 <br>
 *          描述：<br>
 */
public class ToText {
	public static void main(String[] args) {
		Queen(0);
	}

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
				C[x - y + 7] = 1;// 当前皇后占用的路线
				Queen(y + 1);// 递归调用，向下一行查找合适的皇后的位置
				A[x + 7] = 0;
				B[x + y + 7] = 0;
				C[x - y + 7] = 0;// 还原，以便下一次递归使用
			}
		}
	}

	static int count = 0;// 计数

	static void Display(int[] arr, int n) {
		try {
			File file = new File("Queens.txt");

			if (!file.exists())// 如果文件不存在，那么就创建它
				file.createNewFile();

			FileWriter fileWritter = new FileWriter(file.getName(), true);

			for (int y = 0; y < n; y++) {
				if (y % 8 == 0)
					fileWritter.write(++count+"\r\n");
				for (int x = 0; x < 8; x++) {
					if (x != arr[y])
						fileWritter.write("#");
					else
						fileWritter.write("@");
				}
				fileWritter.write("\r\n");
			}
			fileWritter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
