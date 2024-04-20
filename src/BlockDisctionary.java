import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BlockDisctionary {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numWords = Integer.parseInt(in.readLine());
		if (numWords < 1 || numWords > 10)
			throw new IOException("Bad input file. First line of N has to be between 1 and 10 inclusive!");
		// Read letters on each block, create set to hold data for each block for
		// performance
		List<List<Character>> blockLetters = new ArrayList<List<Character>>();
		for (int i = 1; i < 5; i++) {
			Set<Character> block = new HashSet<Character>();
			String theLine = in.readLine();
			String theLineUpper = theLine.toUpperCase();
			if (!theLine.equals(theLineUpper)) {
				throw new IOException("Bad input file. block letters need to be upper case, but we got " + theLine);
			}
			char[] chars = theLine.toCharArray();
			if (chars.length != 6)
				throw new IOException("Bad input file. Each block needs to be 6 chars!");
			for (char c : chars) {
				block.add(c);
			}
			blockLetters.add(new ArrayList<Character>(block));
		}
		// Build the word dictionary
		List<String> allAnswers = new ArrayList<>();
		int block1NumLetters = blockLetters.get(0).size();
		int block2NumLetters = blockLetters.get(1).size();
		int block3NumLetters = blockLetters.get(2).size();
		int block4NumLetters = blockLetters.get(3).size();

		for (int a = 0; a <= block1NumLetters; a++) {
			char fromBlock1 = charUsed(blockLetters.get(0), a, block1NumLetters);

			for (int b = 0; b <= block2NumLetters; b++) {
				char fromBlock2 = charUsed(blockLetters.get(1), b, block2NumLetters);

				for (int c = 0; c <= block3NumLetters; c++) {
					char fromBlock3 = charUsed(blockLetters.get(2), c, block3NumLetters);

					for (int d = 0; d <= block4NumLetters; d++) {
						char fromBlock4 = charUsed(blockLetters.get(3), d, block4NumLetters);
						String letters = ("" + fromBlock1 + fromBlock2 + fromBlock3 + fromBlock4).replace(" ", "");
						ceatePermutation(letters, "", allAnswers);

					}
				}
			}

		}
		// remove empty string from answer
		allAnswers.remove("");

		for (int j = 0; j < numWords; j++) {
			String test = in.readLine();
			int testSz = test.length();
			String theLineUpper = test.toUpperCase();
			if (!test.equals(theLineUpper) || (testSz < 1 || testSz > 4)) {
				throw new IOException(
						"Bad input file. test letters need to be upper case and its length is between 1 and 4 inclusdive, but we got "
								+ test);
			}

			boolean contaion = allAnswers.contains(test);
			if (contaion)
				System.out.println("YES");
			else
				System.out.println("NO");
		}

	}

	private static Character charUsed(List<Character> blockLetter, int index, int blockSz) {
		char r = ' ';
		if (index < blockSz)
			r = blockLetter.get(index);
		return r;

	}

	private static void ceatePermutation(String str, String ans, List<String> allPermutations) {

		// If string is empty
		if (str.length() == 0) {
			allPermutations.add(ans);
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			String ros = str.substring(0, i) + str.substring(i + 1);
			ceatePermutation(ros, ans + ch, allPermutations);
		}
	}
}
