package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Audit;
import com.aaa.eleven.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {
    /***
     * @Author ftt
     * @Description
     * 查看审核记录
     * @Date 2020/7/17 20:52
     * @Param [unitName]
     * @return java.util.List<com.aaa.eleven.model.Audit>
     */

    List<Audit> selectAuditRecordList(@Param("unitName") String unitName);
    /***
     * @Author ftt
     * @Description
     * 单位未审核查询 + 模糊
     * @Date 2020/7/17 20:52
     * @Param [unitName]
     * @return java.util.List
     */
    List<MappingUnit> selectMappingUnitAuditListByStatus(@Param("unitName") String unitName);
    /***
     * @Author ftt
     * @Description
     * 单位已审核查询 + 模糊
     * @Date 2020/7/17 20:52
     * @Param [unitName]
     * @return java.util.List
     */
    List<MappingUnit> selectMappingUnitAuditList(@Param("unitName") String unitName);
    /***
     * @Author ftt
     * @Description
     * 查询注册审核的单位信息,即在user表中status为锁定的单位信息
     * @Date 2020/7/17 20:59
     * @Param [unitName]
     * @return java.util.List<com.aaa.eleven.model.MappingUnit>
     */
    List<MappingUnit> selectAuditForUnitInfo(@Param("unitName") String unitName);
    /***
     * @Author ftt
     * @Description
     * 修改单位注册审核状态
     * @Date 2020/7/17 21:54
     * @Param [id, status]
     * @return java.lang.Integer
     */
    @Update("update t_user set status = #{status}, modify_time=NOW() where id = #{id}")
    Integer updateUserAuditStatus(@Param("id") long id, @Param("status") int status);
}