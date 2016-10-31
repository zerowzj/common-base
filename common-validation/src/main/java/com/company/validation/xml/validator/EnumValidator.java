package com.company.validation.xml.validator;

import com.company.exception.domain.param.ValueIllegalException;
import com.company.validation.xml.Param;
import com.company.validation.xml.rule.EnumRule;
import com.company.validation.xml.rule.Rule;

import java.util.List;

/**
 * 枚举值规则验证器
 *
 * @author wangzhj
 */
public class EnumValidator implements Validator {

    @Override
    public boolean support(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Param param, Object value) {
        String paramName = param.getParamName();

        EnumRule rule = (EnumRule) param.getRule();
        List<String> valueLt = rule.getValueLt();
        if (!valueLt.contains(value.toString())) {
            throw new ValueIllegalException(paramName, value);
        }
    }
}
