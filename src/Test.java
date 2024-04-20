import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numItems = Integer.parseInt(in.readLine());
		long ans =0;
		for (int j = 0; j < numItems; j++) {
			String[] pair = in.readLine().split(" ");
			int y = Integer.parseInt(pair[0]);
			int x = Integer.parseInt(pair[1]);

			if (y % 2 == 0) {
				// even y
				if (x <= y) {
					// within square
					ans = (long) y * y - (x - 1);
					System.out.println(ans);
				}
				else {
					if(x%2 ==1 ) {
						ans = (long) x * x - (y-1);
						System.out.println(ans);
					}
					else {
						ans = (long) (x -1) * (x-1) + y;
						System.out.println(ans);
					}
				}
			} else {
				// odd y
				if (x <= y) {
					// within square
					ans = (long) (y - 1) * (y - 1) + x;
					System.out.println(ans);
				}
				else {
					if(x%2 ==1 ) {
						ans = (long) x * x - (y-1);
						System.out.println(ans);
					}
					else {
						ans = (long) (x -1) * (x-1) + y;
						System.out.println(ans);
					}
				}
			}

		}

	}

}
