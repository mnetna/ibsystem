package com.slee.web.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CustomerDefine {

    @Getter
    @AllArgsConstructor
    public enum ProcTyp {
        REGISTER     ("1"),
        MODIFY       ("2");

        private final String value;

        public static ProcTyp findByValue(String value) throws Exception {
            for (ProcTyp procTyp : ProcTyp.values()) {
                if(procTyp.getValue().equals((value))) {
                    return procTyp;
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum CustTyp {
        INDIVIDUAL     ("1"),
        CORPORATE      ("2");

        private final String value;

        public static CustTyp findByValue(String value) throws Exception {
            for (CustTyp custTyp : CustTyp.values()) {
                if(custTyp.getValue().equals((value))) {
                    return custTyp;
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum CustDvCd {
        INDIVIDAUL_CUSTOMER         ("I"),
        PRIVATE_BUSINESS_OWNER      ("P"),
        CORPORATE_CUSTOMER          ("C"),
        FINANACIAL_INSTITUTION      ("B"),
        GOVERMENT_PUBLIC_INSTITUTION("G"),
        ORGANIZATION                ("O");

        private final String value;

        public static CustDvCd findByValue(String value) throws Exception {
            for (CustDvCd custDvCd : CustDvCd.values()) {
                if(custDvCd.getValue().equals((value))) {
                    return custDvCd;
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum CustInfoKindCd {
        OUR_CUSTOMER       ("0"),
        WALK_IN_CUSTOMER   ("2"),
        BENEFICIAL_OWNER   ("3"),
        COLLATERAL_OWNER   ("4"),
        TEMP_LOAN_CUSTOMER ("5"),
        GUARANTOR_CUSTOMER ("8"),
        LATENT_CUSTOMER    ("9");

        private final String value;

        @JsonCreator
        public static CustInfoKindCd findByValue(String value) throws Exception {
            for (CustInfoKindCd custInfoKindCd : CustInfoKindCd.values()) {
                if(custInfoKindCd.getValue().equals((value))) {
                    return custInfoKindCd;
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum OpKind {
        INSERT          ("I"),
        DELETE          ("D"),
        MODIFY          ("M");

        private final String value;

        public static OpKind findByValue(String value) throws Exception {
            for (OpKind opKind : OpKind.values()) {
                if(opKind.getValue().equals((value))) {
                    return opKind;
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum AdrTypCd {
        HOME                  ("01"),
        WORK                  ("02"),
        VACATION              ("03"),
        LETTER                ("04"),
        BIRTH_PLACE_ADDRESS   ("05"),
        HEAD_OFFICE           ("11"),
        OFFICE                ("12"),
        FACTORY               ("13"),
        SPOUSE                ("14"),
        OTHER_MAILING_ADDRESS ("99");

        private final String value;

        public static AdrTypCd findByValue(String value) throws Exception {
            for (AdrTypCd adrTypCd : AdrTypCd.values()) {
                if(adrTypCd.getValue().equals((value))) {
                    return adrTypCd;
                }
            }
            return null;
        }
    }
}
