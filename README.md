//Change3
# StringProcess Class
## Join with a single space:
String StringProcess.Merge (String[] Data) 
## Split String and format:
String[] StringProcess.Split (String Data)
## Format String:
String StringProcess.Format (String Data)
## Read from file and split and format:
String[] StringProcess.LoadFile (String FileName)


# Graph class: (members)
## Edge: Edge.get(u).get(v) == (u,v)
HashMap<String, HashMap<String, Integer>> Edge
## Insert edge (u,v):	
void InsertEdge (String u, String v)
## Initialize:
void Init ()
## Initialize and Insert edges from Data: (G.Load(StringProcess.LoadFile(FileName));)
void Load (String[] Data)
## Get bridges of (u,v):
String[] GetBridge (String u, String v) 
## Fill text with bridge edges(just one round):
String[] FillWithBridge (String[] Text)
## Get shortest distance from u to each vectex:
HashMap<String, Integer> Dijkstra (String u)
## Get All Shortest Paths from u to v
String[][] ShortestPath (String u, String v)
## Get All Shortest Paths from u to each others
HashMap<String, String[][]> ShortestPath (String u)
## Random walk:
String[] RandomWalk (String u)
