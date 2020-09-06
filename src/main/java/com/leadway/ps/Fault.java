package com.leadway.ps;

/**
 *
 * @author Dev.io
 */
/*
 * Copyright (c) 2016-2017 Ocular-Minds Software Limited
 * 
 * Permission is hereby granted to Vatebra Limited, to any person representing Vatebra in a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY FOR 6-MONTH SUPPORT AFTER INITIAL HANDING OVER
 * OF THE SOURCE CODES
 */
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * The Fault class is processing error wrapper object for holding details result
 * after certain operations.
 *
 * The fault basically contains the error(error code) and the fault(error
 * description) with arbitrary object which is any additional data to be
 * returned along with the Fault.
 *
 * @author Jejelowo B. Festus
 * @author festus.jejelowo@ocularminds.com
 */
public class Fault implements Serializable {

    /**
     * The error code
     */
    private String error;

    /**
     * The error description
     */
    private String fault;

    /**
     * Error group
     */
    private String group;

    /**
     * Optional data
     */
    private String value;

    /**
     * Optional data
     */
    private Object data;

    /**
     * Optional prams
     */
    private Object params;

    private static final Map<String, String> CODES = Fault.load();

    public Fault() {
    }

    public Fault(String error, String fault) {
        this(error, fault, null);
    }

    /**
     * Primary constructor for the Fault class
     *
     * @param error Error code
     * @param fault Error details or description
     * @param data Optional data returned with the error if successful
     *
     */
    public Fault(String error, String fault, Object data) {
        this.error = error;
        this.fault = fault;
        this.data = data;
    }

    public Fault(String value, Long data) {
        this.value = value;
        this.data = data;
    }

    /**
     * @return Compares the error code to '00' to check is they are equal
     */
    public boolean isSuccess() {
        return this.error.equals("00") || this.error.equals("200") || this.error.equals("201");
    }

    /**
     * Checks is the error code meant failure. That is not equals 00
     *
     * @return boolean result of comparing error with 00
     */
    public boolean isFailed() {
        return !this.isSuccess();
    }

    /**
     * For optional data returned with the error
     *
     * @return Optional data
     */
    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return The optional data group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Assigns new error group
     *
     * @param group String error group which also acts as optional data
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     *
     * @return String error code
     */
    public String getError() {
        return error;
    }

    /**
     * Assigns new error code
     *
     * @param error String error code
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return String the error description
     */
    public String getFault() {
        return fault;
    }

    /**
     * Assigns error details
     *
     * @param fault String error description
     */
    public void setFault(String fault) {
        this.fault = fault;
    }

    /**
     *
     * @return Optional data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data value
     *
     * @param o Optional data
     */
    public void setData(Object o) {
        data = o;
    }

    /**
     *
     * @return Optional data
     */
    public Object getParams() {
        return params;
    }

    /**
     * Sets data value
     *
     * @param o Optional data
     */
    public void setParams(Object o) {
        params = o;
    }

    /**
     * ISO Error code mapper
     *
     * @param err String error code
     * @return Error description
     */
    public static String error(String err) {
        return CODES.get(err) != null ? CODES.get(err) : "Unknown error";
    }

    private static Map<String, String> load() {
        Map<String, String> responses = new HashMap<>();
        responses.put("00", "Successful approval");
        responses.put("01", "Refer to card issuer");
        responses.put("XD", "Forward to issuer");
        responses.put("Z3", "Unable to go online");
        return responses;
    }
}
