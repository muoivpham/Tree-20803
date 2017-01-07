import java.util.ArrayList;
import java.util.List;

public class TributaryNode {
	private boolean leftThread;
	private boolean rightThread;
	private TributaryNode leftLink;
	private TributaryNode rightLink;
	private String tributaryName;

	public TributaryNode(String name) {
		tributaryName = name;
		leftLink = null;
		rightLink = null;
		leftThread = false;
		rightThread = false;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ans = "";
		TributaryNode currentNode = this.leftLink;
		int level = 1;
		boolean isBack = false;
		while (currentNode != this) {
			if (!isBack) {
				for (int i = 0; i < level; i++) {
					ans = ans + "  ";
				}
				ans = ans + level + " ";
				ans = ans + currentNode.getTributaryName() + "\n";
				if (currentNode.leftThread == false) {
					// has child(s)
					level++;
					currentNode = currentNode.leftLink;
				} else {
					if (currentNode.rightThread == false) {
						// level dosen't change
						currentNode = currentNode.rightLink;

					} else {

						level--;
						isBack = true;
						currentNode = currentNode.rightLink;
					}
				}
			} else {
				if (currentNode.rightThread == false) {
					// level dosen't change
					isBack = false;
					currentNode = currentNode.rightLink;
				} else {
					level--;
					currentNode = currentNode.rightLink;

				}
			}
		}
		return ans;
	}

	public String levelOrderTranverse() {
		// TODO Auto-generated method stub
		String ans = "";
		TributaryNode currentNode = this.leftLink;// HEADER => BML

		while (currentNode != null) {
			ans = ans + currentNode.getTributaryName();
			System.out.println(currentNode.getTributaryName());

			TributaryNode temp = findRightNodeInSameLevel(currentNode);

			if (temp == null)
				currentNode = findFisrtNodeInNextLevel(currentNode);
			else
				currentNode = temp;
			System.out.println("B");
		}
		return ans;
	}

	private TributaryNode findFisrtNodeInNextLevel(TributaryNode currentNode) {
		// TODO Auto-generated method stub
		int value = 0;
		TributaryNode node = this.leftLink;
		int x = 1;
		System.out.println("==" + currentNode.tributaryName);
		// Tinh khoang cach tu header => current = x;
		// Doan tu header den current node se di uu tien phai:
		while (node != currentNode) {
			if (!node.isRightThread()) {
				node = node.getRightLink();
				x++;
			} else {
				if (!node.isLeftThread()) {
					node = node.getLeftLink();
					x++;
				} else {

					node = node.getLeftLink();
					x--;
				}
			}
			System.out.println("=====" + x + "-------" + node.tributaryName);

		}
		System.out.println("x=" + x);

		// Di tu header uu tien trai 1 doan duong = x+1 => node dau tien tim
		// duoc la kq
		boolean isRBack = false;
		node = this.leftLink;
		int p = 1;
		while (p != x + 1) {
			if (node.getTributaryName().equals("Header"))
				return null;
			System.out.println("##" + node.tributaryName);
			if (!node.isLeftThread()) {
				if (!isRBack) {
					node = node.getLeftLink();
					p++;
					isRBack = false;
				}
			} else {
				if (!node.isRightThread()) {
					node = node.getRightLink();
				} else {
					int l = 0;
					TributaryNode old = node;
					node = node.getRightLink();
					isRBack = true;
					TributaryNode newnode = node;

					newnode = newnode.getLeftLink();
					l++;

					while (newnode != node) {
						newnode = newnode.getRightLink();
						l++;
					}
					p = p - l;
				}
			}

		}

		return node;
	}

	private TributaryNode findRightNodeInSameLevel(TributaryNode currentNode) {
		// TODO Auto-generated method stub
		int value = 0;
		TributaryNode result = currentNode;
		int x = 0;
		boolean isBack = false;
		// di 1 buoc
		if (result.rightThread == false)// Co right that
		{
			result = result.getRightLink();
			x = 1;
			isBack = false;// Thua nhung cu viet vao cho no ro rang
		} else {
			TributaryNode oldNode = result;
			result = result.getRightLink();
			TributaryNode newNode = result;

			newNode = newNode.getLeftLink();
			x = -1;

			while (newNode != oldNode) {
				x = x - 1;
				newNode = newNode.getRightLink();
			}
			isBack = true;
		}
		// Cap nhat value => value !=0
		value = value + x;

		while (value != 0) {
			// currentNode = ????
			// di chuyen uu tien trai
			if (!isBack && currentNode.leftThread == false)// co
			{
				currentNode = currentNode.getLeftLink();
				x = 1;
				isBack = false;
			} else {// Khong co trai that => phai di sang phai
				// Cho nay lam gi
				TributaryNode oldNode = currentNode;
				currentNode = currentNode.getRightLink();// di sang phai

				// tinh xem di may buoc?
				if (oldNode.rightThread == false) {// di theo duong that
					x = 1;
				} else {// di theo duong ao, giong y nhu dong 100 // day la khi
						// quay len
					// x = -??;
					TributaryNode newNode = currentNode;
					newNode = newNode.getLeftLink();
					x = -1;
					while (newNode != oldNode) {
						x = x - 1;
						newNode = newNode.getRightLink();
					}

					// sau khi quay len chi duoc sang phai
					// co the viet luon sang phai o day nhung dai
					// => dung isBack danh dau
				}
			}
			if (currentNode.getTributaryName().equals("Header"))
				return null;
			// tinh do dai 1 buoc
			// cap nhat value
		}
		return currentNode;
	}

