package com.stackoverflow.q51006344;

/**
 * Created by Sujeet on 24/06/18.
 */
public class IntArrayLoop {

    private int groupID[] = new int[6];
    private boolean selected[] = new boolean[6];

    public void resetSelectionOn(int emplacement) {

        int group = groupID[emplacement];

        for (int i = 0; i < groupID.length; i++) {
            if (groupID[i] == group) {
                groupID[i] = 99;
                selected[i] = false;
            }
        }


        for (int i = 0; i < groupID.length; i++) {
            if (groupID[i] > group) {
                groupID[i]--;
            }
        }


        for (int i : groupID) {
            if (i > group)
                i--;
        }


    }

}
