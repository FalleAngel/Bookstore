/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.implement;

import dal.GenericDAO;
import java.util.LinkedHashMap;
import java.util.List;
import model.Product;

/**
 *
 * @author truong hoang minh
 */
public class ProductDAO extends GenericDAO<Product> {

    @Override
    public List<Product> findAll() {
        return queryGenericDAO(Product.class);
    }

    @Override
    public int insert(Product t) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("name", t.getName());
        parameterMap.put("quantity", t.getQuantity());
        parameterMap.put("price", t.getPrice());
        return insertGenericDAO(sql, parameterMap);
    }

    public void delete(Product product) {
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("id", product.getId());
        deleteGenericDAO(sql, parameterMap);
    }

    public void update(Product product) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[quantity] = ?\n"
                + " WHERE id = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("name", product.getName());
        parameterMap.put("quantity", product.getQuantity());
        parameterMap.put("id", product.getId());
        updateGenericDAO(sql, parameterMap);
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        System.out.println("Trước khi có dữ liệu");
        for (Product product : dao.findAll()) {
            System.out.println(product);
        }
        System.out.println("==============================");

//  insert        
//      dao.insert(Product.builder()
//                            .name("")
//                            .quantity(1)
//                            .price(0.5)
//                            .build());
//  delete
//      dao.delete(Product.builder()
//                .id(5)
//                .build());
//  update
        dao.update(Product.builder()
                            .name("minh dep trai")
                            .quantity(10)
                            .id(13)
                            .build());
        System.out.println("Sau khi có dữ liệu");
        for (Product product : dao.findAll()) {
            System.out.println(product);
        }
        System.out.println("==============================");

    }
}
