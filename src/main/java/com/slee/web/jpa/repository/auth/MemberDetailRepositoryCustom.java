package com.slee.web.jpa.repository.auth;

import com.slee.web.constant.ApiDefine;
import com.slee.web.jpa.entity.auth.MemberDetail;

public interface MemberDetailRepositoryCustom {
    MemberDetail findByBankInfo(String iBankId, ApiDefine.Bank bank);
}
