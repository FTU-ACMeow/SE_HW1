
import java.util.*;

public class Graph {
	public HashMap<String, HashMap<String, Integer>> Edge;
	public void InsertEdge (String u, String v) {
		if (!Edge.containsKey(u))
			Edge.put(u, new HashMap<String, Integer>());
		if (!Edge.get(u).containsKey(v))
			Edge.get(u).put(v, 0);
		Edge.get(u).put(v, Edge.get(u).remove(v) + 1);
	}
	public void Init () {
		Edge = new HashMap<String, HashMap<String, Integer>>();
	}
	public void Load (String[] FileData) {
		Init();
		for (int i = 1 ; i < FileData.length ; i++)
			InsertEdge(FileData[i-1], FileData[i]);
	}
	public String[] GetBridge (String u, String v) {
		if (Edge.containsKey(u) && Edge.containsKey(v)) {
			ArrayList<String> Res = new ArrayList<String>();
			Iterator<String> iter = Edge.keySet().iterator();
			while (iter.hasNext()) {
				String w = iter.next();
				if (Edge.get(u).containsKey(w) && Edge.get(w).containsKey(v))
					Res.add(w);
			}
			return Res.toArray(new String[Res.size()]);
		}
		else return new String[0];
	}
	public String[] FillWithBridge (String[] Text) {
		ArrayList<String> Res = new ArrayList<String>();
		for (int i = 0 ; i < Text.length ; i++) {
			Res.add(Text[i]);
			if (i > 1) {
				String[] Bridges = GetBridge (Text[i-1], Text[i]);
				if (Bridges.length > 0)
					Res.add(Bridges[(int)(Math.random()*Bridges.length)]);
			}
		}
		return Res.toArray(new String[Res.size()]);
	}
	public HashMap<String, Integer> Dijkstra (String u) {
		HashMap<String, Integer> Dis = new HashMap<String, Integer>();
		HashSet<String> Visit = new HashSet<String>();
		Dis.put(u, 0);
		while (Visit.size() < Dis.size()) {
			u = null;
			Iterator<String> iter = Dis.keySet().iterator();
			while (iter.hasNext()) {
				String v = iter.next();
				if (!Visit.contains(v) && (u == null || Dis.get(v) < Dis.get(u))) 
					u = v;
			}
			Visit.add(u);
			if (Edge.containsKey(u)) {
				iter = Edge.get(u).keySet().iterator();
				while (iter.hasNext()) {
					String v = iter.next();
					if (!Dis.containsKey(v) || Dis.get(u) + Edge.get(u).get(v) < Dis.get(v))
						Dis.put(v, Dis.get(u) + Edge.get(u).get(v));
				}
			}
		}
		return Dis;
	}
	public String[] RandomWalk (String u) {
		ArrayList<String> Res = new ArrayList<String>();
		HashSet<String> visit = new HashSet<String>();
		Res.add(u);
		while (Edge.containsKey(u)) {
			Set<String> vset = Edge.get(u).keySet();
			String v = (String)vset.toArray()[(int)(Math.random()*vset.size())];
			if (visit.contains(u+" "+v))
				break;
			visit.add(u+" "+v);
			Res.add(v);
			u = v;
		}
		return Res.toArray(new String[Res.size()]);
	}
}
