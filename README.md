# xls2json
仅xls文件转化成json的版本,这个版本的包会小很多


## USE
* [下载编译后的包](https://github.com/aruis/xls2json/raw/xls-only/dist/xls2json-1.1-xls-fat.jar)
* java -jar -Dfile=/yourpath/yourfile.xls /yourpath/xls2json-1.1-xls-fat.jar
* 运行之后，会在youfile.xls文件旁边生成out_array.json以及out_object.json两种格式json文件
* Use in your code:
```java 
import com.aruistar.tool.XLS2Json;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        System.out.println(XLS2Json.getJson(new File("/Users/liurui/Desktop/level.xls")));
    }
}
```

### Notice
* 命令模式下，需要事先准备jre环境,也就是通常说的java环境
* xls文件第一行需要是**列名**行，可以参考[example.xls](https://github.com/aruis/xls2json/raw/master/example.xls)
