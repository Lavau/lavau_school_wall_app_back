package top.leeti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostAndFound extends MixedData implements Serializable {
    private String msg;
    private String claimantId;
    private Date gmtClaim;
}
