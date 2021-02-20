package tesis.offer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDateTime;

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
    @Column(name = "clasification")
    private String clasification;
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type")
    private OfferTypes offerType;
    @Column(name = "from_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;
    @Column(name = "to_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;
    @Column(name = "available")
    private Boolean avaliable;
    @Column(name = "description")
    private String description;

}
