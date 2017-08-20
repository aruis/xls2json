import groovy.json.JsonBuilder

class Application {
    static void main(String[] args) {

        def filePath = System.getProperty('file')
        def arrayOutPath = filePath.replace(new File(filePath).name, "out_array.json")
        def objectOutPath = filePath.replace(new File(filePath).name, "out_object.json")

        if (filePath == null) {
            println("file is null")
            return
        }

//        println(filePath)
        def all = [:]
        def list = []
        def i = 1
        new ExcelBuilder(filePath).eachLine([labels: true]) { row ->

            def map = [:]
            labels.each {
                map[it] = delegate[it]
            }


            all[i] = map

            list << map

            i++
        }

        new File(objectOutPath).setText(new JsonBuilder(all).toPrettyString(), 'utf-8')
        new File(arrayOutPath).setText(new JsonBuilder(list).toPrettyString(), 'utf-8')
    }
}
