package org.launchcode.givemeaquest.persistent.models;

import java.util.ArrayList;

// This is a change made in sandbox.

/**
 * Created by LaunchCode
 */
public class QuestData {


    /**
     * Returns the results of searching the Quests data by field and search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column Quest field that should be searched.
     * @param value Value of the field to search for.
     * @param allQuests The list of quests to search.
     * @return List of all quests matching the criteria.
     */
    public static ArrayList<Quest> findByColumnAndValue(String column, String value, Iterable<Quest> allQuests) {

        ArrayList<Quest> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Quest>) allQuests;
        }

        if (column.equals("all")){
            results = findByValue(value, allQuests);
            return results;
        }
        for (Quest quest : allQuests) {

            String aValue = getFieldValue(quest, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(quest);
            }
        }

        return results;
    }

    public static String getFieldValue(Quest quest, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = quest.getName();
        } else {
            theValue = quest.getQuestTags().toString();
        }

        return theValue;
    }

    /**
     * Search all Quest fields for the given term.
     *
     * @param value The search term to look for.
     * @param allQuests The list of quests to search.
     * @return      List of all quests with at least one field containing the value.
     */
    public static ArrayList<Quest> findByValue(String value, Iterable<Quest> allQuests) {


        ArrayList<Quest> results = new ArrayList<>();

        for (Quest quest : allQuests) {

            if (quest.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(quest);
            } else if (quest.getQuestTags().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(quest);
            }

        }

        return results;
    }


}

