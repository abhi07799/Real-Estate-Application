package com.real.estate.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
public class AdminModel extends BaseUserClassModel
{
}
