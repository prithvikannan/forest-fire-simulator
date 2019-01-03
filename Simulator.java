
public class Simulator {
	static Forest f;

	/***
	 * Create a new simulator. The simulator creates a new Forest of size (r, c).
	 * 
	 * @param r
	 * @param c
	 */
	public Simulator(int r, int c, double density) {
		f = new Forest(r, c, density);
	}

	public Forest getForest() {
		return f;
	}

	public double getPercentTreesBurned() {
		f.runUntilFiresGoOut();

		int count = 0;
		Tree arr[][] = f.getGrid();
		for (int r = 0; r < arr.length; r++)
			for (int c = 0; c < arr[0].length; c++) {
				if (arr[r][c] != null) {
					Tree t = arr[r][c];
					if (t.getState() == Tree.ASH) {
						count++;
					}
				}
			}
		int numOfTrees = (int) (arr.length * arr[0].length * f.getDensity());
		return ((double) count / numOfTrees) * (100.0);
	}

//	public static double getAveragePercentBurned(int width, int height, double density, int trials) {
//		double totalPercent = 0;
//		for (int i = 0; i < trials; i++) {
//			totalPercent += percentTreesBurned();
//		}
//		return totalPercent / trials;
//	}
	

//	public void displayResults(int width, int height, double density, int trials) {
//		f.runUntilFiresGoOut();
//
//		System.out.println(getAveragePercentBurned(width, height, density, trials));
//		// System.out.println(density);
//	}

}