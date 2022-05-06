package com.slee.web.jpa.entity.auth;

import com.slee.web.constant.ApiDefine;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
public class MemberDetailPk implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2917162669760115164L;

	@Id
    @Column
    private String iBankId;

    @Id
    @Enumerated(EnumType.STRING)
    private ApiDefine.Bank bankCd;
}
