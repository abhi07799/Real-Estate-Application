package com.real.estate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class PropertyModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String propertyTitle;

    private String propertyDescription;

    private String propertyPrice;

    private String propertyAddress;

    private String propertyType;

    private String propertyApproval;

    private String propertyStatus;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerModel owner;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel purchasedByCustomer;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminModel approvedByAdmin;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime propertyCreatedDate;

    @UpdateTimestamp
    private LocalDateTime propertyUpdatedDate;


}
