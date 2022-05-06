package com.slee.web.jpa.entity.common;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
public class MenuPk implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8392305196063840846L;

	@Id @Column
    private String menuId;

    @Id @Column
    private String lnggDvCd;
}
