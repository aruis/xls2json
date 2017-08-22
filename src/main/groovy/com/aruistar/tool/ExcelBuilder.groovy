package com.aruistar.tool

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFDateUtil
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory

/**
 * Groovy Builder that extracts data from
 * Microsoft Excel spreadsheets.
 * @author Goran Ehrsson
 */
class ExcelBuilder {

    def workbook
    def labels
    def row

    ExcelBuilder(String fileName) {
        ExcelBuilder(new File(fileName))
    }

    ExcelBuilder(File file) {
        Row.metaClass.getAt = { int idx ->
            def cell = delegate.getCell(idx)
            if (!cell) {
                return null
            }
            def value

            switch (cell.cellType) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        value = cell.dateCellValue
                    } else {
                        def _value = cell.numericCellValue
                        value = _value == _value.intValue() ? _value.intValue() : _value
                    }
                    break
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    value = cell.booleanCellValue
                    break
                case HSSFCell.CELL_TYPE_FORMULA:
                    value = cell.toString()
                    break
                default:
                    value = cell.stringCellValue
                    break
            }
            return value
        }

        file.withInputStream { is ->
            workbook = WorkbookFactory.create(is)
        }
    }

    def getSheet(idx) {
        def sheet
        if (!idx) idx = 0
        if (idx instanceof Number) {
            sheet = workbook.getSheetAt(idx)
        } else if (idx ==~ /^\d+$/) {
            sheet = workbook.getSheetAt(Integer.valueOf(idx))
        } else {
            sheet = workbook.getSheet(idx)
        }
        return sheet
    }

    def cell(idx) {
        if (labels && (idx instanceof String)) {
            idx = labels.indexOf(idx.toLowerCase())
        }
        return row[idx]
    }

    def propertyMissing(String name) {
        cell(name)
    }

    def eachLine(Map params = [:], Closure closure) {
        def offset = params.offset ?: 0
        def max = params.max ?: 9999999
        def sheet = getSheet(params.sheet)
        def rowIterator = sheet.rowIterator()
        def linesRead = 0

        if (params.labels) {
            labels = rowIterator.next().collect { it.toString().toLowerCase() }
        }
        offset.times { rowIterator.next() }

        closure.setDelegate(this)

        while (rowIterator.hasNext() && linesRead++ < max) {
            row = rowIterator.next()
            closure.call(row)
        }
    }
}