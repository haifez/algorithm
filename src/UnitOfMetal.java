import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UnitOfMetal {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] a = readNumbers(N, in);
		int K = Integer.parseInt(in.readLine());
		Recipe[] recipes = readRecipe(K, N, in);
		Recipe NRecipe = recipes[N - 1];
		if (NRecipe == null) {
			System.out.println(a[N - 1]);
		} else {
			int[] NtransformFrom = NRecipe.transformFrom;
			while (canTransform(a, NtransformFrom)) {
				for (int j = 0; j < NtransformFrom.length; j++) {
					a[NtransformFrom[j]]--;
				}
				a[N - 1]++;
			}
			while (prepareTransform(a, NRecipe, recipes)) {
				
			}
			int ans = a[N - 1];
			System.out.println(ans);
		}
		

	}

	private static boolean prepareTransform(int[] a, Recipe recipe, Recipe[] recipes) {
		if (recipe == null)
			return false;
		int[] transformFrom = recipe.transformFrom;
		if (!canTransform(a, transformFrom)) {
			for (int j = 0; j < transformFrom.length; j++) {
				if (a[transformFrom[j]] == 0) {
					return prepareTransform(a, recipes[transformFrom[j]], recipes);
				}
				
			}
		}

		if (!canTransform(a, transformFrom))
			return false;
		for (int j = 0; j < transformFrom.length; j++) {
			a[transformFrom[j]]--;
		}
		a[recipe.tranformTo]++;
		return true;

	}

	private static boolean canTransform(int[] a, int[] transformFrom) {
		for (int j = 0; j < transformFrom.length; j++) {
			if (a[transformFrom[j]] == 0)
				return false;
		}
		return true;
	}

	private static int[] readNumbers(int numItems, BufferedReader in) throws IOException {
		int[] itemInts = new int[numItems];
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		for (int j = 0; j < numItems; j++) {
			itemInts[j] = Integer.parseInt(tokenizer.nextToken());

		}
		return itemInts;
	}

	private static int[] readZeroBasedNumbers(int numItems, StringTokenizer tokenizer) throws IOException {
		int[] itemInts = new int[numItems];
		for (int j = 0; j < numItems; j++) {
			itemInts[j] = Integer.parseInt(tokenizer.nextToken()) - 1;

		}
		return itemInts;
	}

	private static Recipe[] readRecipe(int K, int N, BufferedReader in) throws IOException {
		Recipe[] recipes = new Recipe[N];
		for (int i = 0; i < K; i++) {
			StringTokenizer tokenizer = new StringTokenizer(in.readLine());
			int tranformTo = Integer.parseInt(tokenizer.nextToken()) - 1;
			int numTranformFrom = Integer.parseInt(tokenizer.nextToken());
			int[] transformFrom = readZeroBasedNumbers(numTranformFrom, tokenizer);
			Recipe r = new Recipe(tranformTo, transformFrom);
			recipes[tranformTo] = r;
		}
		return recipes;
	}

	static class Recipe {
		int tranformTo;
		int[] transformFrom;

		public Recipe(int tranformTo, int[] transformFrom) {
			this.tranformTo = tranformTo;
			this.transformFrom = transformFrom;
		}

	}

}
