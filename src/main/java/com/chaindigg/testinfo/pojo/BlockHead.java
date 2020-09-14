package com.chaindigg.testinfo.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * blockhead
 * @author
 */
@Data
public class BlockHead implements Serializable {
    private Integer id;

    private String hash;

    private Integer confirms;

    private String totalTxn;

    private String time;

    private String outputTotal;

    private Integer size;

    private String reward;

    private String fee;

    private String nonce;

    private Integer version;

    private String merkleRoot;

    private List<Tx> txHash;


    private static final long serialVersionUID = 1L;
}