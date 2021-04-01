package top.leeti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Single extends MixedData implements Serializable {
    private String contactInformation;
    private Double height;
    private Double weight;
    private String speciality;
    private String interest;
}
