# xls2json
把xls/xlsx文件转化成json


## USE
* [下载编译后的包](https://github.com/aruis/xls2json/raw/master/dist/xls2json-1.1-all.jar)
* java -jar -Dfile=/yourpath/yourfile.xls /yourpath/xls2json-1.1-all.jar
* 运行之后，会在youfile.xls文件旁边生成out_array.json以及out_object.json两种格式json文件
* Use in your code:
```java 
import com.aruistar.tool.XLS2Json;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        System.out.println(XLS2Json.getJson(new File("/Users/liurui/Desktop/level.xlsx")));
    }
}
```

### Notice
* 命令模式下，需要事先准备jre环境,也就是通常说的java环境
* xls文件第一行需要是**列名**行，可以参考[example.xls](https://github.com/aruis/xls2json/raw/master/example.xls)
