package com.hykang.management.entity.dto;

import com.hykang.management.entity.Manager;
import lombok.Data;

@Data
public class ManagerCustom extends Manager {
    private String token;
}
