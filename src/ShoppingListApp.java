
import java.io.*;
import java.util.*;

class ShoppingList {
    Map<String, List<String>> products;

    public ShoppingList() {
        products = new HashMap<>();
    }

    public int loadProductsFromFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(":");
                String category = parts[0];
                String[] productsArray = parts[1].split(",");
                if (!products.containsKey(category)) {
                    products.put(category, new ArrayList<>());
                }

                for (String product : productsArray) {
                    product = product.trim();
                    products.get(category).add(product);
                }
            }

            scanner.close();
            return 0;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return 1;
        }
    }

    public int addToShoppingList(String categoryToAdd, String productToAdd) {
        if (!products.get(categoryToAdd).contains(productToAdd)) {
            products.get(categoryToAdd).add(productToAdd);
            return 1;
        } else {
            return 0;
        }
    }

    public void displayCategories() {
        System.out.println("Categories:");
        System.out.print("\t");
        for (String category : products.keySet()) {
            if (products.keySet().toArray()[products.keySet().size() - 1] == category) {
                System.out.print(category);
                break;
            }
            System.out.print(category + ", ");
        }
        System.out.println();
    }

    public void displayShoppingList() {
        if (products.isEmpty()) {
            System.out.println("No products in the shopping list.");
        } else {
            System.out.println("Shopping list:");
            for (String category : products.keySet()) {
                System.out.println("Category: " + category);
                System.out.print("\t");
                if (products.get(category).isEmpty()) {
                    System.out.println("No products in this category.");
                } else {
                    displayProducts(category);
                }
            }
        }
    }

    public void displayProducts(String category) {
        for (String product : products.get(category)) {
            if (products.get(category).indexOf(product) == products.get(category).size() - 1) {
                System.out.print(product);
                break;
            }
            System.out.print(product + ", ");
        }
        System.out.println();
    }
    public void displayProductsInCategory(String categoryToShow) {
        if (products.containsKey(categoryToShow)) {
            System.out.println("Products in " + categoryToShow + ":");
            displayProducts(categoryToShow);
        } else {
            System.out.println("Category not found: " + categoryToShow);
        }
    }

    public void displayShoppingListByCategory(String categoryToDisplay) {
        if (products.containsKey(categoryToDisplay)) {
            System.out.println("Shopping list in category: " + categoryToDisplay);
            for (String product : products.get(categoryToDisplay)) {
                System.out.print(product + ", ");
            }
        } else {
            System.out.println("Category not found: " + categoryToDisplay);
        }
    }

    public void removeAllFromShoppingList() {
        products.clear();
    }

    public void removeAllFromShoppingListByCategory(String categoryToRemoveAll) {
        if (products.containsKey(categoryToRemoveAll)) {
            products.get(categoryToRemoveAll).clear();
        } else {
            System.out.println("Category not found: " + categoryToRemoveAll);
        }
    }

    public void saveShoppingListToFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (String category : products.keySet()) {
                fileWriter.write(category + ": ");
                for (String product : products.get(category)) {
                    fileWriter.write(product + ", ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void removeSpecificProduct(String categoryToRemove, String productToRemove) {
        if (products.containsKey(categoryToRemove)) {
            if (products.get(categoryToRemove).contains(productToRemove)) {
                products.get(categoryToRemove).remove(productToRemove);
            } else {
                System.out.println("Product not found in category: " + productToRemove);
            }
        } else {
            System.out.println("Category not found: " + categoryToRemove);
        }
    }
}

public class ShoppingListApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingList shoppingList = new ShoppingList();

        if(shoppingList.loadProductsFromFile(".idea/list.txt")==1){
            return;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add to shopping list");
            System.out.println("2. Display shopping list");
            System.out.println("3. Display products in a category");
            System.out.println("4. Remove shopping list");
            System.out.println("5. Remove all from shopping list by category");
            System.out.println("6. Remove specific product");
            System.out.println("7. Save shopping list to file");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String categoryToAdd;

            switch (choice) {
                case 1:
                    System.out.println("To add a product to the shopping list, follow these steps:");
                    shoppingList.displayCategories();
                    System.out.print("Enter the category: ");
                    categoryToAdd = scanner.nextLine();
                    if (!shoppingList.products.containsKey(categoryToAdd)) {
                        System.out.println("Category not found: " + categoryToAdd);
                        break;
                    }
                    shoppingList.displayProductsInCategory(categoryToAdd);
                    System.out.print("Enter the product: ");
                    String productToAdd = scanner.nextLine();
                    if (shoppingList.addToShoppingList(categoryToAdd, productToAdd) == 1) {
                        System.out.println("Added to shopping list: " + productToAdd + " in category " + categoryToAdd);
                    } else {
                        System.out.println("Product already exists in the shopping list");
                    }
                    System.out.println();
                    break;
                case 2:
                    shoppingList.displayShoppingList();
                    break;
                case 3:
                    System.out.println("To display products in a category, follow these steps:");
                    shoppingList.displayCategories();
                    System.out.print("Enter the category: ");
                    categoryToAdd = scanner.nextLine();
                    if(!shoppingList.products.containsKey(categoryToAdd)) {
                        System.out.println("Category not found: " + categoryToAdd);
                        break;
                    }
                    shoppingList.displayProductsInCategory(categoryToAdd);
                    break;
                case 4:
                    System.out.println("Removing shopping list...");
                    shoppingList.removeAllFromShoppingList();
                    System.out.println("Shopping list removed.");
                    break;
                case 5:
                    System.out.println("To remove all from shopping list in category, follow these steps:");
                    shoppingList.displayCategories();
                    System.out.print("Enter the category: ");
                    categoryToAdd = scanner.nextLine();
                    if(!shoppingList.products.containsKey(categoryToAdd)) {
                        System.out.println("Category not found: " + categoryToAdd);
                        break;
                    }
                    shoppingList.removeAllFromShoppingListByCategory(categoryToAdd);
                    System.out.println("Removed all from shopping list in category: " + categoryToAdd);
                    break;
                case 6:
                    System.out.println("To remove a specific product, follow these steps:");
                    shoppingList.displayCategories();
                    System.out.print("Enter the category: ");
                    categoryToAdd = scanner.nextLine();
                    if(!shoppingList.products.containsKey(categoryToAdd)) {
                        System.out.println("Category not found: " + categoryToAdd);
                        break;
                    }
                    shoppingList.displayProductsInCategory(categoryToAdd);
                    System.out.print("Enter the product: ");
                    String productToRemove = scanner.nextLine();
                    if(!shoppingList.products.get(categoryToAdd).contains(productToRemove)) {
                        System.out.println("Product not found in category: " + productToRemove);
                        break;
                    }
                    shoppingList.removeSpecificProduct(categoryToAdd, productToRemove);
                    System.out.println("Removed from shopping list: " + productToRemove + " in category: " + categoryToAdd);
                    break;
                case 7:
                    System.out.println("Saving shopping list to file...");
                    shoppingList.saveShoppingListToFile(".idea/shoppingList.txt");
                    System.out.println("Shopping list saved to file.");
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}