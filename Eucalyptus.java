
public class Eucalyptus extends Tree {

	private static final int BURN_TIME = 3;
	// Moisture can be 1, 2, or 3
	private int moisture;

	public Eucalyptus(int state) {
		super(state);
		this.moisture = (int) (Math.random() * 3) + 1;
	}

	@Override
	// Eucalyptus trees catch fire if the number of trees around them is greater
	// than their moisture level
	public void update(int row, int col, Tree[][] forest, Tree[][] nextForest) {
		if (forest[row][col].inBounds(forest, row, col)) {
			if (forest[row][col].getState() == Tree.LIVING) {
				if (numSurrondingInState(forest, Tree.ON_FIRE, row, col) >= moisture) {
					nextForest[row][col].setState(ON_FIRE);
				}
			} else if (forest[row][col].getState() == Tree.ON_FIRE) {
				this.life--;
			}
			if (life <= 0) {
				forest[row][col].setState(Tree.ASH);
			}

			Tree[][] temp = forest;
			forest = nextForest;
			nextForest = temp;
		}

	}

	public int numSurrondingInState(Tree[][] forest, int state, int row, int col) {
		int counter = 0;
		if (isNeighborOnFire(forest, row + 1, col)) {
			counter++;
		}
		if (isNeighborOnFire(forest, row - 1, col)) {
			counter++;
		}
		if (isNeighborOnFire(forest, row, col + 1)) {
			counter++;
		}
		if (isNeighborOnFire(forest, row, col - 1)) {
			counter++;
		}
		return counter;
	}

	private boolean isNeighborOnFire(Tree[][] forest, int row, int col) {
		if (inBounds(forest, row, col)) {
			if (forest[row][col].getState() == Tree.ON_FIRE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getTimeToBurn() {
		return moisture * BURN_TIME + BURN_TIME;
	}

}
