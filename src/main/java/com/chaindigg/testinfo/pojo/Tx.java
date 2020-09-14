package com.chaindigg.testinfo.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * tx
 * @author 
 */
@Data
public class Tx implements Serializable {


    private String hash;

    private Integer confirms;

    private Integer blockHeight;

    private String time;

    private String fee;

    private String inputTotal;

    private String outputTotal;

    private Integer size;

    private List<Txninput> txnInput;

    private List<Txnoutput> txnOutput;

    private static final long serialVersionUID = 1L;
}