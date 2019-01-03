public class NonGUI {
	public static void main(String[] args) {
		for (double i = 0.01; i < 1; i = i + 0.01) {
			double counter = 0;
			int trials = 1000;
			for (int j = 0; j < trials; j++) {
				Simulator sim;
				sim = new Simulator(100, 100, i);
				counter += sim.getPercentTreesBurned();
			}
			System.out.println((double)counter/trials);
		}

	}
}
