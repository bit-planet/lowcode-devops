package top.bitplanet.devops.support.mybatisplus.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import top.bitplanet.devops.support.core.helper.BeanHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *  entity 通用父类，提供 id,create_user_id,update_user_id 等 通用字段映射
 *  原则上所有的表在设计之初就必须拥有这六个字段。
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/20 15:50
 */
@Data
public class BaseEntity<Q,R> implements Serializable {

    @ApiModelProperty("主键")
    @TableId
    private Long id;

    @ApiModelProperty("创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;

    @ApiModelProperty("修改人ID")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @ApiModelProperty("删除标志")
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleteFlag;

    //@Jsonig
    protected transient Class<R> respClass = currentRespClass();

    public R covertToResp() {
        return BeanHelper.copy(this,respClass);
    }

    public void covertFromQuery(Q query) {
        BeanHelper.copy(query,this);
    }


    /**
     * 获取 <R> 泛型 的class对象
     * @return
     */
    protected Class<R> currentRespClass() {
        return (Class<R>) this.getResolvableType().as(BaseEntity.class).getGeneric(1).getType();
    }
    protected ResolvableType getResolvableType() {
        return ResolvableType.forClass(ClassUtils.getUserClass(getClass()));
    }





}
