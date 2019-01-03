
public class Pine extends Tree {
	private static final int BURN_TIME = 5;

	public Pine(int state) {
		super(state);
	}

	@Override
	// Pines catch fire if at least 1 tree around them is on fire and 1 is ash
	public void update(int row, int col, Tree[][] forest, Tree[][] nextForest) {
		if (forest[row][col] == null) {
			return;
		}

		if (forest[row][col].getState() == Tree.LIVING) {

			int numFire = numSurrondingInState(forest, Tree.ON_FIRE, row, col);
			int numAsh = numSurrondingInState(forest, Tree.ASH, row, col);

			if (numFire >= 1 && numAsh >= 1) {
				forest[row][col].setState(Tree.ON_FIRE);
			}

		} else {
			takeLife(row, col, forest);
		}
		Tree[][] temp = forest;
		forest = nextForest;
		nextForest = temp;
	}

	public void takeLife(int row, int col, Tree[][] forest) {
		if (forest[row][col].getState() == Tree.ON_FIRE) {
			this.life--;
		}
		if (life <= 0) {
			forest[row][col].setState(Tree.ASH);
		}
	}

	public int numSurrondingInState(Tree[][] forest, int state, int row, int col) {
		int counter = 0;
		if (inBounds(forest, row + 1, col) && forest[row + 1][col].getState() == Tree.ON_FIRE) {
			counter++;
		}
		if (inBounds(forest, row - 1, col) && forest[row - 1][col].getState() == Tree.ON_FIRE) {
			counter++;
		}
		if (inBounds(forest, row, col + 1) && forest[row][col + 1].getState() == Tree.ON_FIRE) {
			counter++;
		}
		if (inBounds(forest, row, col - 1) && forest[row][col - 1].getState() == Tree.ON_FIRE) {
			counter++;
		}
		return counter;
	}

	@Override
	public int getTimeToBurn() {
		return (int) (Math.random() * BURN_TIME) + BURN_TIME;
	}

}
