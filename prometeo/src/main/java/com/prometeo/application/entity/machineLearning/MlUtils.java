package com.prometeo.application.entity.machineLearning;

import java.util.LinkedHashSet;

/**
 *
 * @author L
 */
public class MlUtils {  
    public static int[][] oneHotEncoder(String[] dataSet) {
        String [] categories = extractUniqueCategories(dataSet);
        
        int [][] encoding = new int[dataSet.length][categories.length];

        for (int j = 0; j < categories.length; j++) {
            String category = categories[j];
            for (int i = 0; i < dataSet.length; i++) {
                if (category.equals(dataSet[i])) {
                    encoding[i][j]=1;
                }
            }
        }
        return encoding;
    }
      
    private static String[] extractUniqueCategories(String[] categories) {
        LinkedHashSet<String> uniqueCategories = new LinkedHashSet<>();
        for (String value : categories) {
            if (value != null) {
                uniqueCategories.add(value);
            }            
        }
        return uniqueCategories.toArray(new String[0]);
    }
    
}
