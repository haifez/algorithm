import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CowLineUp {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		String allGHs = in.readLine();
		ArrayList<Pair> allItems = new ArrayList<Pair>();
		for (int i = 0; i < N; i = i + 2) {
			allItems.add(new Pair(allGHs.charAt(i), allGHs.charAt(i + 1)));
		}

		// first pair
		ItemsSofar leftMostGSofar = new ItemsSofar();
		ItemsSofar rightMostGSofar = new ItemsSofar();
		Pair firstPair = allItems.get(0);
		if(firstPair.leftIsG && firstPair.rightIsG) {
			leftMostGSofar.addPair(firstPair);
			rightMostGSofar.addPair(firstPair);
		}
		else if(firstPair.leftIsG) {
			leftMostGSofar.addPair(firstPair);
			rightMostGSofar.addPair(firstPair.reverse());
			rightMostGSofar.numberReverse ++;
		}
		else if(firstPair.rightIsG) {
			rightMostGSofar.addPair(firstPair);
			leftMostGSofar.addPair(firstPair.reverse());
			leftMostGSofar.numberReverse ++;
		}
		else {
			leftMostGSofar.addPair(firstPair);
			rightMostGSofar.addPair(firstPair);
		}		
		
		int numPair = allItems.size();

		for (int i = 1; i < numPair - 1; i++) {
			Pair pair = allItems.get(i);
			ItemsSofar list1 = leftMostGSofar.addPairAndCreatNew(pair);
			ItemsSofar list2 = rightMostGSofar.addPairAndCreatNew(pair);
			ItemsSofar list3 = leftMostGSofar.addPairReverseAndCreatNew(pair);
			ItemsSofar list4 = rightMostGSofar.addPairReverseAndCreatNew(pair);
			leftMostGSofar = getMaxLeftG(list1, list2, list3, list4);
			rightMostGSofar = getMaxRightG(list1, list2, list3, list4);
			list1 = null;
			list2 = null;
			list3 = null;
			list4 = null;

		}
		// last pair
		int numberBasedOnLeft = leftMostGSofar.numberLeftG;
		int numberBasedOnRight = rightMostGSofar.numberRightG;
		Pair lastPair = allItems.get(numPair - 1);
		if (lastPair.rightIsG) {
			numberBasedOnRight++;
		}
		if (lastPair.leftIsG) {
			numberBasedOnLeft++;
			leftMostGSofar.numberReverse++;
		} else {
			leftMostGSofar.numberReverse++;
		}

		// print result
		if (numberBasedOnLeft == numberBasedOnRight) {
			int minReverse = Math.min(leftMostGSofar.numberReverse, rightMostGSofar.numberReverse);
			System.out.println(minReverse);
		} else if (numberBasedOnLeft > numberBasedOnRight) {
			System.out.println(leftMostGSofar.numberReverse);
		} else
			System.out.println(rightMostGSofar.numberReverse);

	}

	static ItemsSofar getMaxLeftG(ItemsSofar list1, ItemsSofar list2, ItemsSofar list3, ItemsSofar list4) {
		ItemsSofar maxSofar = list1;
		if (maxSofar.numberLeftG < list2.numberLeftG) {
			maxSofar = list2;
		}
		if (maxSofar.numberLeftG < list3.numberLeftG) {
			maxSofar = list3;
		}
		if (maxSofar.numberLeftG < list4.numberLeftG) {
			maxSofar = list4;
		}
		return maxSofar;
	}

	static ItemsSofar getMaxRightG(ItemsSofar list1, ItemsSofar list2, ItemsSofar list3, ItemsSofar list4) {
		ItemsSofar maxSofar = list1;
		if (maxSofar.numberRightG < list2.numberRightG) {
			maxSofar = list2;
		}
		if (maxSofar.numberRightG < list3.numberRightG) {
			maxSofar = list3;
		}
		if (maxSofar.numberRightG < list4.numberRightG) {
			maxSofar = list4;
		}
		return maxSofar;
	}

	static class ItemsSofar {
		ArrayList<Pair> items = new ArrayList<Pair>();
		int numberReverse = 0;
		int numberLeftG = 0;
		int numberRightG = 0;

		public ItemsSofar() {

		}

		public ItemsSofar(ArrayList<Pair> items, int numberReverse) {
			this.items = items;
			this.numberReverse = numberReverse;
			int[] r = numberLeftGandRightG(items);
			this.numberLeftG = r[0];
			this.numberRightG = r[1];
		}

		private int[] numberLeftGandRightG(ArrayList<Pair> items) {
			int[] r = new int[2];
			for (Pair pair : items) {
				if (pair.rightIsG)
					r[1]++;
				if (pair.leftIsG)
					r[0]++;
			}
			return r;

		}

		void addPair(Pair pair) {
			items.add(pair);
			if (pair.rightIsG)
				numberRightG++;
			if (pair.leftIsG)
				numberLeftG++;
		}

		ItemsSofar addPairAndCreatNew(Pair pair) {
			ArrayList<Pair> newItems = new ArrayList<Pair>();
			newItems.addAll(items);
			newItems.add(pair);
			return new ItemsSofar(newItems, numberReverse);
		}

		ItemsSofar addPairReverseAndCreatNew(Pair pair) {
			ArrayList<Pair> newItems = new ArrayList<Pair>();
			newItems.add(pair.reverse());
			int num = items.size();
			for (int i = num - 1; i >= 0; i--) {
				newItems.add(items.get(i).reverse());
			}

			return new ItemsSofar(newItems, numberReverse + 1);
		}

	}

	static class Pair {
		char left;
		char right;
		boolean leftIsG;
		boolean rightIsG;

		public Pair(char left, char right) {
			this.left = left;
			leftIsG = left == 'G';
			this.right = right;
			rightIsG = right == 'G';
		}

		Pair reverse() {
			return new Pair(right, left);

		}

	}

}
