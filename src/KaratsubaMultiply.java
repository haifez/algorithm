
public class KaratsubaMultiply {

	private KaratsubaMultiply() {
	}

	// assuming x, y same length
	public static String multiply(String x, String y) {
		int lengthX = length(x);
		int lengthY = length(y);
		// base case
		if (lengthX == 1 && lengthY == 1) {
			return new Integer(times(x, y)).toString();
		}
		// get n and ab from x, cd from y
		int n = lengthX;
		String[] fromX = split(x, n / 2);
		String a = fromX[0];
		String b = fromX[1];
		String[] fromY = split(y, n / 2);
		String c = fromY[0];
		String d = fromY[1];
		String ac = multiply(a, c);
		String bd = multiply(b, d);
		String ad = multiply(a, d);
		String bc = multiply(b, c);
		// String aPlusBTimecPlusd = multiply(plus(a, b), plus(c, d));
		String ac10Ton = paddingZero(ac, n);
		String adbc10ToHalfn = paddingZero(plus(ad, bc), n / 2);
		String f = plus(ac10Ton, adbc10ToHalfn);
		String result = plus(f, bd);
		return result;

	}

	private static String paddingZero(String org, int numZero) {
		StringBuilder builder = new StringBuilder(org);
		for (int i = 0; i < numZero; i++)
			builder.append("0");
		return builder.toString();
	}

	public static int length(String s) {
		if (s == null)
			throw new IllegalArgumentException("String cannnot be null");
		return s.length();
	}

	private static String[] split(String s, int highPartLength) {
		String[] twoParts = new String[2];
		twoParts[0] = s.substring(0, highPartLength);
		twoParts[1] = s.substring(highPartLength);
		return twoParts;
	}

	private static String plus(String a, String b) {
		int aIndex = a.length() - 1;
		int bIndex = b.length() - 1;
		StringBuilder builder = new StringBuilder();
		int carry = 0;
		while (aIndex >= 0 && bIndex >= 0) {
			int v = realPlus("" + a.charAt(aIndex), "" + b.charAt(bIndex)) + carry;
			builder.append(v % 10);
			carry = v / 10;
			aIndex--;
			bIndex--;
		}

		if (aIndex < 0) {
			for (int i = bIndex; i >= 0; i--) {
				int v = Integer.parseInt("" + b.charAt(i)) + carry;
				builder.append(v % 10);
				carry = v / 10;
			}
		}
		if (bIndex < 0) {
			for (int i = aIndex; i >= 0; i--) {
				int v = Integer.parseInt("" + a.charAt(i)) + carry;
				builder.append(v % 10);
				carry = v / 10;
			}
		}
		if (carry > 0)
			builder.append(1);

		return builder.reverse().toString();

	}

	private static int times(String x, String y) {
		return Integer.parseInt(x) * Integer.parseInt(y);
	}

	private static int realPlus(String x, String y) {
		return Integer.parseInt(x) + Integer.parseInt(y);
	}

	private static void lsd() {
		int BITS_PER_BYTE = 8;
		int[] a = { 12345, 45, 678, 21, 45, 78 };
		final int BITS = 32; // each int is 32 bits
		final int R = 1 << BITS_PER_BYTE; // each bytes is between 0 and 255
		final int MASK = R - 1; // 0xFF
		final int w = BITS / BITS_PER_BYTE; // each int is 4 bytes

		int n = a.length;
		int[] aux = new int[n];

		for (int d = 0; d < w; d++) {

			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < n; i++) {
				int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
				count[c + 1]++;
			}

			// compute cumulates
			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];

			// for most significant byte, 0x80-0xFF comes before 0x00-0x7F => negative
			if (d == w - 1) {
				int shift1 = count[R] - count[R / 2];
				int shift2 = count[R / 2];
				for (int r = 0; r < R / 2; r++)
					count[r] += shift1;
				for (int r = R / 2; r < R; r++)
					count[r] -= shift2;
			} 

			// move data
			for (int i = 0; i < n; i++) {
				int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
				aux[count[c]++] = a[i];
			}

			// copy back
			for (int i = 0; i < n; i++)
				a[i] = aux[i];
		}

		System.out.println(a);
	}

	public static void main(String[] args) {
		KaratsubaMultiply.lsd();

		String x = "1234";
		String y = "5678";
		String r = KaratsubaMultiply.multiply(x, y);
		System.out.println(x + "*" + y + "=" + r); // 7006652

		String x2 = "3141592653589793238462643383279502884197169399375105820974944592";
		String y2 = "2718281828459045235360287471352662497757247093699959574966967627";
		String r2 = KaratsubaMultiply.multiply(x2, y2);
		System.out.println(x2 + "*" + y2 + "=" + r2); // 7006652

	}

}
