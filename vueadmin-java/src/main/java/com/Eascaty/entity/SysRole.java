package com.Eascaty.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity  {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    /**
     * 备注
     */
    private String remark;


}
