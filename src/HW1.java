
//Testing

import java.util.*;
import java.io.*;

public class HW1 {
	public static void main (String args[]) throws IOException {
		String[] ss = StringProcess.LoadFile("E:/1.txt");
		System.out.println(StringProcess.Merge(ss));
		Graph G = new Graph();
		G.Load(ss);
		HashMap<String, Integer> res = G.Dijkstra("i");
		Iterator<String> iter = res.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key+" = "+res.get(key));
		}
		System.out.println(StringProcess.Merge(G.GetBridge("i","a")));
		System.out.println(StringProcess.Merge(G.RandomWalk("i")));
		String[][] sss = G.ShortestPath("a", "c");
		System.out.println(sss.length);
		for (int i = 0 ; i < sss.length ; i++)
			System.out.println(":"+StringProcess.Merge(sss[i]));
	}
}
