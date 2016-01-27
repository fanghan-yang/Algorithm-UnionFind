import java.util.Scanner;

public class UnionFind {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();		// number of nodes
		UF uf = new UF(N);
		
		int steps = 3;				// number of connection steps
		for (int i = 0; i < steps; i++) {	// for Percolation Problem, use while loop
			int p = in.nextInt();
			int q = in.nextInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				System.out.println("connecting " + p + " " + q);
			}
		}
		System.out.println("Connection Done!");
		// print out the result
		print(N, uf);
		// check the result
		int times = 2;					// number of checking times
		check(times, uf);
	}
	
	// print out the result
	private static void print(int N, UF uf) {
		System.out.println();
		for (int j = 0; j < N; j++) System.out.print(j + " ");
		System.out.println();
		for (int j = 0; j < N; j++) System.out.print(uf.id[j] + " ");
		System.out.println();
	}
	
	private static void check(int times, UF uf) {
		System.out.println();
		Scanner checkIn = new Scanner(System.in);
		for(int i = 0; i < times; i++) {
			int p = checkIn.nextInt();
			int q = checkIn.nextInt();
			if (uf.connected(p, q)) System.out.println(p + " and " + q + " are connected");
			else System.out.println(p + " and " + q + " are NOT connected");
		}
	}

}

class UF {
	protected int[] id;
	private int[] sz;				// size of the tree
	
	// initialization
	public UF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	// find the root of a node
	private int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]];		// path compression
			i = id[i];
		}
		return i;
	}
	
	// quick find
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	// weighted quick union
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j) return;
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
}
