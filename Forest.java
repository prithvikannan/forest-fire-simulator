
public class Forest {
	private static final int NUM_FIRES = 5;
	private double density;
	Tree[][] currentState;
	Tree[][] nextState;

	static int numTrees = 0;
	static int numBurned = 0;
	static int numAlive = 0;
	static int numStepsToBurn = 0;

	public Forest(int r, int c, double density) {
		currentState = new Tree[r][c];
		nextState = new Tree[r][c];

		this.density = density;

		fillTrees(density, r, c);

		copy();
	}

	private void fillTrees(double density, int r, int c) {
		int target = (int) (r * c * density);
		int numTrees = 0;
		int row = 0;
		int col = 0;
		while (numTrees < target) {
			row = (int) (Math.random() * r);
			col = (int) (Math.random() * c);
			if (nextState[row][col] == null) {
				Tree t = randomTree();
				nextState[row][col] = t;
				numTrees++;
			}
		}
		int numTreesOnFire = 0;
		while (numTreesOnFire < NUM_FIRES) {
			row = (int) (Math.random() * nextState.length);
			col = (int) (Math.random() * nextState[0].length);
			if (nextState[row][col] != null) {
				nextState[row][col].setState(Tree.ON_FIRE);
				numTreesOnFire++;
			}
		}
	}

	private static Tree randomTree() {
		int num = (int) (Math.random() * 4);

		if (num <= 1) {
			return new Oak(Tree.LIVING);
		} else if (num == 2) {
			return new Pine(Tree.LIVING);
		} else {
			return new Eucalyptus(Tree.LIVING);
		}

	}

	public void runOneStep() {
		for (int r = 0; r < currentState.length; r++) {
			for (int c = 0; c < currentState[0].length; c++) {
				if (currentState[r][c] != null) {
					Tree t = currentState[r][c];
					t.update(r, c, currentState, nextState);
				}
			}
		}
		copy();
	}

	public void copy() {
		for (int r = 0; r < currentState.length; r++) {
			for (int c = 0; c < currentState[0].length; c++) {
				currentState[r][c] = nextState[r][c];
			}
		}
	}

	public int getWidth() {
		return currentState[0].length;
	}

	public int getHeight() {
		return currentState.length;

	}

	public Tree getObjectAt(int r, int c) {
		return currentState[r][c];
	}

	public Tree[][] getGrid() {
		return currentState;
	}

	public boolean hasFires() {
		for (int r = 0; r < currentState.length; r++) {
			for (int c = 0; c < currentState[0].length; c++) {
				if (currentState[r][c] != null) {
					Tree t = currentState[r][c];
					if (t.getState() == Tree.ON_FIRE) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public double getDensity() {
		return density;
	}

	public void runUntilFiresGoOut() {
		while (hasFires() == true) {
			runOneStep();
			numStepsToBurn++;
		}
	}

	public double getTime() {
		return numStepsToBurn;
	}

}
