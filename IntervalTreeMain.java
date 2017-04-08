import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * THIS CLASS IS PROVIDED TO STUDENTS AND WILL NOT BE SUBMITTED<br />
 * -- DO NOT EDIT THIS CLASS
 * 
 * <p>This is the main file which runs the program. It has two modes to run:</p> 
 * 
 * <h4>Interactive mode: <code>java IntervalTreeMain 1</code></h4>
 *  
 * <h4>Non-Interactive mode: <code>java IntervalTreeMain 2 data_01.txt</code></h4>
 */
public class IntervalTreeMain {
	
	/** 
	 * Run as a stand-alone application.
	 * @param args first argument indicates mode 
	 * (1 for interactive or 2 for non-interactive);  
	 * 
	 * <p>If mode is 2, the second argument is name of a comma separated file
	 * text file with interval ranges.</p>
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: IntervalTreeMain <mode> <datafile>");
			System.exit(0);
		}

		int mode = Integer.parseInt(args[0]);
		IntervalTreeMain runner = new IntervalTreeMain();

		if (mode == 1) {
			runner.interactiveMode();
		}
		else {
			if (args.length != 2) {
				System.out.println("Please input filename for non-interactive mode.");
				System.out.println("Usage: IntervalTreeMain <mode> <datafile>");
				System.exit(0);
			}
			String filename = args[1];
			runner.nonInteractiveMode(filename);
		}
	}

	/**
	 * Runs the program in non-interactive mode with a data file of 
	 * pre-determined intervals. Reads the interval data from the file
	 * data_01.csv and constructs the interval tree, then runs several 
	 * insert/delete/overlap queries.</p>
	 * 
	 * <p>The format of each line of the data file is:</p>
	 * 
	 * <pre>interval_start,interval_end,interval_label</pre>
	 * 
	 * <p>Example:</p>
	 * <pre>
	 * 16,60,p9
	 * 11,37,p8
	 * 26,62,p7
	 * 31,47,p6
	 * 11,48,p5
	 * 38,43,p4
	 * 25,26,p3
	 * 3,28,p2
	 * 4,46,p1
	 * 3,16,p0</pre>
	 * 
	 * @param filename The name of a csv file where each row represents
	 * one interval and is specified in form: 
	 * <code>start_time,end_time,interval_label</code>
	 */
	public void nonInteractiveMode(String filename) {
		IntervalTreeADT<Integer> tree = new IntervalTree<Integer>();

		ArrayList<IntervalADT<Integer>> intervals = this.parseIntervals(filename);

		// insert all intervals.
		for (IntervalADT<Integer> interval : intervals) {
			tree.insert(interval);
		}

		System.out.println("Inserting intervals and printing stats.");

		// print stats.
		this.printStats(tree);

		// delete interval.
		int idx = (int) (Math.random() * intervals.size());
		IntervalADT<Integer> interval = intervals.get(idx);
		this.deleteInterval(tree, interval);

		System.out.println("Deleting the interval and printing stats.");

		// print stats again.
		this.printStats(tree);

		// delete interval again!
		idx = (int) (Math.random() * intervals.size());
		interval = intervals.get(idx);
		this.deleteInterval(tree, interval);

		System.out.println("Deleting the interval and printing stats.");

		// print stats again.
		this.printStats(tree);

		// test overlapping intervals.
		idx = (int) (Math.random() * intervals.size());
		interval = intervals.get(idx);
		List<IntervalADT<Integer>> result = this.getOverlapping(tree, interval);

		System.out.println("Intervals overlapping " + interval.toString() + ": ");
		System.out.println("------------------------------------------");

		for (IntervalADT<Integer> res : result) {
			System.out.println(res.toString());
		}
	}

