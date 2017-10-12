
import java.util.*;
import java.io.*;

public class StringProcess {
	public static String Merge (String[] Data) {
		StringBuffer Res = new StringBuffer();
		for (int i = 0 ; i < Data.length ; i++) {
			if (i > 0)
				Res.append(" ");
			Res = Res.append(Data[i]);
		}
		return Res.toString();
	}
	public static String[] Split (String Data) {
		ArrayList<String> Res = new ArrayList<String>();
		String tmp = "";
		Data = Data.toLowerCase() + " ";
		for (int i = 0 ; i < Data.length() ; i++) {
			if (Character.isLowerCase(Data.charAt(i)))
				tmp = tmp + Data.charAt(i);
			else if (tmp != "" && (Data.charAt(i) == ' ' || Data.charAt(i) == '\t' || Data.charAt(i) == '\n' || Data.charAt(i) == '.'  || Data.charAt(i) == ','  || Data.charAt(i) == '?'  || Data.charAt(i) == '!'  || Data.charAt(i) == ':'  || Data.charAt(i) == ';'  || Data.charAt(i) == '\'' || Data.charAt(i) == '\"' || Data.charAt(i) == '~'  || Data.charAt(i) == '('  || Data.charAt(i) == ')')) {
				Res.add(tmp);
				tmp = "";
			}
		}
		return Res.toArray(new String[Res.size()]);
	}
	public static String Format (String Data) {
		return Merge(Split(Data));
	}
	public static String[] LoadFile (String FileName) throws IOException {
		String Data = "";
		String Line = "";
		BufferedReader in = new BufferedReader(new FileReader(FileName));
		while ((Line = in.readLine()) != null)
			Data = Data + " " + Line;
		in.close();
		return Split(Data);
	}
}
