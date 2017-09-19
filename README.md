#StringProcess Class

##Join with a single space:
String StringProcess.Merge (String[] Data) 

##Split String and format:
String[] StringProcess.Split (String Data)

##Format String:
String StringProcess.Format (String Data)

##Read from file and split and format:
String[] StringProcess.LoadFile (String FileName)

#Graph class: (members)

##Edge: Edge.get(u).get(v) == <u,v>
HashMap<String, HashMap<String, Integer>> Edge
