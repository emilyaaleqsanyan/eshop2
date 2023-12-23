package org.example.model;

public interface Commands {

    String EXIT = "1";
    String ADD_CATEGORY = "2";
    String EDIT_CATEGORY_BY_ID = "3";
    String DELETE_CATEGORY_BY_ID = "4";
    String ADD_PRODUCT = "5";
    String EDIT_PRODUCT_BY_ID = "6";
    String DELETE_PRODUCT_BY_ID = "7";
    String PRINT_SUM_OF_PRODUCTS = "8";
    String PRINT_MAX_OF_PRICE_PRODUCT = "9";
    String PRINT_MIN_OF_PRICE_PRODUCT = "10";
    String PRINT_AVG_OF_PRICE_PRODUCT = "11";


    static void printMainCommands() {

        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_CATEGORY + " for ADD_CATEGORY");
        System.out.println("Please input " + EDIT_CATEGORY_BY_ID + " for EDIT_CATEGORY_BY_ID");
        System.out.println("Please input " + DELETE_CATEGORY_BY_ID + " DELETE_CATEGORY_BY_ID ");
        System.out.println("Please input " + ADD_PRODUCT + " for ADD_PRODUCT");
        System.out.println("Please input " + EDIT_PRODUCT_BY_ID + " for EDIT_PRODUCT_BY_ID");
        System.out.println("Please input " + DELETE_PRODUCT_BY_ID + " for DELETE_PRODUCT_BY_ID");
        System.out.println("Please input " + PRINT_SUM_OF_PRODUCTS + " for PRINT_SUM_OF_PRODUCTS");
        System.out.println("Please input " + PRINT_MAX_OF_PRICE_PRODUCT+ " for PRINT_MAX_OF_PRICE_PRODUCT");
        System.out.println("Please input " + PRINT_MIN_OF_PRICE_PRODUCT + " for PRINT_MIN_OF_PRICE_PRODUCT");
        System.out.println("Please input " + PRINT_AVG_OF_PRICE_PRODUCT + " for PRINT_AVG_OF_PRICE_PRODUCT");

    }
}