	/**
	 * Runs the program in interactive mode where user can input operation on
	 * command line. Use this mode to iteratively build the program and debug 
	 * it using the TreeViewer.
	 * 
	 * <pre>
     * 1. Insert Schedule
     * 2. Delete Schedule
     * 3. Find Overlapping Schedules
     * 4. Schedules Containing Point
     * 5. Print Tree Stats
     * 6. Show Tree Viewer
     * 7. Quit Program
     * Enter Choice: </pre>
	 */
	public void interactiveMode() {
		IntervalTreeADT<Integer> tree = new IntervalTree<Integer>();


		System.out.println("Welcome to Schedule Planner!");
		System.out.println("=============================");

		int choice = 0;
		Scanner stdin = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("1. Insert Schedule");
			System.out.println("2. Delete Schedule");
			System.out.println("3. Find Overlapping Schedules");
			System.out.println("4. Schedules Containing Point");
			System.out.println("5. Print Tree Stats");
			System.out.println("6. Show Tree Viewer");
			System.out.println("7. Quit Program");
			System.out.println();
			System.out.print("Enter Choice: ");
			choice = stdin.nextInt();
			stdin.nextLine();

			switch (choice) {

			case 1:
				System.out.println("Selected Insert Schedule");
				System.out.println("Enter <label start end>: ");
				String label = stdin.next();
				int left = stdin.nextInt();
				int right = stdin.nextInt();

				IntervalADT<Integer> interval =
								new Interval<Integer>(left, right, label);
				tree.insert(interval);
				System.out.println("Successfully inserted: " + interval.toString());
				break;

			case 2: 
				System.out.println("Selected Delete Schedule");
				System.out.println("Enter <label start end>: ");
				label = stdin.next();
				left = stdin.nextInt();
				right = stdin.nextInt();

				interval =
								new Interval<Integer>(left, right, label);
				try {
					tree.delete(interval);
					System.out.println("Successfully deleted " + interval.toString());
				} catch (IntervalNotFoundException e) {
				}
				break;

			case 3: 
				System.out.println("Selected Find Overlapping Schedules");
				System.out.println("Enter <start end>: ");
				left = stdin.nextInt();
				right = stdin.nextInt();
				interval =
								new Interval<Integer>(left, right, "test");
				List<IntervalADT<Integer>> overlaps = tree.findOverlapping(interval);
				System.out.println("Your input overlaps following schedules:");

				for (IntervalADT<Integer> result : overlaps) {
					System.out.print(result.toString() + " ");
				}
				System.out.println();					
				break;

			case 4: 
				System.out.println("Selected Schedules Containing Point");
				System.out.println("Enter <point>: ");
				int point = stdin.nextInt();
				overlaps = tree.searchPoint(point);
				System.out.println("Following schedules contain the input point " + point + ":");

				for (IntervalADT<Integer> result : overlaps) {
					System.out.print(result.toString() + " ");
				}
				System.out.println();					
				break;

			case 5: 
				System.out.println("Selected Tree Stats");
				tree.printStats();
				break;

			case 6:
				System.out.println("Selected Show Tree Viewer");
				try {
					IntervalTreeGUI<Integer> window =
									new IntervalTreeGUI<Integer>(tree.getRoot());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 7:
				System.out.println("Good Bye!");
				stdin.close();
				System.exit(0);
			}
		}		
	}

	/**
	 * Delete interval from the tree.
	 * 
	 * @param tree
	 *            the root node.
	 * @param interval
	 *            to delete.
	 */
	public void deleteInterval(IntervalTreeADT<Integer> tree, 
					IntervalADT<Integer> interval) {
		try {
			System.out.println("Deleting interval: " + interval.toString());
			System.out.println("------------------------------------------");
			tree.delete(interval);
		} catch (IntervalNotFoundException e) {
			System.out.println("Failed to delete the interval");
		}
	}

	/**
	 * Get overlapping intervals.
	 * 
	 * @param tree
	 *            the tree node.
	 * @param interval
	 *            the target interval.
	 * @return list of candidate intervals.
	 */
	public List<IntervalADT<Integer>> getOverlapping(
					IntervalTreeADT<Integer> tree, 
					IntervalADT<Integer> interval) 
	{
		List<IntervalADT<Integer>> result = tree.findOverlapping(interval);
		return result;
	}

	/**
	 * Print stats of the tree.
	 * 
	 * @param tree
	 *            the root node.
	 */
	public void printStats(IntervalTreeADT<Integer> tree) {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + tree.getHeight());
		System.out.println("Size: " + tree.getSize());
		System.out.println("-----------------------------------------");
	}

	/**
	 * Read the input file, parse the intervals, and a list of Intervals.
	 * It will ignore any lines that are not of the expected format.
	 * 
	 * @param filename name of file with interval information: start,end,label
	 * @return list of parsed intervals.
	 */
	public ArrayList<IntervalADT<Integer>> parseIntervals(String filename) {
		ArrayList<IntervalADT<Integer>> result = new ArrayList<IntervalADT<Integer>>();

		try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				try {
					String[] nums = line.split(",");
					int start = Integer.parseInt(nums[0]);
					int end = Integer.parseInt(nums[1]);
					String name = nums[2];
					IntervalADT<Integer> interval = 
									new Interval<Integer>(start, end, name);
					result.add(interval);
				} catch (Exception e) {
					// unable to parse this line as expected, just ignore it
					System.out.println("INVALID INTERVAL LINE: " + line);
				}
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println(
			"IO EXCEPTION: Unable to complete parseIntervals of "+filename);
		}
		return result;
	}
}
