import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class translator
{
        Map<String, String[]> dict;
        String fileType;
        public translator(String fileType, Map<String, String[]> dictionary)
        {
            this.fileType = fileType;
            dict = dictionary;
        }

        public boolean verifyFile(File f) //returns true if the file is runnable by the language
        {
            if(!f.getName().contains("."+fileType))
                return false;
            return true;
        }

        public void translate(File f, String outputFileType) throws IOException
        {
            ArrayList<String> arr = new ArrayList<>();  //reads input file
            Scanner input = new Scanner(f);
            while(input.hasNext())
                arr.add(input.next());
            input.close();

            File out = new File("out."+outputFileType); //writes to output file
            out.createNewFile();
            FileWriter w = new FileWriter("out."+outputFileType);
            for(int i=0;i<arr.size();i++)
            {
                String[] key = dict.get(arr.get(i));
                for(int j=0;j<key.length;j++)
                    w.write(key[j]+" ");
                w.write("\n");
            }
            w.close();
        }

}
