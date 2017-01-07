import java.awt.Component;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument.LeafElement;

public class Test {
	private static Component frame;
	private static LocationNode listHead;

	public static void main(String[] args) {
		listHead = new LocationNode();
		listHead.startUp("Lab2Data.txt");
		// addNode(listHead.getRightLink(), "ABC", 5, "CimarronRiver");
		System.out.println(listHead.levelOrderTranverse());
	}

	

	
	
	public static void save() {

	}
}
