package com.slee.web.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ApiDefine {

    @Getter
    @AllArgsConstructor
    public enum Target {
        hnb("EAI", "Hana Bank 어댑터"),
        scg("GATEWAY", "Spring Cloud Gateway");

        private final String server;
        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum Bank {
        hanati("하나금융 TI"),
        nextti("NEXT TI");

        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum ServiceCode {
        DPO0100A("수신 계좌 상세 조회"),
        LNO0100A("여신 계좌 상세 조회"),
        MBO0100A("계좌 전체 조회"),
        MBO0200A("계좌 상세 조회"),
        CUO0100A("고객 조회");

        private final String description;
    }
}
