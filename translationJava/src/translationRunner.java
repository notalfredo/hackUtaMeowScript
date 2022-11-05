import java.io.File;
import java.io.IOException;

public class translationRunner {
    public static void main(String[] args) throws IOException
    {
        converter c = new converter("dictionary.dat", "meow");
        translator t = new translator("meow", c.getMap());
        t.translate(new File("code.meow"), "dat");
    }
}
