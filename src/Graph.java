
import java.util.*;

public class Graph {
	public HashMap<String, HashMap<String, Integer>> Edge;
	private HashMap<String, HashMap<String, Integer>> revEdge;
	private HashMap<String, HashMap<String, Integer>> Mem_Dis;
	public void InsertEdge (String u, String v) {
		if (!Edge.containsKey(u))
			Edge.put(u, new HashMap<String, Integer>());
		if (!Edge.get(u).containsKey(v))
			Edge.get(u).put(v, 0);
		Edge.get(u).put(v, Edge.get(u).remove(v) + 1);
		if (!revEdge.containsKey(v))
			revEdge.put(v, new HashMap<String, Integer>());
		if (!revEdge.get(v).containsKey(u))
			revEdge.get(v).put(u, 0);
		revEdge.get(v).put(u, revEdge.get(v).remove(u) + 1);
		Mem_Dis = new HashMap<String, HashMap<String, Integer>>();
	}
	public void Init () {
		Edge = new HashMap<String, HashMap<String, Integer>>();
		revEdge = new HashMap<String, HashMap<String, Integer>>();
		Mem_Dis = new HashMap<String, HashMap<String, Integer>>();
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
		if (!Mem_Dis.containsKey(u)) {
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
			Mem_Dis.put(u, Dis);
		}
		return Mem_Dis.get(u);
	}
	private void SPDFS (String u, String v, String w, ArrayList<String[]> Res, HashSet<String> visit, ArrayList<String> Path, HashMap<String, Integer> Dis) {
		if (w.equals(v))
			Res.add(Path.toArray(new String[Path.size()]));
		else {
			Iterator<String> iter = Edge.get(w).keySet().iterator();
			while (iter.hasNext()) {
				String p = iter.next();
				if (visit.contains(p) && Edge.get(w).containsKey(p) && Dis.get(w) + Edge.get(w).get(p) == Dis.get(p)) {
					Path.add(p);
					SPDFS(u,v,p,Res,visit,Path,Dis);
					Path.remove(Path.size()-1);
				}
			}
		}
	}
	public String[][] ShortestPath (String u, String v) {
		ArrayList<String[]> Res = new ArrayList<String[]>();
		HashMap<String, Integer> Dis = Dijkstra(u);
		HashSet<String> visit = new HashSet<String>();
		Queue<String> que = new LinkedList<String>();
		if (Dis.containsKey(v))
			que.add(v);
		while (que.size() > 0) {
			String w = que.remove();
			visit.add(w);
			Iterator<String> iter = revEdge.get(w).keySet().iterator();
			while (iter.hasNext()) {
				String p = iter.next();
				if (Dis.containsKey(p) && Edge.containsKey(p) && Edge.get(p).containsKey(w) && Dis.get(p) + Edge.get(p).get(w) == Dis.get(w))
					que.add(p);
			}
		}
		ArrayList<String> Path = new ArrayList<String>();
		Path.add(u);
		SPDFS(u,v,u,Res,visit,Path,Dis);
		return Res.toArray(new String[Res.size()][]);
	}
	public HashMap<String, String[][]> ShortestPath (String u) {
		HashMap<String, Integer> Dis = Dijkstra(u);
		Iterator<String> iter = Dis.keySet().iterator();
		HashMap<String, String[][]> Res = new HashMap<String, String[][]>();
		while (iter.hasNext()) {
			String v = iter.next();
			Res.put(v, ShortestPath(u, v));
		}
		return Res;
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
