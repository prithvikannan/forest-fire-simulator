
public class Oak extends Tree {
	private static final int BURN_TIME = 3;

	public Oak(int state) {
		super(state);
		this.life = getTimeToBurn();
	}

	@Override
	// Oaks catch fire as soon as any of their neighbors are on fire.
	public void update(int row, int col, Tree[][] forest, Tree[][] nextForest) {
		if (forest[row][col] == null) {
			return;
		}

		if (forest[row][col].getState() == Tree.LIVING) {
			if (inBounds(forest, row + 1, col) && forest[row + 1][col].getState() == Tree.ON_FIRE) {
				nextForest[row][col].setState(ON_FIRE);
			}
			if (inBounds(forest, row - 1, col) && forest[row - 1][col].getState() == Tree.ON_FIRE) {
				nextForest[row][col].setState(ON_FIRE);
			}
			if (inBounds(forest, row, col + 1) && forest[row][col + 1].getState() == Tree.ON_FIRE) {
				nextForest[row][col].setState(ON_FIRE);
			}
			if (inBounds(forest, row, col - 1) && forest[row][col - 1].getState() == Tree.ON_FIRE) {
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

	@Override
	public int getTimeToBurn() {
		// TODO Auto-generated method stub
		return (int) (Math.random() * BURN_TIME) + BURN_TIME;
	}

}
