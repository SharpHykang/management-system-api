package com.hykang.management.entity.vo;

import com.hykang.management.entity.Manager;
import lombok.Data;

@Data
public class ManagerVo extends Manager {
    private String roleName;
    private String roleDescription;
}
