import java.util.LinkedList;
import java.util.Queue;

public class UniformCostSearch {
	
	private Node startNode;
	private static int totalExploredNodes = 0;
	
	public UniformCostSearch(Node start) {
		this.startNode = start;
	}
	
	public boolean compute() {
	
		
		if(startNode.getBeforeList().isEmpty()) {
			System.out.println("Starting Node is empty");
			return true;
		}
		
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(this.startNode);
		
		
		while(!list.isEmpty() ) {
		
			Node current = findLeastCostlyNode(list);
			totalExploredNodes++;
			
			if(current.getBeforeList().isEmpty()) {
			
				System.out.println("Finished");
				System.out.println("Initial Node-> " + startNode.getStringNode());
				System.out.println(current.traceNodeHistory());
				System.out.println("Total Time of Walking: " + current.getTotalTimeOfWalking() + " minutes");
				System.out.println("Total Nodes explored: " + totalExploredNodes);
				System.out.println("Total Nodes created: " + startNode.getTotalCreatedNodes());
	
				return true;
			}
			else {
				list.addAll(current.getChildren());
				
			}
			
		}
		
		return false;
	}//end of compute
	
	public Node findLeastCostlyNode(LinkedList<Node> list) {
		int min_cost = 9999999;
		Node returnNode = null;
		for(int i = 0 ; i < list.size(); i++) {
			if(list.get(i).getTotalTimeOfWalking() <= min_cost) {
				returnNode = list.get(i);
				min_cost = returnNode.getTotalTimeOfWalking();
			}
		}
		
		if(returnNode != null) {
			list.remove(returnNode);
		}
		return returnNode;
	}

}
