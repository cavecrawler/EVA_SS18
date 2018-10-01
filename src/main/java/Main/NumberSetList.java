package Main;

import java.util.ArrayList;
import java.util.List;

public class NumberSetList extends NumberSet {

    private List<NumberSet> numberSetList = new ArrayList<>();

    public void addNumberSet(NumberSet numberSet) {
        this.numberSetList.add(numberSet);
    }

    // Rückgabe eines Numbersets anhand des Index
    // TODO Methode um Indexposition anhand des Datums zu erhalten
    public NumberSet getNumberSet(int index) {
        return numberSetList.get(index);
    }

    public List<NumberSet> getNumberSetList() {
        return numberSetList;
    }

    //suche NumberSets die ein leeres FloatArray haben
    public void checkNumberSetListForEmptyNumberSets() {
        System.out.println("numberSetList: checking for empty NumberSets");
        ArrayList<NumberSet> emptyNumberSets = new ArrayList<>();

        for (NumberSet currentNumberSet : numberSetList) {
            boolean check = currentNumberSet.checkArrayIntegrity();
            if (check) {
                emptyNumberSets.add(currentNumberSet);
            }
        }

        //printDate aller leeren NumberSets
        int emptyNumberSetsCounter = 0;
        for (NumberSet currentNumberSet : emptyNumberSets) {

            currentNumberSet.printDate();
            emptyNumberSetsCounter++;

        }
        if (emptyNumberSetsCounter == 0) {

            System.out.println("numberSetList: No empty NumberSets found.");
        } else {

            System.out.println("numberSetList: found " + emptyNumberSetsCounter);
        }
    }

    public NumberSet getLastNumberSet() {
        int lastIndex = numberSetList.size();
        NumberSet currentNumberSet = numberSetList.get(lastIndex -1);
        return currentNumberSet;
    }

    public NumberSet getFirstNumberSet() {
        return numberSetList.get(0);
    }
}
