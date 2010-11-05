/*
 *
    XLConnect
    Copyright (C) 2010 Mirai Solutions GmbH

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.miraisolutions.xlconnect.integration.r;

import com.miraisolutions.xlconnect.CellStyle;
import com.miraisolutions.xlconnect.StyleAction;
import com.miraisolutions.xlconnect.Workbook;
import com.miraisolutions.xlconnect.data.DataFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Martin Studer, Mirai Solutions GmbH
 */
public final class RWorkbookWrapper {

    private final Workbook workbook;

    public RWorkbookWrapper(String filename, boolean create) throws FileNotFoundException, IOException, InvalidFormatException {
        this.workbook = Workbook.getWorkbook(filename, create);
    }

    public String[] getSheets() {
        return workbook.getSheets();
    }

    public String[] getDefinedNames(boolean validOnly) {
        return workbook.getDefinedNames(validOnly);
    }

    public void createSheet(String name) {
        workbook.createSheet(name);
    }

    public void createName(String name, String formula, boolean overwrite) {
        workbook.createName(name, formula, overwrite);
    }

    public void removeName(String name) {
        workbook.removeName(name);
    }

    public String getReferenceFormula(String name) {
        return workbook.getReferenceFormula(name);
    }

    public void removeSheet(String name) {
        workbook.removeSheet(name);
    }

    public void removeSheet(int sheetIndex) {
        workbook.removeSheet(sheetIndex);
    }

    public void writeNamedRegion(RDataFrameWrapper dataFrame, String name, boolean header) {
        workbook.writeNamedRegion(dataFrame.dataFrame, name, header);
    }

    public RDataFrameWrapper readNamedRegion(String name, boolean header) {
        DataFrame dataFrame = workbook.readNamedRegion(name, header);
        return new RDataFrameWrapper(dataFrame);
    }

    public boolean existsName(String name) {
        return workbook.existsName(name);
    }

    public boolean existsSheet(String name) {
        return workbook.existsSheet(name);
    }

    public RDataFrameWrapper readWorksheet(int worksheetIndex, boolean header) {
        DataFrame dataFrame = workbook.readWorksheet(worksheetIndex, header);
        return new RDataFrameWrapper(dataFrame);
    }

    public RDataFrameWrapper readWorksheet(String worksheetName, boolean header) {
        DataFrame dataFrame = workbook.readWorksheet(worksheetName, header);
        return new RDataFrameWrapper(dataFrame);
    }

    public RDataFrameWrapper readWorksheet(int worksheetIndex, int startRow, int startCol, int endRow, int endCol, boolean header) {
        DataFrame dataFrame = workbook.readWorksheet(worksheetIndex, startRow, startCol, endRow, endCol, header);
        return new RDataFrameWrapper(dataFrame);
    }

    public RDataFrameWrapper readWorksheet(String worksheet, int startRow, int startCol, int endRow, int endCol, boolean header) {
        DataFrame dataFrame = workbook.readWorksheet(worksheet, startRow, startCol, endRow, endCol, header);
        return new RDataFrameWrapper(dataFrame);
    }

    public void writeWorksheet(RDataFrameWrapper dataFrame, int worksheetIndex, int startRow, int startCol, boolean header) {
        workbook.writeWorksheet(dataFrame.dataFrame, worksheetIndex, startRow, startCol, header);
    }

    public void writeWorksheet(RDataFrameWrapper dataFrame, String worksheetName, int startRow, int startCol, boolean header) {
        workbook.writeWorksheet(dataFrame.dataFrame, worksheetName, startRow, startCol, header);
    }

    public void writeWorksheet(RDataFrameWrapper dataFrame, int worksheetIndex, boolean header) {
        workbook.writeWorksheet(dataFrame.dataFrame, worksheetIndex, header);
    }

    public void writeWorksheet(RDataFrameWrapper dataFrame, String worksheetName, boolean header) {
        workbook.writeWorksheet(dataFrame.dataFrame, worksheetName, header);
    }

    public int getActiveSheetIndex() {
        return workbook.getActiveSheetIndex();
    }

    public String getActiveSheetName() {
        return workbook.getActiveSheetName();
    }

    public void setActiveSheet(int sheetIndex) {
        workbook.setActiveSheet(sheetIndex);
    }

    public void setActiveSheet(String sheetName) {
        workbook.setActiveSheet(sheetName);
    }

    public void hideSheet(int sheetIndex, boolean veryHidden) {
        workbook.hideSheet(sheetIndex, veryHidden);
    }

    public void hideSheet(String sheetName, boolean veryHidden) {
        workbook.hideSheet(sheetName, veryHidden);
    }

    public void unhideSheet(int sheetIndex) {
        workbook.unhideSheet(sheetIndex);
    }

    public void unhideSheet(String sheetName) {
        workbook.unhideSheet(sheetName);
    }

    public boolean isSheetHidden(int sheetIndex) {
        return workbook.isSheetHidden(sheetIndex);
    }

    public boolean isSheetHidden(String sheetName) {
        return workbook.isSheetHidden(sheetName);
    }

    public boolean isSheetVeryHidden(int sheetIndex) {
        return workbook.isSheetVeryHidden(sheetIndex);
    }

    public boolean isSheetVeryHidden(String sheetName) {
        return workbook.isSheetVeryHidden(sheetName);
    }

    public void addImage(String filename, String name, boolean originalSize)
            throws FileNotFoundException, IOException {
        workbook.addImage(filename, name, originalSize);
    }

    public RCellStyleWrapper createCellStyle(String name) {
        CellStyle cs = workbook.createCellStyle(name);
        return new RCellStyleWrapper(cs);
    }

    public RCellStyleWrapper createCellStyle() {
        CellStyle cs = workbook.createCellStyle();
        return new RCellStyleWrapper(cs);
    }

    public void setStyleAction(String action) {
        if("XLCONNECT".equals(action))
            workbook.setStyleAction(StyleAction.XLCONNECT);
        else if("PREDEFINED".equals(action))
            workbook.setStyleAction(StyleAction.PREDEFINED);
        else if("STYLE_NAME_PREFIX".equals(action))
            workbook.setStyleAction(StyleAction.STYLE_NAME_PREFIX);
        else
            throw new IllegalArgumentException("Provided action is not a valid style action!");
    }

    public void setStyleNamePrefix(String prefix) {
        workbook.setStyleNamePrefix(prefix);
    }

    public void save() throws FileNotFoundException, IOException {
        workbook.save();
    }
}