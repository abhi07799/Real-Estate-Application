package com.real.estate.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class CustomerModel extends BaseUserClassModel
{

}
