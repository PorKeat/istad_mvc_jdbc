package model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String uuid;
    private String pName;
    private Date expiredDate;
}
