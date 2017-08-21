package com.aruistar.tool

import groovy.json.JsonBuilder

class XLS2Json {
    static void main(String[] args) {

        def filePath = System.getProperty('file')

        if (filePath == null) {
            println("file is null")
            return
        }

        def file = new File(filePath)
        def arrayOutPath = filePath.replace(file.name, "out_array.json")
        def objectOutPath = filePath.replace(file.name, "out_object.json")

        new File(objectOutPath).setText(getJsonObject(file), 'utf-8')
        new File(arrayOutPath).setText(getJsonArray(file), 'utf-8')
    }

    static String getJson(File file) {
        getJsonArray(file)
    }

    static String getJsonObject(File file) {
        def all = xls2map(file).all
        return new JsonBuilder(all).toPrettyString()
    }

    static String getJsonArray(File file) {
        def list = xls2map(file).list
        return new JsonBuilder(list).toPrettyString()
    }

    private static def xls2map(File file) {
        def all = [:]
        def list = []
        def i = 1
        new ExcelBuilder(file).eachLine([labels: true]) { row ->

            def map = [:]
            labels.each {
                map[it] = delegate[it]
            }


            all[i] = map

            list << map

            i++
        }

        return [all: all, list: list]
    }
}
