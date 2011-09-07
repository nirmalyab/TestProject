import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class CreateScripts {
	
	public static void main(String[] args) {
		try {
		
		String scriptName = args[0] + "/runScripts";
		int startIndex = Integer.parseInt(args[1]);
		int endIndex = Integer.parseInt(args[2]);
		
		PrintWriter finalScript = new PrintWriter(new FileWriter(scriptName));	
		double[] bArr = {200, 500, 1000, 2000, 4000};	
		for (int i = startIndex; i <= endIndex; i++) {
			for (int j = 1; j <= 5; j++) {
				String fileName = args[0] + "/script_" + i + "_" + j + ".m";

					PrintWriter outFile = new PrintWriter(new FileWriter(fileName));
					// Now start writing the the file
					
					outFile.println("dataFile = \'./data.mat\';");
					outFile.println("knockFile = \'./knockList.txt\';");
					outFile.println("sgaPath = \'./data/\';");
					outFile.println("relFile = \'./finalrels.txt\';");
					outFile.println("jpath = \'.\';");
					outFile.println("layerNo = " + i + ";");
					outFile.println("b = " + bArr[i-1] + ";");
					outFile.println("outpath = \'./result/result" + i + "/result" 
							+ j + ".mat\';");
					outFile.println("index = " + j + ";");
					outFile.println("cd ..;");
					outFile.println("emapFunc(dataFile, knockFile, sgaPath, " +
					"relFile, jpath, layerNo, b, outpath, index);");
					
					outFile.close();
					
					String execCommand = "matlab < " + fileName + 
										" > /dev/null &";
					finalScript.println("srun -n 1 " + execCommand);
			}
		}
		
		finalScript.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
