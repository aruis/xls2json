import groovy.json.JsonBuilder

class Application {
    static void main(String[] args) {

        def filePath =  System.getProperty('file')
        def outPath =  System.getProperty('out')

        if(filePath == null){
            println("file is null")
            return
        }

//        println(filePath)
        def all = [:]
        def i = 1
        new ExcelBuilder(filePath).eachLine([labels: true]) { row ->

            def map = [:]
            labels.each {
                map[it] = delegate[it]
            }


            all[i] = map

            i++
        }

        def str =  new JsonBuilder(all).toPrettyString()
        def file =  new File(outPath)
        file.setText(str,'utf-8')
//        file << str

    }
}
