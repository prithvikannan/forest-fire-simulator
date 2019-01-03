
public abstract class Tree {

	public static final int LIVING = 0;
	public static final int ON_FIRE = 1;
	public static final int ASH = 2;
	protected static int timeToBurn;
	protected int state;
	protected int timeBurning = 0;
	protected int life;
	protected int temp;

	public Tree(int state) {
		this.state = state;
	}

	protected void setState(int i) {
		state = i;
	}

	protected int getState() {
		return state;
	}

	protected abstract int getTimeToBurn();

	protected boolean inBounds(Tree[][] forest, int row, int col) {
		if (row >= 0 && row < forest.length) {
			if (col >= 0 && col < forest[0].length) {
				if (forest[row][col] != null) {
					return true;
				}
			}
		}
		return false;
	}

	protected abstract void update(int row, int col, Tree[][] forest, Tree[][] nextForest);

}
