package com.endava.homework.models;

import com.endava.homework.models.Owner;
import com.endava.homework.models.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private int id;
    private String name;
    private String birthDate;
    private Type type;
    private Owner owner;
    private List visits;


}
