package com.slee.web.jpa.entity.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(MenuPk.class)
@Getter
@NoArgsConstructor
@Table(name = "menu")
@Entity
public class Menu implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3196941892373467543L;

	@Id
    @Column
    private String menuId;

    @Id
    @Column
    private String lnggDvCd;

    @Column
    private String parent;

    @Column
    private String menuNm;

    @Column
    private String appId;

    @Column
    private int sortSeq;

}
