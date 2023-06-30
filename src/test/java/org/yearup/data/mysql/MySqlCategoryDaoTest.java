package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.yearup.data.mysql.MySqlProductDao.mapRow;


class MySqlCategoryDaoTest extends BaseDaoTestClass {

    private MySqlCategoryDao dao;

    @BeforeEach
    public void setup() {
        dao = new MySqlCategoryDao(dataSource);
    }

    @Test
    public void getById_shouldReturnCorrectCategory() {
        //Arrange
        int categoryId = 2;
        Category expected = new Category() {{
            setCategoryId(2);
            setName("Fashion");
            setDescription("Discover trendy clothing and accessories for men and women.");

        }};

        //act
        var actual = dao.getById(categoryId);


        //assert
        assertEquals(expected.getCategoryId(), actual.getCategoryId(), "Because I tried to get category 2 from the database.");
        assertEquals(expected.getName(), actual.getName(), "Because I tried to get category 2 from the database.");
        assertEquals(expected.getDescription(), actual.getDescription(), "Because I tried to get category 2 from the database.");


    }


    @Test
    public void create_shouldCreateNewCategory() {
            // Arrange
        Category newCategory = new Category();


            newCategory.setName("Pet Supplies");
            newCategory.setDescription("supplies for pets");

            // Act

            Category created = dao.create(newCategory);

            // Assert
            assertEquals(newCategory.getName(), created.getName());
            assertEquals(newCategory.getDescription(), created.getDescription());
        }

    @Test
    public void update_shouldUpdateCategory(){
        //Arrange
        Category updatedCategory = new Category() {{
            setCategoryId(1);
            setName("Electronics");
            setDescription("Testing");
        }};

        //Act
        dao.update(updatedCategory.getCategoryId(), updatedCategory);

        //Assert
        Category actual = dao.getById(updatedCategory.getCategoryId());
        assertEquals(updatedCategory.getDescription(),actual.getDescription());


    }


}