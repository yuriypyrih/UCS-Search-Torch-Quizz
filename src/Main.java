
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("UCS-Search Algorithm");
		Node startNode = new Node(STATE.BEFORE_BRIDGE);
		
		UniformCostSearch ucs = new UniformCostSearch(startNode);
		
		//Starting the Timer
		long startTimer = System.currentTimeMillis();
		long stopTimer,totalTimer;
		
		//search
		ucs.compute();
		
		//Stoping the Timer and printing it
		stopTimer = System.currentTimeMillis();
		totalTimer = stopTimer - startTimer;
		System.out.println("Runtime Timer: " + totalTimer + " milliseconds");
		
	}

}
