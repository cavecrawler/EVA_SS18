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

}
