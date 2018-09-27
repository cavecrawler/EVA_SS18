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


Technische Hochschule Mittelhessen

Entwicklung Verteilter Anwendungen - SS18