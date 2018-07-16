package com.bril.base.net.function;


import com.bril.base.net.BaseCallModel;
import com.bril.base.net.exception.AuthorizationException;
import com.bril.base.net.exception.HintUserException;

import io.reactivex.functions.Function;

/**
 * Created by 123456 on 2017/9/11.
 */

public class FunctionSwitch<T> implements Function<BaseCallModel<T>, T> {
    @Override
    public T apply(BaseCallModel<T> tBaseCallModel) throws Exception {
        String code = tBaseCallModel.code;
        if ("600".equals(code)) {
            throw new AuthorizationException();
        }
        if ("102".equals(code)) {
            throw new HintUserException(tBaseCallModel.message);
        }
        if (!"0".equals(code)) {
            throw new HintUserException("服务器返回失败，请稍后再试！");
        }
        if (tBaseCallModel.data != null) {
            return tBaseCallModel.data;
        }
        if (tBaseCallModel.rows == null) {
            return (T) new String();
        }
        return tBaseCallModel.rows;
    }
}
