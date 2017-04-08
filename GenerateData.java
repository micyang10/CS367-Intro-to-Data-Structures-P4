import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is a utility class which lets you generate the data file for testing.
 * You can run it by specifying the target file name in the run configuration
 * (E.g., data_02.txt 50) and it will generate 50 random intervals and write it
 * to a file named data_02.txt
 * 
 * You can use this data file to run the IntervalTreeMain in the non-interactive
 * mode.
 * 
 */
public class GenerateData {
	public static void main(String[] args) {
		String filename;
		Integer numIntervals;
		if (args.length < 1) {
			filename = "data.txt";
			numIntervals = 10;
		} else {
			filename = args[0];
			numIntervals = Integer.parseInt(args[1]);
		}

		// open the file for writing.
		try {
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			int maxIntervalLimit = 50;
			int minIntervalLimit = 1;

			// generate intervals.
			while (numIntervals-- > 0) {
				// generate start and end points.
				int start = (int) (Math.random() * maxIntervalLimit + minIntervalLimit);
				int end = (int) (Math.random() * maxIntervalLimit + start + 1);
				writer.println(start + "," + end + "," + "p" + String.valueOf(numIntervals));
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return;
		}
	}
}
