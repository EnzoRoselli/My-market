package tesis.offer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "offers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Integer productID;
    @Column(name = "company_id")
    private Integer companyID;
    @Column(name = "card_id")
    private Integer cardID;
    @Column(name = "price")
    private Float price;
    @Enumerated(EnumType.ORDINAL)
    private OfferTypes offerType;
    @Column(name = "from_date")
    private Timestamp fromDate;
    @Column(name = "to_date")
    private Timestamp toDate;
    @Column(name = "available")
    private Boolean avaliable;
    @Column(name = "description")
    private String description;

}
