package com.teamsking.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ynfeng
 */
public class Result {
    private ObjectMapper mapper = new ObjectMapper();

    public static Result exception() {
        return Result.me().setOperationState(OperationState.EXCEPTION);
    }

    public static Result exception(Exception e) {
        return Result.exception(e.getMessage());
    }

    public static Result exception(String msg) {
        return Result.exception().setErrors(msg);
    }

    public static Result fail(Object... reason) {
        return Result.me().setOperationState(OperationState.FAIL).setErrors(reason);
    }

    public static Result me() {
        return new Result();
    }

    public static Result success() {
        return Result.me().setOperationState(OperationState.SUCCESS);
    }

    public static Result success(Map data) {
        return Result.success().setData(data);
    }

    private Map<String, Object> data = new HashMap<>();

    private OperationState operationState = OperationState.SUCCESS;

    private Object[] errors;

    public Object[] getErrors() {
        return errors;
    }

    public Result setErrors(Object... errors) {
        this.errors = errors;
        return this;
    }

    public Result() {
        super();
    }

    public Result(OperationState operationState, Map data) {
        super();
        this.operationState = operationState;
        this.data = data;
    }


    public Result addData(Map<String, Object> data) {
        Iterator iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            this.data.put(key, data.get(key));
        }
        return this;
    }

    public Result addData(String key, Object object) {
        if (this.data == null) {
            data = new HashMap<>();
        }
        data.put(key, object);
        return this;
    }

    public Result clear() {
        this.operationState = OperationState.SUCCESS;
        if (data != null) {
            this.data.clear();
        }
        return this;
    }

    public Map getData() {
        return data;
    }

    public OperationState getOperationState() {
        return operationState;
    }

    public boolean isSuccess() {
        return getOperationState() == OperationState.SUCCESS;
    }

    public Result setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public Result setOperationState(OperationState operationState) {
        this.operationState = operationState;
        return this;
    }

    @Override
    public String toString() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
