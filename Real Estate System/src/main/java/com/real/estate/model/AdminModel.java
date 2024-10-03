package com.real.estate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "admins")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminModel extends BaseUserClassModel
{
}
