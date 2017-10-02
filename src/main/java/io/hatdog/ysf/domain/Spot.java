package io.hatdog.ysf.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Spot {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(precision=19, scale=6)
	private BigDecimal latitude;
	@Column(precision=19, scale=6)
	private BigDecimal longitude;
	
}
