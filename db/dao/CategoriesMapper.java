package Lesson6.db.dao;

import java.util.List;
import Lesson6.db.model.Categories;
import Lesson6.db.model.CategoriesExample;
import org.apache.ibatis.annotations.Param;

public interface CategoriesMapper {
    long countByExample(CategoriesExample example);
    int deleteByExample(CategoriesExample example);
    int deleteByPrimaryKey(Long id);
    int insert(Categories record);
    int insertSelective(Categories record);
    List<Categories> selectByExample(CategoriesExample example);
    Categories selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("record") Categories record, @Param("example") CategoriesExample example);
    int updateByExample(@Param("record") Categories record, @Param("example") CategoriesExample example);
    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);
}
