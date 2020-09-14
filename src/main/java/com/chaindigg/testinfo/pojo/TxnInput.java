package com.chaindigg.testinfo.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * txnInput
 * @author 
 */
@Data
public class TxnInput implements Serializable {


    private String txnHash;

    private String inputAddress;

    private String inputValue;

    private static final long serialVersionUID = 1L;
}