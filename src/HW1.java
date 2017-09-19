
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
	}
}
