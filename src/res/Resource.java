package res;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class Resource {
    
    
    public static InputStream loadResource(String resName){
        return Resource.class.getClassLoader().getResourceAsStream(resName);
    }
    
    public static Image loadImage(String resName) throws IOException{
        URL url = Resource.class.getClassLoader().getResource(resName);
        return ImageIO.read(url);
    }
}
