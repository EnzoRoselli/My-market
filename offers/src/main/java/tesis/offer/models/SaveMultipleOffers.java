package tesis.offer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class SaveMultipleOffers {

    private Integer productID;
    private List<Integer> branchIDs;
    private Integer cardID;
    private Float price;
    private OfferTypes offerType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;
    private Boolean available;
    private String oldPrice;
    private String offerDescription;
}
