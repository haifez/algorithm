import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LineUpCows {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numCows = Integer.parseInt(in.readLine());
		if (numCows < 1 || numCows > 100000)
			throw new IOException("Bad input! # of cowss have to be tetween 1 and 100000!");
		char[] input = in.readLine().replace(" ", "").toCharArray();
		char[] target = in.readLine().replace(" ", "").toCharArray();
		int inputLen = input.length;
		int targetLen = target.length;
		if (inputLen != targetLen || targetLen != numCows) {
			throw new IOException(
					"Bad input! input and target need to have same # of item as " + numCows + " but they are not!");
		}
		
		int numActions = numberOfActions(input, target, 0);
		System.out.println(numActions);

	}

	private static int numberOfActions(char[] input, char[] target, int startIndex) {		
		// skip the first items which are the same
		int maxSz = target.length;
		while (startIndex < maxSz && input[startIndex] == target[startIndex]) {
			startIndex++;
		}
		if(startIndex >= maxSz) return 0;
		// find target[index] from input array and bubble it up to input[index]
		int nextIndexToMove = startIndex + 1;
		char tobeBubbledItem = target[startIndex];
		for (; nextIndexToMove < input.length; nextIndexToMove++) {
			if (input[nextIndexToMove] == tobeBubbledItem)
				break;
		}
		for (int i = nextIndexToMove; i > startIndex; i--) {
			input[i] = input[i - 1];
		}
		input[startIndex] = tobeBubbledItem;
		return 1 + numberOfActions(input, target, startIndex + 1);

	}

}
