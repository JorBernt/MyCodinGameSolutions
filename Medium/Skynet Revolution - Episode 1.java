import java.util.*;
import java.io.*;
import java.math.*;

class Player {

	public class Link {

		private int m_n1;
		private int m_n2;

		public Link(int n1, int n2) {
			m_n1 = n1;
			m_n2 = n2;
		}

		public void severs() {
			System.out.println(m_n1 + " " + m_n2);
		}

		
	}

	public boolean blockAgent(int agentNode, int[] gateways,
			List<Link> links) {

		for (int gateway : gateways) {
			Link linkToTest = new Link(agentNode, gateway);
			int linkIndex = links.indexOf(linkToTest);
			if (linkIndex >= 0) {
				linkToTest.severs();
				links.remove(linkIndex);
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Player player = new Player();
		// Read init information from standard input, if any
		int nbNodes = in.nextInt();
		int nbLinks = in.nextInt();
		int nbExits = in.nextInt();

		List<Link> links = new ArrayList<Link>(nbLinks);
		int[] gateways = new int[nbExits];

		for (int i = 0; i < nbLinks; i++) {
			int nodeA = in.nextInt();
			links.add(player.new Link(nodeA, in.nextInt()));
		}

		for (int j = 0; j < nbExits; j++) {
			gateways[j] = in.nextInt();
		}

		while (true) {
			// Read information from standard input
			int agentNode = in.nextInt();

			// Compute logic here
			if (!player.blockAgent(agentNode, gateways, links)) {
				links.get(0).severs();
				links.remove(0);
			}
		}
	}
}
