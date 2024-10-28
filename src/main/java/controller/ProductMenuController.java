package controller;

import models.Product;
import DAO.ProductDAO;

public class ProductMenuController extends BaseController {
    private ProductDAO productDAO;

    public ProductMenuController() {
        super();
        this.productDAO = new ProductDAO();
    }

    public void manageProducts() {
        int choice;
        do {
            menu.productMenu();
            choice = getValidChoice(0, 6);
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    searchProductById();
                    break;
                case 5:
                    searchProductByCategory();
                    break;
                case 6:
                    productDAO.viewAllProducts();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void addProduct() {
        try {
            System.out.println("Enter product ID:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            System.out.println("Enter product name:");
            String productName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidProductName(productName)) {
                return;
            }

            System.out.println("Enter category:");
            String categoryName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidCategoryName(categoryName)) {
                return;
            }

            System.out.println("Enter price:");
            double price = Double.parseDouble(scanner.nextLine());
            if (!StoreOwnerValidation.isValidPrice(price)) {
                return;
            }

            System.out.println("Enter stock level:");
            int stockLevel = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidStockLevel(stockLevel)) {
                return;
            }

            System.out.println("Enter description:");
            String description = scanner.nextLine();
            if (!StoreOwnerValidation.isValidDescription(description)) {
                return;
            }

            Product product = new Product(productID, productName, price, stockLevel, description, categoryName);
            int result = productDAO.addProduct(product);
            if (result > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void updateProduct() {
        try {
            System.out.println("Enter product ID to update:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            Product existingProduct = productDAO.searchProductsById(productID);
            if (existingProduct == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.println("Enter new product name (or press enter to skip):");
            String productName = scanner.nextLine();
            if (!productName.isEmpty() && !StoreOwnerValidation.isValidProductName(productName)) {
                return;
            }

            System.out.println("Enter new category (or press enter to skip):");
            String categoryName = scanner.nextLine();
            if (!categoryName.isEmpty() && !StoreOwnerValidation.isValidCategoryName(categoryName)) {
                return;
            }

            System.out.println("Enter new price (or -1 to skip):");
            double price = Double.parseDouble(scanner.nextLine());
            if (price != -1 && !StoreOwnerValidation.isValidPrice(price)) {
                return;
            }

            System.out.println("Enter new stock level (or -1 to skip):");
            int stockLevel = Integer.parseInt(scanner.nextLine());
            if (stockLevel != -1 && !StoreOwnerValidation.isValidStockLevel(stockLevel)) {
                return;
            }

            System.out.println("Enter new description (or press enter to skip):");
            String description = scanner.nextLine();
            if (!description.isEmpty() && !StoreOwnerValidation.isValidDescription(description)) {
                return;
            }

            if (!productName.isEmpty())
                existingProduct.setProductName(productName);

            if (!categoryName.isEmpty())
                existingProduct.setCategory(categoryName);

            if (price != -1)
                existingProduct.setPrice(price);

            if (stockLevel != -1)
                existingProduct.setStockLevel(stockLevel);

            if (!description.isEmpty())
                existingProduct.setDescription(description);

            int result = productDAO.updateProduct(existingProduct);
            if (result > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update product.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        try {
            System.out.println("Enter product ID to delete:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            Product existingProduct = productDAO.searchProductsById(productID);
            if (existingProduct == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.println("Are you sure you want to delete this product? (y/n)");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                int result = productDAO.deleteProduct(productID);
                if (result > 0) {
                    System.out.println("Product deleted successfully.");
                } else {
                    System.out.println("Failed to delete product.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void searchProductById() {
        try {
            System.out.println("Enter product ID to search:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            Product product = productDAO.searchProductsById(productID);
            if (product != null) {
                System.out.println(product.toString());
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void searchProductByCategory() {
        try {
            System.out.println("Enter category name to search:");
            String categoryName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidCategoryName(categoryName)) {
                return;
            }

            Product product = productDAO.searchProductsByCategory(categoryName);
            if (product != null) {
                System.out.println(product.toString());
            } else {
                System.out.println("No products found in this category.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}