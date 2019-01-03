import processing.core.PApplet;

public class GUI extends PApplet {
	int c;
	Simulator sim;
	Display display;

	public void setup() {
		size(640, 550); // set the size of the screen.

		// Create a simulator
		sim = new Simulator(250, 250, 0.9);

		// Create the display
		// parameters: (10,10) is upper left of display
		// (620, 530) is the width and height
		display = new Display(this, 1, 1, 620, 530);

		display.setNumCols(250); // NOTE: these must match your simulator!!
		display.setNumRows(250);

		// Set different grid values to different colors
		// TODO: uncomment these lines!

		// display.setColor(Tree.LIVING, color(10, 240, 10));
		display.setColor(Tree.ON_FIRE, color(255, 0, 0));
		display.setColor(Tree.ASH, color(0, 20, 0));

		// You can use classes instead, for example:
		// display.setColor(Tree.class, color(0, 255, 0));
		display.setColor(Oak.class, color(107, 124, 48));
		display.setColor(Pine.class, color(69, 105, 26));
		display.setColor(Eucalyptus.class, color(72, 105, 76));

	}

	@Override
	public void draw() {
		background(200);

		// have your simulation run one step.

		display.drawGrid(sim.getForest()); // display the game
		sim.getForest().runOneStep();
		long a = System.currentTimeMillis();
		long b = a;
		while (b < (a + 50)) {
			b = System.currentTimeMillis();
		}
	}

}