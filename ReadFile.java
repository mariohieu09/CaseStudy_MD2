import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile<E> {
    public List<E> readFile(File file){
    try{
        List<E> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        if(fis.available() == 0){
            return list;
        }else{
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<E>) ois.readObject();
        }
        return list;
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
     }
    }
}
