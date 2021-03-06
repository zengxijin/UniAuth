package com.dianrong.common.uniauth.server.data.mapper;

import com.dianrong.common.uniauth.server.data.entity.TagType;
import com.dianrong.common.uniauth.server.data.entity.TagTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int countByExample(TagTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int deleteByExample(TagTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int insert(TagType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int insertSelective(TagType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    List<TagType> selectByExample(TagTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    TagType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int updateByExampleSelective(@Param("record") TagType record, @Param("example") TagTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int updateByExample(@Param("record") TagType record, @Param("example") TagTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int updateByPrimaryKeySelective(TagType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag_type
     *
     * @mbggenerated Thu Apr 07 16:46:03 CST 2016
     */
    int updateByPrimaryKey(TagType record);
}