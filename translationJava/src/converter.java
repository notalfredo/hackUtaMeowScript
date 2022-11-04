import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class converter
{
    Map<String,String[]> dict;
    public converter(String fileName) throws FileNotFoundException
    {
        dict = new HashMap<String,String[]>();
        arrayToMap(fileToArray(new File(fileName)));
    }

    private String[] fileToArray(File f) throws FileNotFoundException //converts file to maps
    {
        ArrayList<String> arr = new ArrayList<>();
        Scanner reader = new Scanner(f);
        while(reader.hasNextLine()) //parses line by line
            arr.add(reader.nextLine());
        reader.close();
        ArrayList<String> out = new ArrayList<>();
        for(String item: arr)
        {
            for(String i: item.split(" "))
                out.add(i);
            out.add("\n");
        }
        String[] o = new String[out.size()-1];
        for(int i=0;i<o.length;i++)
            o[i] = out.get(i);
        return o;
    }
    
    private void arrayToMap(String[] arr)    //converts the generated array to a usable hashmap
    {
        for(int i=0;i<arr.length;i++)
        {
            String key;
            String[] value = null;
            if(arr[i].equals("<<"));
            {
                i+=2;
                key = arr[i];
                i+=2;
            }
            if(arr[i].equals("::"))
            {
                ArrayList<String> v = new ArrayList<>();
                i+=2;
                while(arr[i].equals(">>"))
                {
                    v.add(arr[i]);
                    i++;
                }
                value = new String[v.size()];
                for(int j=0;j<value.length;j++)
                    value[j] = v.get(j);
            }
            if(!key.isEmpty() && value!=null)    //checks both parts are there
                dict.put(key,value);
        }
    }

}
