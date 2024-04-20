import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class LyingCow {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		Item[] allItems = new Item[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(in.readLine());
			boolean greater = tokenizer.nextToken().trim().equalsIgnoreCase("G");
			int p = Integer.parseInt(tokenizer.nextToken());
			allItems[i] = new Item(p, greater);
		}

		Arrays.sort(allItems, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				if(o1.num == o2.num && o1.greater != o2.greater) {
					if(o1.greater) return -1;
					else return 1;
				}
				return o1.num - o2.num;
			}
		});

		if (N == 1) {
			System.out.println(0);
		} else {
			int numGreaterSoFar = 0;
			int numLessSoFar = 0;
			int maxHonestCowsSofr = Integer.MIN_VALUE;
			int maxHonestCows = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				if(!allItems[i].greater) numLessSoFar ++;
			}

			for (int i = 0; i < N; i++) {
				Item currentItem = allItems[i];
				int carry  = 0;
				if(currentItem.greater) carry = 1;
				maxHonestCowsSofr = numLessSoFar + numGreaterSoFar + carry;
				if (maxHonestCowsSofr > maxHonestCows)
					maxHonestCows = maxHonestCowsSofr;

				if (currentItem.greater) {
					numGreaterSoFar++;
				} else {
					numLessSoFar--;
				}

			}
			int lier = N - maxHonestCows;
			System.out.println(lier);
		}

	}

	static class Item {
		int num;
		boolean greater;

		public Item(int num, boolean greater) {
			this.num = num;
			this.greater = greater;
		}

	}

}
