package com.library.employee.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public enum EmployeeType {
    CREW_MEMBER(1),
    TEAM_LEADER(2),
    PROJECT_MANAGE(3),
    VIP_RND(4),
    VP(5),
    CEO(6);

    Integer id;
    EmployeeType(Integer id) {
    this.id=id;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    @JsonCreator
    public EmployeeType getEmployeeTypeById(Integer id)
    {
        return Arrays.stream(EmployeeType.values()).filter(x->x.id==id).findFirst().orElse(null);
    }

    public EmployeeType getPromoteType()
    {
       return Arrays.stream(EmployeeType.values()).filter(x->x.id>this.id).min((x,y)->x.id-y.id).orElse(null);
    }

    public EmployeeType getDemoteType()
    {
        return Arrays.stream(EmployeeType.values()).filter(x->x.id<this.id).max((x,y)->x.id-y.id).orElse(null);
    }
}
