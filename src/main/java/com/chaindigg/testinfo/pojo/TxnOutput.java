package com.chaindigg.testinfo.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * txnOutput
 * @author 
 */
@Data
public class TxnOutput implements Serializable {


    private String txnHash;

    private String outputAddress;

    private String outputValue;

    private static final long serialVersionUID = 1L;
}