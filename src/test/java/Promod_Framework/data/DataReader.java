package Promod_Framework.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
//import java.util.SequencedCollection;
import java.util.Collection;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        //read jason to string
       String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\Promod_Framework\\data\\PurchaseOrder.jason"),
               StandardCharsets.UTF_8);

       //String to HashMap Jackson DataBind
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String >> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
        //{map1, map2}



    }
}
