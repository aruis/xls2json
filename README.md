# xls2json
把xls转化成json


### USE
[下载编译后的包](https://github.com/aruis/xls2json/raw/master/dist/xls2json-1.0-SNAPSHOT-all.jar)

java -jar -Dfile=/yourpath/yourfile.xls /yourpath/xls2json-1.0-SNAPSHOT-all.jar

运行之后，会在youfile.xls文件旁边生成out_array.json以及out_object.json两种格式json文件

### Notice
* 需要事先准备jre环境,也就是通常说的java环境
* 暂时只支持xls格式文件
* xls文件第一行需要是**列名**行，可以参考[example.xls](https://github.com/aruis/xls2json/raw/master/example.xls)
