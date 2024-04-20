import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LogModification {

	public static void main(String[] args) throws IOException {
		int x = 2000000000;
		int y = 2000000000;
		long z = (long) x * y;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numTests = Integer.parseInt(in.readLine());
		if (numTests < 1 || numTests > 10)
			throw new IOException("Bad input file. First line of N has to be between 1 and 10 inclusive!");

		int totalN = 0;
		for (int i = 0; i < numTests; i++) {
			int numItems = Integer.parseInt(in.readLine());
			int[] itemInts = new int[numItems];
			totalN += numItems;
			StringTokenizer tokenizer = new StringTokenizer(in.readLine());
			int totalValue = 0;
			for (int j = 0; j < numItems; j++) {
				itemInts[j] = Integer.parseInt(tokenizer.nextToken());
				totalValue += itemInts[j];
			}
			
			if (numItems > 100000 || numItems < 1)
				throw new IOException(
						"Bad input file. The # of item in the list has to be same as what claimed AND between 1 to 100000! ");
			if (totalN > 100000)
				throw new IOException("Bad input file. The total N has to be within 100000! ");
			
			
			if (totalValue > 1000000) {
				throw new IOException(
						"Bad input file. The total number of times falling sleep need to be within 1000000, but we got "
								+ totalValue);
			}

			int minActions = Integer.MAX_VALUE;
			for (int j = 0; j < numItems - 1; j++) {
				int targetValue = 0;
				for (int k = 0; k <= j; k++) {
					targetValue += itemInts[k];
				}
				int currectAction = mergingItems(itemInts, targetValue, j + 1);
				if (currectAction < Integer.MAX_VALUE)
					currectAction = currectAction + j;

				if (currectAction < minActions)
					minActions = currectAction;
			}
			if (minActions == Integer.MAX_VALUE)
				minActions = numItems - 1;

			System.out.println(minActions);

		}
	}

	private static int mergingItems(int[] itemInts, int targetValue, int startIndex) {
		int temp = 0;
		int index = startIndex;
		int numOps = Integer.MAX_VALUE;
		int lastItemIndex = itemInts.length - 1;
		for (; index < itemInts.length; index++) {			
			if (temp != 0) {
				if (numOps == Integer.MAX_VALUE)
					numOps = 1;
				else
					numOps++;
			}
			temp += itemInts[index];
			if (temp == targetValue) {
				temp = 0;
				if (index == lastItemIndex && numOps == Integer.MAX_VALUE) {
					numOps = 0;
				}
			} else if (temp > targetValue) {
				numOps = Integer.MAX_VALUE;
				break;
			} else if (index == lastItemIndex)
				numOps = Integer.MAX_VALUE;
		}
		return numOps;
		

	}


}
