## Excel Importer SS18 
Importer for an Excel-File, containing market information data, to calculate different Key-Performance-Indicators.
The provided excel needs to be a .xlsx file, and needs to be structured like this:

### Source .xlsx-File

n Sheets

" | Value_Name_1 | Value_Name_2 | Value_Name_n 
------|------|----|------
DD.MM.YYYY | 0000,00 | 0000,00 | 0000,00
DD.MM.YYYY | 0000,00 | 0000,00 | 0000,00
DD.MM.YYYY | 0000,00 | 0000,00 | 0000,00



### Target .xlsx-File
- Sheets need to be defined in configs.xml
- A Sheet needs to be provided in the Target Excel for every Calulation type

Sheet 1

"" | yoy 1 | yoy 2 | yoy n
------|------|----|------
Value_Name_1 | 0000,00 | 0000,00 | 0000,00
Value_Name_2 | 0000,00 | 0000,00 | 0000,00
Value_Name_n | 0000,00 | 0000,00 | 0000,00

Sheet 2

" | maxdd 1 | maxdd 2 | maxdd n
------|------|----|------
Value_Name_1 | 0000,00 | 0000,00 | 0000,00
Value_Name_2 | 0000,00 | 0000,00 | 0000,00
Value_Name_n | 0000,00 | 0000,00 | 0000,00

### Configuration .xml-File

Source- Target- and Result- relative paths will be provided here.
<pre>
&lt;source>
    &lt;filepath>
        relative Filepath
    &lt;/filepath>
    
    &lt;sheets>
        &lt;sheet type="sheetType">SheetNumber (eg 0)&lt;/sheet>
        &lt;sheet type="sheetType">SheetNumber (eg 1)&lt;/sheet>
    &lt;/sheets>
&lt;/source>
</pre>

Also, all Calculations are be defined here, within the &lt;calculations> node.
To add a calculation, simply add:
<pre>
&lt;calculation type="calculationType" year="years">
    &lt;bondIndex>
        Name of Bond
    &lt;/bondIndex>
    &lt;bondIndex>
        Name of Another Bond
    &lt;/bondIndex>
&lt;/calculation>                    
</pre> 




Technische Hochschule Mittelhessen

Entwicklung Verteilter Anwendungen - SS18