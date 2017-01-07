import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class View extends JFrame {

	DefaultListModel<String> leftModel;
	private JList leftList;
	private LocationNode listHead;
	private Test runner;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		leftList = new JList<>();
		leftModel = new DefaultListModel<String>();
		scrollPane.setViewportView(leftList);

		initMenu();
	}

	private void initMenu() {
		// TODO Auto-generated method stub
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				int returnvalue = fileChooser.showOpenDialog(null);

				if (returnvalue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();

					listHead = new LocationNode();
					listHead.startUp(selectedFile.getAbsolutePath());

					List<String> lines = listHead.toList();

					for (String line : lines) {
						leftModel.addElement(line);
					}
					leftList.setModel(leftModel);
				}

			}
		});
		mnData.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnData.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnData.add(mntmExit);

		JMenu mnDisplay = new JMenu("Display");
		menuBar.add(mnDisplay);

		JMenuItem mntmPreorder = new JMenuItem("Preorder");
		mntmPreorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> lines = listHead.toList();

				for (String line : lines) {
					leftModel.addElement(line);
				}
				leftList.setModel(leftModel);

			}
		});
		mnDisplay.add(mntmPreorder);

		JMenuItem mntmInorder = new JMenuItem("InOrder");
		mnDisplay.add(mntmInorder);

		JMenuItem mntmReversePreorder = new JMenuItem("Reverse Preorder");
		mntmReversePreorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftModel = new DefaultListModel<String>();
				List<String> lines = listHead.toReversePreorder();

				for (String line : lines) {
					leftModel.addElement(line);
				}
				leftList.setModel(leftModel);
			}
		});
		mnDisplay.add(mntmReversePreorder);

		JMenu mnWaterSystem = new JMenu("Water System");
		menuBar.add(mnWaterSystem);

		JMenuItem mntmAddNewTributary = new JMenuItem("Add New Tributary");
		mntmAddNewTributary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TextField kthTF = new TextField(8);
				JLabel kthLB = new JLabel("Kth: ");

				TextField nameTF = new TextField(8);
				JLabel NameLB = new JLabel("Node's Name: ");

				TextField parentTF = new TextField(8);
				JLabel parentLB = new JLabel("Parent's Name");

				JPanel addTreePanel = new JPanel();
				addTreePanel.add(NameLB);
				addTreePanel.add(nameTF);

				addTreePanel.add(kthLB);
				addTreePanel.add(kthTF);

				addTreePanel.add(parentLB);
				addTreePanel.add(parentTF);

				int result = JOptionPane.showConfirmDialog(null, addTreePanel, "Please Enter New Node's Infomation",
						JOptionPane.OK_CANCEL_OPTION);
				try {
					String newName = nameTF.getText();
					int kth = Integer.parseInt(kthTF.getText());
					String parentName = parentTF.getText();
					addNode(listHead, newName, kth, parentName);
				} catch (Exception ex) {
					int result1 = JOptionPane.showConfirmDialog(frame, "Enter again", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnWaterSystem.add(mntmAddNewTributary);

		JMenuItem mntmRename = new JMenuItem("Rename ");
		mntmRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				JLabel oldNameLB = new JLabel("Current Name: ");
				JTextField oldNameTF = new JTextField(8);

				String name = (String) leftList.getSelectedValue();

				JLabel newNameLB = new JLabel("New Name: ");
				JTextField newTF = new JTextField(8);

				panel.add(oldNameLB);
				panel.add(oldNameTF);

				panel.add(newNameLB);
				panel.add(newTF);

				int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter New Node's Infomation",
						JOptionPane.OK_CANCEL_OPTION);

				try {
					String oldName = oldNameTF.getText();
					String newName = newTF.getText();

					TributaryNode node = searchInAll(listHead, oldName);
					node.setTributaryName(newName);

				} catch (Exception ex) {
					int result1 = JOptionPane.showConfirmDialog(frame, "Enter again", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnWaterSystem.add(mntmRename);

		JMenuItem mntmDelete = new JMenuItem("Delete ");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				TextField nameTF = new TextField(10);
				JPanel deletePanel = new JPanel();
				JLabel deleteLB = new JLabel("Node's Name: ");

				deletePanel.add(deleteLB);
				deletePanel.add(nameTF);

				// int result = JOptionPane.showConfirmDialog(null, deletePanel,
				// "Please Enter New Node's Infomation",
				// JOptionPane.OK_CANCEL_OPTION);

				try {
					name = (String) leftList.getSelectedValue();
					String[] str = name.split("\\s+");
					// name = nameTF.getText();
					deleteStream(str[1], listHead);

				} catch (Exception ex) {
					int result1 = JOptionPane.showConfirmDialog(frame, "Enter again", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				leftModel.remove(leftList.getSelectedIndex());
			}
		});
		mnWaterSystem.add(mntmDelete);

		JMenu mnWaterFlow = new JMenu("Water Flow");
		menuBar.add(mnWaterFlow);

		JMenuItem mntmTraceTheFlow = new JMenuItem("Trace The Flow");
		mntmTraceTheFlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) leftList.getSelectedValue();
				String[] str = name.split("\\s+");
				System.out.println(str[2]);
				String ans = findFlow(str[2], listHead);
				
				
				JOptionPane.showMessageDialog(frame, ans);
				
			}
		});
		mnWaterFlow.add(mntmTraceTheFlow);

		JMenuItem mntmDisplayAllTributaries = new JMenuItem("Display All Tributaries");
		mnWaterFlow.add(mntmDisplayAllTributaries);

		JMenuItem mntmDisplayImmediateTributaries = new JMenuItem("Display Immediate Tributaries");
		mnWaterFlow.add(mntmDisplayImmediateTributaries);

	}

	//
	//
	//
	//
	private Component frame;

	public void addNode(LocationNode header, String newName, int kth, String parentName) {

		// Tim kiem => vi tri them
		TributaryNode currentNode = searchInAll(header, parentName);

		// Tao moi => node moi
		TributaryNode newNode = new TributaryNode(newName);

		// Them => them not moi vao vi tri them

		if (kth == 1) {
			// add first node
			if (currentNode.isLeftThread() == true) {
				// has no child
				newNode.setLeftLink(currentNode.getLeftLink());
				newNode.setLeftThread(true);
				newNode.setRightLink(currentNode);
				newNode.setRightThread(true);

				currentNode.setLeftLink(newNode);
				currentNode.setLeftThread(false);
			} else {
				// already has at least a child

				TributaryNode firstChild = currentNode.getLeftLink();

				if (firstChild.isLeftThread() == true) {

					// set Left Link of new Node
					newNode.setLeftLink(firstChild.getLeftLink());
					newNode.setLeftThread(firstChild.isLeftThread());

					// set Right Link of new Node
					newNode.setRightLink(firstChild);
					newNode.setRightThread(false);

					// build tree
					currentNode.setLeftLink(newNode);

					firstChild.setLeftLink(newNode);
					firstChild.setLeftThread(true);
				} else {
					// First Node has no leftThread
					TributaryNode leftMostNode = findLeftMost(currentNode);
					System.out.println(leftMostNode.getTributaryName());

					// set Left Link of new Node
					newNode.setLeftLink(leftMostNode.getLeftLink());
					newNode.setLeftThread(true);

					// set Right Link of new Node
					newNode.setRightLink(firstChild);
					newNode.setRightThread(false);

					// build tree
					currentNode.setLeftLink(newNode);

					// set the left pointer
					leftMostNode.setLeftLink(newNode);
					leftMostNode.setLeftThread(true);
				}

			}
		} else {
			// current = first child
			currentNode = currentNode.getLeftLink();
			int count = 0;

			// check kth is correct position
			TributaryNode p = currentNode;
			while (currentNode.isRightThread() == false) {
				count++;
				p = p.getRightLink();
			}

			if ((kth - count) == 1 || (kth - count) < 0) {
				// true
				for (int i = 0; i < kth - 1; i++) {
					currentNode = currentNode.getRightLink();
				}

				// current is the node standing before the newNode
				newNode.setRightLink(currentNode.getRightLink());
				newNode.setRightThread(currentNode.isRightThread());

				newNode.setLeftLink(currentNode);
				newNode.setLeftThread(false);

				currentNode.setRightLink(newNode);
				currentNode.setRightThread(false);

			} else {

				JOptionPane.showMessageDialog(frame, "The Kth is invalid");
			}

		}

	}

	private TributaryNode searchInAll(LocationNode head, String name) {

		LocationNode currentNode = head;

		while (currentNode.getRightLink() != head) {
			TributaryNode result = search(currentNode.getRightLink().getLeftLink(), name);
			if (result != null)
				return result;

			currentNode = currentNode.getRightLink();
		}

		return null;
	}

	private TributaryNode search(TributaryNode header, String name) {
		// TODO Auto-generated method stub

		TributaryNode currentNode = header.getLeftLink();
		boolean isBack = false;
		while (currentNode != header) {
			if (currentNode.getTributaryName().equals(name)) {
				return currentNode;
			}
			if (!isBack) {
				if (currentNode.isLeftThread() == false) {
					// has child(s)
					currentNode = currentNode.getLeftLink();
				} else {
					if (currentNode.isRightThread() == false) {
						// level dosen't change
						currentNode = currentNode.getRightLink();

					} else {

						isBack = true;
						currentNode = currentNode.getRightLink();
					}
				}
			} else {
				if (currentNode.isRightThread() == false) {
					// level dosen't change
					isBack = false;
					currentNode = currentNode.getRightLink();
				} else {
					currentNode = currentNode.getRightLink();

				}
			}
		}

		return null;
	}

	public TributaryNode findLeftMost(TributaryNode root) {
		TributaryNode ans = root;

		while (ans.isLeftThread() == false) {
			ans = ans.getLeftLink();

		}

		return ans;
	}

	public String findAlltributaries(String name, LocationNode listHead) {
		String ans = "";

		// TODO Auto-generated method stub
		TributaryNode root = searchInAll(listHead, name);

		if (root.isLeftThread() == true) {
			return "no tributary";
		}

		TributaryNode currentNode = root.getLeftLink();
		ans = currentNode.getTributaryName() + "\n";
		boolean isBack = false;

		while (currentNode != root) {

			if (!isBack) {
				if (currentNode.isLeftThread() == false) {
					// has child(s)
					currentNode = currentNode.getLeftLink();
					ans = ans + currentNode.getTributaryName() + "\n";
				} else {
					if (currentNode.isRightThread() == false) {
						// level dosen't change
						currentNode = currentNode.getRightLink();
						ans = ans + currentNode.getTributaryName() + "\n";
					} else {
						isBack = true;
						currentNode = currentNode.getRightLink();
					}
				}
			} else {
				if (currentNode.isRightThread() == false) {
					// level dosen't change
					isBack = false;
					currentNode = currentNode.getRightLink();
					ans = ans + currentNode.getTributaryName() + "\n";
				} else {
					currentNode = currentNode.getRightLink();
				}
			}
		}
		return ans;
	}

	public String findImmediateTributaries(String name, LocationNode listHead) {

		String ans = "";

		// TODO Auto-generated method stub
		TributaryNode root = searchInAll(listHead, name);

		if (root.isLeftThread() == true) {
			return "no tributary";
		}

		TributaryNode currentNode = root.getLeftLink();
		ans = currentNode.getTributaryName() + "\n";

		while (currentNode.isRightThread() == false) {
			// level dosen't change
			currentNode = currentNode.getRightLink();
			ans = ans + currentNode.getTributaryName() + "\n";
		}
		return ans;
	}

	public void deleteStream(String name, LocationNode listHead) {
		TributaryNode deletingNode = searchInAll(listHead, name);
		TributaryNode previousNode = findPrevNode(deletingNode, listHead);

		// if previousNode have right link -> deletingNode
		if (previousNode.getRightLink().getTributaryName().equals(deletingNode.getTributaryName())) {
			if (deletingNode.isLeftThread() == true) {
				// deletingNode has left Thread
				if (deletingNode.isRightThread() == true) {
					System.out.println("A");
					previousNode.setRightLink(deletingNode.getLeftLink());
					previousNode.setLeftThread(true);
				} else {
					System.out.println("B");
					TributaryNode sibling = deletingNode.getRightLink();
					previousNode.setRightLink(sibling);
					// set left Link ptr of deleting Node
					TributaryNode leftMostNode = findLeftMost(deletingNode);
					TributaryNode leftMostNodeNew = findLeftMost(sibling);
					leftMostNodeNew.setLeftLink(leftMostNode.getLeftLink());
				}
			} else {
				// if has no left Thread
				if (deletingNode.isRightThread() == true) {
					System.out.println("C");
					previousNode = deletingNode.getRightLink();
					previousNode.setRightThread(true);
				} else {
					System.out.println("D");
					previousNode.setRightLink(deletingNode.getRightLink());
					TributaryNode leftMost = findLeftMost(deletingNode);
					leftMost.setLeftLink(previousNode);
					leftMost.setLeftThread(true);
				}
			}
		} else {
			// if previousNode leftLink -> deleting Node
			if (deletingNode.isLeftThread() == true) {
				// deletingNode has left Thread
				if (deletingNode.isRightThread() == false) {
					System.out.println("E");
					TributaryNode sibling = deletingNode.getRightLink();
					TributaryNode leftMost = findLeftMost(sibling);
					leftMost.setLeftLink(deletingNode.getLeftLink());
					leftMost.setLeftThread(true);

					previousNode.setLeftLink(sibling);
				} else {
					System.out.println("F");
					previousNode.setRightLink(deletingNode.getRightLink());
					previousNode.setRightThread(true);
				}

			} else {
				// if has no left Thread
				if (deletingNode.isRightThread() == false) {
					System.out.println("G");
					TributaryNode sibling = deletingNode.getRightLink();
					TributaryNode leftMost = findLeftMost(sibling);
					leftMost.setLeftLink(previousNode);
					leftMost.setLeftThread(true);

					previousNode.setLeftLink(sibling);
				} else {
					System.out.println("H");
					previousNode.setRightLink(deletingNode.getRightLink());
					previousNode.setRightThread(true);
				}
			}

		}

	}

	private TributaryNode findPrevNode(TributaryNode deletingNode, LocationNode locationHeader) {
		// TODO Auto-generated method stub
		LocationNode currentLNode = locationHeader;
		while (currentLNode.getRightLink() != locationHeader) {

			// TODO Auto-generated method stub
			TributaryNode header = currentLNode.getRightLink().getLeftLink();
			TributaryNode currentNode = header.getLeftLink();
			boolean isBack = false;
			while (currentNode != header) {
				if (!isBack) {

					if (currentNode.isLeftThread() == false) {
						// has child(s)
						if (currentNode.getLeftLink().getTributaryName().equals(deletingNode.getTributaryName())) {
							return currentNode;
						}
						currentNode = currentNode.getLeftLink();
					} else {
						if (currentNode.isRightThread() == false) {
							// level dosen't change
							if (currentNode.getRightLink().getTributaryName().equals(deletingNode.getTributaryName())) {
								return currentNode;
							}
							currentNode = currentNode.getRightLink();

						} else {
							isBack = true;
							currentNode = currentNode.getRightLink();
						}
					}
				} else {
					if (currentNode.isRightThread() == false) {
						// level dosen't change
						isBack = false;
						if (currentNode.getRightLink().getTributaryName().equals(deletingNode.getTributaryName())) {
							return currentNode;
						}
						currentNode = currentNode.getRightLink();
					} else {
						currentNode = currentNode.getRightLink();

					}
				}
			}

			currentLNode = currentLNode.getRightLink();
		}

		return null;
	}

	public String findFlow(String name, LocationNode listHead) {
		String ans = "";
		TributaryNode node = searchInAll(listHead, name);

		TributaryNode currentNode = node;

		while (!currentNode.getRightLink().getTributaryName().equals("Header")) {
			if (currentNode.isRightThread() == true) {
				currentNode = currentNode.getRightLink();
				ans = ans + currentNode.getTributaryName() + "\n";
			}
			currentNode = currentNode.getRightLink();
		}
		return ans;
	}

}
