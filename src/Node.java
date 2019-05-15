import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;




public class Node {

	private static int totalCreatedNodes = 0;

	//Every node indicates its parrent
	private Node parentNode;
	//Each Node have many childern
	//private LinkedList<Node> childrenNodes;
	//The state indicates at which side is located the flashlight
	//BEFORE_BRIDGE means the flashlight is about to pass the bridge
	//AFTER_BRIDGE means the flashligh has passed the bridge
	private STATE state;
	//beforeList is the list of available people before passing the bridge
	private LinkedList<Integer> beforeList;
	//afterList is the list of people who are on the other side of the bridge
	private LinkedList<Integer> afterList;
	//people that moved in hte previous turn
	private Integer person1, person2;
	//Total Time of walking
	private int totalTimeOfWalking;
	
	public Node(STATE state) {
		
		totalCreatedNodes ++;
		
		this.state = state;
		//This node has no parent
		parentNode = null;
		//childrenNodes = new LinkedList<>
		beforeList = new LinkedList<Integer>();
		afterList = new LinkedList<Integer>();
		totalTimeOfWalking = 0;
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
		System.out.print("How many people? (int): ");
		Integer intNumOfPeople = scanner.nextInt();
		Integer walkingTime;
		for(int i = 1; i <= intNumOfPeople ; i++) {
			System.out.print("Walking Time for person " + i + " (minutes): ");
			walkingTime = scanner.nextInt();
			beforeList.add(walkingTime);
		}
		
		
	}//end of public Node Constructor
	
	private Node(Node parentNode,STATE state, LinkedList<Integer> beforeList, LinkedList<Integer> afterList, Integer person1, Integer person2) {
		totalCreatedNodes ++;
		//It saves the parentNode
		this.parentNode = parentNode;
		this.state = state;
		this.beforeList = beforeList;
		this.afterList = afterList;
		this.person1 = person1;
		this.person2 = person2;
		
		if(state == STATE.AFTER_BRIDGE) {
			if(person1 > person2) {
				this.totalTimeOfWalking = parentNode.getTotalTimeOfWalking() + person1;
			}else {
				this.totalTimeOfWalking  = parentNode.getTotalTimeOfWalking() + person2;
			}
		}else {
			this.totalTimeOfWalking  = parentNode.getTotalTimeOfWalking() + person1;
		}
		
		
		//printPreviousMove();
		//printNode();
	}//end of private Node Constructor
	
	//Generates all the possible combinations of moves for this turn
	public ArrayList<Node> getChildren(){
		ArrayList<Node> childNodes = new ArrayList<>();
		LinkedList<Integer> tempBeforeList = new LinkedList<Integer>();
		LinkedList<Integer> tempAfterList = new LinkedList<Integer>();
		
		if(state == STATE.BEFORE_BRIDGE) {
			for(int i = 0; i < beforeList.size() - 1; i++) {
				for(int j = i + 1 ; j < beforeList.size(); j++) {
					//reseting the temporary Lists
					//how
					tempBeforeList = (LinkedList) this.beforeList.clone();
					tempAfterList = (LinkedList) this.afterList.clone();
					Integer ps1,ps2;
					
					//Add two people from beforeList to afterList
					tempAfterList.add(tempBeforeList.get(i));
					tempAfterList.add(tempBeforeList.get(j));
					
					ps1 = tempBeforeList.get(i);
					ps2 = tempBeforeList.get(j);
					
					
					
					//Don't forget to remove them from beforeList
					tempBeforeList.remove(j);
					tempBeforeList.remove(i);
					
					//Create the node with the according lists
					childNodes.add(new Node(this, STATE.AFTER_BRIDGE, tempBeforeList, tempAfterList, ps1, ps2));
				
				}
				
				
			}
		}
		else if(state == STATE.AFTER_BRIDGE) {
			for(int i = 0; i < afterList.size(); i++) {
				
				//reseting the temporary Lists
				//how
				tempBeforeList = (LinkedList) this.beforeList.clone();
				tempAfterList = (LinkedList) this.afterList.clone();
			
				
				//Add two people from beforeList to afterList
				tempBeforeList.add(tempAfterList.get(i));
				Integer ps1 = tempAfterList.get(i);
				tempAfterList.remove(i);
				
				//Create the node with the according lists
				childNodes.add(new Node(this, STATE.BEFORE_BRIDGE, tempBeforeList, tempAfterList, ps1, null));
			
			}
		}
		
		return childNodes;	
		
	}
	
	public String traceNodeHistory() {
		String returnString = "";
		if(parentNode != null) returnString += getStringPreviousMove();
		returnString += getStringNode();
		if(parentNode == null) {
			return "";
		}
		return returnString = parentNode.traceNodeHistory() + returnString ;
	}
	
	public String getStringNode() {
		String returnString;
		returnString = " Start{ ";
		for(int i = 0; i < beforeList.size(); i++) {
			returnString += (beforeList.get(i) + " ");
		}
			returnString += "} End{ ";
		for(int i = 0; i < afterList.size(); i++) {
			returnString += (afterList.get(i) + " ");
		}
		returnString += "}\n";
		return returnString;
	}
	
	public String getStringPreviousMove() {
		if(state == STATE.BEFORE_BRIDGE) {
		return ("People " + person1 + " returned back, ");
		}
		else if(state == STATE.AFTER_BRIDGE) {
		return ("People " + person1 + " and " + person2 + " crossed the Bridge, " );
		}
		else return null;
	}
	
	public STATE getState() {
		return state;
	}
	
	public LinkedList<Integer> getBeforeList(){
		return beforeList;
	}
	public LinkedList<Integer> getAfterList(){
		return afterList;
	}
	public int getTotalTimeOfWalking() {
		return totalTimeOfWalking;
	}
	public int getTotalCreatedNodes() {
		return totalCreatedNodes;
	}

}
