package view;

import controller.ProductController;
import controller.UserController;
import model.dto.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    private static final ProductController productController
             = new ProductController();
    private static final UserController userController
             = new UserController();

    private static void thumbnailMain(){
        System.out.println("============================");
        System.out.println("      Our Main System     ");
        System.out.println("============================");
        System.out.println("""
                1. User System
                2. Product System
                3. Exit""");
    }

    private static void thumbnailProduct(){
        System.out.println("============================");
        System.out.println("      Product Inventory     ");
        System.out.println("============================");
        System.out.println("""
                1. Get All Products
                2. Add New Product
                3. Update Product
                4. Find Product by UUID
                5. Delete Product by UUID
                6. Exit""");
    }

    private static void thumbnailUser(){
        System.out.println("============================");
        System.out.println("      User Inventory     ");
        System.out.println("============================");
        System.out.println("""
                1. Get All Users
                2. Add New User
                3. Update User
                4. Find User by UUID
                5. Delete User by UUID
                6. Exit""");
    }

    public static void home(){
        while(true){
            boolean checked = true;
            thumbnailMain();
            System.out.print("[+] Insert option: ");
            switch (new Scanner(System.in).nextInt()){
                case 1->{
                    while (checked){
                        thumbnailUser();
                        System.out.print("[+] Insert option: ");
                        switch (new Scanner(System.in).nextInt()){
                            case 1->{
                                new TableUI<UserResponseDto>().getTableDisplay(userController.getAllUsers());
                            }
                            case 2->{
                                System.out.print("[+] Insert User Name: ");
                                String uName = new Scanner(System.in).nextLine();
                                System.out.print("[+] Insert User Email: ");
                                String uEmail = new Scanner(System.in).nextLine();
                                System.out.print("[+] Insert User Password: ");
                                String uPass = new Scanner(System.in).nextLine();
                                UserCreateDto userCreateDto
                                        = new UserCreateDto(uName, uEmail, uPass,Date.valueOf(LocalDate.now()));
                                UserResponseDto user = userController.insertNewUser(userCreateDto);
                                System.out.println(user);
                            }
                            case 3->{
                                System.out.print("[+] Insert Product Uuid: ");
                                String uuid = new Scanner(System.in).nextLine();
                                System.out.print("[+] Insert new Product Name: ");
                                String newUserName = new Scanner(System.in).nextLine();
                                UserResponseDto updatedUser = userController
                                        .updateUserByUuid(uuid,
                                                new UpdateUserDto(newUserName));
                                System.out.println(updatedUser);
                            }
                            case 4->{
                                System.out.print("[+] Insert user uuid: ");
                                String uuid = new Scanner(System.in).nextLine();
                                System.out.println(userController.getUserByUuid(uuid));
                            }
                            case 5->{
                                System.out.print("[+] Insert user uuid: ");
                                String uuid = new Scanner(System.in).nextLine();
                                System.out.println(userController.deleteUserByUuid(uuid));
                            }
                            case 6-> {
                                checked = false;
                            }
                        }
                    }
                }
                case 2->{
                    while (checked){
                        thumbnailProduct();
                        System.out.print("[+] Insert option: ");
                        switch (new Scanner(System.in).nextInt()){
                            case 1->{
                               new TableUI<ProductResponseDto>().getTableDisplay(productController.getAllProducts());
                            }
                            case 2->{
                                System.out.print("[+] Insert Product Name: ");
                                String pName = new Scanner(System.in).nextLine();
                                System.out.print("[+] Insert Expire Year: ");
                                int year = new Scanner(System.in).nextInt();
                                System.out.print("[+] Insert Expire Month: ");
                                int month = new Scanner(System.in).nextInt();
                                System.out.print("[+] Insert Expire Day: ");
                                int day = new Scanner(System.in).nextInt();
                                ProductCreateDto productCreateDto
                                        = new ProductCreateDto(pName, Date.valueOf(LocalDate.of(year, month, day)));
                                ProductResponseDto product = productController.insertNewProduct(productCreateDto);
                                System.out.println(product);
                            }
                            case 3->{
                                System.out.print("[+] Insert Product Uuid: ");
                                String uuid = new Scanner(System.in).nextLine();
                                System.out.print("[+] Insert new Product Name: ");
                                String newPName = new Scanner(System.in).nextLine();
                                System.out.print("[+] Insert new Expire Year: ");
                                int year = new Scanner(System.in).nextInt();
                                System.out.print("[+] Insert new Expire Month: ");
                                int month = new Scanner(System.in).nextInt();
                                System.out.print("[+] Insert new Expire Day: ");
                                int day = new Scanner(System.in).nextInt();
                                ProductResponseDto updatedProduct = productController
                                        .updateProductByUuid(uuid,
                                                new UpdateProductDto(newPName,
                                                        Date.valueOf(LocalDate.of(year, month, day))));
                                System.out.println(updatedProduct);
                            }
                            case 4->{
                                System.out.print("[+] Insert product uuid: ");
                                String uuid = new Scanner(System.in).nextLine();
                                System.out.println(productController.getProductByUuid(uuid));
                            }
                            case 5->{
                                System.out.print("[+] Insert product uuid: ");
                                String uuid = new Scanner(System.in).nextLine();
                                System.out.println(productController.deleteProductByUuid(uuid));
                            }
                            case 6-> {
                                checked = false;
                            }
                        }
                    }
                }
                case 3-> System.exit(0);
            }
        }
    }
}
