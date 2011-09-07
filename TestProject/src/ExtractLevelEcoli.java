import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class ExtractLevelEcoli {
	
	public static void main(String args[]) {	
		
		try {
			HashMap<String, String> networkMap = Maps.newHashMap();
			BufferedReader netFile = new BufferedReader(new FileReader(args[0]));
			
			String regex 
				= "^(\\S+)\\s+(\\S+)\\s+\\S+\\s+(\\S+)\\s+(\\S+)";
			Pattern pat = Pattern.compile(regex);
			
			String line = null;
			
			while (null != (line = netFile.readLine())) {
				Matcher mat = pat.matcher(line);
				if (mat.find()) {
					String first = mat.group(1);
					String sec = mat.group(2);
					String third = mat.group(3);
					String fourth = mat.group(4);
					
					networkMap.put(first, sec);
					networkMap.put(third, fourth);
				}
				
			}
			netFile.close();
			
			List<LevelClass> levelMap = Lists.newArrayList(); 
			BufferedReader oldFile = new BufferedReader(new FileReader(args[1]));
			
			int currentKey = -1;
			
			while (null != (line = oldFile.readLine())) {
				// Split it.
				String[] strs = line.split("\\s+");
				
				for(String str : strs) {
					if (StringUtils.isNumeric(str)) {
						currentKey = Integer.parseInt(str) -1;
					} else {
						if (networkMap.containsKey(str)) {
							levelMap.add(new LevelClass(networkMap.get(str), currentKey));
						} else {
							System.out.println("Could not map: " + str);
						}
					}
				}
			}
			
			oldFile.close();
			
			PrintWriter outFile = new PrintWriter(new FileWriter(args[2]));
			
			for (LevelClass e : levelMap) {

				String node = e.node;
				int level = e.val;
				
				outFile.println(node + "  " + level);
			}
			
			outFile.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}

class LevelClass {
	public LevelClass(String string, int currentKey) {
		node = string;
		val = currentKey;
	}
	String node;
	int val;
}
