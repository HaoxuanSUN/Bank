import frames.LogoFrame;
import util.Interest;

import java.io.IOException;
import java.text.ParseException;

public class MyBank {
    public MyBank() throws IOException, ParseException {
        new LogoFrame();
        new Interest();
    }
}
