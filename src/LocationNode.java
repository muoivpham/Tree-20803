import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LocationNode {
	private TributaryNode leftLink;
	private LocationNode rightLink;
	private String locationName;
	private int numTributaries;

	public LocationNode() {
		locationName = "";
		numTributaries = 0;
		leftLink = null;
		rightLink = null;
	}

	public LocationNode(String locationName, int numTributaries) {
		leftLink = null;
		rightLink = null;
		this.locationName = locationName;
		this.numTributaries = numTributaries;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ans = "";
		LocationNode currentNode = this;

		while (currentNode.rightLink != this) {
			ans = ans + currentNode.rightLink.getLocationName() + "\n";
			ans = ans + currentNode.rightLink.leftLink.toString();

			currentNode = currentNode.rightLink;
		}

		return ans;
	}

	public TributaryNode getLeftLink() {
		return leftLink;
	}

	public void setLeftLink(TributaryNode leftLink) {
		this.leftLink = leftLink;
	}

	public LocationNode getRightLink() {
		return rightLink;
	}

	public void setRightLink(LocationNode rightLink) {
		this.rightLink = rightLink;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getNumTributaries() {
		return numTributaries;
	}

	public void setNumTributaries(int numTributaries) {
		this.numTributaries = numTributaries;
	}

	public List<String> toList() {

		// TODO Auto-generated method stub
		List<String> lines = new ArrayList<String>();
		LocationNode currentNode = this;

		while (currentNode.rightLink != this) {
			lines.add(currentNode.rightLink.getLocationName());
			lines.addAll(currentNode.rightLink.leftLink.toList());

			currentNode = currentNode.rightLink;
		}

		return lines;

	}
	
	public List<String> toReversePreorder() {

		// TODO Auto-generated method stub
		List<String> lines = new ArrayList<String>();
		LocationNode currentNode = this;

		while (currentNode.rightLink != this) {
			lines.add(currentNode.rightLink.getLocationName());
			lines.addAll(currentNode.rightLink.leftLink.toReversePreorder());
			
			currentNode = currentNode.rightLink;
		}

		return lines;

	}


	public void startUp(String fileName) {
		LocationNode listHead = this;
		try {

			listHead.setRightLink(listHead);

			BufferedReader in = new BufferedReader(new FileReader(fileName));

			// kiem tra in co line ko
			String str = in.readLine();

			LocationNode currentListNode = new LocationNode();
			currentListNode.setLocationName(str);
			// currentListNode.setLeftLink(listHead);
			currentListNode.setRightLink(listHead);

			// listHead.setLeftLink(listHead);
			listHead.setRightLink(currentListNode);

			int level = 0;
			int num = 0;

			// Node vua xet recentNode
			TributaryNode recentTreeNode = new TributaryNode("Header");
			currentListNode.setLeftLink(recentTreeNode);

			recentTreeNode.setRightLink(recentTreeNode);
			recentTreeNode.setLeftLink(recentTreeNode);

			while (!(str = in.readLine()).equals("")) {

				str = str.trim();
				if (!str.equals("$$$$$")) {
					// level - name
					String[] tokens = str.split("\\s+");
					// initialize newNode with its name
					TributaryNode newNode = new TributaryNode(tokens[1]);

					// check level 3TH
					int newLevel = Integer.parseInt(tokens[0]);

					if (newLevel == level) {
						// "equal level" -> sibling of recentNode

						newNode.setLeftLink(recentTreeNode);
						newNode.setLeftThread(true);
						newNode.setRightLink(recentTreeNode.getRightLink());
						newNode.setRightThread(true);

						recentTreeNode.setRightLink(newNode);
						recentTreeNode.setRightThread(false);

					} else if (newLevel > level) {

						// greater -> 1st child of recentNode
						newNode.setLeftLink(recentTreeNode.getLeftLink());
						newNode.setLeftThread(true);
						newNode.setRightLink(recentTreeNode);
						newNode.setRightThread(true);

						recentTreeNode.setLeftLink(newNode);
						recentTreeNode.setLeftThread(false);
					} else {
						// smaller -> parent, grandparent,... of recentNode
						int numbBack = 0;
						numbBack = level - newLevel;

						// go back nth times
						for (int i = 0; i < numbBack; i++) {
							// go to the last rightNode
							while (recentTreeNode.isRightThread() == false) {
								recentTreeNode = recentTreeNode.getRightLink();
							}
							recentTreeNode = recentTreeNode.getRightLink();
						}

						// level = newLevel -> go to lastNode
						while (recentTreeNode.isRightThread() == false) {
							recentTreeNode = recentTreeNode.getRightLink();
						}

						// add new Node to the right of lastNode
						newNode.setRightLink(recentTreeNode.getRightLink());
						newNode.setRightThread(true);
						newNode.setLeftLink(recentTreeNode);
						newNode.setLeftThread(true);

						recentTreeNode.setRightLink(newNode);
						recentTreeNode.setRightThread(false);

					}
					// update the recentNode = newNode;
					recentTreeNode = newNode;
					level = newLevel;
					num++;
					currentListNode.setNumTributaries(num);
				} else {
					// read next line
					str = in.readLine();
					if (str == null) {
						break;
					}
					str = str.trim();
					if (str.equals("")) {
						break;
					}

					// add the new location node linked list
					LocationNode newLocationNode = new LocationNode(str, 0);
					newLocationNode.setRightLink(currentListNode.getRightLink());
					currentListNode.setRightLink(newLocationNode);

					level = 0;
					num = 0;

					// header
					recentTreeNode = new TributaryNode("Header");
					newLocationNode.setLeftLink(recentTreeNode);
					recentTreeNode.setRightLink(recentTreeNode);

					currentListNode = newLocationNode;
					currentListNode.setNumTributaries(num);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	public String levelOrderTranverse() {
		String ans = "";
		/*
		LocationNode currentNode = this;

		while (currentNode.getRightLink() != this) {
			ans = ans + currentNode.getRightLink().getLocationName() + "\n";//?
			ans = ans + currentNode.getRightLink().getLeftLink().levelOrderTranverse();

			currentNode = currentNode.getRightLink();
		}*/

		TributaryNode header = this.rightLink.leftLink;
		ans = header.levelOrderTranverse();
		return ans;
	}

	public String reversePreorder() {
		String ans = "";

		LocationNode currentNode = this;

		while (currentNode.getRightLink() != this) {
			ans = ans + currentNode.getRightLink().getLocationName() + "\n";
			ans = ans + currentNode.getRightLink().getLeftLink().reversePreorder();

			currentNode = currentNode.getRightLink();
		}

		return ans;
	}

	public String preOrderTranverse() {
		String ans = "";
		LocationNode currentNode = this;

		while (currentNode.getRightLink() != this) {
			ans = ans + currentNode.getRightLink().getLocationName() + "\n";
			ans = ans + currentNode.getRightLink().getLeftLink().toString();

			currentNode = currentNode.getRightLink();
		}

		return ans;
	}

}
