package Main;

import java.util.ArrayList;

public class NumberSetList {

    private ArrayList<NumberSet> numberSetList = new ArrayList<>();

    public void addNumberSet(NumberSet numberSet) {
        this.numberSetList.add(numberSet);
    }

    public NumberSet getNumberSet(int index) {
        return numberSetList.get(index);
    }

    public ArrayList<NumberSet> getNumberSetList() {
        return numberSetList;
    }

    public void checkNumberSetList() {
        System.out.println("numberSetList: checking for empty NumberSets");
        ArrayList<NumberSet> emptyNumberSets = new ArrayList<>();

        //suche NumberSets die ein leeres Array haben
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
}
