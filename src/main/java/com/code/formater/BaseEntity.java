package com.code.formater;

import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.TypeDef;

@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonStringType.class),
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@MappedSuperclass
public class BaseEntity { }