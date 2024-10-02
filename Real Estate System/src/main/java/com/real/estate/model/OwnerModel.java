package com.real.estate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class OwnerModel extends BaseUserClassModel
{
    @OneToMany(mappedBy = "owner")
    private List<PropertyModel> propertiesOwned;
}
