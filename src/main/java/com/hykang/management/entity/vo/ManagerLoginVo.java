package com.hykang.management.entity.vo;

import com.hykang.management.entity.Manager;
import lombok.Data;

@Data
public class ManagerLoginVo extends Manager {
    private String token;
}