	public String reversePreorder() {
		// TODO Auto-generated method stub
		String ans = "";
		TributaryNode currentNode = this.getLeftLink();
		boolean isBack = false;
		while (currentNode != this) {
			ans = ans + " " + currentNode.getTributaryName() + "\n";
			if (currentNode.isRightThread() == false) {
				currentNode = currentNode.getRightLink();
			} else {
				// right Thread == true

				if (currentNode.isLeftThread() == false) {
					currentNode = currentNode.getLeftLink();

				} else {
					while (currentNode.isLeftThread() == true) {
						currentNode = currentNode.getLeftLink();
					}

					if (currentNode.getTributaryName() == "Header") {
						break;
					}
					currentNode = currentNode.getLeftLink();
				}
			}
		}

		return ans;
	}

	public String levelOrder() {
		String ans = "";
		TributaryNode currentNode = this.getLeftLink();

		return ans;
	}

	public boolean isLeftThread() {
		return leftThread;
	}

	public void setLeftThread(boolean leftThread) {
		this.leftThread = leftThread;
	}

	public boolean isRightThread() {
		return rightThread;
	}

	public void setRightThread(boolean rightThread) {
		this.rightThread = rightThread;
	}

	public TributaryNode getLeftLink() {
		return leftLink;
	}

	public void setLeftLink(TributaryNode leftLink) {
		this.leftLink = leftLink;
	}

	public TributaryNode getRightLink() {
		return rightLink;
	}

	public void setRightLink(TributaryNode rightLink) {
		this.rightLink = rightLink;
	}

	public String getTributaryName() {
		return tributaryName;
	}

	public void setTributaryName(String tributaryName) {
		this.tributaryName = tributaryName;
	}

	public List<String> toReversePreorder() {

		// TODO Auto-generated method stub
		List<String> lines = new ArrayList<String>();
		String ans = "";
		TributaryNode currentNode = this.getLeftLink();
		boolean isBack = false;
		while (currentNode != this) {
			ans = ans + " ";
			ans = ans + currentNode.getTributaryName();
			lines.add(ans);
			ans = "";
			if (currentNode.isRightThread() == false) {
				currentNode = currentNode.getRightLink();
			} else {
				// right Thread == true

				if (currentNode.isLeftThread() == false) {
					currentNode = currentNode.getLeftLink();

				} else {
					while (currentNode.isLeftThread() == true) {
						currentNode = currentNode.getLeftLink();
					}

					if (currentNode.getTributaryName() == "Header") {
						break;
					}
					currentNode = currentNode.getLeftLink();
				}
			}
		}

		return lines;

	}

	public List<String> toList() {
		// TODO Auto-generated method stub
		List<String> lines = new ArrayList<String>();
		TributaryNode currentNode = this.leftLink;
		String ans = "";
		int level = 1;
		boolean isBack = false;
		while (currentNode != this) {
			if (!isBack) {
				for (int i = 0; i < level; i++) {
					ans = ans + "  ";
				}
				ans = ans + level + " ";
				ans = ans + currentNode.getTributaryName();
				lines.add(ans);
				ans = "";
				if (currentNode.leftThread == false) {
					// has child(s)
					level++;
					currentNode = currentNode.leftLink;
				} else {
					if (currentNode.rightThread == false) {
						// level dosen't change
						currentNode = currentNode.rightLink;

					} else {

						level--;
						isBack = true;
						currentNode = currentNode.rightLink;
					}
				}
			} else {
				if (currentNode.rightThread == false) {
					// level dosen't change
					isBack = false;
					currentNode = currentNode.rightLink;
				} else {
					level--;
					currentNode = currentNode.rightLink;

				}
			}
		}
		return lines;
	}

}
