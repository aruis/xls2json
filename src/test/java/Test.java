import com.aruistar.tool.XLS2Json;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        System.out.println(XLS2Json.getJson(new File("/Users/liurui/Desktop/level.xls")));
    }
}
